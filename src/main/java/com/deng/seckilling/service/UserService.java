package com.deng.seckilling.service;

import com.deng.seckilling.constant.ErrorCode;
import com.deng.seckilling.dao.UserMapper;
import com.deng.seckilling.po.UserPo;
import com.deng.seckilling.rpc.RpcResponse;
import com.deng.seckilling.util.CheckDataUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/1/29 11:47
 */
@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public List<UserPo> getUserService() {

        UserPo userPo = new UserPo();
        userPo.setId(1L);
        userPo.setAddress("2");

        List<UserPo> userPoList = new ArrayList<UserPo>();
        userPoList.add(userPo);
        return userMapper.list();
    }

    /**
     * 验证用户的用户名密码
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return boolean：true(成功)，false(失败)
     */
    public RpcResponse verifyUser(String userName, String passWord) {
        List<UserPo> userPoList = userMapper.getUsersByUserNameAndPassword(userName, passWord);
        if (false == CheckDataUtils.isEmpty(userPoList)) {
            return RpcResponse.success();
        } else {
            return RpcResponse.error(ErrorCode.USERLOGIN_FALSE_ERROR);
        }
    }

}
