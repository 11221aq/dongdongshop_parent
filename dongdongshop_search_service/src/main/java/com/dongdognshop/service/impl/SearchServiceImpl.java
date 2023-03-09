package com.dongdognshop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dongdognshop.service.SearchService;
import com.dongdognshop.vo.ItemVO;
import com.dongdongshop.util.PageParms;
import com.dongdongshop.util.PageUtil;
import com.dongdongshop.util.SearchParms;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestHighLevelClient client;

    @Override
    public PageUtil<ItemVO> searchList(PageParms pageParms) {
        SearchRequest request = new SearchRequest("dongdongshop_es");
        //String title = "123";
        if(StringUtils.isEmpty(pageParms.getTitle())){
            request.source().query(QueryBuilders.matchAllQuery());
        }else{
            String title = pageParms.getTitle();/**/
            MultiMatchQueryBuilder builder = QueryBuilders.multiMatchQuery(title, "title", "category");
            request.source().query(builder)
                    .highlighter(new HighlightBuilder()
                            .field("title")
                            .requireFieldMatch(false)
                            .preTags("<font color='red'>")
                            .postTags("</font>"));
        }
        Integer page = (pageParms.getPageNum() - 1) * pageParms.getPageSize();
        Integer pageSize = pageParms.getPageSize();
        request.source().from(page).size(pageSize);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            PageUtil<ItemVO> pageUtil = new PageUtil<>();
            pageUtil.setPageNum(pageParms.getPageNum());
            pageUtil.setPageSize(pageParms.getPageSize());
            return handleResponse(response,pageUtil);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private PageUtil<ItemVO> handleResponse(SearchResponse response,PageUtil<ItemVO> pageUtil) {

        SearchHits hits = response.getHits();
        //获取总条数
        long total = hits.getTotalHits().value;
        //新建集合存放数据
        List<ItemVO> list = new ArrayList<>();
        //文档数组
        for (SearchHit hit : hits.getHits()) {
            //获取文档source
            String source = hit.getSourceAsString();
            ItemVO itemVO = JSONObject.parseObject(source, ItemVO.class);
            //查看是否高亮显示
            Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();
            if(!highlightFieldMap.isEmpty()){
                //根据字段获取高亮显示内容
                HighlightField title = highlightFieldMap.get("title");
                if(StringUtils.isNotBlank(title.getFragments()[0].string())){
                    String string = title.getFragments()[0].string();
                    itemVO.setTitle(string);
                }
            }
            //放入集合
            list.add(itemVO);
        }
        //allCount % pageSize == 0 ? allCount / pageSize :allCount / pageSize + 1;
        pageUtil.setTotal(total);
        pageUtil.setRows(list);
        return pageUtil;
    }

}
