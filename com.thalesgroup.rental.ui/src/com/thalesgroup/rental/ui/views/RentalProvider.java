package com.thalesgroup.rental.ui.views;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {

	public static final String LOCATIONS = "Locations";
	public static final String CUSTOMERS = "Customers";
	public static final String OBJETS_A_LOUER = "Objets à louer";
	
	@Override
	public Object[] getElements(Object inputElement) {
		if( inputElement instanceof Collection<?> ) {
			return ((Collection<?>) inputElement).toArray();
		}
		return null;
	}
	
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
}
