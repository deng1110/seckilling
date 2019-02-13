package com.deng.seckilling.service;

import com.deng.seckilling.constant.DefaultValue;
import com.deng.seckilling.constant.ErrorCode;
import com.deng.seckilling.dao.UserMapper;
import com.deng.seckilling.po.UserPo;
import com.deng.seckilling.rpc.RpcResponse;
import com.deng.seckilling.util.CheckDataUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户相关操作service
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/1/29 11:47
 */
@Service
@Slf4j
public class UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 验证用户的用户名密码Service
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return boolean：true(成功)，false(失败)
     */
    public RpcResponse verifyUserService(String userName, String passWord) {
        List<UserPo> userPoList = new ArrayList<UserPo>();
        try {
            userPoList = userMapper.getUsersByUserNameAndPassword(userName, passWord);
        } catch (Exception e) {
            log.error("验证用户登录sql执行失败！！！错误信息为" + e);
            return RpcResponse.error(ErrorCode.USERLOGIN_FAIL_ERROR);
        }
        if (false == CheckDataUtils.isEmpty(userPoList)) {
            log.info(userName + "登录成功");
            userPoList.get(0).setPassWord("*****");
            return RpcResponse.success(userPoList.get(0));
        } else {
            log.warn(userName + "登录失败");
            return RpcResponse.error(ErrorCode.USERLOGIN_FAIL_ERROR);
        }
    }

    /**
     * 根据条件查询符合要求的用户集合Service
     * （传输过来什么条件，查询规则就是什么条件。如果没传任何条件默认查所有）
     *
     * @param userPo 存储查询条件的实体
     * @return RpcResponse 查询到的用户集合
     */
    public RpcResponse<List<UserPo>> queryUsersByConditionService(UserPo userPo) {
        List<UserPo> userPoList = new ArrayList<UserPo>();
        try {
            //密码不能作为查询条件
            userPo.setPassWord("");
            userPoList = userMapper.getUserByCondition(userPo);
        } catch (Exception e) {
            log.error("按条件查找用户sql执行失败！！！，错误信息为" + e);
            return RpcResponse.error(ErrorCode.QUERYUSER_FAIL_ERROR);
        }
        if (false == CheckDataUtils.isEmpty(userPoList)) {
            log.info("按条件查询用户成功，共查到" + userPoList.size() + "条数据");
            return RpcResponse.success(userPoList);
        } else {
            log.warn("按条件查询用户成功，但查出0条数据");
            return RpcResponse.error(ErrorCode.QUERYUSER_NULL_ERROR);
        }
    }

    /**
     * 根据条件查询符合要求的用户集合Service
     * （带分页功能）
     *
     * @param pageNum  第几页
     * @param pageSize 每页的数据项
     * @param userPo   存储查询条件的实体
     * @return RpcResponse 查询到的用户集合
     */
    public RpcResponse queryUsersByConditionService(Integer pageNum, Integer pageSize, UserPo userPo) {
        List<UserPo> userPoList = new ArrayList<UserPo>();
        PageHelper.startPage(pageNum, pageSize);
        try {
            //密码不能作为查询条件
            userPo.setPassWord("");
            userPoList = userMapper.getUserByCondition(userPo);
        } catch (Exception e) {
            log.error("带分页的按条件查找用户sql执行失败！！！，错误信息为" + e);
            return RpcResponse.error(ErrorCode.QUERYUSER_FAIL_ERROR);
        }
        if (false == CheckDataUtils.isEmpty(userPoList)) {
            log.info("带分页的按条件查询用户成功，共查到" + userPoList.size() + "条数据");
            PageInfo<UserPo> pageInfo = new PageInfo<UserPo>(userPoList);
            return RpcResponse.success(pageInfo);
        } else {
            log.warn("带分页的按条件查询用户成功，但查出0条数据");
            return RpcResponse.error(ErrorCode.QUERYUSER_NULL_ERROR);
        }
    }

    /**
     * 注册用户service
     * 会验证用户名是否重复！！！
     * （只注册基本信息）
     *
     * @param userPo 承载注册信息的载体
     * @return 注册成功后返回用户信息
     */
    public RpcResponse registerUserService(UserPo userPo) {
        //验证该用户名是否已经存在
        UserPo userPo1 = new UserPo();
        userPo1.setUserName(userPo.getUserName());
        if (0 == this.queryUsersByConditionService(userPo1).getCode()) {
            log.warn(userPo.getUserName() + "的用户名已存在");
            return RpcResponse.error(ErrorCode.USERNAME_EXIT_ERROR);
        }
        //若用户名可用，则进行注册
        int result = 0;
        try {
            userPo.setStatus(DefaultValue.USERSTATUS_VALUE_NORMAL);
            result = userMapper.insertUserBasicInfo(userPo);
        } catch (Exception e) {
            log.error("注册用户sql执行失败！！！，错误信息为" + e);
            return RpcResponse.error(ErrorCode.REGISTER_FAIL_ERROR);
        }
        if (1 == result) {
            log.info(userPo.getUserName() + "注册成功，ID为" + userPo.getId());
            //把密码去掉，不可返回密码！
            userPo.setPassWord("*****");
            return RpcResponse.success(userPo);
        }
        log.warn(userPo.getUserName() + "注册失败");
        return RpcResponse.error(ErrorCode.REGISTER_FAIL_ERROR);
    }

    /**
     * 完善个人信息Service
     * 会验证传过来的用户Id是否存在！！！
     *
     * @param userPo 待完善的信息的载体
     * @return 完善成功返回用户Id
     */
    public RpcResponse completeUserInfoService(UserPo userPo) {
        //验证是否存在该用户
        UserPo userPo1 = new UserPo();
        userPo1.setId(userPo.getId());
        if (0 == this.queryUsersByConditionService(userPo1).getCode()) {
            //若用户存在，则可完善信息
            int result = 0;
            //完善信息不可更改用户名（写此代码的时候的逻辑是用户名一旦创建不可更改）
            userPo.setUserName("");
            //完善信息的时候用户账户的状态不可更改，可通过其他专用方法更改
            userPo.setStatus("");
            //完善信息不可更改用户角色，可通过其他专用方法更改
            userPo.setRank("");
            try {
                result = userMapper.updateUserInfo(userPo);
            } catch (Exception e) {
                log.error("完善用户信息sql执行失败！！！，错误信息为" + e);
                return RpcResponse.error(ErrorCode.COMPLETE_USERINFO_ERROR);
            }
            if (1 == result) {
                log.info(userPo.getId() + "完善用户信息成功");
                return RpcResponse.success(userPo.getId());
            }
            log.warn(userPo.getId() + "完善用户信息失败");
            return RpcResponse.error(ErrorCode.COMPLETE_USERINFO_ERROR);
        }
        log.warn("ID为" + userPo.getId() + "的用户不存在！");
        return RpcResponse.error(ErrorCode.USER_NOTEXIT_ERROR);
    }

    /**
     * 作废用户账户Service
     * 会验证用户是否存在！！！
     *
     * @param id 待作废的用户账户Id
     * @return 作废成功的用户账户Id
     */
    public RpcResponse invalidUserByIdService(Long id) {
        //验证用户Id是否存在
        UserPo userPo1 = new UserPo();
        userPo1.setId(id);
        //如果用户Id存在
        if (0 == this.queryUsersByConditionService(userPo1).getCode()) {
            userPo1.setStatus(DefaultValue.USERSTATUS_VALUE_INVALID);
            int result = 0;
            try {
                result = userMapper.updateUserInfo(userPo1);
            } catch (Exception e) {
                log.error("作废用户账户sql执行失败！！！，错误信息为" + e);
                return RpcResponse.error(ErrorCode.INVALID_USER_ERROR);
            }
            if (1 == result) {
                log.info(id + "的用户账户作废成功");
                return RpcResponse.success(userPo1.getId());
            }
            log.warn(userPo1.getId() + "的用户账户作废失败");
            return RpcResponse.error(ErrorCode.INVALID_USER_ERROR);

        }
        log.warn("ID为" + id + "的用户不存在！");
        return RpcResponse.error(ErrorCode.USER_NOTEXIT_ERROR);
    }

    /**
     * 冻结用户账户Service
     * 会验证用户Id是否存在！！！
     *
     * @param id 待冻结的用户Id
     * @return 冻结成功的用户Id
     */
    public RpcResponse frozenUserByIdService(Long id) {
        //验证用户Id是否存在
        UserPo userPo1 = new UserPo();
        userPo1.setId(id);
        //如果用户Id存在
        if (0 == this.queryUsersByConditionService(userPo1).getCode()) {
            userPo1.setStatus(DefaultValue.USERSTATUS_VALUE_FROZEN);
            int result = 0;
            try {
                result = userMapper.updateUserInfo(userPo1);
            } catch (Exception e) {
                log.error("冻结用户账户sql执行失败！！！，错误信息为" + e);
                return RpcResponse.error(ErrorCode.FROZEN_USER_ERROR);
            }
            if (1 == result) {
                log.info(id + "的用户账户冻结成功");
                return RpcResponse.success(userPo1.getId());
            }
            log.warn(userPo1.getId() + "的用户账户冻结失败");
            return RpcResponse.error(ErrorCode.FROZEN_USER_ERROR);
        }
        log.warn("ID为" + id + "的用户不存在！");
        return RpcResponse.error(ErrorCode.USER_NOTEXIT_ERROR);
    }


    /**
     * 分页demo
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public RpcResponse getUserbyPage(Integer pageNum, Integer pageSize) {

        UserPo userPo = new UserPo();
        userPo.setSex("male");
        PageHelper.startPage(pageNum, pageSize);
        List<UserPo> listUser = userMapper.getUserByCondition(userPo);
        PageInfo<UserPo> pageInfo = new PageInfo<UserPo>(listUser);
        return RpcResponse.success(pageInfo);
    }
}
