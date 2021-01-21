package com.example.demo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ImageScanTriggerDTO implements Serializable {
    private String digest;
    private String image_type;
    private String tag;
    private String created_at;
}
