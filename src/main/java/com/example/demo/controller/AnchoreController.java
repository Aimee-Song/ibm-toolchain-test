package com.example.demo.controller;

import com.example.demo.bean.Page;
import com.example.demo.bean.Result;
import com.example.demo.pojo.ImageScanOverview;
import com.example.demo.pojo.ImageScanOverviewItem;
import com.example.demo.pojo.ImageScanTriggerDTO;
import com.example.demo.pojo.Vulnerabilities;
import com.example.demo.service.AnchoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnchoreController {

    @Autowired
    private AnchoreService anchoreService;

    @RequestMapping(value = "/getVuln", method = RequestMethod.GET)
    public Result<Page<Vulnerabilities>> getVuln(@RequestParam(value="pageSize") Integer pageSize,
                                                 @RequestParam(value="page") Integer page,
                                                 @RequestParam(value = "sha") String sha) {
        return anchoreService.getImageScanResults(pageSize, page, sha);
    }

    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public Result<ImageScanOverviewItem> overview(@RequestParam(value = "sha") String sha) {
        return anchoreService.getImageScanOverview(sha);
    }

    @GetMapping(value = "/getImageInfo")
    public Result getImageInfo(@RequestParam String tag) {
        String status = anchoreService.getImageInfo(tag);
        return Result.success(status);
    }

    @GetMapping(value = "/trigger")
    public Result imageScanTrigger(@RequestParam String tag) {
        ImageScanTriggerDTO trigger = new ImageScanTriggerDTO();
        trigger.setTag(tag);
        anchoreService.getImageScanTrigger(trigger);
        return Result.ok();
    }

}
