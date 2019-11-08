package com.thalesgroup.rental.ui;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;
import com.thalesgroup.rental.ui.views.IRentalUIConstants;

public class DefaultPalette implements IColorProvider, IRentalUIConstants {

	@Inject
	@Named(RENTAL_PREFS)
	private ScopedPreferenceStore prefs;
	
	private Color getColor( String prefKey ) {
		String rgbKey = prefs.getString( prefKey ); 
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		Color col = colorRegistry.get( rgbKey );
		if( col == null ) {
			colorRegistry.put( rgbKey, StringConverter.asRGB( rgbKey ));
			col = colorRegistry.get( rgbKey );
		}
		return col;
	}

	@Override
	public Color getForeground(Object element) {
		if( element instanceof Customer ) {
			return getColor( PREF_CUSTOMER_BACKGROUNDS_COLOR );
		}
		if( element instanceof Rental ) {
			return getColor( PREF_RENTAL_BACKGROUNDS_COLOR );
		}
		if( element instanceof RentalObject ) {
			return getColor( PREF_RENTAL_OBJECTS_BACKGROUNDS_COLOR );
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		if( element instanceof Customer ) {
			return getColor( PREF_CUSTOMER_BACKGROUNDS_COLOR );
		}
		if( element instanceof Rental ) {
			return getColor( PREF_RENTAL_BACKGROUNDS_COLOR );
		}
		if( element instanceof RentalObject ) {
			return getColor( PREF_RENTAL_OBJECTS_BACKGROUNDS_COLOR );
		}
		return null;
	}
}
