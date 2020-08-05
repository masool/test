package com.datdynix.controlX_IX.rest.model;

import java.io.Serializable;

public class Downloadreport implements Serializable {

	private String exportId;
	private String fileName;

	// need default constructor for JSON Parsing
	public Downloadreport() {

	}
	public Downloadreport(String exportId, String fileName) {
		this.setexportId(exportId);
		this.setfileName(fileName);
	}

	public String getexportId() {
		return this.exportId;
	}

	public void setexportId(String exportId) {
		this.exportId = exportId;
	}
	
	public String getfileName() {
		return this.fileName;
	}

	public void setfileName(String fileName) {
		this.fileName = fileName;
	}
}
