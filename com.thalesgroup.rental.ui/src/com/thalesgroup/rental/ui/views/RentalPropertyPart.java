 
package com.thalesgroup.rental.ui.views;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Rental;
import com.thalesgroup.rental.core.RentalCoreActivator;

public class RentalPropertyPart {

	private Label rentedObjectLabel, customerNameLabel, startDateLabel, endDateLabel;
	private Label from, to;

	@Inject
	public RentalPropertyPart() {
		
	}
	
	@PostConstruct
	public void createContent(Composite parent) {
		parent.setLayout( new GridLayout( 1, false ));
		
		Group infoGroup = new Group( parent, SWT.NONE );
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText( "Information" );
		infoGroup.setLayout( new GridLayout( 2, false ));
		
		rentedObjectLabel = new Label( infoGroup, SWT.BORDER );
		{
			GridData gd = new GridData();
			gd.grabExcessHorizontalSpace = true;
			gd.horizontalSpan = 2;
			gd.horizontalAlignment = SWT.FILL;
			rentedObjectLabel.setLayoutData( gd );
		}
		
		Label customerPrompt = new Label( infoGroup, SWT.BORDER );
		{
			customerPrompt.setText( "Lou� � : " );
			GridData gd = new GridData();
			gd.horizontalAlignment = SWT.LEFT;
			customerPrompt.setLayoutData( gd );
		}
		customerNameLabel = new Label( infoGroup, SWT.BORDER );
		{
			GridData gd = new GridData();
			gd.horizontalAlignment = SWT.FILL;
			customerNameLabel.setLayoutData( gd );
		}
		
		Group dateGroup = new Group(parent, SWT.NONE);
		dateGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		dateGroup.setText("Date de location");
		dateGroup.setLayout(new GridLayout(2, false));
		
		Label de = new Label( dateGroup, SWT.BORDER);
		de.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		de.setText("Du : ");
		
		from = new Label(dateGroup, SWT.BORDER);
		from.setText("15/03/2011");
		
		Label au = new Label( dateGroup, SWT.BORDER);
		au.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		au.setText("Au : ");
		
		to = new Label(dateGroup, SWT.BORDER);
		to.setText("22/03/2011");

		setRental( RentalCoreActivator.getDefaultAgency().getRentals().get( 0 ));
	}
	
	public void setRental( Rental rental ) {
		System.out.println( Locale.getDefault());
		rentedObjectLabel.setText( rental.getRentedObject().getName());
		customerNameLabel.setText( rental.getCustomer().getDisplayName());
		from.setText( rental.getStartDate().toString());
		to.setText( rental.getStartDate().toString());
	}
}
