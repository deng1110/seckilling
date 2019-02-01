package com.deng.seckilling.controller;

import com.deng.seckilling.constant.ErrorCode;
import com.deng.seckilling.po.UserPo;
import com.deng.seckilling.rpc.RpcResponse;
import com.deng.seckilling.service.UserService;
import com.deng.seckilling.util.CheckDataUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户相关的所有接口
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 14:14
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/getuser")
    public List<UserPo> getUser() {
        return userService.getUserService();
    }

    @RequestMapping("/login")
    public RpcResponse login(String userName, String passWord) {
        if(CheckDataUtils.isEmpty(userName) || CheckDataUtils.isEmpty(passWord)){
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        return userService.verifyUser(userName, passWord);
    }


    @RequestMapping("/test")
    public String test() {
        return "test.....";
    }
}
