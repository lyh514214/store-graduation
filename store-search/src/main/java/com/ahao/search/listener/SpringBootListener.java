package com.ahao.search.listener;

import com.ahao.clients.ProductClient;
import com.ahao.doc.ProductDoc;
import com.ahao.pojo.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 监听springboot启动，同步es库中的数据
 * @Author: ahao
 * @Date: 2023/1/7 16:56
 **/

@Component
@Slf4j
public class SpringBootListener implements ApplicationRunner {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ProductClient productClient;

    private String createIndex = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"productId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productName\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"categoryId\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productTitle\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"productIntro\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"ik_smart\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"productPicture\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"productPrice\":{\n" +
            "        \"type\": \"double\",\n" +
            "        \"index\": true\n" +
            "      },\n" +
            "      \"productSellingPrice\":{\n" +
            "        \"type\": \"double\"\n" +
            "      },\n" +
            "      \"productNum\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"productSales\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";



    /**
     * @Description: 在此方法里完成es库数据同步
     * 1、判断es中的product索引是否存在
     *    存在：  删除原来数据
     *    不存在：   使用java创建索引
     * 2、查询所有数据
     * 3、插入es库中
    **/
    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Product> productList = productClient.getProductList();

        //  1. 判断索引是否存在
        GetIndexRequest request = new GetIndexRequest("product");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

        //  2. 判断处理
        if (!exists){
            //创建索引
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("product");
            createIndexRequest.source(createIndex, XContentType.JSON);
            client.indices().create(createIndexRequest,RequestOptions.DEFAULT);
        }
        //删除数据
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest("product");
        deleteByQueryRequest.setQuery(QueryBuilders.matchAllQuery());
        client.deleteByQuery(deleteByQueryRequest,RequestOptions.DEFAULT);
        //更新数据
        BulkRequest bulkRequest = new BulkRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        for (Product product : productList){
            ProductDoc productDoc = new ProductDoc(product);
            IndexRequest indexRequest = new IndexRequest("product").id(productDoc.getProductId().toString());
            indexRequest.source(objectMapper.writeValueAsString(productDoc),XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        client.bulk(bulkRequest,RequestOptions.DEFAULT);
        log.info("更新es库已完成");
    }
}
