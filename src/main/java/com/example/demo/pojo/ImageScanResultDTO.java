package com.example.demo.pojo;

import java.io.Serializable;
import java.util.List;

public class ImageScanResultDTO  implements Serializable {
    private String vulnerability_type;

    private List<Vulnerabilities> vulnerabilities;

    private String imageDigest;

    public String getVulnerability_type ()
    {
        return vulnerability_type;
    }

    public void setVulnerability_type (String vulnerability_type)
    {
        this.vulnerability_type = vulnerability_type;
    }

    public List<Vulnerabilities> getVulnerabilities ()
    {
        return vulnerabilities;
    }

    public void setVulnerabilities (List<Vulnerabilities> vulnerabilities)
    {
        this.vulnerabilities = vulnerabilities;
    }

    public String getImageDigest ()
    {
        return imageDigest;
    }

    public void setImageDigest (String imageDigest)
    {
        this.imageDigest = imageDigest;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [vulnerability_type = "+vulnerability_type+", vulnerabilities = "+vulnerabilities+", imageDigest = "+imageDigest+"]";
    }
}
