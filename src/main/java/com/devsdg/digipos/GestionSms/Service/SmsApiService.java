package com.devsdg.digipos.GestionSms.Service;

import com.devsdg.digipos.GestionSms.Model.SmsBody;
import net.minidev.json.JSONObject;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Service
public class SmsApiService {
    private final String urlApi = "https://mainlevee.org/mainlevee-gateway/api";
    private final String tokenApi = "YmVydGluLmNob3Vhbm1vQGRpZ2lwbHVzY29uc3VsdGluZy5jb206Y2hvdWEyMDIwRGlnaXQqKkAhIQ==";
    private final RestTemplate restTemplate;

    public SmsApiService() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        this.restTemplate = new RestTemplate(requestFactory);
        //this.restTemplate = restTemplateBuilder.build();
    }

    public String smsAuthentification(String recipient,String content){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Basic "+ tokenApi);

        SmsBody smsBody = new SmsBody();
        smsBody.setContent(content);
        smsBody.setRecipient(237+recipient);
        smsBody.setSender("Digishop");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sender", smsBody.getSender());
        jsonObject.put("recipient", smsBody.getRecipient());
        jsonObject.put("content", smsBody.getContent());

        HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(),headers);

        String res = restTemplate.postForObject(urlApi+"/sms", entity, String.class);
        System.out.println(res);

        return res;
    }

    public String getSmsTransaction(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Basic "+ tokenApi);

        HttpEntity <String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> res = restTemplate.exchange(urlApi+"/sms/b6a437f6729dea910172a422a4b10019", HttpMethod.GET, entity, String.class);

        return res.getBody();
    }
}
