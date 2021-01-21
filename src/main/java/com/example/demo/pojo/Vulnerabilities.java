package com.example.demo.pojo;

import java.io.Serializable;

public class Vulnerabilities implements Serializable {
    private String severity;

    private Nvd_data[] nvd_data;

    /*private String package;*/

    private String package_path;

    private String package_cpe23;

    private String[] vendor_data;

    private String package_type;

    private String package_cpe;

    private String url;

    private String feed;

    private String package_version;

    private String fix;

    private String vuln;

    private String feed_group;

    private String package_name;

    public String getSeverity ()
    {
        return severity;
    }

    public void setSeverity (String severity)
    {
        this.severity = severity;
    }

    public Nvd_data[] getNvd_data ()
    {
        return nvd_data;
    }

    public void setNvd_data (Nvd_data[] nvd_data)
    {
        this.nvd_data = nvd_data;
    }

    /*public String getPackage ()
    {
        return package;
    }

    public void setPackage (String package)
    {
        this.package = package;
    }*/

    public String getPackage_path ()
    {
        return package_path;
    }

    public void setPackage_path (String package_path)
    {
        this.package_path = package_path;
    }

    public String getPackage_cpe23 ()
    {
        return package_cpe23;
    }

    public void setPackage_cpe23 (String package_cpe23)
    {
        this.package_cpe23 = package_cpe23;
    }

    public String[] getVendor_data ()
    {
        return vendor_data;
    }

    public void setVendor_data (String[] vendor_data)
    {
        this.vendor_data = vendor_data;
    }

    public String getPackage_type ()
    {
        return package_type;
    }

    public void setPackage_type (String package_type)
    {
        this.package_type = package_type;
    }

    public String getPackage_cpe ()
    {
        return package_cpe;
    }

    public void setPackage_cpe (String package_cpe)
    {
        this.package_cpe = package_cpe;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getFeed ()
    {
        return feed;
    }

    public void setFeed (String feed)
    {
        this.feed = feed;
    }

    public String getPackage_version ()
    {
        return package_version;
    }

    public void setPackage_version (String package_version)
    {
        this.package_version = package_version;
    }

    public String getFix ()
    {
        return fix;
    }

    public void setFix (String fix)
    {
        this.fix = fix;
    }

    public String getVuln ()
    {
        return vuln;
    }

    public void setVuln (String vuln)
    {
        this.vuln = vuln;
    }

    public String getFeed_group ()
    {
        return feed_group;
    }

    public void setFeed_group (String feed_group)
    {
        this.feed_group = feed_group;
    }

    public String getPackage_name ()
    {
        return package_name;
    }

    public void setPackage_name (String package_name)
    {
        this.package_name = package_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [severity = "+severity+", nvd_data = "+nvd_data+", package = "/*+package+", package_path = "*/+package_path+", package_cpe23 = "+package_cpe23+", vendor_data = "+vendor_data+", package_type = "+package_type+", package_cpe = "+package_cpe+", url = "+url+", feed = "+feed+", package_version = "+package_version+", fix = "+fix+", vuln = "+vuln+", feed_group = "+feed_group+", package_name = "+package_name+"]";
    }
}
