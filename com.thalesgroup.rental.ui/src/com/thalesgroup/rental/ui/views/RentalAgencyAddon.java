 
package com.thalesgroup.rental.ui.views;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.helpers.RentalAgencyGenerator;
import com.thalesgroup.rental.ui.Activator;

public class RentalAgencyAddon implements IRentalUIConstants {

	private ScopedPreferenceStore getScopedPreferenceStore() {
		ScopedPreferenceStore prefsStore = new ScopedPreferenceStore(InstanceScope.INSTANCE, Activator.PLUGIN_ID );
		return prefsStore;
	}
	
	private ImageRegistry getLocalImageRegistry() {
		Bundle b = FrameworkUtil.getBundle( getClass());
		ImageRegistry reg = new ImageRegistry();
		reg.put( IMG_CUSTOMER     , ImageDescriptor.createFromURL( b.getEntry( IMG_CUSTOMER      )));
		reg.put( IMG_RENTALS      , ImageDescriptor.createFromURL( b.getEntry( IMG_RENTALS        )));
		reg.put( IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL( b.getEntry( IMG_RENTAL_OBJECT )));
		reg.put( IMG_AGENCY       , ImageDescriptor.createFromURL( b.getEntry( IMG_AGENCY        )));
		return reg;
	}
	
	@PostConstruct
	public void initializeContext( IEclipseContext context ) {
		context.set( RentalAgency.class, RentalAgencyGenerator.createSampleAgency());
		context.set( DEFAULT_AGENCY, 1 );
		context.set( RENTAL_UI_IMG_REGISTRY, getLocalImageRegistry());
		context.set( RENTAL_PREFS, getScopedPreferenceStore());
	}
	
	@Inject
	@Optional
	private void customerCopied( @UIEventTopic(TOPIC_CUSTOMER_COPIED) Customer customer ) {
		System.err.println( customer );
	}
}
