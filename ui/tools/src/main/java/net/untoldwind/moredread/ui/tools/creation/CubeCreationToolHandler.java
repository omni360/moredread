package net.untoldwind.moredread.ui.tools.creation;

import java.util.EnumSet;
import java.util.List;

import net.untoldwind.moredread.model.scene.Scene;
import net.untoldwind.moredread.ui.controls.IModelControl;
import net.untoldwind.moredread.ui.controls.Modifier;
import net.untoldwind.moredread.ui.tools.IDisplaySystem;
import net.untoldwind.moredread.ui.tools.spi.IToolAdapter;
import net.untoldwind.moredread.ui.tools.spi.IToolHandler;

import com.jme.math.Vector3f;

public class CubeCreationToolHandler implements IToolHandler {

	@Override
	public void activate(final Scene scene) {
	}

	@Override
	public List<? extends IModelControl> getModelControls(final Scene scene,
			final IDisplaySystem displaySystem) {
		// TODO Auto-generated method stub
		return null;
	}

	public static class CubeCreateAdapter implements IToolAdapter {

		@Override
		public Vector3f getCenter() {
			return new Vector3f();
		}

		@Override
		public void handleClick(final Vector3f point,
				final EnumSet<Modifier> modifiers) {
			// TODO Auto-generated method stub

		}

		@Override
		public void handleDrag(final Vector3f point,
				final EnumSet<Modifier> modifiers, final boolean finished) {
			// TODO Auto-generated method stub

		}
	}
}
