package com.deng.seckilling.controller;

import com.deng.seckilling.constant.DefaultValue;
import com.deng.seckilling.constant.ErrorCode;
import com.deng.seckilling.constant.Sex;
import com.deng.seckilling.constant.Status;
import com.deng.seckilling.dto.BaseUserInfoDTO;
import com.deng.seckilling.domain.User;
import com.deng.seckilling.rpc.constant.RpcResponse;
import com.deng.seckilling.rpc.util.CheckDataUtils;
import com.deng.seckilling.rpc.util.EnumUtils;
import com.deng.seckilling.service.UserService;
import com.deng.seckilling.util.SeckillingUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册接口
     * 基本信息[必填项]:（用户名，密码，性别【1:男;2:女】，手机号,角色【2:买家；3：卖家】）
     *
     * @param baseUserInfoDTO 承载注册信息的载体
     * @return 用户ID
     */
    @RequestMapping("/register")
    public RpcResponse register(BaseUserInfoDTO baseUserInfoDTO) {
        if (CheckDataUtils.isEmpty(baseUserInfoDTO.getUserName()) || CheckDataUtils.isEmpty(baseUserInfoDTO.getPassWord()) ||
                CheckDataUtils.isEmpty(baseUserInfoDTO.getSex()) || CheckDataUtils.isEmpty(baseUserInfoDTO.getPhoneNumber()) ||
                CheckDataUtils.isEmpty(baseUserInfoDTO.getRank()) || false == EnumUtils.isEnumCode(Sex.class, baseUserInfoDTO.getSex()) ||
                false == SeckillingUtil.isCommonRank(baseUserInfoDTO.getRank())) {
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        if (userService.isExist(baseUserInfoDTO.getUserName())) {
            return RpcResponse.error(ErrorCode.USERNAME_EXIT_ERROR);
        }

        User userResult = null;
        try {
            userResult = userService.registerUserService(baseUserInfoDTO);
            if (null == userResult) {
                throw new RuntimeException("register user false");
            }
        } catch (Exception e) {
            log.error("===>register controller error:{}", e.getMessage());
            return RpcResponse.error(ErrorCode.SYSTEM_ERROR);
        }

        return RpcResponse.success(userResult.getId());
    }

    /**
     * 用户登录接口
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return RpcResponse 登录返回结果
     */
    @PostMapping("/login")
    public RpcResponse login(HttpServletRequest request, HttpServletResponse response, String userName, String passWord) {
        if (CheckDataUtils.isEmpty(userName) || CheckDataUtils.isEmpty(passWord)) {
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        User user;
        try {
            user = userService.verifyUserService(userName, passWord);
        } catch (Exception e) {
            log.error("===>login controller error:{}", e.getMessage());
            return RpcResponse.error(ErrorCode.SYSTEM_ERROR);
        }

        if (null == user) {
            return RpcResponse.error(ErrorCode.USERLOGIN_FAIL_ERROR);
        }

        request.getSession().setAttribute(DefaultValue.SESSION_KEY_VALUE, user.getId());
        return RpcResponse.success(user.getId());
    }

    /**
     * 根据条件查询符合要求的用户集合接口(超级管理员)
     *
     * @param user 参数实体
     * @return 满足要求的用户集合
     */
    @RequestMapping("/querybycondition")
    public RpcResponse queryUsersByCondition(HttpServletRequest request, HttpServletResponse response, User user) {
        Object sessionValue = request.getSession().getAttribute(DefaultValue.SESSION_KEY_VALUE);
        if (null == sessionValue || false == (DefaultValue.SESSION_ROOT_VALUE == Long.parseLong(sessionValue.toString()))) {
            log.warn("===>query user controller Permission denied error");
            return RpcResponse.error(ErrorCode.PERMISSION_DENIED_ERROR);
        }
        if (CheckDataUtils.isEmpty(user)) {
            log.warn("===>query user controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        List<User> userList = null;
        try {
            userList = userService.queryUsersByConditionService(user);
        } catch (Exception e) {
            log.error("===>query user controller error:{}", e.getMessage());
            return RpcResponse.error(ErrorCode.SYSTEM_ERROR);
        }
        if (null == userList) {
            return RpcResponse.error(ErrorCode.QUERYUSER_NULL_ERROR);
        }
        return RpcResponse.success(userList);
    }

    /**
     * 带分页的根据条件查询符合要求的用户集合接口(超级管理员)
     *
     * @param pageNum 第几页
     * @param user    满足要求的用户集合
     * @return 满足要求的用户集合
     */
    @RequestMapping("/querybycondition/{pageNum}")
    public RpcResponse queryUsersByCondition(@PathVariable Integer pageNum, HttpServletRequest request, HttpServletResponse response, User user) {
        Object sessionValue = request.getSession().getAttribute(DefaultValue.SESSION_KEY_VALUE);
        if (null == sessionValue || false == (DefaultValue.SESSION_ROOT_VALUE == Long.parseLong(sessionValue.toString()))) {
            log.warn("===>query user controller Permission denied error");
            return RpcResponse.error(ErrorCode.PERMISSION_DENIED_ERROR);
        }
        if (CheckDataUtils.isEmpty(user) || CheckDataUtils.isEmpty(pageNum)) {
            log.warn("===>query user controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        PageInfo<User> pageInfo = null;
        try {
            pageInfo = userService.queryUsersByConditionService(pageNum, DefaultValue.FENYE_PAGESIZE_VALUE, user);
        } catch (Exception e) {
            log.error("===>query user controller error:{}", e.getMessage());
            return RpcResponse.error(ErrorCode.SYSTEM_ERROR);
        }
        return RpcResponse.success(pageInfo);
    }

    /**
     * 完善个人信息接口
     * (不能修改ID和用户名)
     *
     * @param user 待完善信息载体
     * @return 完善用户的Id
     */
    @RequestMapping("/complete")
    public RpcResponse complete(User user) {
        if (CheckDataUtils.isEmpty(user.getId())) {
            log.warn("===>complete controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        User userResult = null;
        try {
            userResult = userService.completeUserInfoService(user);
        } catch (Exception e) {
            log.error("===>complete controller error:{}", e.getMessage());
            return RpcResponse.error(ErrorCode.SYSTEM_ERROR);
        }
        if (userResult == null) {
            log.warn("===>complete controller fail, complete message:{}", user.toString());
            return RpcResponse.error(ErrorCode.COMPLETE_USERINFO_ERROR);
        }
        return RpcResponse.success(userResult.getId());
    }

    /**
     * 冻结用户账户接口
     *
     * @param id 待冻结用户Id
     * @return 冻结结果：冻结用户账户的Id
     */
    @RequestMapping("/frozen")
    public RpcResponse frozen(Long id) {
        if (CheckDataUtils.isEmpty(id)) {
            log.warn("===>frozen controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        List<User> userList = userService.queryUsersByConditionService(new User(id));
        if (CheckDataUtils.isEmpty(userList)) {
            log.warn("===>frozen controller, id:{} not exist", id);
            return RpcResponse.error(ErrorCode.USER_NOTEXIT_ERROR);
        }
        if (false == Status.NORMAL.getValue().equals(userList.get(0).getStatus())) {
            log.warn("===>frozen id:{} fail, not {} status error", Status.NORMAL.getValue(), id);
            return RpcResponse.error(ErrorCode.NOTNORMALUSER_CANNOT_FROZEN);
        }
        try {
            id = userService.frozenUserByIdService(id);
        } catch (Exception e) {
            log.error("===>frozen controller error:{}", e.getMessage());
            return RpcResponse.error(ErrorCode.SYSTEM_ERROR);
        }
        if (CheckDataUtils.isEmpty(id)) {
            log.warn("===>frozen id:{} fail", id);
            return RpcResponse.error(ErrorCode.FROZEN_USER_ERROR);
        }
        log.info("===>frozen id:{} success", id);
        return RpcResponse.success(id);
    }

    /**
     * 解冻用户账户接口
     *
     * @param id 待解冻用户Id
     * @return 解冻结果：解冻用户账户的Id
     */
    @RequestMapping("/unfrozen")
    public RpcResponse unfrozen(Long id) {
        if (CheckDataUtils.isEmpty(id)) {
            log.warn("===>unfrozen controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        List<User> userList = userService.queryUsersByConditionService(new User(id));
        if (CheckDataUtils.isEmpty(userList)) {
            log.warn("===>unfrozen controller, id:{} not exist", id);
            return RpcResponse.error(ErrorCode.USER_NOTEXIT_ERROR);
        }
        if (false == Status.FROZEN.getValue().equals(userList.get(0).getStatus())) {
            log.warn("===>unfrozen id:{} fail, not {} status error", Status.FROZEN.getValue(), id);
            return RpcResponse.error(ErrorCode.NOTFROZENUSER_CANNOT_UNFROXEN);
        }
        try {
            id = userService.unfrozenUserByIdService(id);
        } catch (Exception e) {
            log.error("===>unfrozen controller error:{}", e.getMessage());
            return RpcResponse.error(ErrorCode.SYSTEM_ERROR);
        }
        if (CheckDataUtils.isEmpty(id)) {
            log.warn("===>unfrozen id:{} fail", id);
            return RpcResponse.error(ErrorCode.UNFROZEN_USER_ERROR);
        }
        log.info("===>unfrozen id:{} success", id);
        return RpcResponse.success(id);
    }

    /**
     * 作废用户账户接口
     *
     * @param id 待作废用户Id
     * @return 作废结果：作废用户账户的Id
     */
    @RequestMapping("/invalid")
    public RpcResponse invalid(Long id) {
        if (CheckDataUtils.isEmpty(id)) {
            log.warn("===>invalid controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        List<User> userList = userService.queryUsersByConditionService(new User(id));
        if (CheckDataUtils.isEmpty(userList)) {
            log.warn("===>invalid controller, id:{} not exist", id);
            return RpcResponse.error(ErrorCode.USER_NOTEXIT_ERROR);
        }
        try {
            id = userService.invalidUserByIdService(id);
        } catch (Exception e) {
            log.error("===>invalid controller error:{}", e.getMessage());
            return RpcResponse.error(ErrorCode.SYSTEM_ERROR);
        }
        if (CheckDataUtils.isEmpty(id)) {
            log.warn("===>invalid id:{} fail", id);
            return RpcResponse.error(ErrorCode.INVALID_USER_ERROR);
        }
        log.info("===>invalid id:{} success", id);
        return RpcResponse.success(id);
    }

}
