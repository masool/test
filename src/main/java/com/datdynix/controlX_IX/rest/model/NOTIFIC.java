package com.datdynix.controlX_IX.rest.model;

public class NOTIFIC {

		private String currentUser;
		private Integer page;
		private Integer pageSize;
		
		public String getcurrentUser() {
			return currentUser;
		}
		public String setcurrentUser(String currentUser) {
			this.currentUser = currentUser;
			return currentUser;
		}
		public Integer getpage() {
			return page;
		}
		public Integer setpage(Integer page) {
			this.page = page;
			return page;
		}
		public Integer getpageSize() {
			return pageSize;
		}
		public Integer setpageSize(Integer pageSize) {
			this.pageSize = pageSize;
			return pageSize;
		}
}
