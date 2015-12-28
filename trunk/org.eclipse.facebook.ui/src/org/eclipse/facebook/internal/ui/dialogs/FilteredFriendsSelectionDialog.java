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

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.facebook.internal.core.session.ISession;
import org.eclipse.facebook.internal.core.session.SessionManager;
import org.eclipse.facebook.internal.ui.Activator;
import org.eclipse.facebook.internal.ui.SharedImages;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

import com.facebook.api.FacebookException;
import com.facebook.api.FacebookRestClient;
import com.facebook.api.ProfileField;
import com.facebook.api.schema.FriendsGetResponse;
import com.facebook.api.schema.User;
import com.facebook.api.schema.UsersGetInfoResponse;

public class FilteredFriendsSelectionDialog extends
		FilteredItemsSelectionDialog {

	private static final String DIALOG_SETTINGS = "org.eclipse.facebook.ui.dialogs.FilteredFriendsSelectionDialog"; //$NON-NLS-1$
	
	private FriendsListLabelProvider listLabelProvider = new FriendsListLabelProvider();
	private FriendsDetailsLabelProvider detailsLabelProvider = new FriendsDetailsLabelProvider();
	
	private class FriendsItemsFilter extends ItemsFilter {

		@Override
		public boolean isConsistentItem(Object item) {
			return true;
		}

		@Override
		public boolean matchItem(Object item) {
			if (item instanceof User) {
				User user = (User) item;
				return matches(user.getFirstName()) || matches(user.getLastName());
			}
			return false;
		}
		
	}
	
	private class FriendsComparator implements Comparator {

		public int compare(Object arg0, Object arg1) {
			return 0;
		}

	}
	
	private class FriendsListLabelProvider extends LabelProvider implements ILabelDecorator {

		@Override
		public String getText(Object element) {
			if(element instanceof User) {
				User user = (User) element;
				return user.getName();
			}
			return super.getText(element);
		}

		@Override
		public Image getImage(Object element) {
			return SharedImages.getImage(SharedImages.DESC_FRIEND_OBJ);
		}

		public Image decorateImage(Image image, Object element) {
			// TODO Auto-generated method stub
			return null;
		}

		public String decorateText(String text, Object element) {
			if(element instanceof User) {
				User user = (User) element;
				// do get status if possible
			}
			return text;
		}
		
	}
	
	private class FriendsDetailsLabelProvider extends LabelProvider {
		public String getText(Object element) {
			if(element instanceof User) {
				User user = (User) element;
				return user.getStatus().getValue().getMessage();
			}
			return super.getText(element);
		}
	}
	
	public FilteredFriendsSelectionDialog(Shell shell) {
		super(shell, true);
		
		setTitle("Select Friend");
		setListLabelProvider(listLabelProvider);
		setDetailsLabelProvider(detailsLabelProvider);
	}

	@Override
	protected Control createExtendedContentArea(Composite parent) {
		// TODO if we aren't logged in... create a link here to log-in
		return null;
	}

	@Override
	protected ItemsFilter createFilter() {
		return new FriendsItemsFilter();
	}

	@Override
	protected void fillContentProvider(AbstractContentProvider contentProvider,
			ItemsFilter itemsFilter, IProgressMonitor progressMonitor)
			throws CoreException {
		
		SessionManager manager = SessionManager.INSTANCE;
		ISession session = manager.getSession();
		if (session != null) {
			try {
				FacebookRestClient client = session.getClient();
				client.friends_get();
				FriendsGetResponse resp = (FriendsGetResponse)client.getResponsePOJO();
				List<Long> friends = resp.getUid();
				client.users_getInfo(friends, java.util.EnumSet.of(
						ProfileField.FIRST_NAME,
						ProfileField.LAST_NAME,
						ProfileField.STATUS,
						ProfileField.NAME));
				UsersGetInfoResponse response = (UsersGetInfoResponse)client.getResponsePOJO();
				List<User> users = response.getUser();
				progressMonitor.beginTask("fetching users", users.size());
				for(int i = 0; i < users.size(); i++) {
					User user = users.get(i);
					System.out.println("adding... " + user.getName());
					contentProvider.add(user, itemsFilter);
					progressMonitor.worked(1);
				}
			} catch (FacebookException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		progressMonitor.done();

	}

	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings = Activator.getDefault().getDialogSettings().getSection(DIALOG_SETTINGS);
		if (settings == null) {
			settings = Activator.getDefault().getDialogSettings().addNewSection(DIALOG_SETTINGS);
		}
		return settings;
	}

	@Override
	public String getElementName(Object item) {
		if (item instanceof User) {
			User user = (User) item;
			return user.getName();
		}
		return null;
	}

	@Override
	protected Comparator getItemsComparator() {
		return new FriendsComparator();
	}

	@Override
	protected IStatus validateItem(Object item) {
		return new Status(IStatus.OK, "org.eclipse.facebook.ui", 0, "", null); //$NON-NLS-1$ //$NON-NLS-2$
	}

}
