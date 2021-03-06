package net.untoldwind.moredread.ui.controls.impl;

import java.util.List;

import net.untoldwind.moredread.ui.controls.IControlHandle;
import net.untoldwind.moredread.ui.controls.IModelControl;
import net.untoldwind.moredread.ui.controls.IViewport;
import net.untoldwind.moredread.ui.tools.spi.IToolAdapter;

import com.jme.scene.Spatial;

public class FullScreenModelControl implements IModelControl {
	private final IToolAdapter toolAdapter;

	public FullScreenModelControl(final IToolAdapter toolAdapter) {
		this.toolAdapter = toolAdapter;
	}

	@Override
	public void collectControlHandles(final List<IControlHandle> handles,
			final IViewport viewport) {
		// Full screen should be last to pick (high salience)
		handles.add(new FullScreenControlHandle(IControlHandle.SELECT_SALIENCE,
				this));
	}

	@Override
	public Spatial getSpatial() {
		// Nothing to display
		return null;
	}

	@Override
	public void setActive(final boolean active) {
		// Do nothing
	}

	@Override
	public void viewportChanged(final IViewport viewport) {
		// Do nothing
	}

	@Override
	public void updatePositions() {
		// Do nothing
	}

	@Override
	public IToolAdapter getToolAdapter() {
		return toolAdapter;
	}

}
