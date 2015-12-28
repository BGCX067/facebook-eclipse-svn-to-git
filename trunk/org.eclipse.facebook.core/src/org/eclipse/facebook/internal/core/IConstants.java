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
package org.eclipse.facebook.internal.core;

public interface IConstants {
	
	public static final String KEY_API = "2ecd53bb27494415866617ced8dfe6d7"; //$NON-NLS-1$
	public static final String KEY_SECRET = "f03230b5c351375e952c60ebb2e309ba"; //$NON-NLS-1$
	
	// see http://developers.facebook.com/documentation.php?doc=auth
	public static final String URL_LOGIN = "http://www.facebook.com/login.php?popup&v=1.0&api_key=" + KEY_API; //$NON-NLS-1$
	
	public static final String URL_PERMISSION_AUTH = "http://www.facebook.com/authorize.php?api_key=" //$NON-NLS-1$
			+ KEY_API + "&v=1.0&ext_perm="; //$NON-NLS-1$
	
}
