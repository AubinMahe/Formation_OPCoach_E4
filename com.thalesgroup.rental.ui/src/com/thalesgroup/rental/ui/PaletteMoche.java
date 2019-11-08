package com.thalesgroup.rental.ui;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.thalesgroup.rental.ui.views.IRentalUIConstants;

public class PaletteMoche implements IColorProvider, IRentalUIConstants {

	@Override
	public Color getForeground(Object element) {
		return Display.getCurrent().getSystemColor( SWT.COLOR_DARK_CYAN );
	}

	@Override
	public Color getBackground(Object element) {
		return Display.getCurrent().getSystemColor( SWT.COLOR_DARK_MAGENTA );
	}
}
