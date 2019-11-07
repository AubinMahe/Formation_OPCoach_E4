package com.thalesgroup.rental.ui.commands;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class Handler {

	@Execute
	public void execute( @Named(IServiceConstants.ACTIVE_SHELL) Shell shell ) {
		MessageDialog.openInformation(shell, "About", "e4 app example");
	}
}
