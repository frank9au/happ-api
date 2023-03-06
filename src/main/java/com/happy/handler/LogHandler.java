package com.happy.handler;

import com.happy.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
@Order(1)
@Slf4j
public class LogHandler {

    @Resource
    private JacksonUtils jacksonUtils;

    private final static String POINT_CUT = "(execution(* com.happy..*Api.*(..)))";

    @Around(POINT_CUT)
    public Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 提取切面方法参数
        Map<String, Object> params = getJoinPointParams(proceedingJoinPoint, null);
        String requestServletPath = request.getServletPath();
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("请求路径：【{}】,请求参数：【{}】,响应结果：【{}】, 耗时【{}】",
                requestServletPath,
                params.get("params").toString(),
                jacksonUtils.toJson2(result),
                endTime - startTime
        );
        return result;
    }

    /**
     * 异常处理
     * @param point
     * @param e
     */
    @AfterThrowing(value = POINT_CUT, throwing = "e")
    public void afterThrowing(JoinPoint point, Exception e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Map<String, Object> params = getJoinPointParams(null, point);
        log.info("请求异常路径：【{}】,请求参数：【{}】,异常原因：【{}】",
                request.getServletPath(),
                params.get("params").toString(),
                e.getMessage()
        );
    }

    /**
     * 提取切面方法的参数
     * @param proceedingJoinPoint
     * @param point
     * @return
     */
    private Map<String, Object> getJoinPointParams(@Nullable ProceedingJoinPoint proceedingJoinPoint,
                                                   @Nullable JoinPoint point) {
        Map<String, Object> paramsMap = new HashMap<>();
        Object[] args = null;
        if(Objects.nonNull(proceedingJoinPoint)){
            args = proceedingJoinPoint.getArgs();
        }else if(Objects.nonNull(point)){
            args = point.getArgs();
        }
        int argsLength = args.length;
        Object[] arguments = new Object[argsLength];
        if (args != null && argsLength > 0) {
            Map<String, String> dataMap = new HashMap<>();
            for (int i = 0; i < argsLength; i++) {
                if (args[i] instanceof ServletRequest
                        || args[i] instanceof ServletResponse
                        || args[i] instanceof MultipartFile) {
                    continue;
                }
                arguments[i] = args[i];
                dataMap = jacksonUtils.json2map(jacksonUtils.toJson2(arguments[i]), String.class, String.class);
            }
            paramsMap.put("paramsMap", dataMap);
        }
        String params = "";
        if (arguments.length > 0) {
            try {
                params = jacksonUtils.toJson2(arguments);
            } catch (Exception e) {
                params = Arrays.toString(arguments);
            }
        }
        paramsMap.put("params", params);
        return paramsMap;
    }
}
