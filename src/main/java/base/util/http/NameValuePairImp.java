package base.util.http;


import org.springframework.util.StringUtils;

public class NameValuePairImp implements org.apache.http.NameValuePair {
    private String name;
    private String value;

    public NameValuePairImp(String name, String value) {
        this.name = name;
        this.value = value;
        if (StringUtils.isEmpty(name) || value == null)
            throw new RuntimeException();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }
}
