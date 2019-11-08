package com.thalesgroup.rental.ui.prefs;

import javax.inject.Inject;

import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;

import com.thalesgroup.rental.ui.views.IRentalUIConstants;

public abstract class AbstractColorPreferencePage extends FieldEditorPreferencePage implements IRentalUIConstants {

	@Inject
	private EventBroker eventBroker;

	public AbstractColorPreferencePage( int swtFlags ) {
		super( swtFlags );
	}

	@Override
	public void performApply() {
		super.performApply();
		eventBroker.post( PREF_COLORS_UPDATED, "" );		
	}
}