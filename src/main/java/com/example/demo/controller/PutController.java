package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.maven.surefire.shade.org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@Slf4j
public class PutController {

    public static String doPut(String url) {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut put = new HttpPut(url);
            HttpResponse response = httpClient.execute(put);
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            } else {
                log.error("Http request :" + url + ",status code :" + code);
            }
        } catch (ClientProtocolException e) {
            log.error("put fail in url " + url, e);
        } catch (IOException e) {
            log.warn("put fail in url " + url, e);
        }
        return null;
    }

    public static void main(String[] aa) {
        String response = doPut("http://test.csl.anchnet.com/api/cb/ci/finished/b908da3b-47f1-4e07-a167-3ef78b586551?identity=cci-170-618556&buildSuccess=true");
        if (StringUtils.isNotBlank(response)) {
            System.out.println(response);
        }
    }
}
