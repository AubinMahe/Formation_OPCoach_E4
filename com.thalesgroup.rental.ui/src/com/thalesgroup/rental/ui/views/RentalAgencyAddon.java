 
package com.thalesgroup.rental.ui.views;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.helpers.RentalAgencyGenerator;
import com.thalesgroup.rental.ui.Activator;
import com.thalesgroup.rental.ui.Palette;

public class RentalAgencyAddon implements IRentalUIConstants {

	private final Map<String, Palette> paletteMgr = new HashMap<>();

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
	public void initializeContext( IEclipseContext context, IExtensionRegistry reg ) throws ClassNotFoundException {
		context.set( RentalAgency.class, RentalAgencyGenerator.createSampleAgency());
		context.set( DEFAULT_AGENCY, 1 );
		context.set( RENTAL_UI_IMG_REGISTRY, getLocalImageRegistry());
		ScopedPreferenceStore ps = getScopedPreferenceStore();
		context.set( RENTAL_PREFS, ps);
		context.set( RENTAL_PALETTE_MGR, paletteMgr );
		for( IConfigurationElement elt : reg.getConfigurationElementsFor( PLUGIN_ID + ".palette" )) {
			if( elt.getName().equals("palette")) {
				Bundle bdl = Platform.getBundle( elt.getContributor().getName());
				String id = elt.getAttribute("id");
				String name = elt.getAttribute("name");
				String providerClassname = elt.getAttribute("palette_class");
				@SuppressWarnings("unchecked")
				Class<IColorProvider> providerClass = (Class<IColorProvider>) bdl.loadClass( providerClassname );
				IColorProvider provider = ContextInjectionFactory.make( providerClass, context );
				paletteMgr.put( id, new Palette( id, name, provider ));
			}
		}		
		String palId = ps.getString( PREF_PALETTE );
		Palette palette = paletteMgr.get( palId );
		context.set( Palette.class, palette );
	}
	
	@Inject
	@Optional
	private void customerCopied( @UIEventTopic(TOPIC_CUSTOMER_COPIED) Customer customer ) {
		System.err.println( customer );
	}
	
	@Inject
	public void getExtensionsQuickAccess( IExtensionRegistry reg ) {
		for( IConfigurationElement elt : reg.getConfigurationElementsFor( "org.eclipse.e4.workbench.model" )) {
			if( elt.getName().equals( "fragment" )) {
				String attValue = elt.getAttribute( "uri" );
				System.out.println( "Found this element: " + elt.getName() + " with attr = " + attValue +
						" in " + elt.getNamespaceIdentifier());
			}
			else if( elt.getName().equals( "processor" )) {
				String attValue = elt.getAttribute( "class" );
				System.out.println( "Found this element: " + elt.getName() + " with attr = " + attValue +
						" in " + elt.getNamespaceIdentifier());
			}
		}
	}
}
