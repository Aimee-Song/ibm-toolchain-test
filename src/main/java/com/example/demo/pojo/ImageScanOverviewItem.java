package com.example.demo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ImageScanOverviewItem implements Serializable {

    public ImageScanOverviewItem(List<Vulnerabilities> vulnList){
        this.issueNumber = vulnList.size();
        List<Vulnerabilities> highList = vulnList.stream().filter(e->"High".equals(e.getSeverity())).collect(Collectors.toList());
        List<Vulnerabilities> mediumList = vulnList.stream().filter(e->"Medium".equals(e.getSeverity())).collect(Collectors.toList());
        List<Vulnerabilities> lowList = vulnList.stream().filter(e->"Low".equals(e.getSeverity())).collect(Collectors.toList());
        List<Vulnerabilities> criticalList = vulnList.stream().filter(e->"Critical".equals(e.getSeverity())).collect(Collectors.toList());
        this.list.add(new ImageScanOverview(highList, "High", 3));
        this.list.add(new ImageScanOverview(mediumList, "Medium", 2));
        this.list.add(new ImageScanOverview(lowList, "Low", 1));
        this.list.add(new ImageScanOverview(criticalList, "Critical", 0));
    }

    private List<ImageScanOverview> list = new ArrayList<>();
    private int issueNumber;
}
