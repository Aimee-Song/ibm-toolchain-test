package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.Page;
import com.example.demo.bean.Result;
import com.example.demo.config.AnchoreConfig;
import com.example.demo.pojo.*;
import kong.unirest.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import kong.unirest.Unirest;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@Service
public class AnchoreService {

    @Autowired
    private AnchoreConfig anchoreConfig;

    public Result<Page<Vulnerabilities>> getImageScanResults(Integer pageSize, Integer page, String sha) {
        try {
            String url = anchoreConfig.getUrl() + "/v1/images/by_id/" + sha + "/vuln/all";

            HttpResponse<ImageScanResultDTO> httpResponse = Unirest.get(url).header("Authorization", "Basic YWRtaW46Zm9vYmFy").asObject(ImageScanResultDTO.class);
            ImageScanResultDTO dto = httpResponse.getBody();
            System.out.println(dto);
            if (HttpStatus.OK.value() == httpResponse.getStatus()) {
                List<Vulnerabilities> vulnList = dto.getVulnerabilities();
                Page<Vulnerabilities> vulnPage = new Page<>(pageSize, page, vulnList);
                return Result.success(vulnPage);
            }
        } catch (Exception e) {
            log.error("获取Anchore代码扫描结果错误: %s, %s", sha, e.getMessage());
        }
        return Result.ok();
    }

    public Result<ImageScanOverviewItem> getImageScanOverview(String sha) {
        ImageScanOverviewItem imageScanOverviewItem = null;
        try {
            String url = anchoreConfig.getUrl() + "/v1/images/by_id/" + sha + "/vuln/all";

            HttpResponse<ImageScanResultDTO> httpResponse = Unirest.get(url).header("Authorization", "Basic YWRtaW46Zm9vYmFy").asObject(ImageScanResultDTO.class);
            ImageScanResultDTO dto = httpResponse.getBody();
            System.out.println(dto);
            if (HttpStatus.OK.value() == httpResponse.getStatus()) {
                List<Vulnerabilities> vulnList = dto.getVulnerabilities();
                imageScanOverviewItem = new ImageScanOverviewItem(vulnList);
                return Result.success(imageScanOverviewItem);
            }
        } catch (Exception e) {
            log.error("获取Anchore代码扫描结果错误: %s, %s", sha, e.getMessage());
        }
        return Result.success(new ImageScanOverviewItem(new ArrayList<Vulnerabilities>()));
    }

    public String getImageInfo(String tag) {
        try {
            String url = anchoreConfig.getUrl() + "/v1/images/" + tag;
            HttpResponse<String> httpResponse = Unirest.get(url).header("Authorization", anchoreConfig.getAuthorization()).asString();
            System.out.println(url);
            System.out.println(httpResponse.getBody());
            System.out.println(httpResponse.getStatus());
            if(HttpStatus.OK.value() == httpResponse.getStatus()) {
                String result = httpResponse.getBody();
                List<ImageInfo> list = JSONObject.parseArray(result, ImageInfo.class);
                if(list.size() > 0) {
                    return list.get(0).getAnalysis_status();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "not_scan";
    }

    public void getImageScanTrigger(ImageScanTriggerDTO trigger) {
        try {
            String url = anchoreConfig.getUrl() + "/v1/images";
            HttpResponse<String> httpResponse = Unirest.post(url).header("Authorization", anchoreConfig.getAuthorization())
                    .header("Content-Type", "application/json").body(trigger).asString();
            System.out.println(httpResponse.getBody());
            System.out.println(httpResponse.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
