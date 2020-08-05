package com.datdynix.controlX_IX.rest.model;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Certification {
	public void certification() throws KeyManagementException, NoSuchAlgorithmException, IOException{
	     TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
      public java.security.cert.X509Certificate[] getAcceptedIssuers() {
          return null;
      }
      @Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
			}
			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
					throws CertificateException {
			}
  }
};
	     // Install the all-trusting trust manager
	     SSLContext sc = SSLContext.getInstance("SSL");
	     sc.init(null, trustAllCerts, new java.security.SecureRandom());
	     HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

	     // Create all-trusting host name verifier
	     HostnameVerifier allHostsValid = new HostnameVerifier() {
	     public boolean verify(String hostname, SSLSession session) {
	     return true;
	     }
	  };
	  // Install the all-trusting host verifier
	  HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	}
 }



