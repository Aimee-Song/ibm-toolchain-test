package com.example.demo.controller;

import com.example.demo.bean.Result;
import com.example.demo.exception.CoreException;
import com.example.demo.pojo.WebHookForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
@Slf4j
@Validated
public class DemoController {
    @GetMapping("/")
    public String index() {
        log.info("in");
        return "demo";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "http://e.csl.anchnet.com"+"/api/user/ci/codepecker?"+"url=http://192.168.201.168:9527/code/getPdfReport&auth=123";
    }

    @RequestMapping(value = "/addUser/{projectId}/add", method = RequestMethod.POST)
    public WebHookForm addUser(@PathVariable int projectId,
                               @Valid WebHookForm form,
                               Errors errors) {
        System.out.println(errors);
        return form;
    }

    @GetMapping("/testUrl")
    @ResponseBody
    public String testUrl(@RequestParam String targetUrl) {
        final String urlPatternError = "url_pattern_error";
        final String urlResolveError = "url_resolve_error";
        final String urlReservedError = "url_reserved_error";

        URL url = null;
        try {
            url = new URL(targetUrl);
        } catch (MalformedURLException ignore) {
        }

        if (null == url) {
            return urlPatternError;
        }

        String host = url.getHost();
        if (StringUtils.isEmpty(host)) {
            return urlPatternError;
        }

        try {
            InetAddress address = InetAddress.getByName(host);
            String ip = address.getHostAddress();
            if (StringUtils.isEmpty(ip)) {
                return urlResolveError;
            }
                /*if (IPUtils.isReserved(ip)) {
                    return urlReservedError;
                }*/
        } catch (UnknownHostException e) {
            return urlResolveError;
        }
        return null;
    }

    @RequestMapping(value = {"/user/project/testWebhook"}, method = RequestMethod.POST)
    public Result webhook(HttpServletRequest request) {
        log.info("Come on brother");
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try
        {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        log.info(sb.toString());
        return Result.success("true");
    }

    @PostMapping("/postSourceCode")
    @ResponseBody
    public Map<String, Object> uploadApk(@RequestParam("uploadFile") MultipartFile file,
                            @RequestParam(required = false) String langType,
                            @RequestParam(required = false) String projectId,
                            @RequestParam(required = false) String projectLevel,
                            @RequestParam(required = false) String defectType,
                            @RequestParam(required = false) String auth) throws IOException {

        System.out.println(file.getOriginalFilename()+":"+file.getSize());
        System.out.println(langType +":"+projectId+":"+projectLevel+":"+defectType+":"+auth);
        //获取输入流
        InputStream is = file.getInputStream();
        //获取输出流
        OutputStream os = new FileOutputStream(new File(file.getOriginalFilename()));
        //开始复制
        int i = 0;
        byte[] bytes = new byte[1024];
        while((i = is.read(bytes))!=-1) {
            os.write(bytes, 0, i);
        }
        os.close();
        is.close();
        Map<String, Object> map = new HashMap<>();
        long j = System.currentTimeMillis() %3;
        if(j == 0) {
            map.put("status", 1);
            map.put("errorMsg", "error message");
        }else {
            map.put("status", 0);
            map.put("taskId", "123213");
        }
        map.put("projetcId", 1);
        return map;
    }

    @PostMapping("/queryTaskStatus")
    @ResponseBody
    public Map<String, Object> queryTaskStatus(@RequestParam String taskId,
                            @RequestParam String auth) throws IOException {
        Map<String, Object> map = new HashMap<>();
        long i = System.currentTimeMillis() %7;
        if(i == 0) {
            map.put("status", 0);
            map.put("taskStatus", 0);
            map.put("errorMsg", "error message");
        }else if(i == 1){
            map.put("status", 0);
            map.put("taskStatus", 1);
            map.put("taskId", "123213");
        }else if(i == 2){
            map.put("status", 0);
            map.put("taskStatus", 2);
            map.put("taskId", "123213");
        }else if(i == 3){
            map.put("status", 0);
            map.put("taskStatus", 3);
            map.put("taskId", "123213");
        }else if(i == 4){
            map.put("status", 1);
            map.put("taskStatus", 4);
            map.put("errorMsg", "123213");
        }else if(i == 5 || i == 6){
            map.put("status", 0);
            map.put("taskStatus", 99);
            map.put("taskId", "123213");
        }
        return map;

    }

    @PostMapping("/queryLangType")
    @ResponseBody
    public Map<String, Object> queryLangType(@RequestParam String auth) throws IOException {
        System.out.println("auth:"+auth);
        Map<String, Object> map = new HashMap<>();
        long i = System.currentTimeMillis() %2;
        if(i == 0) {
            map.put("status", 0);
            List<String> list=new ArrayList<>();
            list.add("Java");
            list.add("C");
            map.put("language", list);
        }else {
            map.put("status", 1);
            map.put("errorMsg", "error");
        }
        return map;
    }

    @PostMapping("/queryDefectType")
    @ResponseBody
    public Map<String, Object> queryDefectType(@RequestParam String auth) throws IOException {
        System.out.println("auth:"+auth);
        Map<String, Object> map = new HashMap<>();
        long i = System.currentTimeMillis() %2;
        if(i == 0) {
            map.put("status", 0);
            List<String> list=new ArrayList<>();
            Defect defect = new Defect();
            defect.setId("1");
            defect.setName("跨站脚本问题(XSS)");
            defect.setLanguage("java");
            List<DefectType> types = new ArrayList<>();
            DefectType type = new DefectType();
            type.setId("40");
            type.setCname("跨站脚本");
            type.setSecurity("CP.VUL.XSS");
            types.add(type);
            defect.setDefectType(types);
            map.put("defectTypeOneLevel", list);
        }else {
            map.put("status", 1);
            map.put("errorMsg", "error");
        }
        return map;
    }

    @PostMapping("/queryRuleList")
    @ResponseBody
    public Map<String, Object> queryRuleList(@RequestParam String auth) throws IOException {
        System.out.println("auth:"+auth);
        Map<String, Object> map = new HashMap<>();
        long i = System.currentTimeMillis() %2;
        if(i == 0) {
            map.put("status", 0);
            List<Rule> list=new ArrayList<>();
            Rule rule = new Rule();
            rule.setId("123");
            rule.setLan("JAVA");
            rule.setName("efee3");
            rule.setExtra("CP.VUL.XSS,CP.VUL.XSS.DB,CP.VUL.XSS.REF,CP.JAVA.SQL.Injection");
            list.add(rule);
            map.put("ruleList", list);
        }else {
            map.put("status", 1);
            map.put("errorMsg", "error");
        }
        return map;
    }

    class Rule{
        private String id;
        private String name;
        private String lan;
        private String extra;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLan() {
            return lan;
        }

        public void setLan(String lan) {
            this.lan = lan;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }
    }

    class Defect{
        private String id;
        private String name;
        private String language;
        private List<DefectType> defectType;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public List<DefectType> getDefectType() {
            return defectType;
        }

        public void setDefectType(List<DefectType> defectType) {
            this.defectType = defectType;
        }
    }

    class DefectType{
        private String id;
        private String cname;
        private String levelId;
        private String security;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getLevelId() {
            return levelId;
        }

        public void setLevelId(String levelId) {
            this.levelId = levelId;
        }

        public String getSecurity() {
            return security;
        }

        public void setSecurity(String security) {
            this.security = security;
        }
    }
}
