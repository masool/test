package com.datdynix.controlX_IX.rest.model;

import java.io.Serializable;

public class ListReport implements Serializable {

	private String resourceId;

	// need default constructor for JSON Parsing
	public ListReport() {

	}
	public ListReport(String resourceId) {
		this.setresourceId(resourceId);
	}

	public String getresourceId() {
		return this.resourceId;
	}

	public void setresourceId(String resourceId) {
		this.resourceId = resourceId;
	}
}