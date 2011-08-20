package net.untoldwind.moredread.model.scene;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.untoldwind.moredread.model.scene.change.CompositeChangeCommand;
import net.untoldwind.moredread.model.scene.change.ISceneChangeCommand;
import net.untoldwind.moredread.model.scene.event.SceneChangeEvent;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.OperationHistoryFactory;

public class SceneChangeHandler {
	private final Scene scene;

	transient Thread lockOwner;
	transient boolean allowUndo;
	transient Map<String, ISceneChangeCommand> stage;

	SceneChangeHandler(final Scene scene) {
		this.scene = scene;
	}

	public synchronized void begin(final boolean allowUndo) {
		if (lockOwner != null) {
			throw new RuntimeException(
					"Scene is already manipulated by thread: " + lockOwner);
		}
		lockOwner = Thread.currentThread();
		this.allowUndo = allowUndo;
		if (stage == null) {
			this.stage = new LinkedHashMap<String, ISceneChangeCommand>();
		}
	}

	public synchronized void savepoint() {
		if (lockOwner == null) {
			throw new RuntimeException("Scene is not in change");
		} else if (lockOwner != Thread.currentThread()) {
			throw new RuntimeException(
					"Scene is manipulated by anowher thread: " + lockOwner);
		}

		lockOwner = null;
		final List<INode> affectedNodes = new ArrayList<INode>();
		for (final ISceneChangeCommand cmd : stage.values()) {
			cmd.updateCurrentValues(scene);
			cmd.collectAffectedNodes(scene, affectedNodes);
		}
		for (final INode node : affectedNodes) {
			node.markDirty();
		}
		scene.fireSceneGeometryChangeEvent(new SceneChangeEvent(scene,
				affectedNodes));

	}

	public synchronized void commit() {
		if (lockOwner == null) {
			throw new RuntimeException("Scene is not in change");
		} else if (lockOwner != Thread.currentThread()) {
			throw new RuntimeException(
					"Scene is manipulated by anowher thread: " + lockOwner);
		}
		lockOwner = null;
		final List<INode> affectedNodes = new ArrayList<INode>();
		for (final ISceneChangeCommand cmd : stage.values()) {
			cmd.updateCurrentValues(scene);
			cmd.collectAffectedNodes(scene, affectedNodes);
		}

		if (allowUndo) {
			if (stage.size() == 1) {
				queueCommand(stage.values().iterator().next());
			} else if (stage.size() > 1) {
				queueCommand(new CompositeChangeCommand(stage.values()));
			}
		}

		stage = null;

		for (final INode node : affectedNodes) {
			node.markDirty();
		}
		scene.fireSceneGeometryChangeEvent(new SceneChangeEvent(scene,
				affectedNodes));
	}

	public boolean isChangeAllowed() {
		return lockOwner == Thread.currentThread();
	}

	public void checkChangeAllowed() {
		if (lockOwner != Thread.currentThread()) {
			throw new RuntimeException(
					"Current thread is not allowed to perform changes");
		}
	}

	public void registerCommand(final ISceneChangeCommand command) {
		checkChangeAllowed();

		synchronized (stage) {
			if (!stage.containsKey(command.getStageId())) {
				command.updateOriginalValues(scene);
				stage.put(command.getStageId(), command);
			}
		}
	}

	void queueCommand(final ISceneChangeCommand command) {
		command.addContext(IOperationHistory.GLOBAL_UNDO_CONTEXT);
		getOperationHistory().add(command);
	}

	private IOperationHistory getOperationHistory() {
		return OperationHistoryFactory.getOperationHistory();
	}
}