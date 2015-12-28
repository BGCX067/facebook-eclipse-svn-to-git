/*******************************************************************************
 * Copyright (c) 2008 Chris Aniszczyk and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Chris Aniszczyk - initial API and implementation
 *******************************************************************************/
package org.eclipse.facebook.internal.core;

public interface IPermissionConstants {
	
	/**
	 * This permissions allows you to send status updates
	 * 
	 * @see {@link http://wiki.developers.facebook.com/index.php/Users.setStatus}
	 */
	public static final String STATUS_UPDATE = "status_update"; //$NON-NLS-1$
	
	/**
	 * This permission allows you to manipulate photos
	 * 
	 * @see {@link http://wiki.developers.facebook.com/index.php/Photos.upload}
	 * @see {@link http://wiki.developers.facebook.com/index.php/Photos.addTag}
	 */
	public static final String PHOTO_UPLOAD = "photo_upload"; //$NON-NLS-1$
	
	/**
	 * This permission allows you to manipulate listings in the marketplace
	 * 
	 * @see {@link http://wiki.developers.facebook.com/index.php/Marketplace.createListing}
	 * @see {@link http://wiki.developers.facebook.com/index.php/Marketplace.removeListing}
	 */
	public static final String CREATE_LISTING = "create_listing"; //$NON-NLS-1$

}
