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
package org.eclipse.facebook.internal.core.model.impl;

import org.eclipse.facebook.internal.core.model.IFriend;

public class FriendImpl implements IFriend {
	
	private String fFirstName;
	private String fLastName;
	private String fStatus;

	public String getName() {
		return fFirstName + " " + fLastName;
	}

	public String getStatus() {
		return fStatus;
	}

	public void setStatus(String status) {
		fStatus = status;
	}

	public String getFirstName() {
		return fFirstName;
	}

	public String getLastName() {
		return fLastName;
	}

	public void setFirstName(String name) {
		fFirstName = name;
	}

	public void setLastName(String name) {
		fLastName = name;
	}

}
