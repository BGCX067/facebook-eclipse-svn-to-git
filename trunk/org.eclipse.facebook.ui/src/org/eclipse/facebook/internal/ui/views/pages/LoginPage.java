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
package org.eclipse.facebook.internal.ui.views.pages;

import org.eclipse.facebook.internal.ui.SharedImages;
import org.eclipse.facebook.internal.ui.dialogs.FacebookLoginDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.Page;

public class LoginPage extends Page {
	private FormToolkit fToolkit;
	private ScrolledForm fScrolledForm;

	public LoginPage(FormToolkit toolkit) {
		fToolkit = toolkit;
	}

	public void createControl(final Composite parent) {
		fScrolledForm = fToolkit.createScrolledForm(parent);
		fScrolledForm.setText("Login");
		fScrolledForm.setImage(SharedImages
				.getImage(SharedImages.DESC_LOGIN_OBJ));
		fToolkit.decorateFormHeading(fScrolledForm.getForm());

		TableWrapLayout layout = new TableWrapLayout();
		layout.leftMargin = 10;
		layout.rightMargin = 10;
		layout.topMargin = 10;
		layout.verticalSpacing = 10;
		fScrolledForm.getBody().setLayout(layout);

		TableWrapData td = new TableWrapData();
		td.align = TableWrapData.LEFT;
		td.grabHorizontal = true;

		Link link = new Link(fScrolledForm.getBody(), SWT.NONE);
		link.setText("You aren't currently logged in. Please <a>login</a>...");

		link.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FacebookLoginDialog dialog = new FacebookLoginDialog(parent
						.getShell());
				dialog.open();
			}
		});
		link.setLayoutData(td);
		fToolkit.adapt(link, false, false);
	}

	public Control getControl() {
		return fScrolledForm;
	}

	public void setFocus() {
		if (fScrolledForm != null)
			fScrolledForm.setFocus();
	}
}
