package com.example.demo.controller;

import com.example.demo.bean.Result;
import com.example.demo.pojo.Wiki;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/code")
@Slf4j
public class TestController {



    @PostMapping("/{item}")
    public String queryDepartment(HttpServletRequest request, @PathVariable String item, HttpServletResponse response) {
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@ Begin @@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(item);
        //log.info(data);
        //getRequest(request);
        Map<String, Object> map = new HashMap<>();
        switch (item) {
            case "postSourceCode":
                map.put("status", 0);
                map.put("projetcId", "xxx");
                map.put("taskId", "yyy");
                getRequest(request);
                break;
            case "queryTaskStatus":
                if(System.currentTimeMillis() %2 != 0){
                    map.put("status", 0);
                    map.put("taskStatus ", 3);
                    map.put("taskId", "yyy");
                } else {
                    map.put("status", 0);
                    map.put("errorMsg", "ssssss");
                    map.put("taskId", "yyy");
                }
                break;
            case "getPdfReport":
                if(System.currentTimeMillis() %2 != 0){
                    downloadZip(response);
                } else {
                    map.put("status", 1);
                    map.put("errorMsg ", 1);
                    map.put("taskId", "yyy");
                }
                break;
            case "queryDefectType":
                map.put("status", 0);
                List<String> list = new ArrayList<>();
                list.add("Java");
                list.add("C#");
                list.add("Python");
                map.put("language ", list);
                break;
            default:
                break;
        }
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@ End @@@@@@@@@@@@@@@@@@@@@@@@");
        return map.toString();
    }

    @PostMapping("/getPdfReport")
    public void queryDepartment(HttpServletResponse response) {
        downloadZip(response);
    }

    private void getRequest(HttpServletRequest request) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try
        {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        log.info(sb.toString());
    }

    private void downloadZip(HttpServletResponse response) {
        try (OutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {
            String templateContent = "123";
            byte tag_bytes[] = templateContent.getBytes();
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"test\".txt");
            response.addHeader("Content-Length", "" + tag_bytes.length);
            response.setContentType("application/octet-stream;charset=UTF-8");
            outputStream.write(tag_bytes);
            response.flushBuffer();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @RequestMapping(value = "/codepecker", method = RequestMethod.GET)
    public void getCodePecker(
            HttpServletResponse response,
            @RequestParam(value="url") String url,
            @RequestParam(value="auth") String auth
    ) {
        //配置POST要的数据
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        //设置为浏览器的兼容模式
        multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        StringBody baseAuth = new StringBody(auth, ContentType.TEXT_PLAIN);

        multipartEntityBuilder.addPart("auth", baseAuth);

        try {
            String result = httpPost(url, multipartEntityBuilder);
            try (OutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {
                byte tag_bytes[] = result.getBytes();
                response.reset();
                String fileName = URLEncoder.encode("啄木鸟代码审计结果","UTF-8");
                response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\".pdf");
                response.addHeader("Content-Length", "" + tag_bytes.length);
                response.setContentType("application/octet-stream;charset=UTF-8");
                outputStream.write(tag_bytes);
                response.flushBuffer();
            } catch (Exception e) {
                e.getMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String httpPost(String url, MultipartEntityBuilder multipartEntityBuilder) throws IOException {
        String result = null;
        HttpPost httpPost = new HttpPost(url);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpEntity httpEntity = multipartEntityBuilder.build();
        httpPost.setEntity(httpEntity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity httpEntity2 = httpResponse.getEntity();
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(httpEntity2);
        } else if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) {
            String locationUrl = httpResponse.getLastHeader("location").getValue();
            httpPost(locationUrl, multipartEntityBuilder);
        } else {
            result = EntityUtils.toString(httpEntity2);
        }
        return result;

    }

/*    public static void main(String[] arr) {
        String logResult = "=================== 啄木鸟代码审计结果：http://localhost:8081/code/getPdfReport ==================";
        String pattern = "(?<=啄木鸟代码审计结果：)[\\S]*";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(logResult);
        if (m.find()) {
            System.out.println(m.group());
        }
    }*/
}
