package org.eclipse.facebook.internal.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.facebook.internal.core.session.ISession;
import org.eclipse.facebook.internal.core.session.SessionUtil;
import org.eclipse.ui.progress.UIJob;

public class UpdateStatusJob extends UIJob {

	public static final String NAME = "fbUpdateStatus";
	
	private ISession fSession;
	private String fStatus;

	public UpdateStatusJob(ISession session, String status) {
		super(NAME);
		fSession = session;
		fStatus = status;
	}

	@Override
	public IStatus runInUIThread(IProgressMonitor monitor) {
		monitor.beginTask("Updating status...", 1);
		SessionUtil.setStatus(fSession, fStatus);
		monitor.done();
		return Status.OK_STATUS;
	}

}
