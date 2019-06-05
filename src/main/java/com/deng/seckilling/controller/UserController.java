package com.deng.seckilling.controller;

import com.deng.seckilling.annotation.IsLogin;
import com.deng.seckilling.constant.*;
import com.deng.seckilling.domain.UserCookie;
import com.deng.seckilling.dto.BaseUserInfoDTO;
import com.deng.seckilling.domain.User;
import com.deng.seckilling.rpc.constant.RpcResponse;
import com.deng.seckilling.rpc.util.*;
import com.deng.seckilling.service.UserService;
import com.deng.seckilling.util.SeckillingUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * 用户相关的所有接口
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 14:14
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/to_register")
    public String register() {
        return "register";
    }

    @RequestMapping("/to_login")
    public String login() {
        return "login";
    }

    @RequestMapping("/to_root")
    @IsLogin(requiredRoot = true)
    public String root(Model model, UserCookie userCookie) {
        model.addAttribute("user", userCookie);
        return "root/root";
    }

    @RequestMapping("to_common")
    @IsLogin
    public String common(Model model, UserCookie userCookie) {
        model.addAttribute("user", userCookie);
        return "common/common";
    }

    /**
     * 用户注册接口
     * 基本信息[必填项]:（用户名，密码，性别【1:男;2:女】，手机号,角色【2:买家；3：卖家】）
     *
     * @param baseUserInfoDTO 承载注册信息的载体
     * @return 用户ID
     */
    @PostMapping("/do_register")
    @ResponseBody
    public RpcResponse register(BaseUserInfoDTO baseUserInfoDTO) {
        if (CheckDataUtils.isEmpty(baseUserInfoDTO.getUserName()) || CheckDataUtils.isEmpty(baseUserInfoDTO.getPassWord()) ||
                CheckDataUtils.isEmpty(baseUserInfoDTO.getSex()) || CheckDataUtils.isEmpty(baseUserInfoDTO.getPhoneNumber()) ||
                CheckDataUtils.isEmpty(baseUserInfoDTO.getRank()) || false == EnumUtils.isEnumCode(Sex.class, baseUserInfoDTO.getSex()) ||
                false == SeckillingUtil.isCommonRank(baseUserInfoDTO.getRank())) {
            log.warn("===>register  controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        if (userService.isExist(baseUserInfoDTO.getUserName())) {
            log.warn("===>register controller user name:{} already exist", baseUserInfoDTO.getUserName());
            return RpcResponse.error(ErrorCode.USERNAME_EXIT_ERROR);
        }

        User userResult = userService.registerUserService(baseUserInfoDTO);
        if (null == userResult) {
            log.warn("===>register controller fail, message:{}", baseUserInfoDTO.toString());
            return RpcResponse.error(ErrorCode.REGIEST_FAIL_ERROR);
        }

        log.info("===>register controller success, message:{}", userResult.toString());
        return RpcResponse.success(userResult.getId());
    }

    /**
     * 用户登录接口
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return RpcResponse 用户信息
     */
    @PostMapping("/do_login")
    @ResponseBody
    public RpcResponse login(HttpServletRequest request, HttpServletResponse response, String userName, String passWord) {
        if (CheckDataUtils.isEmpty(userName) || CheckDataUtils.isEmpty(passWord)) {
            log.warn("===>login controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        User user = userService.verifyUserService(userName, passWord);
        if (null == user) {
            log.warn("===>login controller fail, username:{}; password:{}", userName, passWord);
            return RpcResponse.error(ErrorCode.USERLOGIN_FAIL_ERROR);
        }

        log.info("===>login controller success, message:{}", user.toString());
        userService.perUserLogout(request);

        UserCookie userCookie = new UserCookie(new Date());
        DataUtils.entityTransform(user, userCookie);
        userService.setCookie(Md5Utils.encryptMd5(UUIDUtils.uuid()), response, userCookie);
        return RpcResponse.success(user);
    }

    @PostMapping("/logout")
    @IsLogin(requiredController = true)
    @ResponseBody
    public RpcResponse logOut() {
        UserCookie userCookie = userService.getUserFromRequest();
        if (CheckDataUtils.isEmpty(userCookie)) {
            RpcResponse.error(ErrorCode.NOT_LOGIN_ERROR);
        }
        userService.logOutService(userCookie.getToken());
        return RpcResponse.success(userCookie.getId());
    }

    /**
     * 根据条件查询符合要求的用户集合接口(超级管理员)
     *
     * @param user 参数实体
     * @return 满足要求的用户集合
     */
    @GetMapping("/querybycondition")
    @ResponseBody
    @IsLogin(requiredRoot = true, requiredController = true, errorMessage = "权限不足，只有管理员可操作")
    public RpcResponse queryUsersByCondition(User user) {
        if (CheckDataUtils.isEmpty(user)) {
            log.warn("===>query user controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        List<User> userList = userService.queryUsersByConditionService(user);
        if (CheckDataUtils.isEmpty(userList)) {
            log.warn("===>query user controller by condition:{}, get null data", user.toString());
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
    @GetMapping("/querybycondition/{pageNum}")
    @ResponseBody
    @IsLogin(requiredRoot = true, requiredController = true, errorMessage = "权限不足，只有管理员可操作")
    public RpcResponse queryUsersByCondition(@PathVariable Integer pageNum, Integer pageSize, User user) {
        if (CheckDataUtils.isEmpty(user) || CheckDataUtils.isEmpty(pageNum)) {
            log.warn("===>query user controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        PageInfo<User> pageInfo = userService.queryUsersByConditionService(pageNum, CheckDataUtils.isEmpty(pageSize) ? DefaultValue.FENYE_PAGESIZE_VALUE : pageSize, user);
        return RpcResponse.success(pageInfo);
    }

    /**
     * 完善个人信息接口
     * (不能修改ID和用户名)
     *
     * @param user 待完善信息载体
     * @return 完善用户的Id
     */
    @PostMapping("/complete")
    @ResponseBody
    @IsLogin(requiredController = true)
    public RpcResponse complete(User user) {
        if (CheckDataUtils.isEmpty(user.getId())) {
            log.warn("===>complete controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        userService.isLoginOrRoot(user.getId());
        List<User> userList = userService.queryUsersByConditionService(new User(user.getId()));
        if (CheckDataUtils.isEmpty(userList)) {
            log.warn("===>complete controller id:{} not exist", user.getId());
            return null;
        }

        User userResult = userService.completeUserInfoService(user);
        if (userResult == null) {
            log.warn("===>complete controller fail, complete message:{}", user.toString());
            return RpcResponse.error(ErrorCode.COMPLETE_USERINFO_ERROR);
        }

        log.info("===>complete controller success, complete message:{}", userResult.toString());
        return RpcResponse.success(userResult.getId());
    }

    /**
     * 冻结用户账户接口
     *
     * @param id 待冻结用户Id
     * @return 冻结结果：冻结用户账户的Id
     */
    @PostMapping("/frozen")
    @ResponseBody
    @IsLogin(requiredController = true)
    public RpcResponse frozen(Long id) {
        if (CheckDataUtils.isEmpty(id)) {
            log.warn("===>frozen controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        userService.isLoginOrRoot(id);
        List<User> userList = userService.queryUsersByConditionService(new User(id));
        if (CheckDataUtils.isEmpty(userList)) {
            log.warn("===>frozen controller, id:{} not exist", id);
            return RpcResponse.error(ErrorCode.USER_NOTEXIT_ERROR);
        }

        if (false == Status.NORMAL.getValue().equals(userList.get(0).getStatus())) {
            log.warn("===>frozen id:{} fail, not {} status error", Status.NORMAL.getValue(), id);
            return RpcResponse.error(ErrorCode.NOTNORMALUSER_CANNOT_FROZEN);
        }

        id = userService.frozenUserByIdService(id);
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
    @PostMapping("/unfrozen")
    @ResponseBody
    @IsLogin(requiredController = true)
    public RpcResponse unfrozen(Long id) {
        if (CheckDataUtils.isEmpty(id)) {
            log.warn("===>unfrozen controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        userService.isLoginOrRoot(id);
        List<User> userList = userService.queryUsersByConditionService(new User(id));
        if (CheckDataUtils.isEmpty(userList)) {
            log.warn("===>unfrozen controller, id:{} not exist", id);
            return RpcResponse.error(ErrorCode.USER_NOTEXIT_ERROR);
        }

        if (false == Status.FROZEN.getValue().equals(userList.get(0).getStatus())) {
            log.warn("===>unfrozen id:{} fail, not {} status error", Status.FROZEN.getValue(), id);
            return RpcResponse.error(ErrorCode.NOTFROZENUSER_CANNOT_UNFROXEN);
        }

        id = userService.unfrozenUserByIdService(id);
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
    @PostMapping("/invalid")
    @ResponseBody
    @IsLogin(requiredController = true)
    public RpcResponse invalid(Long id) {
        if (CheckDataUtils.isEmpty(id)) {
            log.warn("===>invalid controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        userService.isLoginOrRoot(id);
        List<User> userList = userService.queryUsersByConditionService(new User(id));
        if (CheckDataUtils.isEmpty(userList)) {
            log.warn("===>invalid controller, id:{} not exist", id);
            return RpcResponse.error(ErrorCode.USER_NOTEXIT_ERROR);
        }

        id = userService.invalidUserByIdService(id);
        if (CheckDataUtils.isEmpty(id)) {
            log.warn("===>invalid id:{} fail", id);
            return RpcResponse.error(ErrorCode.INVALID_USER_ERROR);
        }

        log.info("===>invalid id:{} success", id);
        return RpcResponse.success(id);
    }

}
