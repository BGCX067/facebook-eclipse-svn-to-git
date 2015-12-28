/*******************************************************************************
 * Copyright (c) 2008 Chris Aniszczyk and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Chris Aniszczyk <caniszczyk@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.facebook.internal.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.facebook.ui";

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry registry) {
		registry.put(SharedImages.DESC_FRIEND_OBJ,
				createImageDescriptor(SharedImages.DESC_FRIEND_OBJ));
		registry.put(SharedImages.DESC_WALL_OBJ,
				createImageDescriptor(SharedImages.DESC_WALL_OBJ));
		registry.put(SharedImages.DESC_POKE_OBJ,
				createImageDescriptor(SharedImages.DESC_POKE_OBJ));
		registry.put(SharedImages.DESC_REFRESH_OBJ,
				createImageDescriptor(SharedImages.DESC_REFRESH_OBJ));
		registry.put(SharedImages.DESC_LOGIN_OBJ,
				createImageDescriptor(SharedImages.DESC_LOGIN_OBJ));
		registry.put(SharedImages.DESC_PHOTO_OBJ,
				createImageDescriptor(SharedImages.DESC_PHOTO_OBJ));
		registry.put(SharedImages.DESC_MESSAGE_OBJ,
				createImageDescriptor(SharedImages.DESC_MESSAGE_OBJ));

	}

	private ImageDescriptor createImageDescriptor(String id) {
		return imageDescriptorFromPlugin(PLUGIN_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
