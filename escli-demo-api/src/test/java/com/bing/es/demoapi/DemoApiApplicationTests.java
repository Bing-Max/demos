package com.bing.es.demoapi;

import com.alibaba.fastjson.JSON;
import com.bing.es.demoapi.entity.User;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class DemoApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;

    @Test
    public void testCreated() throws IOException {
        // 创建索引请求
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("kuangshen_index");
        // 客户端执行请求
        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        System.out.println(createIndexResponse);
    }

    @Test
    public void testgetIndex() throws IOException {
        // 测试索引是否存在
        GetIndexRequest getIndexRequest = new GetIndexRequest("test");
        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    void testdelIndex() throws IOException {
        //删除索引
        DeleteIndexRequest kuangshen_index = new DeleteIndexRequest("kuangshen_index");
        AcknowledgedResponse delete = client.indices().delete(kuangshen_index, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    @Test
    void testAddDocument() throws IOException {
        User user = new User("张三",10);
        IndexRequest index = new IndexRequest("kuangshen_index");

        index.id("1");
        index.timeout(TimeValue.timeValueSeconds(1));
        index.timeout("1s");

        // 将数据放入请求
        index.source(JSON.toJSONString(user), XContentType.JSON);
        // 客户端发送请求
        IndexResponse response = client.index(index, RequestOptions.DEFAULT);

        System.out.println(response);
        System.out.println(response.status());
    }

    @Test
    void testExistDocument() throws IOException {
        // 测试文档是否存在
        GetRequest kuangshen_index = new GetRequest("kuangshen_index", "1");
        boolean exists = client.exists(kuangshen_index, RequestOptions.DEFAULT);

        System.out.println(exists);
    }

    @Test
    void testGetDocument() throws IOException{
        // 测试获取文档记录
        GetRequest getRequest = new GetRequest("kuangshen_index", "1");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);

        System.out.println(getResponse.getSourceAsString());
        System.out.println(getResponse);
    }

    @Test
    void testUpdateDocument() throws IOException{
        // 更新文档记录
        UpdateRequest updateRequest = new UpdateRequest("kuangshen_index", "1");
        User user = new User("李四",3);
        updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);

        updateRequest.timeout("1s");

        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println(updateResponse);
    }

    @Test
    void testDelDocument() throws IOException{
        // 删除文档记录
        DeleteRequest deleteRequest = new DeleteRequest("kuangshen_index", "1");
        DeleteResponse deleteResponse = client.delete(deleteRequest, RequestOptions.DEFAULT);
        System.out.println(deleteResponse);
    }

    @Test
    void testBulkRequest() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");

        ArrayList<User>users = new ArrayList<>();
        users.add(new User("kuangshen1",13));
        users.add(new User("kuangshen2",13));
        users.add(new User("kuangshen3",13));
        users.add(new User("qinjiang1",13));
        users.add(new User("qinjiang2",13));
        users.add(new User("qinjiang3",13));

        for (int i = 0; i < users.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("kuangshen_index")
//                            .id(""+(i+1)) // 没有id则 默认生成长随机id
                            .source(JSON.toJSONString(users.get(i)), XContentType.JSON)
            );
        }

        BulkResponse bulkItemResponses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkItemResponses.hasFailures());

    }


    /**
     * public SearchSourceBuilder query(QueryBuilder query) {
     *         this.queryBuilder = query;
     *         return this;
     *     }
     */
    // searchSourceBuilder  构建查询条件
    // QueryBuilders 工具类帮助设置查询条件
    // HighlightBuilder 高亮的构建
    //
    @Test
    void testSearch() throws IOException {
        SearchRequest searchrequest = new SearchRequest("kuangshen_index");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("name");
        searchSourceBuilder.highlighter(highlightBuilder);


        // 生成查询条件
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "qinjiang1");
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "kuangshen");
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "kuangshen");


        // 设置查询条件
//        searchSourceBuilder.query(termQueryBuilder);
//        searchSourceBuilder.query(matchQueryBuilder);
        searchSourceBuilder.query(fuzzyQueryBuilder);   // 模糊查询
        searchSourceBuilder.timeout(new TimeValue(
                60, TimeUnit.SECONDS));

        // request 的 source
        searchrequest.source(searchSourceBuilder);
        searchSourceBuilder.from(1);
        searchSourceBuilder.size(2);// 分页
        SearchResponse search = client.search(searchrequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(search.getHits()));

        for (SearchHit hit : search.getHits().getHits()) {
            System.out.println(hit.getSourceAsMap());
        }
    }

}
