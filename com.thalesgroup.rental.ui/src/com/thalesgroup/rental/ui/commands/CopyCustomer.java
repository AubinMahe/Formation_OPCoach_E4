package com.thalesgroup.rental.ui.commands;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Evaluate;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;

public class CopyCustomer {

	@Execute
	public void execute( @Named(IServiceConstants.ACTIVE_SELECTION) Customer customer ) {
	 	Clipboard clipboard = new Clipboard( Display.getDefault());
		String textData = customer.getDisplayName();
		String rtfData = "{\\rtf1\\b\\i " + textData + "}";
		TextTransfer textTransfer = TextTransfer.getInstance();
		RTFTransfer rtfTransfer = RTFTransfer.getInstance();
		Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer};
		Object[] data = new Object[]{textData, rtfData};
		clipboard.setContents(data, transfers);
		clipboard.dispose();
	}
	
	@Evaluate
	public boolean evaluate( @Named(IServiceConstants.ACTIVE_SELECTION) Object customer) {
		boolean eval = customer instanceof Customer;
		System.err.println( "@Evaluate : " + eval );
		return eval;
	}
	
	@CanExecute
	public boolean canExecute( @Named(IServiceConstants.ACTIVE_SELECTION) Object customer) {
		boolean canExecute = customer instanceof Customer;
		System.err.println( "canExecute : " + canExecute );
		return canExecute;
	}
}