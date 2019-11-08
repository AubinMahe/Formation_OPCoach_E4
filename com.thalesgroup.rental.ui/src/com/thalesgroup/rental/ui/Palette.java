package com.thalesgroup.rental.ui;

import org.eclipse.jface.viewers.IColorProvider;

public class Palette {

	private final String id, name;
	private final IColorProvider provider;
	
	public Palette(String id, String name, IColorProvider provider) {
		super();
		this.id = id;
		this.name = name;
		this.provider = provider;
	}

	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public IColorProvider getProvider() {
		return provider;
	}
}
