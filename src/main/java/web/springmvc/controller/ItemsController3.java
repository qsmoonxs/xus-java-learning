package web.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.springmvc.model.Items;

import javax.jws.WebParam;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xus
 * @Description: 注解开发handler
 * @Date: Created in 上午 10:51 2018-03-11
 */
@Controller
public class ItemsController3 {

    @RequestMapping("/queryItemsAnnotation")
    public ModelAndView queryItems() {
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
        modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        return modelAndView;
    }
}
