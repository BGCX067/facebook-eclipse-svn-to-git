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

import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.facebook.internal.core.IPermissionConstants;
import org.eclipse.facebook.internal.core.session.ISession;
import org.eclipse.facebook.internal.core.session.SessionUtil;
import org.eclipse.facebook.internal.ui.SharedImages;
import org.eclipse.facebook.internal.ui.UpdateStatusJob;
import org.eclipse.facebook.internal.ui.actions.MessageAction;
import org.eclipse.facebook.internal.ui.actions.PhotoAction;
import org.eclipse.facebook.internal.ui.actions.PokeAction;
import org.eclipse.facebook.internal.ui.actions.WallAction;
import org.eclipse.facebook.internal.ui.dialogs.FacebookAuthDialog;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.HyperlinkSettings;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.Page;

import com.facebook.api.Permission;

public class MainPage extends Page {
	private FormToolkit fToolkit;

	private ScrolledForm fScrolledForm;
	private ISession fSession;
	private IJobChangeListener fJobChangeListener = new MainJobChangeAdapter();
	
	private class MainJobChangeAdapter extends JobChangeAdapter {

		@Override
		public void aboutToRun(IJobChangeEvent event) {
			if (UpdateStatusJob.NAME.equals(event.getJob().getName())) {
				fScrolledForm.setBusy(true);
			}
		}

		@Override
		public void done(IJobChangeEvent event) {
			if (UpdateStatusJob.NAME.equals(event.getJob().getName())) {
				fScrolledForm.setBusy(false);
			}
		}
		
	}

	public MainPage(FormToolkit toolkit, ISession session) {
		fToolkit = toolkit;
		fSession = session;
		Job.getJobManager().addJobChangeListener(fJobChangeListener);
	}

	public void createControl(final Composite parent) {
		fToolkit.getHyperlinkGroup().setHyperlinkUnderlineMode(
				HyperlinkSettings.UNDERLINE_HOVER);
		fScrolledForm = fToolkit.createScrolledForm(parent);
		fScrolledForm.setText("Main");
		fScrolledForm.setImage(SharedImages
				.getImage(SharedImages.DESC_FRIEND_OBJ));

		TableWrapLayout layout = new TableWrapLayout();
		layout.leftMargin = 10;
		layout.rightMargin = 10;
		layout.topMargin = 10;
		layout.verticalSpacing = 10;
		fScrolledForm.getBody().setLayout(layout);

		TableWrapData td = new TableWrapData();
		td.align = TableWrapData.LEFT;
		td.grabHorizontal = true;

		Composite headClient = createHeadClient(fToolkit, fScrolledForm
				.getForm());
		fScrolledForm.getForm().setHeadClient(headClient);
		
		// add toolbar entries for convenience
		createToolbarEntries();

		// create the sections
		createStatusUpdatesSection(fScrolledForm.getForm());
		createNotificationsSection(fScrolledForm.getForm());

		// remember to update the toolbar
		fScrolledForm.updateToolBar();
		decorate();
	}

	private void createToolbarEntries() {
		IToolBarManager manager = fScrolledForm.getToolBarManager();
		manager.add(new PokeAction(fScrolledForm.getShell(), fSession));
		manager.add(new WallAction(fScrolledForm.getShell()));
		manager.add(new MessageAction(fScrolledForm.getShell(), fSession));
		manager.add(new PhotoAction(fScrolledForm.getShell()));
	}

	private void createStatusUpdatesSection(Form form) {
		Section section = fToolkit.createSection(form.getBody(),
				Section.TWISTIE | Section.TITLE_BAR);
		section.setText("Status Updates");
		section.setDescription("View the status updates of your friends");

		ToolBar toolbar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
		ToolItem item = new ToolItem(toolbar, SWT.NULL);
		item.setImage(SharedImages.getImage(SharedImages.DESC_REFRESH_OBJ));
		section.setTextClient(toolbar);
		
		// set layout
		Composite client = fToolkit.createComposite(section);
		section.setClient(client);
		TableWrapLayout layout = new TableWrapLayout();
		client.setLayout(layout);
		layout.leftMargin = layout.rightMargin = 0;
		TableWrapData td = new TableWrapData();
		td.align = TableWrapData.FILL;
		td.grabHorizontal = true;
		section.setLayoutData(td);

		// TODO fill section

	}

	private void createNotificationsSection(Form form) {
		Section section = fToolkit.createSection(form.getBody(),
				Section.TWISTIE | Section.TITLE_BAR);
		section.setText("Notifications");
		
		ToolBar toolbar = new ToolBar(section, SWT.FLAT | SWT.HORIZONTAL);
		ToolItem item = new ToolItem(toolbar, SWT.NULL);
		item.setImage(SharedImages.getImage(SharedImages.DESC_REFRESH_OBJ));
		section.setTextClient(toolbar);
		
		// set layout
		Composite client = fToolkit.createComposite(section);
		section.setClient(client);
		TableWrapLayout layout = new TableWrapLayout();
		client.setLayout(layout);
		layout.leftMargin = layout.rightMargin = 0;
		TableWrapData td = new TableWrapData();
		td.align = TableWrapData.FILL;
		td.grabHorizontal = true;
		section.setLayoutData(td);

		// TODO fill section
	}

	private Composite createHeadClient(FormToolkit toolkit, final Form form) {
		Composite headClient = toolkit
				.createComposite(form.getHead(), SWT.NONE);
		headClient.setBackground(null);
		headClient.setLayout(new GridLayout());
		final Hyperlink link = toolkit.createHyperlink(headClient,
				"<to set status click here>",
				SWT.NONE);
		link.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		link.setBackground(null);
		
		link.setToolTipText("Click to set status message...");
		link.addHyperlinkListener(new HyperlinkAdapter() {

			@Override
			public void linkActivated(HyperlinkEvent e) {
				if (SessionUtil.hasPermission(fSession,
						Permission.STATUS_UPDATE)) {
					InputDialog dialog = new InputDialog(form.getShell(),
							"Status", "Set status message:", link.getText(),
							null);
					if (dialog.open() == Window.OK) {
						UpdateStatusJob job = new UpdateStatusJob(fSession,
								dialog.getValue());
						job.schedule();
						String name = SessionUtil.getName(fSession);
						link.setText(name + " is " + dialog.getValue());
					}
				} else {
					Dialog dialog = new FacebookAuthDialog(
							form.getShell(),
							IPermissionConstants.STATUS_UPDATE);
					dialog.open();
				}

			}

		});
		return headClient;
	}

	public Control getControl() {
		return fScrolledForm;
	}

	public void setFocus() {
		if (fScrolledForm != null)
			fScrolledForm.setFocus();
	}
	
	public void decorate() {
		FormColors colors = fToolkit.getColors();
		Color top = colors.getColor(IFormColors.H_GRADIENT_END);
		Color bot = colors.getColor(IFormColors.H_GRADIENT_START);
		fScrolledForm.getForm().setTextBackground(new Color[] { top, bot },
				new int[] { 100 }, true);
		fScrolledForm.getForm().setHeadColor(IFormColors.H_BOTTOM_KEYLINE1,
				colors.getColor(IFormColors.H_BOTTOM_KEYLINE1));
		fScrolledForm.getForm().setHeadColor(IFormColors.H_BOTTOM_KEYLINE2,
				colors.getColor(IFormColors.H_BOTTOM_KEYLINE2));
		fScrolledForm.getForm().setHeadColor(IFormColors.H_HOVER_LIGHT,
				colors.getColor(IFormColors.H_HOVER_LIGHT));
		fScrolledForm.getForm().setHeadColor(IFormColors.H_HOVER_FULL,
				colors.getColor(IFormColors.H_HOVER_FULL));
		fScrolledForm.getForm().setHeadColor(IFormColors.TB_TOGGLE,
				colors.getColor(IFormColors.TB_TOGGLE));
		fScrolledForm.getForm().setHeadColor(IFormColors.TB_TOGGLE_HOVER,
				colors.getColor(IFormColors.TB_TOGGLE_HOVER));

		fScrolledForm.reflow(true);
		fScrolledForm.redraw();
	}
	
	@Override
	public void dispose() {
		Job.getJobManager().removeJobChangeListener(fJobChangeListener);
		super.dispose();
	}
}
