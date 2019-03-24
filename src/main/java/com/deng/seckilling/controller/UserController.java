package com.deng.seckilling.controller;

import com.deng.seckilling.constant.DefaultValue;
import com.deng.seckilling.constant.ErrorCode;
import com.deng.seckilling.constant.Sex;
import com.deng.seckilling.po.User;
import com.deng.seckilling.rpc.RpcCommonUtil;
import com.deng.seckilling.rpc.RpcResponse;
import com.deng.seckilling.service.UserService;
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
    UserService userService;

    /**
     * 用户登录接口
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return RpcResponse 登录返回结果
     */
    @PostMapping("/login")
    public RpcResponse login(HttpServletRequest request, HttpServletResponse response, String userName, String passWord) {
        if (RpcCommonUtil.isEmpty(userName) || RpcCommonUtil.isEmpty(passWord)) {
            log.warn("===>login controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        User user = null;
        try {
            user = userService.verifyUserService(userName, passWord);
        } catch (Exception e) {
            log.error("===>login controller error:{}", e.getMessage());
            return RpcResponse.error(ErrorCode.SYSTEM_ERROR);
        }
        if (null == user) {
            log.info("===>login controller username:{};password:{};login fail", userName, passWord);
            return RpcResponse.error(ErrorCode.USERLOGIN_FAIL_ERROR);
        }
        request.getSession().setAttribute("sessionId", user.getId());
        log.info("===>login controller username:{};password:{};login success", userName, passWord);
        return RpcResponse.success(user.getId().toString());
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
        if (null == sessionValue || false == (DefaultValue.SESSION_VALUE_VALUE == Long.parseLong(sessionValue.toString()))) {
            log.warn("===>query user controller Permission denied error");
            return RpcResponse.error(ErrorCode.PERMISSION_DENIED_ERROR);
        }
        if (RpcCommonUtil.isEmpty(user)) {
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
        if (null == sessionValue || false == (DefaultValue.SESSION_VALUE_VALUE == Long.parseLong(sessionValue.toString()))) {
            log.warn("===>query user controller Permission denied error");
            return RpcResponse.error(ErrorCode.PERMISSION_DENIED_ERROR);
        }
        if (RpcCommonUtil.isEmpty(user) || RpcCommonUtil.isEmpty(pageNum)) {
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
     * 用户注册接口
     * （只需要基本信息即可注册，基本信息[必填项]如下： ）
     * （用户名，密码，性别，手机号,角色）
     *
     * @param user 承载注册信息的载体
     * @return RpcResponse注册用户信息
     */
    @RequestMapping("/register")
    public RpcResponse register(User user) {
        if (RpcCommonUtil.isEmpty(user) || RpcCommonUtil.isEmpty(user.getUserName()) ||
                RpcCommonUtil.isEmpty(user.getPassWord()) || RpcCommonUtil.isEmpty(user.getSex()) ||
                RpcCommonUtil.isEmpty(user.getPhoneNumber()) || false == RpcCommonUtil.isEnumCode(Sex.class, Integer.parseInt(user.getSex()))) {
            log.warn("===>register controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        User userResult = null;
        try {
            userResult = userService.registerUserService(user);
        } catch (Exception e) {
            log.error("===>register controller error:{}", e.getMessage());
            return RpcResponse.error(ErrorCode.SYSTEM_ERROR);
        }
        if (null == userResult) {
            log.warn("===>register controller fail register message:{}", user.toString());
            return RpcResponse.error(ErrorCode.REGISTER_FAIL_ERROR);
        }
        log.info("===>register controller success register message:{}", userResult.toString());
        return RpcResponse.success(userResult);
    }
//
//    /**
//     * 完善个人信息接口
//     * (不能修改ID和用户名)
//     *
//     * @param user 待完善信息载体
//     * @return 完善用户的Id
//     */
//    @RequestMapping("/complete")
//    public RpcResponse complete(User user) {
//        if (CheckDataUtils.isEmpty(user.getId())) {
//            log.warn("complete接口入参错误！");
//            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
//        }
//        if (null != user.getSex()) {
//            if (false == CheckDataUtils.isSex(user.getSex())) {
//                log.warn("complete接口入参错误！(性别字段入参失败)");
//                return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
//            }
//        }
//        log.info("待完善的信息为" + user.toString());
//        return userService.completeUserInfoService(user);
//    }
//
//    /**
//     * 作废用户账户接口
//     *
//     * @param id 待作废用户Id
//     * @return 作废结果：作废用户账户的Id
//     */
//    @RequestMapping("/invalid")
//    public RpcResponse invalid(Long id) {
//        if (CheckDataUtils.isEmpty(id)) {
//            log.warn("invalid接口入参错误");
//            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
//        }
//        log.info("要作废的用户Id为" + id);
//        return userService.invalidUserByIdService(id);
//    }
//
//    /**
//     * 冻结用户账户接口
//     *
//     * @param id 待冻结用户Id
//     * @return 冻结结果：冻结用户账户的Id
//     */
//    @RequestMapping("/frozen")
//    public RpcResponse frozen(Long id) {
//        if (CheckDataUtils.isEmpty(id)) {
//            log.warn("frozen接口入参错误");
//            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
//        }
//        log.info("要冻结的用户Id为" + id);
//        return userService.frozenUserByIdService(id);
//    }
//
//    /**
//     * 解冻用户账户接口
//     *
//     * @param id 待解冻用户Id
//     * @return 解冻结果：解冻用户账户的Id
//     */
//    @RequestMapping("/unfrozen")
//    public RpcResponse unfrozen(Long id) {
//        if (CheckDataUtils.isEmpty(id)) {
//            log.warn("unfrozen接口入参错误");
//            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
//        }
//        log.info("要解冻的用户Id为" + id);
//        return userService.unfrozenUserByIdService(id);
//    }
//
//    /**
//     * 测试RabbitMq的接口
//     */
//    @Autowired
//    UserSenderMq userSenderMq;
//
//    @RequestMapping("/test")
//    public RpcResponse test() {
//        userSenderMq.sendMsg("sendUser.direct", "sendUserRoutingKey", "hello");
//        return RpcResponse.success();
//    }
//
//    @RequestMapping("/setsession")
//    public RpcResponse setSess(HttpServletRequest request, String name) {
//        return RpcResponse.success(SessionUtils.setSession(request, name));
//    }
//
//    @RequestMapping("/getsession")
//    public RpcResponse getSess(HttpServletRequest request, String sessionId) {
//        return RpcResponse.success(SessionUtils.getSession(request, sessionId));
//    }
}
