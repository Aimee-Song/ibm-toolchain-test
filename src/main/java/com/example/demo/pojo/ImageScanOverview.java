package com.example.demo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ImageScanOverview implements Serializable {

    public ImageScanOverview(List<Vulnerabilities> list, String name, int level) {
        this.name = name;
        this.num = list.size();
        this.level = level;
    }

    private int level;
    private int num;
    private String name;

}
