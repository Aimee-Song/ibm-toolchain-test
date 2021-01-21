package com.example.demo.pojo;

import java.io.Serializable;

public class Nvd_data  implements Serializable {
    private Cvss_v3 cvss_v3;

    private Cvss_v2 cvss_v2;

    private String id;

    public Cvss_v3 getCvss_v3 ()
    {
        return cvss_v3;
    }

    public void setCvss_v3 (Cvss_v3 cvss_v3)
    {
        this.cvss_v3 = cvss_v3;
    }

    public Cvss_v2 getCvss_v2 ()
    {
        return cvss_v2;
    }

    public void setCvss_v2 (Cvss_v2 cvss_v2)
    {
        this.cvss_v2 = cvss_v2;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cvss_v3 = "+cvss_v3+", cvss_v2 = "+cvss_v2+", id = "+id+"]";
    }
}
