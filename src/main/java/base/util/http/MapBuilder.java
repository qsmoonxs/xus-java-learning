package base.util.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangqiyun on 2017/6/5.
 */
public class MapBuilder {
    private Map<String, String> map = new HashMap<>();

    public static MapBuilder Map() {
        return new MapBuilder();
    }

    public MapBuilder put(String key, String value) {
        map.put(key, value);
        return this;
    }

    public Map<String, String> build() {
        return map;
    }
}
