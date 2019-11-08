package com.thalesgroup.rental.ui.prefs;

import org.eclipse.jface.preference.ColorFieldEditor;

import com.thalesgroup.rental.ui.views.IRentalUIConstants;

public class ColorPreferencePage extends AbstractColorPreferencePage implements IRentalUIConstants {

	public ColorPreferencePage() {
		super( GRID );
	}

	@Override
	protected void createFieldEditors() {
		addField( new ColorFieldEditor( PREF_CUSTOMER_BACKGROUNDS_COLOR      , "Customers background color : "     , getFieldEditorParent()));
		addField( new ColorFieldEditor( PREF_RENTAL_BACKGROUNDS_COLOR        , "Rental background color : "        , getFieldEditorParent()));
		addField( new ColorFieldEditor( PREF_RENTAL_OBJECTS_BACKGROUNDS_COLOR, "Rental objects background color : ", getFieldEditorParent()));
		addField( new ColorFieldEditor( PREF_CUSTOMER_FOREGROUNDS_COLOR      , "Customers foreground color : "     , getFieldEditorParent()));
		addField( new ColorFieldEditor( PREF_RENTAL_FOREGROUNDS_COLOR        , "Rental foreground color : "        , getFieldEditorParent()));
		addField( new ColorFieldEditor( PREF_RENTAL_OBJECTS_FOREGROUNDS_COLOR, "Rental objects foreground color : ", getFieldEditorParent()));
	}
}
