package com.spring.project.common.jsoup;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class JsoupSSL {
	
	//private final static String USER_AGENT = "MOZILLA/5.0 (WINDOWS NT 10.0; WIN64; X64) APPLEWEBKIT/537.36 (KHTML, LIKE GECKO) CHROME/67.0.3396.99 SAFARI/537.36";
	
	// SSL 처리
    public static void setSSL() throws NoSuchAlgorithmException, KeyManagementException {
    	 TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
             public X509Certificate[] getAcceptedIssuers(){return new X509Certificate[0];}
             public void checkClientTrusted(X509Certificate[] certs, String authType){}
             public void checkServerTrusted(X509Certificate[] certs, String authType){}
         }};

         SSLContext sc = SSLContext.getInstance("TLS");
         sc.init(null, trustAllCerts, new SecureRandom());
         HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }
}
