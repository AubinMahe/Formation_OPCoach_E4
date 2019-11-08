package com.thalesgroup.rental.ui.views;

import java.util.Arrays;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.helpers.RentalAgencyGenerator;
import com.thalesgroup.rental.ui.Palette;

public class RentalAgencyView implements IRentalUIConstants {
	
	private static final String MENU_ID = "com.thalesgroup.rental.ui.popupmenu.pop";

	private TreeViewer agencies;

	private RentalProvider rp;

	@PostConstruct
	public void initialize(
			Composite parent,
			RentalAgency defaultAgency,
			IEclipseContext context,
			EMenuService menuService,
			ESelectionService ss )
	{
		agencies = new TreeViewer( parent );
		rp = ContextInjectionFactory.make( RentalProvider.class, context );
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
		agencies.addSelectionChangedListener(event -> {
			IStructuredSelection sel = (IStructuredSelection) event.getSelection();
			ss.setSelection( sel.size() < 2 ? sel.getFirstElement() : sel.toArray());
		});
		menuService.registerContextMenu( agencies.getControl(), MENU_ID );
	}
	
	@Inject
	@Optional
	public void colorsHasChanged(
			@UIEventTopic(PREF_COLORS_UPDATED) String notUsed,
			@Preference(PREF_PALETTE) String paletteID,
			@Named(RENTAL_PALETTE_MGR) Map<String, Palette> palettes )
	{
		if( agencies != null ) {
			rp.setPalette( palettes.get( paletteID ));
			agencies.refresh();
		}
	}
}
