package com.thalesgroup.rental.ui.prefs;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.ComboFieldEditor;

import com.thalesgroup.rental.ui.Palette;
import com.thalesgroup.rental.ui.views.IRentalUIConstants;

public class PalettesPage extends AbstractColorPreferencePage implements IRentalUIConstants {

	@Inject
	@Named(RENTAL_PALETTE_MGR)
	private Map<String, Palette> palettes;
	
	public PalettesPage() {
		super( GRID );
	}

	@Override
	protected void createFieldEditors() {
		String[][] entries = new String[palettes.size()][2];
		Iterator<Entry<String, Palette>> it = palettes.entrySet().iterator();
		for( int i = 0; i < palettes.size(); ++i ) {
			final Entry<String, Palette> e = it.next();
			entries[i] = new String[] {e.getValue().getName(), e.getKey()};
		}
		addField( new ComboFieldEditor( PREF_PALETTE, "Palette active : ", entries, getFieldEditorParent()));
	}
}
