package com.dongdognshop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("dict")
@RefreshScope//随配置文件随时改变的
public class DictController {

    @Value("#{'${test.list:}'.empty ? null : '${test.list:}'.split(',')}")
    private List<String> dictTable;   //判断空 null


    /**
     * 最后修改时间
     */
    private Date lastModified = new Date();

    /**
     * 停用词最后修改时间
     */
    private Date stopLastModified = new Date();

    /**
     * 分词词典表
     */
    //private List<String> dictTable = new ArrayList<>();

    /**
     * 停用词词典表
     */
    private List<String> stopDictTable = new ArrayList<>();


    /**
     * 初始化自定字典，也可以从数据库读取然后进行加载
     */
    @PostConstruct
    public void init() {

    }

    /**
     * 添加词典
     * @param dict 词典
     * @param stop 是否停用词
     * @return
     */
    @PostMapping("addDict")
    public boolean addDict(String dict,Boolean stop) {
        // 判断是否存在，如果不存在则添加
        if (dict != null && dict.trim().length() > 0) {
            // 如果不是停用词则添加到词典
            if(stop == null || !stop && !dictTable.contains(dict)){
                dictTable.add(dict);
                lastModified = new Date();
                return true;
            }else if(stop && !stopDictTable.contains(dict)){
                stopDictTable.add(dict);
                stopLastModified = new Date();
                return true;
            }
        }
        return false;
    }

    /**
     * 删除词典
     * @param dict 词典
     * @param stop 是否停用词
     * @return
     */
    @DeleteMapping("delDict")
    public boolean delDict(String dict,Boolean stop) {
        // 判断是否存在，如果不存在则添加
        if (dict != null && dict.trim().length() > 0) {
            // 如果不是停用词则添加到词典
            if(stop == null || !stop && dictTable.contains(dict)){
                dictTable.remove(dict);
                lastModified = new Date();
                return true;
            }else if(stop && stopDictTable.contains(dict)){
                stopDictTable.remove(dict);
                stopLastModified = new Date();
                return true;
            }
        }
        return false;
    }

    /**
     * 获取字典
     * @param httpServletResponse
     */
    @GetMapping("getCustomDict")
    public void getCustomDict(HttpServletResponse httpServletResponse) throws IOException {
        getDict(null,httpServletResponse);
        httpServletResponse.flushBuffer();
    }

    /**
     * 获取停用字典
     * @param httpServletResponse
     */
    @GetMapping("getCustomStopDict")
    public void getCustomStopDict(HttpServletResponse httpServletResponse) throws IOException {
        getDict(true,httpServletResponse);
        httpServletResponse.flushBuffer();
    }

    /**
     * 获取字典
     * @param stop
     * @param httpServletResponse
     */
    public void getDict(Boolean stop,HttpServletResponse httpServletResponse) {
        Long time = null;
        String eTag = null;
        List<String> dictTable = null;
        if(stop == null || !stop){
            time = lastModified.getTime();
            dictTable = this.dictTable;
            eTag = String.valueOf(this.dictTable.hashCode()) + "-" +String.valueOf(this.dictTable.size());
        }else {
            time = stopLastModified.getTime();
            dictTable = this.stopDictTable;
            eTag = String.valueOf(this.stopDictTable.hashCode()) + "-" +String.valueOf(this.stopDictTable.size());
        }
        httpServletResponse.setDateHeader("Last-Modified",time);
        httpServletResponse.setHeader("ETag", eTag);
        httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        try (OutputStream outputStream = httpServletResponse.getOutputStream();){
            StringBuilder str = new StringBuilder();
            for (String dict : dictTable) {
                str.append(dict);
                str.append("\n");
            }
            outputStream.write(str.toString().getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
