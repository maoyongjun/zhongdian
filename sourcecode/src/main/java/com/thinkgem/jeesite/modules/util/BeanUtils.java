package com.thinkgem.jeesite.modules.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {

    //把JavaBean转化为map
    public static Map<String,Object> bean2map(Object bean) {

        Map<String,Object> map = new HashMap<>();
        try {
            //获取JavaBean的描述器
            BeanInfo b = Introspector.getBeanInfo(bean.getClass(),Object.class);
            //获取属性描述器
            PropertyDescriptor[] pds = b.getPropertyDescriptors();
            //对属性迭代
            for (PropertyDescriptor pd : pds) {
                //属性名称
                String propertyName = pd.getName();
                //属性值,用getter方法获取
                Method m = pd.getReadMethod();
                Object properValue = m.invoke(bean);//用对象执行getter方法获得属性值

                //把属性名-属性值 存到Map中
                map.put(propertyName, properValue);
            }
        }catch (Exception e){

        }

        return map;
    }
}
