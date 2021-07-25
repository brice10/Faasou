package com.gteam.faasou.services.impl;

import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.gteam.faasou.models.SearchModel;
import com.gteam.faasou.services.SearchService;
import com.gteam.faasou.utils.Constants;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.HttpUrl;

/**
 *
 * @author Arleon Zemtsop
 */

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

	@Value("${app.search_api_url}")
	private String search_api_url;

	@Value("${app.search_api_key}")
	private String search_api_key;

	@Override
	public String search(SearchModel model) {
		MediaType mediaType = MediaType.parse("application/json");
		okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(mediaType, "");
		Request.Builder builder = new Request.Builder().addHeader("content-type", "application/json");
		HttpUrl.Builder httpBuilder = HttpUrl.parse(search_api_url).newBuilder();
		httpBuilder.addQueryParameter("key", search_api_key);
		httpBuilder.addQueryParameter("cx", model.getCx());
		httpBuilder.addQueryParameter("searchType", model.getSearchType().getSearchType());
		//httpBuilder.addQueryParameter("hq", Constants.COVID + " " + Constants.COVID19 + " " + Constants.CORONA_VIRUS + " " + Constants.PANDEMIE + " " + Constants.GEL_HYDROALCOLIQUE + " " + Constants.CONFINEMENT);
		httpBuilder.addQueryParameter("hq", Constants.COVID19);
		httpBuilder.addQueryParameter("q", model.getQ());
		Request request =  builder.url(httpBuilder.build())
							.get()
							.build();
		OkHttpClient client = getHttpClient();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			switch (response.code()) {
				case 200:
					String responseObject = response.body().string();
					return responseObject;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if(response != null) response.close();
		}
		return "";
	}

	private static OkHttpClient getHttpClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		try {
            final TrustManager[] trustAllCerts = getTrustAllCerts();

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
        }
		builder.connectTimeout(2, TimeUnit.MINUTES) // connect timeout
				.writeTimeout(2, TimeUnit.MINUTES) // write timeout
				.readTimeout(2, TimeUnit.MINUTES); // read timeout
		return builder.build();
	}

	private static TrustManager[] getTrustAllCerts() {
        return new TrustManager[] { new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }
        } };
    }
}
