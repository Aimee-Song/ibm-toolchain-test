package com.example.demo.service;

import com.alibaba.fastjson.util.IOUtils;
import com.example.demo.pojo.Wiki;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class ExportWikiService {

    public void exportAllWikisByProjectId(Wiki wiki, HttpServletRequest request,
                                          HttpServletResponse response) {
        List<File> list = new ArrayList<>();
        for(int i = 0; i < 10; i ++){
            File file = downloadZip(wiki);
            list.add(file);
        }
        try (ZipOutputStream out = new ZipOutputStream(response.getOutputStream())) {
            byte[] buffer = new byte[10240];
            for (int i = 0; i < list.size(); i++) {
                try (FileInputStream fis = new FileInputStream(list.get(i))) {
                    out.putNextEntry(new ZipEntry(list.get(i).getName()));
                    // 设置压缩文件内的字符编码，不然会变成乱码
                    int len;
                    // 读入需要下载的文件的内容，打包到zip文件
                    while ((len = fis.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }
                    out.closeEntry();
                }
            }
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=11.zip");
            response.flushBuffer();
        } catch(Exception e) {
            log.error(e.getMessage());
        } finally {
            for(File f : list) {
                f.delete();
            }
        }
    }

    public void exportWikiByProjectIdAndWikiId(Wiki wiki, HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
        downloadHtml(wiki, response);
    }

    private File downloadZip(Wiki wiki) {
        String title = wiki.getTitle() + "_" + UUID.randomUUID();
        String fileName = title + ".html";
        File file = new File(fileName);
        try (FileOutputStream fileoutputstream = new FileOutputStream(file, true)){
            File template = ResourceUtils.getFile("classpath:templates/template.html");
            FileInputStream fileinputstream = new FileInputStream(template);// 读取模板文件
            int length = fileinputstream.available();
            byte bytes[] = new byte[length];
            fileinputstream.read(bytes);
            String templateContent = new String(bytes);
            String html = wiki.getContent();
            html = html.replaceAll("\\$", "RDS_CHAR_DOLLAR");
            templateContent = templateContent.replaceAll("###content###", html);
            templateContent = templateContent.replaceAll("RDS_CHAR_DOLLAR", "\\$");
            templateContent = templateContent.replaceAll("###title###", wiki.getTitle());
            byte[] tag_bytes = templateContent.getBytes();
            fileoutputstream.write(tag_bytes);
        } catch (IOException e) {
            e.getMessage();
        }
        return file;
    }

    private void downloadHtml(Wiki wiki, HttpServletResponse response) {
        try (OutputStream outputStream = new BufferedOutputStream(response.getOutputStream())) {
            String templateContent = new String(template);
            String html = wiki.getContent();
            html = html.replaceAll("\\$", "RDS_CHAR_DOLLAR");
            templateContent = templateContent.replaceAll("###content###", html);
            templateContent = templateContent.replaceAll("RDS_CHAR_DOLLAR", "\\$");
            templateContent = templateContent.replaceAll("###title###", wiki.getTitle());
            String title = wiki.getTitle();
            String fileName = title + "_" + UUID.randomUUID();
            byte tag_bytes[] = templateContent.getBytes();
            response.reset();
            fileName = URLEncoder.encode(fileName,"UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\".html");
            response.addHeader("Content-Length", "" + tag_bytes.length);
            response.setContentType("application/octet-stream;charset=UTF-8");
            outputStream.write(tag_bytes);
            response.flushBuffer();
        } catch (Exception e) {
            e.getMessage();
        }
    }



    String template = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>###title###</title>\n" +
            "    <style type=\"text/css\">\n" +
            "        body {\n" +
            "            display: table;\n" +
            "            table-layout: fixed;\n" +
            "            font-family: \"PingFang SC\", \"Helvetica Neue\", \"Hiragino Sans GB\", \"Segoe UI\", \"Microsoft YaHei\", 微软雅黑, sans-serif;\n" +
            "            font-size: 14px;\n" +
            "            line-height: 23px;\n" +
            "            word-wrap: break-word;\n" +
            "            text-rendering: optimizeLegibility;\n" +
            "            color: #202d40;\n" +
            "            -webkit-font-smoothing: antialiased;\n" +
            "            -moz-osx-font-smoothing: grayscale;\n" +
            "        }\n" +
            "\n" +
            "        sup > sup {\n" +
            "            top: 0;\n" +
            "        }\n" +
            "\n" +
            "        sub > sub {\n" +
            "            bottom: 0;\n" +
            "        }\n" +
            "\n" +
            "        > *:first-child {\n" +
            "            margin-top: 0 !important;\n" +
            "        }\n" +
            "\n" +
            "        > *:last-child {\n" +
            "            margin-bottom: 0 !important;\n" +
            "        }\n" +
            "\n" +
            "        a.absent {\n" +
            "            color: #c00;\n" +
            "        }\n" +
            "\n" +
            "        a.anchor {\n" +
            "            display: none;\n" +
            "            cursor: pointer;\n" +
            "            position: absolute;\n" +
            "            top: 0;\n" +
            "            left: 0;\n" +
            "            bottom: 0;\n" +
            "        }\n" +
            "\n" +
            "        a.anchor:focus {\n" +
            "            outline: none;\n" +
            "        }\n" +
            "\n" +
            "        h1,\n" +
            "        h2,\n" +
            "        h3,\n" +
            "        h4,\n" +
            "        h5,\n" +
            "        h6 {\n" +
            "            margin: 1em 0 15px;\n" +
            "            padding: 0;\n" +
            "            cursor: text;\n" +
            "            position: relative;\n" +
            "            font-weight: 700;\n" +
            "            line-height: 2;\n" +
            "            color: #202d40;\n" +
            "        }\n" +
            "\n" +
            "        h1 .octicon-link,\n" +
            "        h2 .octicon-link,\n" +
            "        h3 .octicon-link,\n" +
            "        h4 .octicon-link,\n" +
            "        h5 .octicon-link,\n" +
            "        h6 .octicon-link {\n" +
            "            display: none;\n" +
            "            color: #000;\n" +
            "        }\n" +
            "\n" +
            "        h1:hover a.anchor,\n" +
            "        h2:hover a.anchor,\n" +
            "        h3:hover a.anchor,\n" +
            "        h4:hover a.anchor,\n" +
            "        h5:hover a.anchor,\n" +
            "        h6:hover a.anchor {\n" +
            "            display: inline;\n" +
            "            text-decoration: none;\n" +
            "            left: -1em;\n" +
            "            padding-right: 1em;\n" +
            "        }\n" +
            "\n" +
            "        h1:hover a.anchor .octicon-link,\n" +
            "        h2:hover a.anchor .octicon-link,\n" +
            "        h3:hover a.anchor .octicon-link,\n" +
            "        h4:hover a.anchor .octicon-link,\n" +
            "        h5:hover a.anchor .octicon-link,\n" +
            "        h6:hover a.anchor .octicon-link {\n" +
            "            display: inline-block;\n" +
            "        }\n" +
            "\n" +
            "        h1 tt,\n" +
            "        h1 code,\n" +
            "        h2 tt,\n" +
            "        h2 code,\n" +
            "        h3 tt,\n" +
            "        h3 code,\n" +
            "        h4 tt,\n" +
            "        h4 code,\n" +
            "        h5 tt,\n" +
            "        h5 code,\n" +
            "        h6 tt,\n" +
            "        h6 code {\n" +
            "            font-size: inherit;\n" +
            "        }\n" +
            "\n" +
            "        h1 {\n" +
            "            font-size: 28px;\n" +
            "            font-weight: 400;\n" +
            "            border-bottom: 1px solid #ddd;\n" +
            "        }\n" +
            "\n" +
            "        .bubble h1,\n" +
            "        .bubble h2 {\n" +
            "            border: none !important;\n" +
            "        }\n" +
            "\n" +
            "        h2 {\n" +
            "            font-size: 24px;\n" +
            "            border-bottom: 1px solid #ddd;\n" +
            "            font-weight: 400;\n" +
            "        }\n" +
            "\n" +
            "        h3 {\n" +
            "            font-size: 20px;\n" +
            "        }\n" +
            "\n" +
            "        h4 {\n" +
            "            font-size: 16px;\n" +
            "        }\n" +
            "\n" +
            "        h5 {\n" +
            "            font-size: 14px;\n" +
            "        }\n" +
            "\n" +
            "        h6 {\n" +
            "            font-size: 14px;\n" +
            "        }\n" +
            "\n" +
            "        p,\n" +
            "        blockquote,\n" +
            "        ul,\n" +
            "        ol,\n" +
            "        dl,\n" +
            "        table,\n" +
            "        pre {\n" +
            "            margin: 15px 0;\n" +
            "        }\n" +
            "\n" +
            "        hr {\n" +
            "            border: none;\n" +
            "            border-top: 1px dashed #ddd;\n" +
            "            height: 1px;\n" +
            "            padding: 0;\n" +
            "            margin: 30px 0;\n" +
            "        }\n" +
            "\n" +
            "        ul,\n" +
            "        ol {\n" +
            "            padding-left: 30px;\n" +
            "        }\n" +
            "\n" +
            "        ul {\n" +
            "            list-style-type: disc;\n" +
            "        }\n" +
            "\n" +
            "        ol {\n" +
            "            list-style-type: decimal;\n" +
            "        }\n" +
            "\n" +
            "        ul.no-list,\n" +
            "        ol.no-list {\n" +
            "            list-style-type: none;\n" +
            "            padding: 0;\n" +
            "        }\n" +
            "\n" +
            "        ul ul,\n" +
            "        ul ol,\n" +
            "        ol ol,\n" +
            "        ol ul {\n" +
            "            margin-top: 0;\n" +
            "            margin-bottom: 0;\n" +
            "        }\n" +
            "\n" +
            "        dl {\n" +
            "            padding: 0;\n" +
            "        }\n" +
            "\n" +
            "        dl dt {\n" +
            "            font-size: 14px;\n" +
            "            font-weight: bold;\n" +
            "            font-style: italic;\n" +
            "            padding: 0;\n" +
            "            margin-top: 15px;\n" +
            "        }\n" +
            "\n" +
            "        dl dd {\n" +
            "            margin-bottom: 15px;\n" +
            "            padding: 0 15px;\n" +
            "        }\n" +
            "\n" +
            "        blockquote {\n" +
            "            border-left: 4px solid #dadfe6;\n" +
            "            padding: 10px 13px;\n" +
            "            color: #dadfe6;\n" +
            "            background: #f5f7fa;\n" +
            "        }\n" +
            "\n" +
            "        blockquote > :first-child {\n" +
            "            margin-top: 0px;\n" +
            "        }\n" +
            "\n" +
            "        blockquote > :last-child {\n" +
            "            margin-bottom: 0px;\n" +
            "        }\n" +
            "\n" +
            "        .markdown-table {\n" +
            "            width: 100%;\n" +
            "            overflow: auto;\n" +
            "            margin: 15px 0 !important;\n" +
            "        }\n" +
            "        .markdown-table table {\n" +
            "            margin: 0;\n" +
            "        }\n" +
            "\n" +
            "        table {\n" +
            "            width: 100%;\n" +
            "            overflow: auto;\n" +
            "            border-collapse: collapse;\n" +
            "        }\n" +
            "        table th {\n" +
            "            font-weight: bold;\n" +
            "            background: #dadfe6;\n" +
            "            border-top: 1px solid #dadfe6;\n" +
            "            color: #202d40;\n" +
            "            border-right: 1px solid #fff;\n" +
            "        }\n" +
            "        table th:first-child {\n" +
            "            border-left: 1px solid #dadfe6;\n" +
            "        }\n" +
            "        table th:last-child {\n" +
            "            border-right: 1px solid #dadfe6;\n" +
            "        }\n" +
            "        table th,\n" +
            "        table td {\n" +
            "            padding: 9px 15px;\n" +
            "        }\n" +
            "        table tr {\n" +
            "            background-color: #fff;\n" +
            "        }\n" +
            "        table tr:nth-child(2n) {\n" +
            "            background-color: #f5f7fa;\n" +
            "        }\n" +
            "        table thead tr {\n" +
            "            text-align: left;\n" +
            "        }\n" +
            "        table tbody {\n" +
            "            border: 1px solid #dadfe6;\n" +
            "            color: #606c80;\n" +
            "        }\n" +
            "        table tbody tr {\n" +
            "            color: #0066ff;\n" +
            "        }\n" +
            "\n" +
            "        img {\n" +
            "            max-width: 100%;\n" +
            "            -moz-box-sizing: border-box;\n" +
            "            box-sizing: border-box;\n" +
            "            background: #fff;\n" +
            "            padding: 8px;\n" +
            "            border: 1px solid #eee;\n" +
            "            border-radius: 3px;\n" +
            "        }\n" +
            "\n" +
            "        img.emotion {\n" +
            "            padding: 0;\n" +
            "            border-radius: 0;\n" +
            "            border: none;\n" +
            "            background: none;\n" +
            "        }\n" +
            "\n" +
            "        span.frame {\n" +
            "            display: block;\n" +
            "            overflow: hidden;\n" +
            "        }\n" +
            "\n" +
            "        span.frame > span {\n" +
            "            border: 1px solid #ddd;\n" +
            "            display: block;\n" +
            "            float: left;\n" +
            "            overflow: hidden;\n" +
            "            margin: 13px 0 0;\n" +
            "            padding: 7px;\n" +
            "            width: auto;\n" +
            "        }\n" +
            "\n" +
            "        span.frame span img {\n" +
            "            display: block;\n" +
            "            float: left;\n" +
            "        }\n" +
            "\n" +
            "        span.frame span span {\n" +
            "            clear: both;\n" +
            "            color: #333;\n" +
            "            display: block;\n" +
            "            padding: 5px 0 0;\n" +
            "        }\n" +
            "\n" +
            "        span.align-center {\n" +
            "            display: block;\n" +
            "            overflow: hidden;\n" +
            "            clear: both;\n" +
            "        }\n" +
            "\n" +
            "        span.align-center > span {\n" +
            "            display: block;\n" +
            "            overflow: hidden;\n" +
            "            margin: 13px auto 0;\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "\n" +
            "        span.align-center span img {\n" +
            "            margin: 0 auto;\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "\n" +
            "        span.align-right {\n" +
            "            display: block;\n" +
            "            overflow: hidden;\n" +
            "            clear: both;\n" +
            "        }\n" +
            "\n" +
            "        span.align-right > span {\n" +
            "            display: block;\n" +
            "            overflow: hidden;\n" +
            "            margin: 13px 0 0;\n" +
            "            text-align: right;\n" +
            "        }\n" +
            "\n" +
            "        span.align-right span img {\n" +
            "            margin: 0;\n" +
            "            text-align: right;\n" +
            "        }\n" +
            "\n" +
            "        span.float-left {\n" +
            "            display: block;\n" +
            "            margin-right: 13px;\n" +
            "            overflow: hidden;\n" +
            "            float: left;\n" +
            "        }\n" +
            "\n" +
            "        span.float-left span {\n" +
            "            margin: 13px 0 0;\n" +
            "        }\n" +
            "\n" +
            "        span.float-right {\n" +
            "            display: block;\n" +
            "            margin-left: 13px;\n" +
            "            overflow: hidden;\n" +
            "            float: right;\n" +
            "        }\n" +
            "\n" +
            "        span.float-right > span {\n" +
            "            display: block;\n" +
            "            overflow: hidden;\n" +
            "            margin: 13px auto 0;\n" +
            "            text-align: right;\n" +
            "        }\n" +
            "\n" +
            "        code,\n" +
            "        tt {\n" +
            "            margin: 0;\n" +
            "            padding: 2px 0;\n" +
            "            background-color: #f8f8f8;\n" +
            "            border-radius: 2px;\n" +
            "            vertical-align: middle;\n" +
            "            white-space: normal;\n" +
            "            font-family: Consolas, \"Liberation Mono\", Menlo, Courier, monospace;\n" +
            "            font-size: 13px;\n" +
            "        }\n" +
            "\n" +
            "        code:before,\n" +
            "        code:after,\n" +
            "        tt:before,\n" +
            "        tt:after {\n" +
            "            content: \" \";\n" +
            "            letter-spacing: -0.2em;\n" +
            "        }\n" +
            "\n" +
            "        del code {\n" +
            "            text-decoration: inherit;\n" +
            "            vertical-align: text-top;\n" +
            "        }\n" +
            "\n" +
            "        .highlight pre,\n" +
            "        pre {\n" +
            "            background-color: #f5f7fa;\n" +
            "            padding: 10px 16px;\n" +
            "            border-radius: 3px;\n" +
            "            max-height: 500px;\n" +
            "            overflow: auto;\n" +
            "            font-family: Consolas, \"Liberation Mono\", Menlo, Courier, monospace;\n" +
            "        }\n" +
            "\n" +
            "        pre > code {\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            white-space: pre;\n" +
            "            border: none;\n" +
            "            font-size: 13px;\n" +
            "            line-height: 1.5em;\n" +
            "        }\n" +
            "\n" +
            "        pre code,\n" +
            "        pre tt {\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            background-color: transparent;\n" +
            "            border: none;\n" +
            "            word-wrap: normal;\n" +
            "            max-width: initial;\n" +
            "            display: inline;\n" +
            "            overflow: initial;\n" +
            "        }\n" +
            "\n" +
            "        pre code:before,\n" +
            "        pre code:after,\n" +
            "        pre tt:before,\n" +
            "        pre tt:after {\n" +
            "            content: normal;\n" +
            "        }\n" +
            "\n" +
            "        .hljs {\n" +
            "            background: inherit;\n" +
            "        }\n" +
            "\n" +
            "        .three_wrapper img.emotion.monkey {\n" +
            "            width: 31%;\n" +
            "            margin: 1%;\n" +
            "        }\n" +
            "\n" +
            "        .two_wrapper img.emotion.monkey {\n" +
            "            width: 46%;\n" +
            "            margin: 2%;\n" +
            "        }\n" +
            "\n" +
            "        .task-list {\n" +
            "            list-style-type: none;\n" +
            "            padding-left: 10px;\n" +
            "        }\n" +
            "\n" +
            "        .task-list-item label {\n" +
            "            font-weight: normal;\n" +
            "        }\n" +
            "\n" +
            "        .task-list-item + .task-list-item {\n" +
            "            margin-top: 3px;\n" +
            "        }\n" +
            "\n" +
            "        .markdown-toc {\n" +
            "            margin: 1em !important;\n" +
            "            margin-top: 0;\n" +
            "        }\n" +
            "\n" +
            "        .markdown-toc ul {\n" +
            "            list-style-type: none;\n" +
            "            margin: 0;\n" +
            "            padding: 0 !important;\n" +
            "            line-height: 1.5em;\n" +
            "        }\n" +
            "\n" +
            "        .markdown-toc > ul ul {\n" +
            "            padding-left: 20px !important;\n" +
            "        }\n" +
            "\n" +
            "        .code-block {\n" +
            "            position: relative;\n" +
            "        }\n" +
            "        .code-block:hover .copy-btn {\n" +
            "            display: block;\n" +
            "        }\n" +
            "\n" +
            "        .copy-btn {\n" +
            "            display: none;\n" +
            "            position: absolute;\n" +
            "            top: 0;\n" +
            "            right: 0;\n" +
            "            cursor: pointer;\n" +
            "        }\n" +
            "        .wiki-title {\n" +
            "            margin: 0;\n" +
            "            font-size: 18px;\n" +
            "            font-weight: 400;\n" +
            "            width: 100%;\n" +
            "            overflow: hidden;\n" +
            "            -o-text-overflow: ellipsis;\n" +
            "            text-overflow: ellipsis;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div class=\"wiki-title\">###title###</div>\n" +
            "<div>###content###</div>\n" +
            "</body>\n" +
            "</html>";

}
