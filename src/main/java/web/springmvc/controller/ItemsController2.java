package web.springmvc.controller;

import org.springframework.web.HttpRequestHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import web.springmvc.model.Items;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xus
 * @Description:
 * @Date: Created in 下午 6:50 2018-03-10
 */
public class ItemsController2 implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
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

        // 设置模型数据
        httpServletRequest.setAttribute("itemsList",itemsList);
        //返回ModelAndView
        //ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("itemsList",itemsList);
        /** 指定视图 **/
        //modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/items/itemsList.jsp").forward(httpServletRequest,httpServletResponse);
        /** 这种方式可以返回我们想要的数据格式 比如json**/

    }
}
