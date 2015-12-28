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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ListenerList;

public class SessionManager {
	
	public static SessionManager INSTANCE = new SessionManager();
	
	private ListenerList fListeners = new ListenerList();
	private List<ISession> fSessions = new ArrayList<ISession>(3);
	
	private SessionManager() {
		// do nothing
	}
	
	public void addSession(ISession session) {
		fSessions.add(session);
		fireSessionAdded(session);
	}
	
	public void removeSession(ISession session) {
		fSessions.remove(session);
		fireSessionRemoved(session);
	}
	
	public void addSessionListener(ISessionListener listener) {
		fListeners.add(listener);
	}
	
	public void removeSessionListener(ISessionListener listener) {
		fListeners.remove(listener);
	}
	
	// hardcode returning just one session
	public ISession getSession() {
		return fSessions.get(0);
	}
	
	private void fireSessionAdded(ISession session) {
		Object[] listeners = fListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			ISessionListener listener = (ISessionListener) listeners[i];
			listener.sessionAdded(session);
		}
	}
	
	private void fireSessionRemoved(ISession session) {
		Object[] listeners = fListeners.getListeners();
		for (int i = 0; i < listeners.length; i++) {
			ISessionListener listener = (ISessionListener) listeners[i];
			listener.sessionRemoved(session);
		}
	}

}
