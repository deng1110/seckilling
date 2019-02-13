package com.deng.seckilling.controller;

import com.deng.seckilling.constant.DefaultValue;
import com.deng.seckilling.constant.ErrorCode;
import com.deng.seckilling.po.UserPo;
import com.deng.seckilling.rpc.RpcResponse;
import com.deng.seckilling.service.UserService;
import com.deng.seckilling.util.CheckDataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户相关的所有接口
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 14:14
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    UserService userService;

    /**
     * 用户登录接口
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return RpcResponse 登录返回结果
     */
    @RequestMapping("/login")
    public RpcResponse login(String userName, String passWord) {
        if (CheckDataUtils.isEmpty(userName) || CheckDataUtils.isEmpty(passWord)) {
            log.warn("login接口入参错误！");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        log.info(userName + "尝试登录的密码是" + passWord);
        return userService.verifyUserService(userName, passWord);
    }

    /**
     * 根据条件查询符合要求的用户集合接口
     * （只能查正常用户账户状态的用户）
     * TODO:该方法需要root权限
     *
     * @param userPo 存储条件的实体
     * @return RpcResponse 满足要求的用户集合
     */
    @RequestMapping("/querybycondition")
     public RpcResponse queryUsersByCondition(UserPo userPo) {
        log.info("查询用户集合接口，查询条件为：" + userPo.toString());
        return userService.queryUsersByConditionService(userPo);
    }

    /**
     * 带分页的按条件查询用户集合接口
     *
     * @param pageNum 第几页
     * @param userPo 满足要求的用户集合
     * @return RpcResponse 满足要求的用户集合
     */
    @RequestMapping("/querybycondition/{pageNum}")
    public RpcResponse queryUsersByCondition(@PathVariable Integer pageNum, UserPo userPo) {
        if (CheckDataUtils.isEmpty(pageNum)) {
            log.warn("带分页的querybycondition接口入参错误！");
            return RpcResponse.error(ErrorCode.FENYE_PARAMS_ERROR);
        }
        log.info("分页查询接口，正在查询第" + pageNum + "页数据");
        log.info("查询用户集合接口，查询条件为：" + userPo.toString());
        return userService.queryUsersByConditionService(pageNum, DefaultValue.FENYE_PAGESIZE_VALUE,userPo);
    }

    /**
     * 用户注册接口
     * （只需要基本信息即可注册，基本信息[必填项]如下： ）
     * （用户名，密码，性别，手机号,角色）
     *
     * @param userPo 承载注册信息的载体
     * @return RpcResponse注册用户信息
     */
    @RequestMapping("/register")
    public RpcResponse register(UserPo userPo) {
        if (CheckDataUtils.isEmpty(userPo.getUserName()) || CheckDataUtils.isEmpty(userPo.getPhoneNumber()) ||
                CheckDataUtils.isEmpty(userPo.getPassWord()) || (false == CheckDataUtils.isSex(userPo.getSex())) ||
                (false == CheckDataUtils.isRank(userPo.getRank()))
                ) {
            log.warn("register接口入参错误！");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        log.info("待注册的信息为" + userPo.toString());
        return userService.registerUserService(userPo);
    }

    /**
     * 完善个人信息接口
     * (不能修改ID和用户名)
     *
     * @param userPo 待完善信息载体
     * @return 完善用户的Id
     */
    @RequestMapping("/complete")
    public RpcResponse complete(UserPo userPo) {
        if (CheckDataUtils.isEmpty(userPo.getId())) {
            log.warn("complete接口入参错误！");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        log.info("待完善的信息为" + userPo.toString());
        return userService.completeUserInfoService(userPo);
    }

    /**
     * 作废用户账户接口
     *
     * @param id 待作废用户Id
     * @return 作废结果：作废用户账户的Id
     */
    @RequestMapping("invalid")
    public RpcResponse invalid(Long id) {
        if (CheckDataUtils.isEmpty(id)) {
            log.warn("invalid接口入参错误");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        log.info("要作废的用户Id为" + id);
        return userService.invalidUserByIdService(id);
    }

    /**
     * 冻结用户账户接口
     *
     * @param id 待冻结用户Id
     * @return 冻结结果：冻结用户账户的Id
     */
    @RequestMapping("frozen")
    public RpcResponse frozen(Long id) {
        if (CheckDataUtils.isEmpty(id)) {
            log.warn("frozen接口入参错误");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        log.info("要冻结的用户Id为" + id);
        return userService.frozenUserByIdService(id);
    }

}
