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
package org.eclipse.facebook.internal.ui.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.facebook.internal.core.session.ISession;
import org.eclipse.facebook.internal.core.session.SessionManager;
import org.eclipse.facebook.internal.ui.dialogs.FacebookLoginDialog;
import org.eclipse.facebook.internal.ui.dialogs.FilteredFriendsSelectionDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.part.ViewPart;

import com.facebook.api.FacebookException;
import com.facebook.api.FacebookRestClient;
import com.facebook.api.schema.User;

public class TestFacebookView extends ViewPart {

	@Override
	public void createPartControl(Composite parent) {
		final Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		
		Link link = new Link(composite, SWT.NONE);
		link.setText("<a>Login</a>");
		
		Link link2 = new Link(composite, SWT.NONE);
		link2.setText("<a>Browse Friends</a>");
	
		Link link3 = new Link(composite, SWT.NONE);
		link3.setText("<a>Message</a>");
		
		link.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FacebookLoginDialog dialog = new FacebookLoginDialog(composite.getShell());
				dialog.open();
			}
			
		});
		
		link2.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FilteredFriendsSelectionDialog dialog = new FilteredFriendsSelectionDialog(composite.getShell());
				dialog.open();
			}
			
		});
		
		link3.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FilteredFriendsSelectionDialog dialog = new FilteredFriendsSelectionDialog(composite.getShell());
				int status = dialog.open();
				if (status == Window.OK) {
					Object[] result = dialog.getResult();
					Object object = result[0];
					if (object instanceof User) {
						User user = (User) object;
						Long id = user.getUid();
						Collection<Long> recipients = new ArrayList<Long>();
						recipients.add(id);
						SessionManager manager = SessionManager.INSTANCE;
						ISession session = manager.getSession();
						FacebookRestClient client = session.getClient();
						try {
							client.notifications_sendTextEmail(recipients, "Howdy from Eclipse", "This is a test message from zx's eclipse-based facebook client");
						} catch (FacebookException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
			
		});

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
