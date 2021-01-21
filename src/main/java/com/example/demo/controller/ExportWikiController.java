package com.example.demo.controller;

import com.example.demo.pojo.Wiki;
import com.example.demo.service.ExportWikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ExportWikiController {

    @Autowired
    private ExportWikiService service;

    @RequestMapping(value = "/wiki", method = RequestMethod.POST)
        public void wiki(Wiki wiki,
                            HttpServletRequest request,
                            HttpServletResponse response){
        try {
            service.exportWikiByProjectIdAndWikiId(wiki, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/wiki/all", method = RequestMethod.POST)
    public void wikiAll(Wiki wiki,
                        HttpServletRequest request,
                        HttpServletResponse response){
        try {
            service.exportAllWikisByProjectId(wiki, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/wiki/test", method = RequestMethod.GET)
    public void testWiki(HttpServletRequest request,
                        HttpServletResponse response){
        try {
            Wiki wiki = new Wiki();
            wiki.setTitle("20191101 数据库出错");
            wiki.setContent("<p>2019 年 11 月 1 日下午四点半左右，用户内网机器数据库服务一直处于 restarting 状态</p> \n" +
                    "<p>日志如下：<br> <a href=\"/api/project/233032/files/2305485/imagePreview\" target=\"_blank\" class=\"bubble-markdown-image-link\"><img src=\"/api/project/233032/files/2305485/imagePreview\" alt=\"图片\" class=\"bubble-markdown-image\" origin-src=\"/api/project/233032/files/2305485/imagePreview\"></a></p> \n" +
                    "<p>处理过程如下：</p> \n" +
                    "<p>先将用户的数据文件备份</p> \n" +
                    "<pre><code>cp /data/docker/mysql-data / /data/coding-data/\n" +
                    "</code></pre> \n" +
                    "<p>然后将用户的数据库的配置进行修改</p> \n" +
                    "<pre><code>vim /root/pd/mysql/connections.cnf\n" +
                    "</code></pre> \n" +
                    "<p>最后一行加上</p> \n" +
                    "<pre><code>innodb_force_recovery = 6\n" +
                    "</code></pre> \n" +
                    "<p>然后重启数据库</p> \n" +
                    "<pre><code>docker-compose restart mariadb\n" +
                    "</code></pre> \n" +
                    "<p>然后将数据的数据 dump 出来</p> \n" +
                    "<pre><code>docker exec -it pd_mariadb_1 bash -c \"mysqldump -ucoding -pcoding123 --databases coding\" &gt; 20191101.sql\n" +
                    "</code></pre> \n" +
                    "<p>然后停掉数据库服务，删除原有的数据</p> \n" +
                    "<pre><code>docker-compsoe stop mariadb\n" +
                    "rm -fr /data/docker/mysql-data\n" +
                    "</code></pre> \n" +
                    "<p>启动数据服务</p> \n" +
                    "<pre><code>docker-compose up -d mariadb\n" +
                    "</code></pre> \n" +
                    "<p>恢复数据</p> \n" +
                    "<pre><code>cat 20191101.sql | docker exec -i pd_maraidb_1 mysql --default-character-set=utf8 -pcoding123 -B coding\n" +
                    "</code></pre> \n" +
                    "<p>然后重启使用了数据库的服务</p> \n" +
                    "<pre><code>docker-compose restart coding statistic repo-auth-server email\n" +
                    "</code></pre> \n" +
                    "<p>服务恢复</p>");
            service.exportWikiByProjectIdAndWikiId(wiki, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/wiki/all/test", method = RequestMethod.GET)
    public void testWikiAll(HttpServletRequest request,
                        HttpServletResponse response){
        try {
            Wiki wiki = new Wiki();
            wiki.setTitle("20191101 数据库出错");
            wiki.setContent("<p>2019 年 11 月 1 日下午四点半左右，用户内网机器数据库服务一直处于 restarting 状态</p> \n" +
                    "<p>日志如下：<br> <a href=\"/api/project/233032/files/2305485/imagePreview\" target=\"_blank\" class=\"bubble-markdown-image-link\"><img src=\"/api/project/233032/files/2305485/imagePreview\" alt=\"图片\" class=\"bubble-markdown-image\" origin-src=\"/api/project/233032/files/2305485/imagePreview\"></a></p> \n" +
                    "<p>处理过程如下：</p> \n" +
                    "<p>先将用户的数据文件备份</p> \n" +
                    "<pre><code>cp /data/docker/mysql-data / /data/coding-data/\n" +
                    "</code></pre> \n" +
                    "<p>然后将用户的数据库的配置进行修改</p> \n" +
                    "<pre><code>vim /root/pd/mysql/connections.cnf\n" +
                    "</code></pre> \n" +
                    "<p>$$$$$$$最后一行加上</p> \n" +
                    "<pre><code>innodb_force_recovery = 6\n" +
                    "</code></pre> \n" +
                    "<p>然后重启数据库</p> \n" +
                    "<pre><code>docker-compose restart mariadb\n" +
                    "</code></pre> \n" +
                    "<p>然后将数据的数据 dump 出来</p> \n" +
                    "<pre><code>docker exec -it pd_mariadb_1 bash -c \"mysqldump -ucoding -pcoding123 --databases coding\" &gt; 20191101.sql\n" +
                    "</code></pre> \n" +
                    "<p>然后停掉数据库服务，删除原有的数据</p> \n" +
                    "<pre><code>docker-compsoe stop mariadb\n" +
                    "rm -fr /data/docker/mysql-data\n" +
                    "</code></pre> \n" +
                    "<p>启动数据服务</p> \n" +
                    "<pre><code>docker-compose up -d mariadb\n" +
                    "</code></pre> \n" +
                    "<p>恢复数据</p> \n" +
                    "<pre><code>cat 20191101.sql | docker exec -i pd_maraidb_1 mysql --default-character-set=utf8 -pcoding123 -B coding\n" +
                    "</code></pre> \n" +
                    "<p>然后重启使用了数据库的服务</p> \n" +
                    "<pre><code>docker-compose restart coding statistic repo-auth-server email\n" +
                    "</code></pre> \n" +
                    "<p>服务恢复</p>");
            service.exportAllWikisByProjectId(wiki, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
