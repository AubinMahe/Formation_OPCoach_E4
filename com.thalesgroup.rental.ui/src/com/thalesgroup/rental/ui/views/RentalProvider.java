package com.thalesgroup.rental.ui.views;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider
	extends LabelProvider
	implements ITreeContentProvider, IColorProvider, IRentalUIConstants
{
	public static final String LOCATIONS      = "Locations";
	public static final String CUSTOMERS      = "Customers";
	public static final String OBJETS_A_LOUER = "Objets à louer";
	
	class Node {

		String label;
		RentalAgency agency;
		
		Node( String label, RentalAgency agency ) {
			this.label  = label;
			this.agency = agency;
		}
		
		Object[] getChildren() {
			switch( label ) {
			case CUSTOMERS     : return agency.getCustomers().toArray();
			case LOCATIONS     : return agency.getRentals().toArray();
			case OBJETS_A_LOUER: return agency.getObjectsToRent().toArray();
			}
			return null;
		}
		
		@Override
		public String toString() {
			return label;
		}
	}
	
	@Inject
	@Named(RENTAL_UI_IMG_REGISTRY)
	private ImageRegistry registry;
	
	@Override
	public Object[] getElements(Object inputElement) {
		if( inputElement instanceof Collection<?> ) {
			return ((Collection<?>) inputElement).toArray();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if( parentElement instanceof RentalAgency ) {
			RentalAgency agency = (RentalAgency)parentElement;
			Object[] children = new Object[] {
				new Node( CUSTOMERS     , agency ),
				new Node( LOCATIONS     , agency ),
				new Node( OBJETS_A_LOUER, agency ),
			};
			return children;
		}
		if( parentElement instanceof Node ) {
			return ((Node) parentElement).getChildren();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if( element instanceof EObject ) {
			return ((EObject) element).eContainer();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return ( element instanceof RentalAgency )||( element instanceof Node );
	}

	@Override
	public String getText(Object element) {
		if( element instanceof RentalAgency ) {
			return ((RentalAgency) element).getName();
		} 
		if( element instanceof Node ) {
			return ((Node) element).toString();
		}
		if( element instanceof Customer ) {
			return ((Customer) element).getDisplayName();
		}
		if( element instanceof RentalObject ) {
			return ((RentalObject) element).getName();
		}
		return super.getText(element);
	}

	@Override
	public Color getForeground(Object element) {
		if( element instanceof Customer ) {
			return Display.getCurrent().getSystemColor( SWT.COLOR_BLUE );
		}
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		return null;
	}
	
	@Override
	public Image getImage(Object element) {
		if( element instanceof RentalAgency ) {
			return registry.get( IMG_AGENCY );
		}
		if( element instanceof Customer ) {
			return registry.get( IMG_CUSTOMER );
		}
		if( element instanceof Rental ) {
			return registry.get( IMG_RENTALS );
		}
		if( element instanceof RentalObject ) {
			return registry.get( IMG_RENTAL_OBJECT );
		}
		return null;
	}
}
