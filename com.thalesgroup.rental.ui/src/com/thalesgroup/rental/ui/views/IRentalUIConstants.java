package com.thalesgroup.rental.ui.views;

public interface IRentalUIConstants {

	String PLUGIN_ID = "com.thalesgroup.rental.ui";

	String RENTAL_UI_IMG_REGISTRY = PLUGIN_ID + ".IMG_REGISTRY";
	String RENTAL_PREFS           = PLUGIN_ID + ".preferences";
	String RENTAL_PALETTE_MGR     = PLUGIN_ID + ".palette";
	
	String IMG_CUSTOMER      = "icons/Customers.png";
	String IMG_RENTALS       = "icons/Rentals.png";
	String IMG_RENTAL_OBJECT = "icons/RentalObjects.png";
	String IMG_AGENCY        = "icons/Agency.png";

	String DEFAULT_AGENCY    = "DEFAULT_AGENCY";

	String PREF_PALETTE                          = "PREF_PALETTE";
	String PREF_COLORS_UPDATED					 = "com/thalesgroup/colorsupdated";
	String PREF_CUSTOMER_BACKGROUNDS_COLOR 	     = "PREF_CUSTOMER_BACKGROUNDS_COLOR";
	String PREF_RENTAL_BACKGROUNDS_COLOR 		 = "PREF_RENTAL_BACKGROUNDS_COLOR";
	String PREF_RENTAL_OBJECTS_BACKGROUNDS_COLOR = "PREF_RENTAL_OBJECTS_BACKGROUNDS_COLOR";
	String PREF_CUSTOMER_FOREGROUNDS_COLOR 	     = "PREF_CUSTOMER_FOREGROUNDS_COLOR";
	String PREF_RENTAL_FOREGROUNDS_COLOR 		 = "PREF_RENTAL_FOREGROUNDS_COLOR";
	String PREF_RENTAL_OBJECTS_FOREGROUNDS_COLOR = "PREF_RENTAL_OBJECTS_FOREGROUNDS_COLOR";

	String TOPIC_CUSTOMER_COPIED = "rental/customer/copy";
}
