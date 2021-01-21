package com.example.demo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ImageInfoRes implements Serializable {
    private ImageInfo[] imageInfos;
}