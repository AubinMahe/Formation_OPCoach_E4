package com.thalesgroup.rental.ui.views;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.helpers.RentalAgencyGenerator;

public class RentalAgencyView {
	
	private TreeViewer agencies;

	@PostConstruct
	public void initialize(Composite parent, RentalAgency defaultAgency, IEclipseContext context ) {
		agencies = new TreeViewer( parent );
		RentalProvider rp = ContextInjectionFactory.make( RentalProvider.class, context );
		agencies.setContentProvider( rp );
		agencies.setLabelProvider( rp );
		RentalAgency[] arr = new RentalAgency[] {
			defaultAgency,
			RentalAgencyGenerator.createSampleAgency(),
			RentalAgencyGenerator.createSampleAgency(),
			RentalAgencyGenerator.createSampleAgency(),
		};
		arr[1].setName("Lyon");
		arr[2].setName("Bordeaux");
		arr[3].setName("Marseille");
		agencies.setInput( Arrays.asList( arr ));
	}
}