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
package org.eclipse.facebook.internal.core.session;

import java.io.IOException;

import org.eclipse.facebook.internal.core.IConstants;

import com.facebook.api.FacebookException;
import com.facebook.api.FacebookRestClient;

public class Session implements ISession {

	private static boolean authenticated = false;
	private FacebookRestClient client = null;
	private String token;
	
	public Session(String token) {
		this.token = token;
	}

	public FacebookRestClient getClient() {
		return client;
	}
	

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void connect() {
		try {
			client = new FacebookRestClient(IConstants.KEY_API,
					IConstants.KEY_SECRET);
			client.setIsDesktop(true);
			client.auth_getSession(token);
			System.out.println("logged in using token: " + token);
		} catch (FacebookException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		// todo... what do we do here?
	}
	
}
