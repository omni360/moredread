package net.untoldwind.moredread.ui.tools.creation;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import net.untoldwind.moredread.model.generator.AbstractCenterSizeGenerator;
import net.untoldwind.moredread.model.math.Vector3;
import net.untoldwind.moredread.model.scene.AbstractSceneOperation;
import net.untoldwind.moredread.model.scene.GeneratorNode;
import net.untoldwind.moredread.model.scene.Scene;
import net.untoldwind.moredread.ui.controls.IModelControl;
import net.untoldwind.moredread.ui.controls.IViewport;
import net.untoldwind.moredread.ui.controls.Modifier;
import net.untoldwind.moredread.ui.controls.impl.PlaneRestrictedModelControl;
import net.untoldwind.moredread.ui.tools.IToolController;
import net.untoldwind.moredread.ui.tools.spi.IToolAdapter;
import net.untoldwind.moredread.ui.tools.spi.IToolHandler;

import com.jme.math.FastMath;

public abstract class AbstractCenterSizeCreationToolHandler implements
		IToolHandler {
	protected IToolController toolController;
	protected IModelControl activeControl;

	@Override
	public void activated(final IToolController toolController,
			final Scene scene) {
		this.toolController = toolController;
		this.activeControl = new PlaneRestrictedModelControl(
				createToolAdapter(scene));
	}

	@Override
	public void aborted(final IToolController toolController, final Scene scene) {
		this.activeControl = null;
		scene.getSceneChangeHandler().rollback();
	}

	@Override
	public void completed(final IToolController toolController,
			final Scene scene) {
		this.activeControl = null;
		scene.getSceneChangeHandler().commit();
	}

	@Override
	public List<? extends IModelControl> getModelControls(final Scene scene,
			final IViewport viewport) {
		final List<IModelControl> controls = new ArrayList<IModelControl>();

		if (activeControl != null) {
			controls.add(activeControl);
		}

		return controls;
	}

	protected abstract IToolAdapter createToolAdapter(Scene scene);

	public abstract class AbstractCenterSizeCreationToolAdapter implements
			IToolAdapter {
		Scene scene;
		Vector3 position = new Vector3();
		AbstractCenterSizeGenerator generator;
		GeneratorNode node;

		protected AbstractCenterSizeCreationToolAdapter(final Scene scene) {
			this.scene = scene;
		}

		protected abstract AbstractCenterSizeGenerator createGenerator(
				Vector3 point);

		@Override
		public Vector3 getCenter() {
			return position;
		}

		@Override
		public Vector3 getFeedbackPoint() {
			return getCenter();
		}

		@Override
		public boolean handleClick(final IModelControl modelControl,
				final Vector3 point, final EnumSet<Modifier> modifiers) {
			if (node == null) {
				generator = createGenerator(point);

				scene.undoableSavepoint(new AbstractSceneOperation("Create "
						+ generator.getName()) {
					@Override
					public void perform(final Scene scene) {
						node = new GeneratorNode(scene, generator);
					}
				});
			} else {
				scene.undoableSavepoint(new AbstractSceneOperation("Create "
						+ generator.getName()) {
					@Override
					public void perform(final Scene scene) {
						generator.setSize(maxDistance(generator.getCenter(),
								point));
						node.regenerate();

						node = null;
						generator = null;

						toolController.setActiveTool(null);
					}
				});

			}
			return true;
		}

		@Override
		public boolean handleMove(final IModelControl modelControl,
				final Vector3 point, final EnumSet<Modifier> modifiers) {
			this.position.set(point);

			modelControl.updatePositions();

			if (node != null) {
				scene.undoableSavepoint(new AbstractSceneOperation("Create "
						+ generator.getName()) {
					@Override
					public void perform(final Scene scene) {
						generator.setSize(maxDistance(generator.getCenter(),
								point));
						node.regenerate();
					}
				});
			}
			return true;
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

		private float maxDistance(final Vector3 p1, final Vector3 p2) {
			final float sizeX = FastMath.abs(p1.x - p2.x);
			final float sizeY = FastMath.abs(p1.y - p2.y);
			final float sizeZ = FastMath.abs(p1.z - p2.z);

			if (sizeZ > sizeX && sizeZ > sizeY) {
				return sizeZ;
			} else if (sizeY > sizeZ && sizeY > sizeX) {
				return sizeY;
			} else {
				return sizeX;
			}
		}
	}
}
