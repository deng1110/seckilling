package com.deng.seckilling.sqlprovider;

import org.apache.ibatis.annotations.Param;

/**
 * 用户相关的用来根据复杂业务需求动态生成sql的类
 * 相当于配置文件版本的UserMapper.xml
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 14:51
 */
public class UserSqlProvider {

    public String getUsersByUserNameAndPassword(@Param("userName123") String userName,@Param("passWord") String passWord) {
        return "select * from user where user_name =#{userName123} and pass_word =#{passWord}";
    }

}
