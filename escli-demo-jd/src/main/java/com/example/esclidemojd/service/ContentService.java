package com.example.esclidemojd.service;

import com.alibaba.fastjson.JSON;
import com.example.esclidemojd.entity.Content;
import com.example.esclidemojd.util.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.ml.EvaluateDataFrameRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ContentService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public Boolean parseKeyWords(String keywords) throws IOException {
        List<Content> contents = HtmlParseUtil.parseRequest(keywords);

        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        for (Content content : contents) {
            bulkRequest.add(new IndexRequest("jd_goods").source(JSON.toJSONString(content), XContentType.JSON));
        }

        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }

    public List<Map<String, Object>> searchPage(String keyword, int pageNo, int pageSize) throws IOException {
        if(pageNo <= 1){
            pageNo = 1;
        }
        // 结果
        ArrayList<Map<String, Object>> list = new ArrayList<>();

        // SearchRequest
        SearchRequest request = new SearchRequest("jd_goods");

        // 设置查询条件与查询的方式，范围等
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // TermQueryBuilder
        // 精确查询Builder
        TermQueryBuilder title = new TermQueryBuilder("title", keyword);

        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(false); //多个高亮
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");


        // 设置builder
        searchSourceBuilder.query(title);
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);
        searchSourceBuilder.highlighter(highlightBuilder);

        //
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        request.source(searchSourceBuilder);

        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);


        for (SearchHit documentFields : response.getHits().getHits()) {

            Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
            HighlightField titleField = highlightFields.get("title");

            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap(); // 原结果

            // 解析高亮字段，将原来的字段换成高亮字段
            if (null != titleField){
                Text[] fragments = titleField.fragments();
                String n_title = "";
                for (Text fragment : fragments) {
                    n_title += fragment;
                }
                sourceAsMap.put("title", n_title); // 高亮字段替换掉原来的内容
            }

            list.add(sourceAsMap);
        }

        return list;
    }
}
