package org.xinyu.com.mq;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import java.util.Map.Entry;

/**
 *
 * @author liuxiny
 *
 */
public class AppPropsConfig {


    public static Map<Object, Object> getProperties(String pResourceName, Class pClass){
        InputStream iStream = pClass.getClassLoader().getResourceAsStream(pResourceName);
        Map<Object, Object> map = getProperties( iStream );
        return map;
    }

    public static Map<Object, Object> getProperties(String pPropertiesFilePath){
        InputStream iStream = null;
        Map<Object, Object> map = null;
        try {
            iStream = new BufferedInputStream(new FileInputStream(new File(pPropertiesFilePath)));
            map = getProperties( iStream );
        } catch (FileNotFoundException e) {
            String exJson = GsonUtils.toJsonStr(e);
            LoggerUtils.error( AppPropsConfig.class, exJson );
        }
        return map;
    }

    public static Map<Object, Object> getProperties(InputStream pInputStream){
        Map<Object, Object> map = new HashMap<>();
        Properties cfgProperties = new Properties();
        try {
            cfgProperties.load(pInputStream);
            for (Map.Entry e : cfgProperties.entrySet()) {
                map.put( e.getKey(), e.getValue() );
            }

        } catch (IOException e) {
            String exJson = GsonUtils.toJsonStr(e);
            LoggerUtils.error( AppPropsConfig.class, exJson);
        } finally {
            if( pInputStream != null ){
                try {
                    pInputStream.close();
                } catch (IOException e) {
                    String exJson = GsonUtils.toJsonStr(e);
                    LoggerUtils.error( AppPropsConfig.class, exJson);
                }
            }
        }
        return map;
    }

    public static Map<String, String> getStrProperties(String pResourceName, Class pClass){

        InputStream iStream = pClass.getClassLoader().getResourceAsStream(pResourceName);
        Map<String, String> map = getStrProperties( iStream );
        return map;
    }

    public static Map<String, String> getStrProperties(String pPropertiesFilePath){
        InputStream iStream = null;
        Map<String, String> map = null;
        try {
            iStream = new BufferedInputStream(new FileInputStream(new File(pPropertiesFilePath)));
            map = getStrProperties( iStream );
        } catch (FileNotFoundException e) {
            String exJson = GsonUtils.toJsonStr(e);
            LoggerUtils.error( AppPropsConfig.class, exJson );
        }
        return map;
    }

    public static Map<String, String> getStrProperties(InputStream pInputStream){
        Map<String, String> map = new HashMap<>();
        Properties cfgProperties = new Properties();
        try {
            cfgProperties.load(pInputStream);
            for (Entry e : cfgProperties.entrySet()) {
                Object keyObj = e.getKey();
                Object valueObj = e.getValue();
                if( keyObj != null && keyObj instanceof String)
                    map.put( (String) keyObj, (String) valueObj );
            }

        } catch (IOException e) {
            String exJson = GsonUtils.toJsonStr(e);
            LoggerUtils.error( AppPropsConfig.class, exJson );
        } finally {
            if( pInputStream != null ){
                try {
                    pInputStream.close();
                } catch (IOException e) {
                    String exJson = GsonUtils.toJsonStr(e);
                    LoggerUtils.error( AppPropsConfig.class, exJson );
                }
            }
        }
        return map;
    }

}

