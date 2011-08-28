package net.untoldwind.moredread.model.generator;

import java.io.IOException;

import net.untoldwind.moredread.model.state.IStateHolder;
import net.untoldwind.moredread.model.state.IStateWriter;

import com.jme.math.Vector3f;

public abstract class AbstractCenterSizeGenerator implements IMeshGenerator,
		IStateHolder {
	protected Vector3f center = new Vector3f();
	protected float size = 1f;

	protected AbstractCenterSizeGenerator(final Vector3f center,
			final float size) {
		this.center = center;
		this.size = size;
	}

	public Vector3f getCenter() {
		return center;
	}

	public void setCenter(final Vector3f center) {
		this.center = center;
	}

	public float getSize() {
		return size;
	}

	public void setSize(final float size) {
		this.size = size;
	}

	@Override
	public void writeState(final IStateWriter writer) throws IOException {
		writer.writeVector3f("center", center);
		writer.writeFloat("size", size);
	}

}
