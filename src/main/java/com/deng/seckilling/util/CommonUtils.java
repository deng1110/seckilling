package com.deng.seckilling.util;

import com.deng.seckilling.po.UserPo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import java.lang.reflect.Field;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 14:14
 */
public class CommonUtils {

    public static void main(String[] args) {
        UserPo userPo = new UserPo();
        System.out.println(getResultsStr(userPo.getClass()));
    }

    /**
     * 用于获取结果集的映射关系
     *
     * @param origin
     * @return String类型的结果集
     */
    public static String getResultsStr(Class origin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results({\n");
        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();
            //映射关系：对象属性(驼峰)->数据库字段(下划线)
            String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName()).toUpperCase();
            stringBuilder.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", property, column));
        }
        stringBuilder.append("})");
        return stringBuilder.toString();
    }
}
