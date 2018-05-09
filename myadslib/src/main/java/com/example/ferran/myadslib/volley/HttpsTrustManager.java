package com.example.ferran.myadslib.volley;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpsTrustManager implements X509TrustManager {

    private static TrustManager[] trustManagers = null;
    private static X509TrustManager x509TrustManager = null;

    public static void sslConnection() {

        if (trustManagers == null) {
            trustManagers = new TrustManager[]{new HttpsTrustManager()};
        }

        if (x509TrustManager == null) {

            boolean found = false;
            int i = 0;

            while (i < trustManagers.length && !found) {
                if (trustManagers[i] instanceof X509TrustManager) {
                    x509TrustManager = (X509TrustManager) trustManagers[i];
                    found = true;
                }

                i++;
            }
        }

        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String ipHost, SSLSession sslSession) {
                    return sslSession.isValid();
                }
            });

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            /*if (Config.DEBUG) {
                System.out.println("X509 ERROR: " + e.getMessage());
            }**/
        }
    }

    @Override
    public void checkClientTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
        /*if (Config.DEBUG) {
            System.out.println("CERT CLIENT: " + Arrays.toString(certificates) + " AUTH TYPE: " + authType);
        }**/

        x509TrustManager.checkClientTrusted(certificates, authType);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
        /*if (Config.DEBUG) {
            System.out.println("CERT SERVER: " + Arrays.toString(certificates) + " AUTH TYPE: " + authType);
        }**/

        try {
            certificates[0].checkValidity();
        } catch (Exception e) {
            throw new CertificateException("Certificate not valid or trusted.");

        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return x509TrustManager.getAcceptedIssuers();
    }
}
