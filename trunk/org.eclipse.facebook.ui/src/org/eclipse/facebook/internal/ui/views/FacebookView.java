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

import java.util.HashMap;

import org.eclipse.facebook.internal.core.session.ISession;
import org.eclipse.facebook.internal.core.session.ISessionListener;
import org.eclipse.facebook.internal.core.session.SessionManager;
import org.eclipse.facebook.internal.ui.views.pages.LoginPage;
import org.eclipse.facebook.internal.ui.views.pages.MainPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.IPageBookViewPage;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;

public class FacebookView extends PageBookView implements ISessionListener {

	private FormToolkit fToolkit;
	private SessionManager fSessionManager = SessionManager.INSTANCE;
	private ISession fSession = null;
	protected static final IWorkbenchPart PART_LOGIN = new DummyPart();
	protected static final IWorkbenchPart PART_VIEW = new DummyPart();
	private HashMap<DummyPart, IPageBookViewPage> fPartsToPages;
	private HashMap<IPageBookViewPage, DummyPart> fPagesToParts;

	static class DummyPart implements IWorkbenchPart {
		public void addPropertyListener(IPropertyListener listener) {/* dummy */
		}

		public void createPartControl(Composite parent) {/* dummy */
		}

		public void dispose() {/* dummy */
		}

		public Object getAdapter(Class adapter) {
			return null;
		}

		public IWorkbenchPartSite getSite() {
			return null;
		}

		public String getTitle() {
			return null;
		}

		public Image getTitleImage() {
			return null;
		}

		public String getTitleToolTip() {
			return null;
		}

		public void removePropertyListener(IPropertyListener listener) {/* dummy */
		}

		public void setFocus() {/* dummy */
		}
	}

	public FacebookView() {
		fPartsToPages = new HashMap<DummyPart, IPageBookViewPage>();
		fPagesToParts = new HashMap<IPageBookViewPage, DummyPart>();
		fSessionManager.addSessionListener(this);
	}

	@Override
	public void createPartControl(Composite parent) {
		fToolkit = new FormToolkit(parent.getDisplay());
		super.createPartControl(parent);
	}

	@Override
	public void dispose() {
		fToolkit.dispose();
		fSessionManager.removeSessionListener(this);
		super.dispose();
	}

	public void sessionAdded(ISession session) {
		fSession = session;
		IPageBookViewPage page = new MainPage(fToolkit, fSession);
		System.out.println("page book: " + getPageBook());
		page.createControl(getPageBook());
		initPage(page);
		DummyPart part = new DummyPart();
		fPartsToPages.put(part, page);
		fPagesToParts.put(page, part);
		System.out.println("showing part: " + part);
		partActivated(part);
	}

	public void sessionRemoved(ISession session) {
		fSession = null;
		// TODO cleanup!
	}

	protected IPage createDefaultPage(PageBook book) {
		IPageBookViewPage page = new LoginPage(fToolkit);
		initPage(page);
		page.createControl(book);
		DummyPart part = new DummyPart();
		fPartsToPages.put(part, page);
		fPagesToParts.put(page, part);
		return page;
	}

	protected PageRec doCreatePage(IWorkbenchPart part) {
		IPageBookViewPage page = (IPageBookViewPage) fPartsToPages.get(part);
		initPage(page);
		page.createControl(getPageBook());
		PageRec rec = new PageRec(part, page);
		return rec;
	}

	protected void doDestroyPage(IWorkbenchPart part, PageRec pageRecord) {
		IPage page = pageRecord.page;
		page.dispose();
		pageRecord.dispose();

		// empty cross-reference cache
		fPartsToPages.remove(part);
	}

	@Override
	protected IWorkbenchPart getBootstrapPart() {
		return null;
	}

	protected boolean isImportant(IWorkbenchPart part) {
		return part instanceof DummyPart;
	}

}
