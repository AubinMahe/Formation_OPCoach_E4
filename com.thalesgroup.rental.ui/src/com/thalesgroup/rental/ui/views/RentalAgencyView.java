package com.thalesgroup.rental.ui.views;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.helpers.RentalAgencyGenerator;

@SuppressWarnings("restriction")
public class RentalAgencyView implements IRentalUIConstants {
	
	private static final String MENU_ID = "com.thalesgroup.rental.ui.popupmenu.pop";

	private TreeViewer agencies;

	@PostConstruct
	public void initialize(
			Composite parent,
			RentalAgency defaultAgency,
			IEclipseContext context,
			EMenuService menuService,
			ESelectionService ss )
	{
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
		agencies.addSelectionChangedListener(event -> {
			IStructuredSelection sel = (IStructuredSelection) event.getSelection();
			ss.setSelection( sel.size() < 2 ? sel.getFirstElement() : sel.toArray());
		});
		menuService.registerContextMenu( agencies.getControl(), MENU_ID );
	}
	
	@Inject
	public void prefsHasChanged(
			@Preference(PREF_CUSTOMER_BACKGROUNDS_COLOR      ) Object customerBgColor,
			@Preference(PREF_RENTAL_BACKGROUNDS_COLOR        ) Object rentalBgColor,
			@Preference(PREF_RENTAL_OBJECTS_BACKGROUNDS_COLOR) Object rentalObjectsBgColor )
	{
		if( agencies != null ) {
			agencies.refresh();
		}
	}
}
