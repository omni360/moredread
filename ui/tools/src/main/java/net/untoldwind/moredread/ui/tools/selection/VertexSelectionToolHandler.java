package net.untoldwind.moredread.ui.tools.selection;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.untoldwind.moredread.annotations.Singleton;
import net.untoldwind.moredread.model.math.Vector3;
import net.untoldwind.moredread.model.mesh.IVertex;
import net.untoldwind.moredread.model.mesh.IVertexGeometry;
import net.untoldwind.moredread.model.mesh.Vertex;
import net.untoldwind.moredread.model.mesh.VertexGeometry;
import net.untoldwind.moredread.model.scene.IGeometryNode;
import net.untoldwind.moredread.model.scene.INode;
import net.untoldwind.moredread.model.scene.IVertexGeometryNode;
import net.untoldwind.moredread.model.scene.Scene;
import net.untoldwind.moredread.model.scene.SceneSelection.VertexSelection;
import net.untoldwind.moredread.ui.controls.IModelControl;
import net.untoldwind.moredread.ui.controls.IViewport;
import net.untoldwind.moredread.ui.controls.Modifier;
import net.untoldwind.moredread.ui.controls.impl.MoveCrossModelControl;
import net.untoldwind.moredread.ui.controls.impl.VertexSelectionModelControl;
import net.untoldwind.moredread.ui.tools.IToolController;
import net.untoldwind.moredread.ui.tools.spi.IToolAdapter;
import net.untoldwind.moredread.ui.tools.spi.IToolHandler;

@Singleton
public class VertexSelectionToolHandler implements IToolHandler {
	@Override
	public void activated(final IToolController toolController,
			final Scene scene) {
	}

	@Override
	public void completed(final IToolController toolController,
			final Scene scene) {
	}

	@Override
	public void aborted(final IToolController toolController, final Scene scene) {
	}

	@Override
	public List<? extends IModelControl> getModelControls(final Scene scene,
			final IViewport viewport) {
		final List<IModelControl> controls = new ArrayList<IModelControl>();

		for (final INode node : scene.getSceneSelection().getSelectedNodes()) {
			if (node instanceof IVertexGeometryNode<?, ?>) {
				final IVertexGeometryNode<?, ?> vertexGeometryNode = (IVertexGeometryNode<?, ?>) node;
				final IVertexGeometry<?> geometry = vertexGeometryNode
						.getGeometry();

				if (geometry != null) {
					for (final IVertex vertex : geometry.getVertices()) {
						controls.add(new VertexSelectionModelControl(
								vertexGeometryNode, vertex.getIndex(),
								new SelectToolAdapter(scene,
										vertexGeometryNode, vertex.getIndex())));
					}
				}
			}
		}
		if (!scene.getSceneSelection().getSelectedVertices().isEmpty()) {
			controls.add(new MoveCrossModelControl(new TransformToolAdapter(
					scene)));
		}

		return controls;
	}

	private static class SelectToolAdapter implements IToolAdapter {
		private final Scene scene;
		private final IVertexGeometryNode<?, ?> node;
		private final int vertexIndex;

		private SelectToolAdapter(final Scene scene,
				final IVertexGeometryNode<?, ?> node, final int vertexIndex) {
			this.scene = scene;
			this.node = node;
			this.vertexIndex = vertexIndex;
		}

		@Override
		public Vector3 getCenter() {
			// TODO
			return new Vector3(0, 0, 0);
		}

		@Override
		public Vector3 getFeedbackPoint() {
			return getCenter();
		}

		@Override
		public boolean handleMove(final IModelControl modelControl,
				final Vector3 point, final EnumSet<Modifier> modifiers) {
			return false;
		}

		@Override
		public boolean handleClick(final IModelControl modelControl,
				final Vector3 point, final EnumSet<Modifier> modifiers) {
			// Click anywhere inside face is ok
			if (modifiers.contains(Modifier.LEFT_MOUSE_BUTTON)) {
				if (modifiers.contains(Modifier.SHIFT_KEY)) {
					scene.getSceneSelection().switchSelectedVertex(node,
							vertexIndex);
				} else {
					scene.getSceneSelection().setSelectedVertex(node,
							vertexIndex);
				}
				return true;
			}
			return false;
		}

		@Override
		public boolean handleDragStart(final IModelControl modelControl,
				final Vector3 point, final EnumSet<Modifier> modifiers) {
			return false;
		}

		@Override
		public boolean handleDragMove(final IModelControl modelControl,
				final Vector3 dragStart, final Vector3 dragEnd,
				final EnumSet<Modifier> modifiers) {
			return false;
		}

		@Override
		public boolean handleDragEnd(final IModelControl modelControl,
				final Vector3 dragStart, final Vector3 dragEnd,
				final EnumSet<Modifier> modifiers) {
			return false;
		}
	}

	private static class TransformToolAdapter implements IToolAdapter {
		private final Scene scene;

		private TransformToolAdapter(final Scene scene) {
			this.scene = scene;
		}

		@Override
		public Vector3 getCenter() {
			final Vector3 center = new Vector3();
			int count = 0;

			for (final VertexSelection vertexSelection : scene
					.getSceneSelection().getSelectedVertices()) {
				final IGeometryNode<?, ?> node = vertexSelection.getNode();

				if (node instanceof IVertexGeometryNode<?, ?>) {
					final IVertexGeometry<?> geometry = ((IVertexGeometryNode<?, ?>) node)
							.getGeometry();
					final IVertex vertex = geometry.getVertex(vertexSelection
							.getVertexIndex());

					center.addLocal(node.localToWorld(vertex.getPoint(),
							new Vector3()));
					count++;
				}
			}
			if (count > 0) {
				center.divideLocal(count);
			}
			return center;
		}

		@Override
		public Vector3 getFeedbackPoint() {
			return getCenter();
		}

		@Override
		public boolean handleMove(final IModelControl modelControl,
				final Vector3 point, final EnumSet<Modifier> modifiers) {
			return false;
		}

		@Override
		public boolean handleClick(final IModelControl modelControl,
				final Vector3 point, final EnumSet<Modifier> modifiers) {
			return false;
		}

		@Override
		public boolean handleDragStart(final IModelControl modelControl,
				final Vector3 point, final EnumSet<Modifier> modifiers) {
			return false;
		}

		@Override
		public boolean handleDragMove(final IModelControl modelControl,
				final Vector3 dragStart, final Vector3 dragEnd,
				final EnumSet<Modifier> modifiers) {
			scene.getSceneChangeHandler().beginUndoable("Move vertices");

			try {
				updateScene(dragEnd);
			} finally {
				scene.getSceneChangeHandler().savepoint();
			}
			return true;
		}

		@Override
		public boolean handleDragEnd(final IModelControl modelControl,
				final Vector3 dragStart, final Vector3 dragEnd,
				final EnumSet<Modifier> modifiers) {
			scene.getSceneChangeHandler().beginUndoable("Move vertices");

			try {
				updateScene(dragEnd);
			} finally {
				scene.getSceneChangeHandler().commit();
			}
			return true;
		}

		private void updateScene(final Vector3 point) {
			final Vector3 centerDiff = getCenter();
			centerDiff.subtractLocal(point);

			final Set<INode> changedNodes = new HashSet<INode>();

			for (final VertexSelection vertexSelection : scene
					.getSceneSelection().getSelectedVertices()) {
				final IGeometryNode<?, ?> node = vertexSelection.getNode();
				Vertex vertex = null;

				if (node instanceof IVertexGeometryNode<?, ?>) {
					final VertexGeometry<?> geometry = ((IVertexGeometryNode<?, ?>) node)
							.getEditableGeometry();
					vertex = geometry.getVertex(vertexSelection
							.getVertexIndex());
				}

				if (vertex == null) {
					continue;
				}

				final Vector3 worldPoint = node.localToWorld(vertex.getPoint(),
						new Vector3());
				worldPoint.subtractLocal(centerDiff);
				vertex.setPoint(node.worldToLocal(worldPoint, new Vector3()));

				changedNodes.add(node);
			}
		}
	}
}
