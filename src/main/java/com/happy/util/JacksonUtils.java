package com.happy.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description: JacksonUtil
 * 参考http://blog.csdn.net/xiong9999/article/details/53781695
 * @author jibingsai
 * @date 2022/2/9 18:40
 * @version 1.0
 */
@Slf4j
@Service
public class JacksonUtils {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 将json文件转换成JavaBean对象
     * <pre>
     *     String filePath = "D:\\InteliJ\\wallet-app-restfull-apis\\app-wallet\\src\\main\\resources\\Application.json";
     *     //比如：类型为Map<String,Application>对象，可以写成：
     *     JavaType javaType = TypeFactory.defaultInstance().constructMapType(Map.class,String.class, Application.class);
     *     Map map = JacksonUtil.fromJson(filePath,javaType,"yyyy-MM-dd HH:mm:ss.SSS",true)
     * </pre>
     *
     * @param jsonFilePath json文件
     * @param javaType     <pre>
     *                                                                            // 比如：类型为Map<String,Application>对象，可以写成：
     *                                                                            TypeFactory.defaultInstance().constructMapType(Map.class,String.class, Application.class)
     *                                                                        </pre>
     * @return JavaBean
     */
    public <T> T fromJson(String jsonFilePath, JavaType javaType) {

        T javaBean = null;
        if (null == jsonFilePath || jsonFilePath.isEmpty()) {
            return javaBean;
        } else {

            File file = new File(jsonFilePath);

            // 从json映射到java对象，得到JavaBean对象后就可以遍历查找,下面遍历部分内容，能说明问题就可以了
            try {
                javaBean = objectMapper.readValue(file, javaType);
            } catch (JsonParseException e) {
                log.error(e.getMessage());
            } catch (JsonMappingException e) {
                log.error(e.getMessage());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return javaBean;
    }

    /**
     * 将json文件转换成JavaBean对象
     * <pre>
     *     String filePath = "D:\\InteliJ\\wallet-app-restfull-apis\\app-wallet\\src\\main\\resources\\Application.json";
     *     Map map = JacksonUtil.fromJson(filePath,Map.class);
     * </pre>
     *
     * @param jsonFilePath json文件
     * @param clazz
     * @return JavaBean
     */
    public <T> T fromJson(String jsonFilePath, Class<T> clazz) {

        T javaBean = null;
        if (null == jsonFilePath || jsonFilePath.isEmpty()) {
            return javaBean;
        } else {

            File file = new File(jsonFilePath);

            // 从json映射到java对象，得到JavaBean对象后就可以遍历查找,下面遍历部分内容，能说明问题就可以了
            try {
                javaBean = objectMapper.readValue(file, clazz);
            } catch (JsonParseException e) {
                log.error(e.getMessage());
            } catch (JsonMappingException e) {
                log.error(e.getMessage());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return javaBean;
    }


    /**
     * 将json字符串转换成JavaBean对象
     *
     * @param json     字符串
     * @param javaType <pre>
     *                 比如：类型为Map<String,Application>对象，可以写成：
     *                 TypeFactory.defaultInstance().constructMapType(Map.class,String.class, Application.class)
     *                 </pre>
     * @return JavaBean
     */
    public <T> T fromJson2(String json, JavaType javaType) {
        T javaBean = null;
        if (null == json || json.isEmpty()) {
            return javaBean;
        }

        try {
            javaBean = objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return javaBean;
    }

    /**
     * 将json字符串转换成JavaBean对象
     *
     * @param json  字符串
     * @param clazz
     * @return JavaBean
     */
    public <T> T fromJson2(String json, Class<T> clazz) {
        T javaBean = null;
        if (null == json || json.isEmpty()) {
            return javaBean;
        }

        try {
            javaBean = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return javaBean;
    }

    /**
     * 将json字符串转换成JavaBean对象
     *
     * @param json  字符串
     * @param typeReference
     * @return JavaBean
     */
    public <T> T fromJson2(String json, TypeReference<T> typeReference) {
        T javaBean = null;
        if (null == json || json.isEmpty()) {
            return javaBean;
        }

        try {
            javaBean = objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return javaBean;
    }


    public  <E> List<E> json2list(String json, Class<E> eClass) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, eClass));
        } catch (IOException var3) {
            log.error("json转List出错：" + json, var3);
            return null;
        }
    }

    public  <K, V> Map<K, V> json2map(String json, Class<K> kClass, Class<V> vClass) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
        } catch (IOException var4) {
            log.error("json转map出错：" + json, var4);
            return null;
        }
    }

    /**
     * javaBean转换成json文件
     *
     * @param javaBean javaBean对象
     * @param pathName json文件保存路径
     */
    public <T> void toJson(T javaBean, String pathName) {

        if (null == javaBean) {
            return;
        } else if (null == pathName || pathName.isEmpty()) {
            return;
        } else {
            File resultFile = null;
            try {
                resultFile = new File(pathName);
                objectMapper.writeValue(resultFile, javaBean);
            } catch (JsonGenerationException e) {
                log.error(e.getMessage());
            } catch (JsonMappingException e) {
                log.error(e.getMessage());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    /**
     * javaBean转换成json字符串
     *
     * @param javaBean
     * @return String
     */
    public <T> String toJson2(T javaBean) {
        String jsonData = null;

        if (null == javaBean) {
            return jsonData;
        } else {
            try {
                jsonData = objectMapper.writeValueAsString(javaBean);
            } catch (JsonProcessingException e) {
                log.error(e.getMessage());
            }
        }
        return jsonData;
    }
}
