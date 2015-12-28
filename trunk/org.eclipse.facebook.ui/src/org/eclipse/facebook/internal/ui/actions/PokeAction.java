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
package org.eclipse.facebook.internal.ui.actions;

import org.eclipse.facebook.internal.core.session.ISession;
import org.eclipse.facebook.internal.ui.SharedImages;
import org.eclipse.facebook.internal.ui.dialogs.FilteredFriendsSelectionDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import com.facebook.api.schema.User;

public class PokeAction extends Action {

	private Shell fShell;
	private ISession fSession;

	public PokeAction(Shell shell, ISession session) {
		fShell = shell;
		fSession = session;
		setText("Poke Someone");
		setImageDescriptor(SharedImages
				.getImageDescriptor(SharedImages.DESC_POKE_OBJ));
	}

	@Override
	public void run() {
		// TODO
		FilteredFriendsSelectionDialog dialog = new FilteredFriendsSelectionDialog(
				fShell);
		int status = dialog.open();
		if (status == Window.OK) {
			Object[] result = dialog.getResult();
			Object object = result[0];
			if (object instanceof User) {
				
				// TODO poke user
			}
		}
	}

}
