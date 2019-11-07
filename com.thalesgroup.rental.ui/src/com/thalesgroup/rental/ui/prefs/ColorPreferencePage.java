package com.thalesgroup.rental.ui.prefs;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;

import com.thalesgroup.rental.ui.views.IRentalUIConstants;

public class ColorPreferencePage extends FieldEditorPreferencePage implements IRentalUIConstants {

	public ColorPreferencePage() {
		super( GRID );
	}

	@Override
	protected void createFieldEditors() {
		addField( new ColorFieldEditor( PREF_CUSTOMER_BACKGROUNDS_COLOR      , "Customers backgrounf color : "     , getFieldEditorParent()));
		addField( new ColorFieldEditor( PREF_RENTAL_BACKGROUNDS_COLOR        , "Rental backgrounf color : "        , getFieldEditorParent()));
		addField( new ColorFieldEditor( PREF_RENTAL_OBJECTS_BACKGROUNDS_COLOR, "Rental objects backgrounf color : ", getFieldEditorParent()));
	}

}
