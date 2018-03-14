package web.springmvc.controller;

import org.springframework.core.convert.converter.Converter;
import web.springmvc.model.User;

/**
 * @Author: xus
 * @Description:
 * @Date: Created in 下午 9:47 2018-03-14
 */
public class StringToUserConverter implements Converter<String,User> {

    @Override
    public User convert(String s) {
        return null;
    }
}
