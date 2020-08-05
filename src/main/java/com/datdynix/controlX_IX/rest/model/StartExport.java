package com.datdynix.controlX_IX.rest.model;

import java.io.Serializable;

public class StartExport implements Serializable {
	

	private String complianceId;
	private String currentUser;
	private String exportType;
	private String resourceId;
	private String visualId;

	// need default constructor for JSON Parsing
	public StartExport() {

	}
	public StartExport(String complianceId, String currentUser, 
			  String exportType, String resourceId, String visualId) {
		this.setcomplianceId(complianceId);
		this.setcurrentUser(currentUser);
		this.setexportType(exportType);
		this.setresourceId(resourceId);
		this.setvisualId(visualId);
	}

	public String getcomplianceId() {
		return this.complianceId;
	}

	public void setcomplianceId(String complianceId) {
		this.complianceId = complianceId;
	}
	
	public String getcurrentUser() {
		return this.currentUser;
	}

	public void setcurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	
	public String getexportType() {
		return this.exportType;
	}

	public void setexportType(String exportType) {
		this.exportType = exportType;
	}
	
	public String getresourceId() {
		return this.resourceId;
	}

	public void setresourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	public String getvisualId() {
		return this.visualId;
	}

	public void setvisualId(String visualId) {
		this.visualId = visualId;
	}

}
