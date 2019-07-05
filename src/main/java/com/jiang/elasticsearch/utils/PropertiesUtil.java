package com.jiang.elasticsearch.utils;

import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * Created by chenqi on 2015/8/25.
 * Email: 302616601@qq.com
 * edit by zhangzhen 2015-08-31
 */
public class PropertiesUtil {

    private static final Logger LOGGER = Logger.getLogger(PropertiesUtil.class);

    private static Properties property = new Properties(); // 获取配置参数信息

    private PropertiesUtil() {
    }

    static {
        try {
            URL url = getResource("sys.properties");
            property.load(url.openStream());
        } catch (Exception e) {
            LOGGER.error("工具类初始化失败--信息", e);
        }
    }

    public static String getProperty(String key, Object... args) {
        return MessageFormat.format(getProperty(key), args);
    }

    public static String getProperty(String key) {
        return property.getProperty(key);
    }

    private static URL getResource(String resource) {
        ClassLoader classLoader = null;
        URL url = null;
        try {
            classLoader = getTCL();
            if (classLoader != null) {
                url = classLoader.getResource(resource);
                if (url != null) {
                    return url;
                }
            }
            classLoader = PropertiesUtil.class.getClassLoader();
            if (classLoader != null) {
                url = classLoader.getResource(resource);
                if (url != null) {
                    return url;
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error("参数错误", e);
        } catch (IllegalAccessException e) {
            LOGGER.error("通道/路径错误", e);
        } catch (InvocationTargetException e) {
            LOGGER.error("调用目标异常", e);
        }
        return ClassLoader.getSystemResource(resource);
    }

    /**
     * Get the Thread Context Loader which is a JDK 1.2 feature. If we are
     * running under JDK 1.1 or anything else goes wrong the method returns
     * <code>null<code>.
     */
    private static ClassLoader getTCL() throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        // Are we running on a JDK 1.2 or later system?
        Method method = null;
        try {
            method = Thread.class.getMethod("getContextClassLoader");
        } catch (NoSuchMethodException e) {
            LOGGER.error("没有getContextClassLoader方法", e);
            return null;
        }
        return (ClassLoader) method.invoke(Thread.currentThread());
    }
}
