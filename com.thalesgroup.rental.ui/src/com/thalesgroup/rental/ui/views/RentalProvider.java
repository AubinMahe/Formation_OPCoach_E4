package com.thalesgroup.rental.ui.views;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.opcoach.e4.preferences.ScopedPreferenceStore;
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}

		private RentalProvider getEnclosingInstance() {
			return RentalProvider.this;
		}
		
		
	}
	
	@Inject
	@Named(RENTAL_UI_IMG_REGISTRY)
	private ImageRegistry registry;
	
	@Inject
	@Named(RENTAL_PREFS)
	private ScopedPreferenceStore prefs;
	
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
