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

import org.eclipse.core.runtime.Platform;

public class TracingUtil {
	
	public static boolean isTracing() {
		String option = Activator.PLUGIN_ID + '/' + "tracing"; //$NON-NLS-1$
		return Platform.getDebugOption(option) != null;
	}

}
