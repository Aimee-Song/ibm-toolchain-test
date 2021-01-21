package com.example.demo.pojo;

import java.io.Serializable;

public class Cvss_v3 implements Serializable {
    private String base_score;

    private String impact_score;

    private String exploitability_score;

    public String getBase_score ()
    {
        return base_score;
    }

    public void setBase_score (String base_score)
    {
        this.base_score = base_score;
    }

    public String getImpact_score ()
    {
        return impact_score;
    }

    public void setImpact_score (String impact_score)
    {
        this.impact_score = impact_score;
    }

    public String getExploitability_score ()
    {
        return exploitability_score;
    }

    public void setExploitability_score (String exploitability_score)
    {
        this.exploitability_score = exploitability_score;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [base_score = "+base_score+", impact_score = "+impact_score+", exploitability_score = "+exploitability_score+"]";
    }
}