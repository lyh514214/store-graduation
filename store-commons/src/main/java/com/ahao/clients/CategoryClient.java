package com.ahao.clients;

import com.ahao.param.PageParam;
import com.ahao.param.ProductHotParam;
import com.ahao.pojo.Category;
import com.ahao.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description: 模块间服务调用接口
 * product调用category
 * @Author: ahao
 * @Date: 2022/12/13 1:58
 **/
@FeignClient("category-server")
public interface CategoryClient {

    @GetMapping("category/promo/{categoryName}")
    R byName(@PathVariable("categoryName") String categoryName);

    @GetMapping("category/hots")
    R byNameList(ProductHotParam productHotParam);

    @GetMapping("category/list")
    R list();

    @GetMapping("category/clistByAdmin")
    List<Category> clistByAdmin();

    @GetMapping("category/admin/list")
    R CategoryListByPage(@RequestBody PageParam pageParam);

    @PostMapping("category/admin/save")
    R saveByAdmin(@RequestBody Category category);

    @PostMapping("category/admin/remove")
    R removeByAdmin(@RequestBody Integer categoryId);

    @PostMapping("category/admin/update")
    R updateByAdmin(@RequestBody Category category);
}
