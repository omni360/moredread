package net.untoldwind.moredread.model.scene;

import net.untoldwind.moredread.model.state.IStateHolder;

/**
 * A generic node inside a scene tree.
 * 
 * This actually may be anything, not necessarily visible in the 3D view.
 */
public interface INode extends IStateHolder {
	long getNodeId();

	Scene getScene();

	String getName();

	IComposite getParent();

	boolean isSelected();

	void markDirty();

	<T> T accept(ISceneVisitor<T> visitor);

	void remove();
}