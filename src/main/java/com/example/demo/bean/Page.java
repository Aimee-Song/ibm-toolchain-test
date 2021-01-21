package com.example.demo.bean;

import com.example.demo.util.PageUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Page<T> implements Serializable {

    public Page(int pageSize, int pageNum, List<T> records){
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.totals = records.size();
        this.pages = (totals % this.pageSize == 0 ? totals / this.pageSize : totals / this.pageSize + 1);
        this.results = PageUtil.startPage(records, this.pageNum, this.pageSize);
    }

    /**
     * 页码
     */
    private int pageNum;

    /**
     * 每页显示数
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int pages;


    /**
     * 总记录数
     */
    private int totals;

    /**
     * 总数据
     */
    @JsonIgnore
    private List<T> records;

    /**
     * 分页结果集
     */
    private List<T> results;

}

