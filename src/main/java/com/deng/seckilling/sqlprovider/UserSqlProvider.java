package com.deng.seckilling.sqlprovider;

import com.deng.seckilling.domain.User;

/**
 * 用户相关的用来根据复杂业务需求动态生成sql的类
 * 相当于配置文件版本的UserMapper.xml
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 14:51
 */
public class UserSqlProvider {

    public String updateUser(User user) {
        String sql = "update user_info set id = #{id} ";
        if (null != user.getUserName()) {
            sql += ",user_name = #{userName}";
        }
        if (null != user.getPassWord()) {
            sql += ",pass_word = #{passWord} ";
        }
        if (null != user.getSex()) {
            sql += ",sex = #{sex} ";
        }
        if (null != user.getPhoneNumber()) {
            sql += ",phone_number = #{phoneNumber} ";
        }
        if (null != user.getIdentityCardId()) {
            sql += ",identity_card_id = #{identityCardId} ";
        }
        if (null != user.getBirthday()) {
            sql += ",birthday = #{birthday} ";
        }
        if (null != user.getStatus()) {
            sql += ",status = #{status}";
        }
        if (null != user.getRank()) {
            sql += ",rank = #{rank}";
        }
        sql += " where id = #{id}";
        return sql;
    }

    public String listUser(User user) {
        String sql = "select * from user_info where 1=1 ";
        if (null == user) {
            sql += " and 1 = 0 ";
            return sql;
        }
        if (null != user.getStatus()) {
            sql += "and status = #{status} ";
        }
        if (null != user.getId()) {
            sql += "and id = #{id} ";
        }
        if (null != user.getUserName()) {
            sql += "and user_name = #{userName} ";
        }
        if (null != user.getPassWord()) {
            sql += "and pass_word = #{passWord} ";
        }
        if (null != user.getSex()) {
            sql += "and sex = #{sex} ";
        }
        if (null != user.getPhoneNumber()) {
            sql += "and phone_number = #{phoneNumber} ";
        }
        if (null != user.getIdentityCardId()) {
            sql += "and identity_card_id = #{identityCardId} ";
        }
        if (null != user.getBirthday()) {
            sql += "and birthday = #{birthday} ";
        }
        if (null != user.getRank()) {
            sql += "and rank = #{rank} ";
        }
        return sql;
    }

}
