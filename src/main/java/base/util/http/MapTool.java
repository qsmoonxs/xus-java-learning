package base.util.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangqiyun on 16/5/23.
 */
public class MapTool extends HashMap<String,Object> {
    @Override
    public MapTool put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static MapTool Map() {
        return new MapTool();
    }

    public static MapTool Mapok() {
        MapTool mapTool = new MapTool();
        mapTool.put("state", "0");
        return mapTool;
    }

    public static MapTool Mapoktime() {
        MapTool mapTool = Mapok();
        mapTool.put("time", System.currentTimeMillis());
        return mapTool;
    }

    public static Map<String, Object> stringToMap(String str) {
        return new Gson().fromJson(str, new TypeToken<Map<String, Object>>() {
        }.getType());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

