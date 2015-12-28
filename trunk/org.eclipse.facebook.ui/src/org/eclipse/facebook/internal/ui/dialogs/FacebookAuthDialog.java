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
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class FacebookAuthDialog extends TitleAreaDialog {
	
	private String permission;

	public FacebookAuthDialog(Shell parentShell, String permission) {
		super(parentShell);
		this.permission = permission;
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setHelpAvailable(false);
		setTitle("Permissions");
		setMessage("Please grant permissions.");
		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = (Composite) super.createDialogArea(parent);
		composite.setLayout(new GridLayout());

		// create browser to login to facebook application
		Browser browser = new Browser(composite, SWT.NONE);
		browser.setUrl(IConstants.URL_PERMISSION_AUTH + permission);
		browser.setSize(550, 425);
		return composite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// we only need to create a cancel button
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

}
