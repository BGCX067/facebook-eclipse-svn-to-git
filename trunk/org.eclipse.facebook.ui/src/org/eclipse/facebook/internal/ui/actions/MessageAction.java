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
import org.eclipse.facebook.internal.core.session.SessionUtil;
import org.eclipse.facebook.internal.ui.SharedImages;
import org.eclipse.facebook.internal.ui.dialogs.FilteredFriendsSelectionDialog;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import com.facebook.api.schema.User;

public class MessageAction extends Action {
	
	private Shell fShell;
	private ISession fSession;
	
	public MessageAction(Shell shell, ISession session) {
		fShell = shell;
		fSession = session;
		setText("Send a Message");
		setImageDescriptor(SharedImages
				.getImageDescriptor(SharedImages.DESC_MESSAGE_OBJ));
	}

	@Override
	public void run() {
		FilteredFriendsSelectionDialog dialog = new FilteredFriendsSelectionDialog(
				fShell);
		int status = dialog.open();
		if (status == Window.OK) {
			Object[] result = dialog.getResult();
			Object object = result[0];
			if (object instanceof User) {
				User user = (User) object;
				SessionUtil.message(fSession, user);
			}
		}
	}

}
