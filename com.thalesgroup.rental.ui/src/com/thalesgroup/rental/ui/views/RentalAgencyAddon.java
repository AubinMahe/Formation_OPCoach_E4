 
package com.thalesgroup.rental.ui.views;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.IEclipseContext;

import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.helpers.RentalAgencyGenerator;

public class RentalAgencyAddon implements IRentalAgencyAddon {

	@PostConstruct
	public void initializeContext( IEclipseContext context ) {
		context.set( RentalAgency.class, RentalAgencyGenerator.createSampleAgency());
		context.set( DEFAULT_AGENCY, 1 );
	}
}
