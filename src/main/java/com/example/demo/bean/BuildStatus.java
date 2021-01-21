package com.example.demo.bean;

import org.springframework.util.StringUtils;

import java.util.Objects;

public enum BuildStatus {
    QUEUED,
    INITIALIZING,
    NOT_BUILT,
    RUNNING,
    SUCCEED,
    FAILED,
    ABORTED,
    TIMEOUT;

    public static BuildStatus nameOf(String name) {
        BuildStatus[] values = BuildStatus.values();
        for (BuildStatus value : values) {
            if (Objects.equals(value.name(), name)) {
                return value;
            }
        }
        return BuildStatus.QUEUED;
    }

    public boolean isTerminated() {
        return this == SUCCEED || this == FAILED || this == ABORTED || this == TIMEOUT;
    }

    public boolean isSucceeded() {
        return this == SUCCEED;
    }

    public static void main(String[] arr){
        BuildStatus[] values = BuildStatus.values();
        for (BuildStatus value : values) {
            if (!StringUtils.isEmpty(value.name())) {
                System.out.println(BuildStatus.nameOf(value.name()).isTerminated());
            } else {
                System.out.println(value);
            }
        }
    }
}
