package cn.edu.zzh.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author zzh
 * @date 2020-06-03 9:19
 * @description
 */
public class PropertiesMgr {
    private static PropertiesMgr instance;

    private static Properties properties = new Properties();
    private PropertiesMgr(){}
    public static PropertiesMgr getInstance(){
        if(instance == null){
            instance = new PropertiesMgr();//调用在创建
        }
        return instance;
    }

    static {
        try {
            properties.load(PropertiesMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getString(String key) {
        if(properties == null) return null;
        return (String) properties.get(key);
    }
    public Integer getInt(String key) {
        if(properties == null) return null;
        return Integer.parseInt((String) properties.get(key));
    }
}
