 
package com.thalesgroup.rental.eapp.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

import com.thalesgroup.rental.eapp.parts.SwitchPerspectivePart;

public class SelectRentalPerspective {
	@Execute
	public void execute(EPartService ps, 
            EModelService ms, MApplication appli) {
		// Add this perspective in  the perspective Stack 
		MPerspectiveStack pstack = (MPerspectiveStack) ms.find(SwitchPerspectivePart.PERS_STACK, appli);
		pstack.setSelectedElement(pstack.getChildren().get(1));


	}
		
}