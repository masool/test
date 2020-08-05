package com.datdynix.controlX_IX.rest.model;

import org.json.JSONObject;

public class GetReportList {
	 private String resourceId;
	 QueryAttribute QueryAttributeObject;


	 // Getter Methods 

	 public String getResourceId() {
	  return resourceId;
	 }

	 public QueryAttribute getQueryAttribute() {
	  return QueryAttributeObject;
	 }

	 // Setter Methods 

	 public void setResourceId(String resourceId) {
	  this.resourceId = resourceId;
	 }

	 public void setQueryAttribute(QueryAttribute queryAttributeObject) {
	  this.QueryAttributeObject = queryAttributeObject;
	 }
	 
}
	