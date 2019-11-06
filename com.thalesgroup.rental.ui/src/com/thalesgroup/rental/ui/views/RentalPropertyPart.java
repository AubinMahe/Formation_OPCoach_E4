package com.thalesgroup.rental.ui.views;

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

public class RentalPropertyPart {

	private Label rentedObjectLabel, customerNameLabel, from, to;

	@PostConstruct
	public void createContent(
			Composite parent,
			RentalAgency agency,
			@Named(IRentalUIConstants.DEFAULT_AGENCY) int agencyIndex )
	{
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
			customerPrompt.setText( "Loué à : " );
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
		from.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		from.setText("15/03/2011");
		
		Label au = new Label( dateGroup, SWT.BORDER);
		au.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		au.setText("Au : ");
		to = new Label(dateGroup, SWT.BORDER);
		to.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		to.setText("22/03/2011");
	}

	@Inject @Optional
	public void setRental( @Named(IServiceConstants.ACTIVE_SELECTION) Rental rental ) {
		rentedObjectLabel.setText( rental.getRentedObject().getName());
		customerNameLabel.setText( rental.getCustomer().getDisplayName());
		SimpleDateFormat sdf = new SimpleDateFormat();
		from.setText( sdf.format( rental.getStartDate()));
		to.setText( sdf.format( rental.getStartDate()));
	}
}
