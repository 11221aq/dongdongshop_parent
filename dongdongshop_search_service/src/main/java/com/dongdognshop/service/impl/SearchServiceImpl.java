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
import org.elasticsearch.search.builder.SearchSourceBuilder;
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
    public PageUtil<ItemVO> searchList(SearchParms searchParms) {
        SearchRequest request = new SearchRequest("dongdongshop_es");
        //String title = "123";
        if(StringUtils.isEmpty(searchParms.getTitle())){//判断title是否为空
            request.source().query(QueryBuilders.matchAllQuery());//为空则查询全部
        }else{//不为空则按照条件查询
            String title = searchParms.getTitle();/**/
            MultiMatchQueryBuilder builder = QueryBuilders.multiMatchQuery(title, "title", "category");

            SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(builder);

            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("title");
            highlightBuilder.requireFieldMatch(false);
            highlightBuilder.preTags("<font color='red'>");
            highlightBuilder.postTags("</font>");

            sourceBuilder.highlighter(highlightBuilder);

            request.source(sourceBuilder);
//            request.source().query(builder)
//                    .highlighter(new HighlightBuilder()
//                            .field("title")
//                            .requireFieldMatch(false)
//                            .preTags("<font color='red'>")
//                            .postTags("</font>"));
        }
        Integer page = (searchParms.getPageNum() - 1) * searchParms.getPageSize();
        Integer pageSize = searchParms.getPageSize();
        request.source().from(page).size(pageSize);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            PageUtil<ItemVO> pageUtil = new PageUtil<>();
            pageUtil.setPageNum(searchParms.getPageNum());
            pageUtil.setPageSize(searchParms.getPageSize());
            return handleResponse(response,pageUtil);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private PageUtil<ItemVO> handleResponse(SearchResponse response,PageUtil<ItemVO> pageUtil) {

        SearchHits hits = response.getHits();//获取查询结果的集合
        //获取总条数
        long total = hits.getTotalHits().value;
        //新建集合存放数据
        List<ItemVO> list = new ArrayList<>();
        //文档数组
        for (SearchHit hit : hits.getHits()) {//遍历查询结果
            //获取文档source
            String source = hit.getSourceAsString();//获取到具体内容
            ItemVO itemVO = JSONObject.parseObject(source, ItemVO.class);//json转对象
            //查看是否高亮显示
            Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();//获取高亮对象
            if(!highlightFieldMap.isEmpty()){//判断高亮对象是否为空
                //根据字段获取高亮显示内容
                HighlightField title = highlightFieldMap.get("title");//不为空获取高亮对象
                if(StringUtils.isNotBlank(title.getFragments()[0].string())){//判断下标为0 的内容是否为空
                    String string = title.getFragments()[0].string();//不为空获取内容
                    itemVO.setTitle(string);//赋值
                }
            }
            //放入集合
            list.add(itemVO);
        }
        //allCount % pageSize == 0 ? allCount / pageSize :allCount / pageSize + 1;
        pageUtil.setTotal(total);
        Long pages = pageUtil.getTotal() / pageUtil.getPageSize() == 0 ? pageUtil.getTotal() / pageUtil.getPageSize() : pageUtil.getTotal() / pageUtil.getPageSize() + 1;
        pageUtil.setPage(pages);
        pageUtil.setRows(list);
        return pageUtil;
    }

}
