package com.ecommerce.gateway.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengzhiqing on 2018/6/18.
 */
public class RequestLogging {

    public static String logRequest(HttpServletRequest request) {

        StringBuilder sb = new StringBuilder(700);
        sb.append("\n");
        sb.append(request.getMethod());
        sb.append(" ");
        sb.append(request.getRequestURL().toString());
        sb.append(" ");
        sb.append(request.getProtocol());
        sb.append("\n\n");

        Enumeration<String> e = request.getHeaderNames();
        while (e.hasMoreElements()) {
            String name = e.nextElement();
            String value = request.getHeader(name);
            sb.append(name + ": " + value + "\n");
        }
        sb.append("\n");

        Map<String, String> paramMap = getParameterMap(request);
        parseParamMap(paramMap, sb);
        sb.append("\n");

        return sb.toString();
    }

    /**
     *
     * @param request
     * @return
     */
    private static Map<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String[]> properties = request.getParameterMap();
        Map<String, String> returnMap = new HashMap<String, String>(properties.size());
        String name = "";
        String value = "";

        for (Map.Entry<String, String[]> entry : properties.entrySet()) {
            name = entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                if (value.length() == 0) {
                    continue;
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }

            returnMap.put(name, value);
        }

        return returnMap;
    }

    /**
     *
     * @param map
     * @param sb
     */
    private static void parseParamMap(Map<String, String> map, StringBuilder sb) {
        int count = 0;
        for (Map.Entry<String, String> me : map.entrySet()) {
            if (sb.length() > 0 && count > 0) {
                sb.append("&");
            }
            String key = (String) me.getKey();
            String value = (String) me.getValue();

            //DO NOT log password
            if (key.equalsIgnoreCase("password")) {
                value = "******";
            }
            sb.append(key + "=" + value);
            count++;
        }
    }
}
