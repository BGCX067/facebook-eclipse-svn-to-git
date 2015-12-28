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
package org.eclipse.facebook.internal.core.model;

public interface IFriend {
	
	public String getFirstName();

	public void setFirstName(String name);
	
	public String getLastName();

	public void setLastName(String name);

	public String getName();
	
	public String getStatus();

	public void setStatus(String status);

}
