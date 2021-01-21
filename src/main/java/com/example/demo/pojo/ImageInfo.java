package com.example.demo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ImageInfo implements Serializable {
    private String analysis_status;
    private String imageDigest;
    private String created_at;
}
