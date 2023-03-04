package com.ahao.collect.controller;

import com.ahao.collect.service.CollectService;
import com.ahao.param.CollectParam;
import com.ahao.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 收藏服务控制器
 * @Author: ahao
 * @Date: 2023/2/8 16:12
 **/

@RestController
@RequestMapping("collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * @Description: 添加收藏
    **/
    @PostMapping("save")
    public R save(@RequestBody CollectParam collectParam){
        return collectService.save(collectParam);
    }

    /**
     * @Description: 查看收藏
    **/
    @PostMapping("list")
    public R list(@RequestBody CollectParam collectParam){
        return collectService.list(collectParam);
    }

    @PostMapping("remove")
    public R removeOne(@RequestBody CollectParam collectParam){
        return collectService.remove(collectParam);
    }

}
