package net.untoldwind.moredread.ui.controls.impl;

import java.util.EnumSet;

import net.untoldwind.moredread.ui.controls.IControlHandle;
import net.untoldwind.moredread.ui.controls.Modifier;
import net.untoldwind.moredread.ui.tools.spi.IToolAdapter;

import com.jme.math.Vector2f;
import com.jme.math.Vector3f;

public class FullScreenControlHandle implements IControlHandle {
	private final float salience;
	private final IToolAdapter toolAdapter;

	public FullScreenControlHandle(final float salience,
			final IToolAdapter toolAdapter) {
		this.salience = salience;
		this.toolAdapter = toolAdapter;
	}

	@Override
	public float matches(final Vector2f screenCoord) {
		return salience;
	}

	@Override
	public void handleMove(final Vector2f position,
			final EnumSet<Modifier> modifiers) {
		toolAdapter.handleMove(new Vector3f(position.x, position.y, 0),
				modifiers);
	}

	@Override
	public void handleClick(final Vector2f position,
			final EnumSet<Modifier> modifiers) {
		toolAdapter.handleClick(new Vector3f(position.x, position.y, 0),
				modifiers);
	}

	@Override
	public void handleDragStart(final Vector2f dragStart,
			final EnumSet<Modifier> modifiers) {
		toolAdapter.handleDragStart(new Vector3f(dragStart.x, dragStart.y, 0),
				modifiers);
	}

	@Override
	public void handleDragMove(final Vector2f dragStart,
			final Vector2f dragEnd, final EnumSet<Modifier> modifiers) {
		toolAdapter.handleDragMove(new Vector3f(dragEnd.x, dragEnd.y, 0),
				modifiers);
	}

	@Override
	public void handleDragEnd(final Vector2f dragStart, final Vector2f dragEnd,
			final EnumSet<Modifier> modifiers) {
		toolAdapter.handleDragEnd(new Vector3f(dragEnd.x, dragEnd.y, 0),
				modifiers);
	}

	@Override
	public void setActive(final boolean active) {
		// Do nothing
	}

}
