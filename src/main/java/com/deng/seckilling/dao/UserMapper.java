package com.deng.seckilling.dao;

import com.deng.seckilling.po.UserPo;
import com.deng.seckilling.sqlprovider.UserSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 14:14
 */
@Mapper
public interface UserMapper {

    @Results({
            @Result(property = "id", column = "ID"),
            @Result(property = "userName", column = "USER_NAME"),
            @Result(property = "passWord", column = "PASS_WORD"),
            @Result(property = "sex", column = "SEX"),
            @Result(property = "identityCardId", column = "IDENTITY_CARD_ID"),
            @Result(property = "address", column = "ADDRESS"),
    })
    @Select("select * from user")
    List<UserPo> list();

    /**
     * 根据用户名和密码查询用户，表中user_name字段设置唯一索引了。
     * 所以讲道理来说虽然使用List接，但不出意外的话，正常的话都会只有一条数据
     * ps:此方法中的@Param中的名字不需要和后面String 声明的保持一致，
     *    要和SqlProvider中的对应方法中的@Param保持一致，不需要和后面String 声明的保持一致
     *    并且其中的sql语句中的El表达式取得值是@Param中的，固也需要保持一致
     *
     * @param userName
     * @param passWord
     * @return
     */
    @SelectProvider(type = UserSqlProvider.class, method = "getUsersByUserNameAndPassword")
    public List<UserPo> getUsersByUserNameAndPassword(@Param("userName123") String userName, @Param("passWord") String passWord);




}
