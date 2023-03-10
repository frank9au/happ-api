package com.happy.util;

import com.happy.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-6-15
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Component
public class HttpUtils {

    @Resource
    private JacksonUtils jacksonUtil;

    /**
     * 发送http post 请求
     * @param sendUrl->请求地址
     * @param params ->请求参数
     * @param charset ->请求参数编码格式
     * @param contentType ->请求参数形式
     * @return
     * @throws Exception
     */
    public String sendHttpPostReq(String sendUrl,
                                         Map<String,String> params,
                                         String charset,
                                         String contentType) throws Exception {
        // 创建URL对象
        URL url = new URL(sendUrl);
        // 创建HttpURLConnection对象
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod(Constants.POST_METHOD);
        httpConn.setDoInput(true);
        httpConn.setDoOutput(true);
        // 设置通用的请求属性
        if("json".equalsIgnoreCase(contentType)) {
            httpConn.setRequestProperty("Content-Type", "application/json");
        }
        httpConn.setConnectTimeout(60000);
        httpConn.setReadTimeout(60000);
        httpConn.connect();
        StringBuffer sb = new StringBuffer();
        if (params != null) {
            if("json".equalsIgnoreCase(contentType)){
                sb.append(jacksonUtil.toJson2(params));
            }else if("xml".equalsIgnoreCase(contentType)){
                for (Map.Entry<String, String> e : params.entrySet()) {
                    sb.append("<");
                    sb.append(e.getKey());
                    sb.append(">");
                    sb.append(e.getValue());
                    sb.append("<");
                    sb.append(e.getKey());
                    sb.append(">");
                }
            }else{
                for (Map.Entry<String, String> e : params.entrySet()) {
                    sb.append(e.getKey());
                    sb.append("=");
                    sb.append(e.getValue());
                    sb.append("&");
                }
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        log.info("sendHttpPostReq: {}", sb.toString());
        DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());
        charset = !StringUtils.hasText(charset)? Constants.CHARSET:charset;
        out.write(sb.toString().getBytes(charset));
        out.flush();
        out.close();
        // 读取返回内容
        StringBuffer buffer = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), charset));
        String temp;
        while ((temp = br.readLine()) != null) {
            buffer.append(temp);
        }
        log.info("sendHttpPostReq->buffer.toString(): {}", buffer.toString());
        return buffer.toString();
    }

    /**
     * 发送http get 请求
     * @param sendUrl->请求地址
     * @param params ->请求参数
     * @param charset ->请求参数编码格式
     * @param contentType ->请求参数形式
     * @return
     * @throws Exception
     */
    public String sendHttpGetReq(String sendUrl,Map<String,String> params,String charset,String contentType) throws Exception {
        StringBuffer sb = new StringBuffer();
        if (params != null) {
            if(contentType.equalsIgnoreCase("json")){
                sb.append(jacksonUtil.toJson2(params));
            }else if(contentType.equalsIgnoreCase("xml")){
                for (Map.Entry<String, String> e : params.entrySet()) {
                    sb.append("<");
                    sb.append(e.getKey());
                    sb.append(">");
                    sb.append(URLEncoder.encode(e.getValue(), charset));
                    sb.append("<");
                    sb.append(e.getKey());
                    sb.append(">");
                }
            }else{
                for (Map.Entry<String, String> e : params.entrySet()) {
                    sb.append(e.getKey());
                    sb.append("=");
                    sb.append(URLEncoder.encode(e.getValue(), charset));
                    sb.append("&");
                }
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        log.info("sendHttpGetReq: {}", sb.toString());
        // 创建URL对象
        URL url = new URL(sendUrl.concat("?").concat(sb.toString()));
        // 创建HttpURLConnection对象
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod(Constants.GET_METHOD);
        httpConn.setConnectTimeout(400000);
        httpConn.setReadTimeout(400000);
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        httpConn.setUseCaches(false);
        httpConn.setRequestProperty(
                "Content-Type","application/".concat(!StringUtils.hasText(contentType)?"x-www-form-urlencoded":contentType));
        httpConn.connect();
        // 读取返回内容
        StringBuffer buffer = new StringBuffer();
        charset = !StringUtils.hasText(charset)? Constants.CHARSET:charset;
        BufferedReader br = new BufferedReader(
                new InputStreamReader(httpConn.getInputStream(), charset));
        String temp;
        while ((temp = br.readLine()) != null) buffer.append(temp);
        log.info("sendHttpGetReq->buffer.toString(): {}", buffer.toString());
        return buffer.toString();
    }
}
