package com.jason.auto.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class UrlOperationUtils {
    private static final Logger log = LoggerFactory.getLogger(UrlOperationUtils.class);

    public static String buildUiURL(String urlKey) {
        return getPort().equals("") ? getURL() + PropertyReader.getProperty(urlKey) : getURL() + ":" + getPort() + PropertyReader.getProperty(urlKey);
    }

    public static String buildUiURLWithId(String urlKey, String id) {
        return buildUiURL(urlKey).replace("{ID}", id);
    }

    public static String buildApiURL(String urlKey) {
        return getURL() + PropertyReader.getProperty(urlKey);
    }

    public static String buildLoginURL(String urlKey) {
        return getLoginURL() + PropertyReader.getProperty(urlKey);
    }

    public static String buildGridURL(String basePath) {
        return getGridURL() + ":" + getGridPort() + basePath;
    }

    public static String buildJiraURL(String urlKey) {
        return getJiraURL() + PropertyReader.getProperty(urlKey);
    }

    public static String getURL() {
        String host = getHost();
        String env = "";
        String host_prefix = "http://";
        if (StringUtils.isEmpty(host)) {
            host = PropertyReader.getProperty("HOST.DEFAULT");
            env = PropertyReader.testEnvironment;
        }

        if (env.equals("prod")) {
            host_prefix = "https://";
        }

        return host_prefix + host;
    }

    public static String getPort() {
        String hostPort = getHostPort();
        if (StringUtils.isEmpty(hostPort)) {
            hostPort = PropertyReader.getProperty("HOST.DEFAULT.PORT");
        }

        return hostPort;
    }

    private static String getLoginURL() {
        String host = PropertyReader.getProperty("HOST.LOGIN");
        return "https://" + host;
    }

    private static String getJiraURL() {
        String host = PropertyReader.getProperty("HOST.JIRA");
        return "https://" + host;
    }

    private static String getGridURL() {
        String host = getSeleniumGridHost();
        if (StringUtils.isEmpty(host)) {
            host = PropertyReader.getProperty("HOST.SELENIUM.GRID");
        }

        return "http://" + host;
    }

    private static String getGridPort() {
        String hostPort = getSeleniumGridPort();
        if (StringUtils.isEmpty(hostPort)) {
            hostPort = PropertyReader.getProperty("HOST.SELENIUM.GRID.PORT");
        }

        return hostPort;
    }

    public static String getValue(String key) {
        String val = PropertyReader.getProperty(key);
        return val;
    }

    private static String getHost() {
        return PropertyReader.getProperty("server.host");
    }

    private static String getHostPort() {
        return PropertyReader.getProperty("server.port");
    }

    private static String getSeleniumGridHost() {
        return PropertyReader.getProperty("grid.host");
    }

    private static String getSeleniumGridPort() {
        return PropertyReader.getProperty("grid.port");
    }

    private UrlOperationUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}
