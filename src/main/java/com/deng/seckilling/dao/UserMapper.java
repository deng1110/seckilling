package com.deng.seckilling.dao;

import com.deng.seckilling.domain.User;
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
     * 方法中的@Param中的名字不需要和后面String 声明的保持一致，
     * 要和SqlProvider中的对应方法中的@Param保持一致，不需要和后面String 声明的保持一致
     * 并且其中的sql语句中的El表达式取得值是@Param中的，固也需要保持一致
     */


    /**
     * 保存基本信息用户
     * 其中@Options注解的意思是 返回数据库自动生成的主键
     *
     * @param user 待插入数据的载体
     * @return 影响的记录数
     */
    @Insert("insert into user_info (user_name, pass_word, sex, phone_number) values(#{userName}, #{passWord}, #{sex}, #{phoneNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 待更新信息的载体
     * @return 影响记录条数
     */
    @UpdateProvider(type = UserSqlProvider.class, method = "updateUser")
    int updateUser(User user);


    /**
     * 根据条件查询用户账户状态normal的用户组
     *
     * @param user 存储查询条件的实体
     * @return List<User> 符合条件的用户List
     */
    @SelectProvider(type = UserSqlProvider.class, method = "listUser")
    List<User> listUser(User user);
}
