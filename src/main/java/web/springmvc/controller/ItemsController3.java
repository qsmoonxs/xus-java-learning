package web.springmvc.controller;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.springmvc.model.Items;
import web.springmvc.model.User;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xus
 * @Description: 注解开发handler
 * @Date: Created in 上午 10:51 2018-03-11
 */
@Controller
public class ItemsController3 {

    @RequestMapping(path = "/queryItemsAnnotation")
    public ModelAndView queryItems(@RequestHeader(name = "Host") String host, HttpServletRequest request) {
        System.out.println(host);
        List<Items> itemsList = new ArrayList<>();
        //向list中填充静态数据
        Items items_1 = new Items();
        items_1.setName("联想笔记本");
        items_1.setPrice(6000f);
        items_1.setDetail("ThinkPad T430 联想笔记本电脑！");

        Items items_2 = new Items();
        items_2.setName("苹果手机");
        items_2.setPrice(5000f);
        items_2.setDetail("iphone6苹果手机！");

        itemsList.add(items_1);
        itemsList.add(items_2);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        modelAndView.setViewName("items/itemsList");
        return modelAndView;
    }

    /**
     * 得用post
     * 用x-www-form-urlencode格式确实可以被FormHttpMessageConverter解析，但参数类型必须是MultiValueMap或者子类 不然解析会失败，
     * @param requestBody
     * @return
     */
    @RequestMapping(value = "/handle41")
    public String handle41(@RequestBody User requestBody){
        System.out.println(requestBody);
        return "items/itemsList";
    }

    @RequestMapping(value = "/handle43")
    public String handle41(@RequestBody LinkedMultiValueMap<String, List<String>> requestBody){
        System.out.println(requestBody);
        return "items/itemsList";
    }

    @RequestMapping(value = "/handle42")
    public String handler43(HttpEntity<String> httpEntity) {
        /** 使用StringHttpMessageConverter将请求报文体和报文头的信息绑定到httpEntity中**/
        Long contentLen = httpEntity.getHeaders().getContentLength();
        System.out.println(httpEntity.getBody());
        return "items/itemsList";

    }


}
