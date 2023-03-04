package com.ahao.search.service.impl;

import com.ahao.param.ProductSearchParam;
import com.ahao.pojo.Product;
import com.ahao.search.service.SearchService;
import com.ahao.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Description: 搜索服务接口实现类
 * @Author: ahao
 * @Date: 2023/2/3 16:47
 **/
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestHighLevelClient client;

    /**
     * @Description: 通过关键字进行搜索和分页
     *  1、判断关键字是否为空
     *  2、添加分页属性
     *  3、es操作
     *  4、结果处理
    **/
    @Override
    public R searchProduct(ProductSearchParam productSearchParam) {

        SearchRequest searchRequest = new SearchRequest("product");

        String search = productSearchParam.getSearch();

        if (StringUtils.isEmpty(search)){
            //空
            searchRequest.source().query(QueryBuilders.matchAllQuery());
        }else {
            //非空
            searchRequest.source().query(QueryBuilders.matchQuery("all",search));
        }

        //分页操作
        searchRequest.source().from((productSearchParam.getCurrentPage()-1)*productSearchParam.getPageSize());
        searchRequest.source().size(productSearchParam.getPageSize());

        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException("查询超时");
        }

        //抽取数据  R: 总数，数据，信息
        SearchHits hits = searchResponse.getHits();
        long total = hits.getTotalHits().value;

        SearchHit[] hitsHits = hits.getHits();
        ArrayList<Product> productList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();  //json 处理器 ==》json转化为对象
        for (SearchHit hitsHit : hitsHits) {
            String sourceAsString = hitsHit.getSourceAsString();
            Product product = null;
            try {
                product = objectMapper.readValue(sourceAsString, Product.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            productList.add(product);
        }
        log.info("搜索业务完成,共有{}条数据",total);
        return R.ok(null,productList,total);
    }
}
