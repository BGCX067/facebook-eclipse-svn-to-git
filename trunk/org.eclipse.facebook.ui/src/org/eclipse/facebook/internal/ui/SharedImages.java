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
import org.eclipse.swt.graphics.Image;

public final class SharedImages {

	private SharedImages() { // do nothing
	}
	
	public final static String ICONS_PATH = "icons/"; //$NON-NLS-1$

	private static final String PATH_OBJ = ICONS_PATH + "obj16/"; //$NON-NLS-1$
	
	// image constants
	public static final String DESC_FRIEND_OBJ = PATH_OBJ + "friend_obj.gif"; //$NON-NLS-1$
	public static final String DESC_WALL_OBJ = PATH_OBJ + "wall_obj.gif"; //$NON-NLS-1$
	public static final String DESC_POKE_OBJ = PATH_OBJ + "poke_obj.gif"; //$NON-NLS-1$
	public static final String DESC_REFRESH_OBJ = PATH_OBJ + "refresh_obj.gif"; //$NON-NLS-1$
	public static final String DESC_LOGIN_OBJ = PATH_OBJ + "login_obj.gif"; //$NON-NLS-1$
	public static final String DESC_PHOTO_OBJ = PATH_OBJ + "photo_obj.gif"; //$NON-NLS-1$
	public static final String DESC_MESSAGE_OBJ = PATH_OBJ + "message_obj.gif"; //$NON-NLS-1$
	
	
	public static ImageDescriptor getImageDescriptor(String key) {
		return Activator.getDefault().getImageRegistry().getDescriptor(key);
	}

	public static Image getImage(String key) {
		return Activator.getDefault().getImageRegistry().get(key);
	}

}
