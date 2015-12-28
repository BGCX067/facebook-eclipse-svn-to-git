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

public class SessionFactory {
	
	public static SessionFactory INSTANCE = new SessionFactory();
	public static SessionManager manager = SessionManager.INSTANCE;
	
	private SessionFactory() {
		// do nothing
	}
	
	public ISession createSession(String token) {
		ISession session = new Session(token);
		session.connect();
		manager.addSession(session);
		return session;
	}
}
