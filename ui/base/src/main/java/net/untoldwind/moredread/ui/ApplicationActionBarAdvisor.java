package net.untoldwind.moredread.ui;

import net.untoldwind.moredread.ui.actions.GlobalDeleteAction;
import net.untoldwind.moredread.ui.actions.GlobalOpenFileAction;
import net.untoldwind.moredread.ui.actions.GlobalRedoAction;
import net.untoldwind.moredread.ui.actions.GlobalUndoAction;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	// Actions - important to allocate these only in makeActions, and then use
	// them
	// in the fill methods. This ensures that the actions aren't recreated
	// when fillActionBars is called with FILL_PROXY.
	private IWorkbenchAction exitAction;
	private IWorkbenchAction aboutAction;
	private IWorkbenchAction newWindowAction;
	private IWorkbenchAction newAction;
	private IAction openFileAction;
	private IWorkbenchAction saveAction;
	private IWorkbenchAction saveAsAction;
	private IAction undoAction;
	private IAction redoAction;
	private IAction deleteAction;

	private IWorkbenchAction preferencesActions;

	private MenuManager showViewMenuMgr;
	private IContributionItem showViewItem;

	public ApplicationActionBarAdvisor(final IActionBarConfigurer configurer) {
		super(configurer);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void makeActions(final IWorkbenchWindow window) {
		// Creates the actions and registers them.
		// Registering is needed to ensure that key bindings work.
		// The corresponding commands keybindings are defined in the plugin.xml
		// file.
		// Registering also provides automatic disposal of the actions when
		// the window is closed.

		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);

		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);

		newAction = ActionFactory.NEW.create(window);
		register(newAction);

		openFileAction = new GlobalOpenFileAction(window);
		register(openFileAction);

		saveAction = ActionFactory.SAVE.create(window);
		register(saveAction);

		saveAsAction = ActionFactory.SAVE_AS.create(window);
		register(saveAsAction);

		undoAction = new GlobalUndoAction(window);
		register(undoAction);

		redoAction = new GlobalRedoAction(window);
		register(redoAction);

		deleteAction = new GlobalDeleteAction(window);
		register(deleteAction);

		newWindowAction = ActionFactory.OPEN_NEW_WINDOW.create(window);
		register(newWindowAction);

		preferencesActions = ActionFactory.PREFERENCES.create(window);
		register(preferencesActions);

		showViewMenuMgr = new MenuManager("Show View", "showView");
		showViewItem = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillMenuBar(final IMenuManager menuBar) {
		final MenuManager fileMenu = new MenuManager("&File",
				IWorkbenchActionConstants.M_FILE);
		final MenuManager editMenu = new MenuManager("&Edit",
				IWorkbenchActionConstants.M_EDIT);
		final MenuManager windowMenu = new MenuManager("&Window",
				IWorkbenchActionConstants.M_WINDOW);
		final MenuManager helpMenu = new MenuManager("&Help",
				IWorkbenchActionConstants.M_HELP);

		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		// Add a group marker indicating where action set menus will appear.
		menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		menuBar.add(windowMenu);
		menuBar.add(helpMenu);

		// File
		fileMenu.add(newAction);
		fileMenu.add(openFileAction);
		fileMenu.add(new Separator());
		fileMenu.add(saveAction);
		fileMenu.add(saveAsAction);
		fileMenu.add(new Separator());
		fileMenu.add(exitAction);

		// Edit
		editMenu.add(undoAction);
		editMenu.add(redoAction);
		editMenu.add(new Separator());
		editMenu.add(deleteAction);

		// Window
		showViewMenuMgr.add(showViewItem);
		windowMenu.add(newWindowAction);
		windowMenu.add(new Separator());
		windowMenu.add(showViewMenuMgr);
		windowMenu.add(new Separator());
		windowMenu.add(preferencesActions);

		// Help
		helpMenu.add(aboutAction);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillCoolBar(final ICoolBarManager coolBar) {
		final IToolBarManager toolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
		coolBar.add(new ToolBarContributionItem(toolbar, "main"));

		final IToolBarManager selectionMode = new ToolBarManager(SWT.FLAT
				| SWT.RIGHT);
		final ToolBarContributionItem item = new ToolBarContributionItem(
				selectionMode, "selectionMode");
		coolBar.add(item);
	}
}
