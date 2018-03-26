package base.util.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by wangqiyun on 2017/6/3.
 */
public class HttpHandler {

    public static final int maxTotal = 200;
    public static final int maxPerRoute = 50;
    public static final int socketTimeout = 4000;
    public static final int connectTimeout = 4000;
    public static final int connectionRequestTimeout = 4000;


    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
    private static RequestConfig requestConfig;

    static {
        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory
                .getSocketFactory();
        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory
                .getSocketFactory();
        Registry<ConnectionSocketFactory> registry = RegistryBuilder
                .<ConnectionSocketFactory>create().register("http", plainsf)
                .register("https", sslsf).build();
        poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(registry);
        poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);

        requestConfig = RequestConfig.custom().setConnectionRequestTimeout(
                connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(
                connectTimeout).build();
    }

    private static final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).setDefaultRequestConfig(requestConfig).build();

    public static HttpResponse execute(HttpRequest request) throws Exception {
        try {
            HttpUriRequest httpUriRequest;
            if (HttpRequest.METHOD_GET.equals(request.getMethod())) {
                URI uri;
                if (request.getQueryMap() != null && !request.getQueryMap().isEmpty()) {
                    List<NameValuePair> list = new ArrayList<>();
                    for (Map.Entry<String, String> entry : request.getQueryMap().entrySet()) {
                        if (entry.getValue() != null)
                            list.add(new NameValuePairImp(entry.getKey(), entry.getValue()));
                    }
                    uri = new URIBuilder(new URI(request.getUrl())).setCharset(Charset.forName("utf8")).addParameters(list).build();
                } else if (!StringUtils.isEmpty(request.getQueryString())) {
                    uri = new URI(request.getUrl() + "?" + request.getQueryString());
                } else
                    uri = new URI(request.getUrl());
                HttpGet httpGet = new HttpGet(uri);
                for (Map.Entry<String, String> entry : request.getRequestHeader().entrySet()) {
                    httpGet.addHeader(entry.getKey(), entry.getValue());
                }
                httpUriRequest = httpGet;

            } else if (HttpRequest.METHOD_POST.equals(request.getMethod())) {
                URI uri = new URI(request.getUrl());
                HttpPost httpPost = new HttpPost(uri);
                if (request.getQueryMap() != null && !request.getQueryMap().isEmpty()) {
                    List<NameValuePair> list = new ArrayList<>();
                    for (Map.Entry<String, String> entry : request.getQueryMap().entrySet()) {
                        if (entry.getValue() != null)
                            list.add(new NameValuePairImp(entry.getKey(), entry.getValue()));
                    }
                    httpPost.setEntity(new UrlEncodedFormEntity(list, Charset.forName("utf8")));
                } else {
                    boolean no_content = false;
                    EntityBuilder entityBuilder = EntityBuilder.create();
                    if (!StringUtils.isEmpty(request.getQueryString())) {
                        entityBuilder.setText(request.getQueryString());
                        entityBuilder.setContentType(ContentType.create("text/plain", Charset.forName("utf8")));
                    } else if (request.getBinary() != null)
                        entityBuilder.setBinary(request.getBinary());
                    else if (request.getStream() != null)
                        entityBuilder.setStream(request.getStream());
                    else if (request.getFile() != null)
                        entityBuilder.setFile(request.getFile());
                    else {
                        no_content = true;
                    }

                    if (!no_content) {
                        if (request.getContentType() != null)
                            entityBuilder.setContentType(request.getContentType());
                        httpPost.setEntity(entityBuilder.build());
                    }
                }
                for (Map.Entry<String, String> entry : request.getRequestHeader().entrySet()) {
                    httpPost.addHeader(entry.getKey(), entry.getValue());
                }
                httpUriRequest = httpPost;
            } else if (HttpRequest.METHOD_PUT.equals(request.getMethod())) {
                URI uri = new URI(request.getUrl());
                HttpPut httpPut = new HttpPut(uri);
                if (request.getQueryMap() != null && !request.getQueryMap().isEmpty()) {
                    List<NameValuePair> list = new ArrayList<>();
                    for (Map.Entry<String, String> entry : request.getQueryMap().entrySet()) {
                        if (entry.getValue() != null)
                            list.add(new NameValuePairImp(entry.getKey(), entry.getValue()));
                    }
                    httpPut.setEntity(new UrlEncodedFormEntity(list, Charset.forName("utf8")));
                } else {
                    EntityBuilder entityBuilder = EntityBuilder.create();
                    if (!StringUtils.isEmpty(request.getQueryString())) {
                        entityBuilder.setText(request.getQueryString());
                        entityBuilder.setContentType(ContentType.create("text/plain", Charset.forName("utf8")));
                    } else if (request.getBinary() != null)
                        entityBuilder.setBinary(request.getBinary());
                    else if (request.getStream() != null)
                        entityBuilder.setStream(request.getStream());
                    else if (request.getFile() != null)
                        entityBuilder.setFile(request.getFile());
                    if (request.getContentType() != null)
                        entityBuilder.setContentType(request.getContentType());
                    httpPut.setEntity(entityBuilder.build());
                }
                for (Map.Entry<String, String> entry : request.getRequestHeader().entrySet()) {
                    httpPut.addHeader(entry.getKey(), entry.getValue());
                }
                httpUriRequest = httpPut;
            } else throw new RuntimeException();

            HttpResponse httpResponse = new HttpResponse();
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpUriRequest);
                if (response == null) throw new HttpException();
                httpResponse.setResponseHeaders(response.getAllHeaders());
                HttpEntity entity = response.getEntity();
                httpResponse.setByteResult(EntityUtils.toByteArray(entity));
                EntityUtils.consumeQuietly(entity);
                httpResponse.setResponseHeaders(response.getAllHeaders());
            } finally {
                if (response != null)
                    response.close();
            }
            return httpResponse;
        } catch (Exception e) {
            throw e;
        }
    }
}