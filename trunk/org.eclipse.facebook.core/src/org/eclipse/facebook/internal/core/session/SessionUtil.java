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
package org.eclipse.facebook.internal.core.session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.facebook.api.FacebookException;
import com.facebook.api.FacebookRestClient;
import com.facebook.api.Permission;
import com.facebook.api.ProfileField;
import com.facebook.api.schema.User;
import com.facebook.api.schema.UsersGetInfoResponse;

public class SessionUtil {

	public static void setStatus(ISession session, String text) {
		try {
			FacebookRestClient client = session.getClient();
			client.users_setStatus(text);
		} catch (FacebookException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getName(ISession session) {
		try {
			FacebookRestClient client = session.getClient();
			long id = client.users_getLoggedInUser();
			List<Long> friends = new ArrayList<Long>();
			friends.add(new Long(id));
			client.users_getInfo(friends, java.util.EnumSet.of(
					ProfileField.FIRST_NAME));
			UsersGetInfoResponse response = (UsersGetInfoResponse)client.getResponsePOJO();
			List<User> users = response.getUser();
			User user = users.get(0);
			return user.getFirstName();
		} catch (FacebookException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean hasPermission(ISession session, Permission permission) {
		try {
			FacebookRestClient client = session.getClient();
			if (client.users_hasAppPermission(permission)) {
				return true;
			}
		} catch (FacebookException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static void poke(ISession session, User user) {
		try {
			FacebookRestClient client = session.getClient();
			Long id = user.getUid();
			List<Long> friends = new ArrayList<Long>();
			friends.add(new Long(id));
			client.sms_sendMessage(id, new String("test message from Eclipse"));
		} catch (FacebookException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void message(ISession session, User user) {
		try {
			FacebookRestClient client = session.getClient();
			Long id = user.getUid();
			client.sms_sendMessage(id, new String("test message from Eclipse"));
		} catch (FacebookException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
