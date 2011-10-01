package net.untoldwind.moredread.ui.tools.utilities;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.untoldwind.moredread.model.generator.ThicknessMeshGenerator;
import net.untoldwind.moredread.model.scene.AbstractSceneOperation;
import net.untoldwind.moredread.model.scene.GeneratorNode;
import net.untoldwind.moredread.model.scene.INode;
import net.untoldwind.moredread.model.scene.Scene;
import net.untoldwind.moredread.ui.controls.IModelControl;
import net.untoldwind.moredread.ui.controls.IViewport;
import net.untoldwind.moredread.ui.tools.IToolController;
import net.untoldwind.moredread.ui.tools.spi.IToolHandler;

public class ThicknessToolHandler implements IToolHandler {

	@Override
	public void activated(final IToolController toolController,
			final Scene scene) {
		final Set<INode> nodes = scene.getSceneSelection().getSelectedNodes();

		if (nodes.size() != 1) {
			return;
		}

		scene.undoableChange(new AbstractSceneOperation("Create Boolean") {
			@Override
			public void perform(final Scene scene) {
				final GeneratorNode booleanNode = new GeneratorNode(scene,
						new ThicknessMeshGenerator());
				for (final INode node : nodes) {
					node.setParent(booleanNode);
				}
			}
		});
	}

	@Override
	public void aborted(final IToolController toolController, final Scene scene) {
	}

	@Override
	public void completed(final IToolController toolController,
			final Scene scene) {
	}

	@Override
	public List<? extends IModelControl> getModelControls(final Scene scene,
			final IViewport viewport) {
		return Collections.emptyList();
	}

}
