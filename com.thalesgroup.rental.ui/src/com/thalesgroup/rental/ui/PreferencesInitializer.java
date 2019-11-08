package com.thalesgroup.rental.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import com.thalesgroup.rental.ui.views.IRentalUIConstants;

public class PreferencesInitializer extends AbstractPreferenceInitializer implements IRentalUIConstants {

	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences prefs = DefaultScope.INSTANCE.getNode( Activator.PLUGIN_ID );
		prefs.put( PREF_CUSTOMER_BACKGROUNDS_COLOR      , "0,0,255" );
		prefs.put( PREF_RENTAL_BACKGROUNDS_COLOR        , "0,255,0" );
		prefs.put( PREF_RENTAL_OBJECTS_BACKGROUNDS_COLOR, "255,0,0" );
		prefs.put( PREF_PALETTE, "com.thalesgroup.rental.ui.default" );
	}
}
