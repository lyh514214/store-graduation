package com.ahao.admin.controller;

import com.ahao.clients.CategoryClient;
import com.ahao.pojo.Category;
import com.ahao.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 视图跳转器
 * @Author: ahao
 * @Date: 2023/3/1 23:29
 **/

@Controller
@Slf4j
public class ViewController {

    @Autowired
    private CategoryClient categoryClient;

    /**
     * @Description: 跳转到 登录页 login
    **/
    @GetMapping({"/", "/index", "/index.html"})
    public String welcome(){
        log.info("跳转到登录页面！");
        return "login";
    }

    /**
     * @Description: 登录成功跳转到 index
    **/
    @GetMapping("/home")
    public String home(){
        log.info("跳转到主页！");
        return "index";
    }

    /**
     * @Description: 跳转到用户管理页面
    **/
    @GetMapping("/user")
    public String user(){
        log.info("跳转到用户管理页面！");
        return "user/user";
    }

    /**
     * @Description: 商品管理页面
    **/
    @GetMapping("/product")
    public String product(){
        log.info("跳转到商品管理页面！");
        return "product/product";
    }

    /**
     * @Description: 类别管理页面
    **/
    @GetMapping("/category")
    public String category(){
        log.info("跳转到商品管理页面！");
        return "category/category";
    }

    /**
     * @Description: 订单管理页面
     * @return java.lang.String
    **/
    @GetMapping("/order")
    public String order(){
        log.info("跳转到订单管理页面！");
        return "order/order";
    }

    /**
     * @Description: 用户编辑页面
    **/
    @GetMapping("/user/update/html")
    public String userUpdateHtml(){
        log.info("跳转到用户编辑页面！");
        return "user/edit";
    }

    /**
     * @Description: 用户添加页面
    **/
    @GetMapping("/user/save/html")
    public String userSaveHtml(){
        log.info("跳转到用户添加页面！");
        return "user/add";
    }

    /**
     * @Description: 商品编辑页面
    **/
    @GetMapping("/product/update/html")
    public String productUpdateHtml(Model model){
        List<Category> categoryList = categoryClient.clistByAdmin();
        model.addAttribute("clist",categoryList);
        log.info("跳转到商品编辑页面！");
        return "product/edit";
    }

    /**
     * @Description: 商品添加页面
    **/
    @GetMapping("/product/save/html")
    public String productSaveHtml(Model model){
        List<Category> categoryList = categoryClient.clistByAdmin();
        model.addAttribute("clist",categoryList);
        log.info("跳转到商品添加页面！");
        return "product/add";
    }

    /**
     * @Description: 类别编辑页面
    **/
    @GetMapping("/category/update/html")
    public String categoryUpdateHtml(){
        log.info("跳转到类别编辑页面！");
        return "category/edit";
    }

    /**
     * @Description: 类别添加页面
    **/
    @GetMapping("/category/save/html")
    public String categorySaveHtml(){
        log.info("跳转到类别添加页面！");
        return "category/add";
    }



}
