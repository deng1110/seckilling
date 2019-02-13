package com.deng.seckilling.dao;

import com.deng.seckilling.po.UserPo;
import com.deng.seckilling.sqlprovider.UserSqlProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户相关操作Dao接口
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 14:14
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户名和密码查询用户，表中user_name字段设置唯一索引了。
     * 所以讲道理来说虽然使用List接，但不出意外的话，正常的话都会只有一条数据
     * ps:此方法中的@Param中的名字不需要和后面String 声明的保持一致，
     * 要和SqlProvider中的对应方法中的@Param保持一致，不需要和后面String 声明的保持一致
     * 并且其中的sql语句中的El表达式取得值是@Param中的，固也需要保持一致
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return
     */
    @Select("select * from user where user_name =#{userName123} and pass_word =#{passWord} and status = 'normal'")
    List<UserPo> getUsersByUserNameAndPassword(@Param("userName123") String userName, @Param("passWord") String passWord);

    /**
     * 根据条件查询对应用户(若条件非空则为查询条件)
     *
     * @param userPo 存储查询条件的实体
     * @return List<UserPo> 符合条件的用户List
     */
    @SelectProvider(type = UserSqlProvider.class, method = "getUserByCondition")
    List<UserPo> getUserByCondition(UserPo userPo);

    /**
     * 插入一条用户数据，仅包含一些基本信息
     * 其中@Options注解的意思是 返回数据库自动生成的主键
     * 在service中直接可以用
     *
     * @param userPo 待插入数据的载体
     * @return 影响的记录数
     */
    @Insert("insert into user (user_name, pass_word, sex, phone_number) values(#{userName}, #{passWord}, #{sex}, #{phoneNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUserBasicInfo(UserPo userPo);

    /**
     * 根据用户Id更新用户的信息（账户状态必须是正常的，否则更新不了）
     *
     * @param userPo 待更新信息的载体
     * @return 影响记录条数
     */
    @UpdateProvider(type = UserSqlProvider.class, method = "updateUserInfo")
    int updateUserInfo(UserPo userPo);

    /**
     * 根据用户Id查询用户信息
     *
     * @param id 待查询的用户id
     * @return 返回的用户信息
     */
    @Select("select * from user where id = #{id}")
    UserPo getUserByUserId(@Param("id") Long id);
}
