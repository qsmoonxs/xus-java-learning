package base.util.http;

/**
 * 
 * @author xus
 * 2017年11月9日下午7:47:35
 * HttpUtilTest.java
 * HttpUtil的使用方法
 */
public class HttpUtilTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		HttpRequest httpRequest = new HttpRequest().setMethod(HttpRequest.METHOD_POST).setUrl("http://www.baidu.com")
                .setQueryMap(MapBuilder.Map().put("a", "b").put("c", "d").build());
        HttpResponse httpResponse = HttpHandler.execute(httpRequest);
        String result = httpResponse.getStringResult();

	}

}
