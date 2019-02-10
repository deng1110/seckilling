package com.deng.seckilling.sqlprovider;

import com.deng.seckilling.po.UserPo;
import com.deng.seckilling.util.CheckDataUtils;

/**
 * 用户相关的用来根据复杂业务需求动态生成sql的类
 * 相当于配置文件版本的UserMapper.xml
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 14:51
 */
public class UserSqlProvider {

    public String getUserByCondition(UserPo userPo) {
        String sql = "select * from user where status = 'normal' ";
        if (false == CheckDataUtils.isEmpty(userPo.getId())) {
            sql += "and id = #{id} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getUserName())) {
            sql += "and user_name = #{userName} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getPassWord())) {
            sql += "and pass_word = #{passWord} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getSex())) {
            sql += "and sex = #{sex} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getPhoneNumber())) {
            sql += "and phone_number = #{phoneNumber} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getIdentityCardId())) {
            sql += "and identity_card_id = #{identityCardId} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getBirthday())) {
            sql += "and birthday = #{birthday} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getAddress())) {
            sql += "and address = #{address} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getRank())) {
            sql += "and rank = #{rank} ";
        }
        return sql;
    }

    public String updateUserInfo(UserPo userPo) {
        String sql = "update user set id = #{id} ";
        if (false == CheckDataUtils.isEmpty(userPo.getUserName())) {
            sql += ",user_name = #{userName}";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getPassWord())) {
            sql += ",pass_word = #{passWord} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getSex())) {
            sql += ",sex = #{sex} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getPhoneNumber())) {
            sql += ",phone_number = #{phoneNumber} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getIdentityCardId())) {
            sql += ",identity_card_id = #{identityCardId} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getBirthday())) {
            sql += ",birthday = #{birthday} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getAddress())) {
            sql += ",address = #{address} ";
        }
        if (false == CheckDataUtils.isEmpty(userPo.getStatus())) {
            sql += ",status = #{status}";
        }
        sql += "where id = #{id}";
        return sql;
    }
}
