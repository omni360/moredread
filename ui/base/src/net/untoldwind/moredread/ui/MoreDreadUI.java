package net.untoldwind.moredread.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import net.untoldwind.moredread.annotations.Singleton;
import net.untoldwind.moredread.jme.MoreDreadJME;
import net.untoldwind.moredread.model.generator.CubeMeshGenerator;
import net.untoldwind.moredread.model.generator.DodecahedronMeshGenerator;
import net.untoldwind.moredread.model.generator.OctahedronMeshGenerator;
import net.untoldwind.moredread.model.scene.GeneratorNode;
import net.untoldwind.moredread.model.scene.ISceneHolder;
import net.untoldwind.moredread.model.scene.MeshNode;
import net.untoldwind.moredread.model.scene.Scene;
import net.untoldwind.moredread.model.scene.SceneHolder;
import net.untoldwind.moredread.model.scene.SpatialNode;
import net.untoldwind.moredread.ui.canvas.MDCanvasConstructor;
import net.untoldwind.moredread.ui.tools.IToolDescriptor;
import net.untoldwind.moredread.ui.tools.UIToolsPlugin;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.jme.math.Vector3f;
import com.jme.system.DisplaySystem;

/**
 * The activator class controls the plug-in life cycle
 */
@Singleton
public class MoreDreadUI extends AbstractUIPlugin {

	private ResourceBundle resourceBundle;

	private final Map<String, Image> images = new HashMap<String, Image>();

	private ISceneHolder sceneHolder;

	// The plug-in ID
	public static final String PLUGIN_ID = "net.untoldwind.moredread.ui";

	// The shared instance
	private static MoreDreadUI plugin;

	/**
	 * The constructor
	 */
	public MoreDreadUI() {
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;

		initializeScene();

		final DisplaySystem displaySystem = MoreDreadJME.getDefault()
				.getDisplaySystem();
		displaySystem
				.registerCanvasConstructor("MD", MDCanvasConstructor.class);
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static MoreDreadUI getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(final String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	/**
	 * Get an image from the plugin path.
	 * 
	 * @param path
	 *            The path relative to the plugin
	 * @return Image for <tt>path</tt>
	 */
	public synchronized Image getImage(final String path) {
		Image image = images.get(path);

		if (image != null) {
			return image;
		}

		final ImageDescriptor imageDescriptor = getImageDescriptor(path);

		if (imageDescriptor != null) {
			image = imageDescriptor.createImage();
		} else {
			image = PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}

		images.put(path, image);

		return image;
	}

	public String getString(final String key) {
		if (resourceBundle == null) {
			resourceBundle = Platform.getResourceBundle(getBundle());
		}

		return resourceBundle.getString(key);
	}

	public void log(final Throwable e) {
		getLog().log(
				new Status(Status.ERROR, PLUGIN_ID, Status.ERROR, e.toString(),
						e));
	}

	protected void initializeScene() {
		final Scene scene = new Scene();

		scene.getSceneChangeHandler().begin(false);

		try {
			final SpatialNode node1 = new GeneratorNode(scene,
					new CubeMeshGenerator());

			node1.setLocalScale(new Vector3f(2.0f, 2.0f, 2.0f));

			final SpatialNode node2 = new MeshNode(scene,
					new CubeMeshGenerator().generateMesh());

			node2.setLocalTranslation(new Vector3f(3.5f, 0, 0));

			final SpatialNode node3 = new GeneratorNode(scene,
					new OctahedronMeshGenerator());

			node3.setLocalTranslation(new Vector3f(-3.5f, 0, 0));

			final SpatialNode node4 = new GeneratorNode(scene,
					new DodecahedronMeshGenerator());

			node4.setLocalScale(new Vector3f(3.0f, 3.0f, 3.0f));
			node4.setLocalTranslation(new Vector3f(10f, 0, 0));

			final SpatialNode node5 = new MeshNode(scene,
					new DodecahedronMeshGenerator().generateMesh());

			node5.setLocalScale(new Vector3f(3.0f, 3.0f, 3.0f));
			node5.setLocalTranslation(new Vector3f(-10f, 0, 0));

			final SpatialNode node6 = new MeshNode(scene,
					new OctahedronMeshGenerator().generateMesh());

			node6.setLocalScale(new Vector3f(3.0f, 3.0f, 3.0f));
			node6.setLocalTranslation(new Vector3f(0, 10f, 0));
		} finally {
			scene.getSceneChangeHandler().commit();
		}

		sceneHolder = new SceneHolder(scene);
	}

	public ISceneHolder getSceneHolder() {
		return sceneHolder;
	}

	public Set<IToolDescriptor> getActiveTools() {
		return UIToolsPlugin.getDefault().getActiveTools(
				sceneHolder.getSelectionMode(),
				sceneHolder.getScene().getSceneSelection());
	}
}
