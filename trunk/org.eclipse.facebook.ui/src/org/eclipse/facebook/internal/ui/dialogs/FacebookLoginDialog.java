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
package org.eclipse.facebook.internal.ui.dialogs;

import org.eclipse.facebook.internal.core.IConstants;
import org.eclipse.facebook.internal.core.TracingUtil;
import org.eclipse.facebook.internal.core.session.SessionFactory;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class FacebookLoginDialog extends TitleAreaDialog {

	public FacebookLoginDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setHelpAvailable(false);
		setTitle("Log-in");
		setMessage("Please log-in to facebook.");
		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new GridLayout());

		// create browser to login to facebook application
		Browser browser = new Browser(composite, SWT.NONE);
		browser.setUrl(IConstants.URL_LOGIN);
		browser.setSize(550, 425);

		browser.addLocationListener(new LocationListener() {

			public void changing(LocationEvent event) {
				String location = event.location;
				System.out.println(location);
				if(location.indexOf("auth_token") != -1) {
					close();
					int i = location.indexOf("=");
					String token = location.substring(i + 1, location.length());
					if (TracingUtil.isTracing()) {
						System.out.println("Logged into facebook with token: "
								+ token);
					}
					SessionFactory factory = SessionFactory.INSTANCE;
					factory.createSession(token);
				}
			}

			public void changed(LocationEvent event) {
			}
		});

		return composite;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// we only need to create a cancel button
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

}
