package com.example.demo.controller;

import com.example.demo.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/c/self-service-api/public/api/pipeline")
@Slf4j
public class PiccController {

    @GetMapping("/tigger/{projectId}/{pipelineId}/by")
    public Map<String, Object> trigger(@PathVariable String projectId,
                          @PathVariable String pipelineId,
                          @RequestParam(value="bk_app_secret") String bkAppSecret,
                          @RequestParam(value="bk_app_code") String bkAppCode,
                          @RequestParam(value="bk_username") String bkUsername,
                          @RequestParam(value="tfsJson") String tfsJson) {
        System.out.println(projectId+":"+pipelineId+":"+bkAppSecret+":"+bkAppCode+":"+bkUsername+":"+tfsJson);
        Map<String, Object> map = new HashMap<>();
        /*if(System.currentTimeMillis() %2 != 0){
            map.put("code", 200);
            map.put("status", 1);
            map.put("result", null);
        } else {*/
            map.put("code", 200);
            map.put("status", 0);
            map.put("result", null);
            map.put("request_id", "request_id");
            map.put("message", "messagemessage");
            Map<String, Object> data = new HashMap<>();
            data.put("url", "url");
            data.put("buildNo", "buildNo");
            data.put("pipelineId", "pipelineId");
            map.put("data", data);
        /*}*/
        return map;
    }

    @GetMapping("/statusNew/{projectId}/{pipelineId}/{buildNo}")
    public Map<String, Object> getStatus(@PathVariable String projectId,
                                         @PathVariable String pipelineId,
                                         @PathVariable String buildNo,
                                         @RequestParam(value="bk_app_secret") String bkAppSecret,
                                         @RequestParam(value="bk_app_code") String bkAppCode,
                                         @RequestParam(value="bk_username") String bkUsername) {
        System.out.println(projectId+":"+pipelineId+":"+buildNo);
        Map<String, Object> map = new HashMap<>();
        long i = System.currentTimeMillis() %4;
        if(i == 0){
            map.put("code", 200);
            map.put("status", 0);
            map.put("message", "messagemessage");
            Map<String, Object> data = new HashMap<>();
            data.put("buildStatus", "SUCCEED");
            map.put("data", data);
        } else if (i == 1){
            map.put("code", 200);
            map.put("status", 0);
            map.put("message", "messagemessage");
            Map<String, Object> data = new HashMap<>();
            data.put("buildStatus", "FAILED");
            map.put("data", data);
        } else {
            map.put("code", 200);
            map.put("status", 0);
            map.put("message", "messagemessage");
            Map<String, Object> data = new HashMap<>();
            data.put("buildStatus", "RUNNING");
            map.put("data", data);
            return map;
        }
        return map;
    }
}
