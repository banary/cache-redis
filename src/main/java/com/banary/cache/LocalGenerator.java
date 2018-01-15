package com.banary.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Component
public class LocalGenerator implements KeyGenerator {

    private static final String SEPARATOR = ":";

    private static final String DEFAULT_PREFIX = "";

    private String prefix = DEFAULT_PREFIX;

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder();
        if(!StringUtils.isEmpty(prefix)){
            sb.append(prefix);
            sb.append(SEPARATOR);
        }
        //类型名
        sb.append(method.getDeclaringClass().getName());
        sb.append(SEPARATOR);
        //方法名
        sb.append(method.getName());
        sb.append(SEPARATOR);

        if(params==null){
            sb.append(String.valueOf(params));
            return sb.toString();
        }
        for (int i=0; i<params.length; i++) {
            if(i==params.length-1){
                sb.append(String.valueOf(params[i]));
            }else{
                sb.append(String.valueOf(params[i]));
                sb.append(SEPARATOR);
            }
        }
        return sb.toString();
    }

}