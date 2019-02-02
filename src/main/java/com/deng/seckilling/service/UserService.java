package com.deng.seckilling.service;

import com.deng.seckilling.constant.ErrorCode;
import com.deng.seckilling.dao.UserMapper;
import com.deng.seckilling.po.UserPo;
import com.deng.seckilling.rpc.RpcResponse;
import com.deng.seckilling.util.CheckDataUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserService {

    @Resource
    UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

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
            logger.error("验证用户登录sql执行失败！！！错误信息为" + e);
            return RpcResponse.error(ErrorCode.USERLOGIN_FAIL_ERROR);
        }
        if (false == CheckDataUtils.isEmpty(userPoList)) {
            logger.info(userName + "登录成功");
            return RpcResponse.success(userPoList.get(0));
        } else {
            logger.warn(userName + "登录失败");
            return RpcResponse.error(ErrorCode.USERLOGIN_FAIL_ERROR);
        }
    }

    /**
     * 根据条件查询符合要求的用户集合Service
     * （传输过来什么条件，查询规则就是什么条件。如果没传任何条件默认查所有）
     *
     * @param userPo 存储查询条件的实体
     * @return RpcResponse<List                                                                                                                                                                                                                                                               <                                                                                                                                                                                                                                                               UserPo>>满足要求的用户集合
     */
    public RpcResponse<List<UserPo>> queryUsersByConditionService(UserPo userPo) {
        List<UserPo> userPoList = new ArrayList<UserPo>();
        try {
            userPoList = userMapper.getUserByCondition(userPo);
        } catch (Exception e) {
            logger.error("按条件查找用户sql执行失败！！！，错误信息为" + e);
            return RpcResponse.error(ErrorCode.QUERYUSER_FAIL_ERROR);
        }
        if (false == CheckDataUtils.isEmpty(userPoList)) {
            logger.info("按条件查询用户成功，共查到" + userPoList.size() + "条数据");
            return RpcResponse.success(userPoList);
        } else {
            logger.warn("按条件查询用户成功，但查出0条数据");
            return RpcResponse.error(ErrorCode.QUERYUSER_NULL_ERROR);
        }
    }

    /**
     * 注册用户service，会验证用户名是否重复！！！
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
            logger.warn(userPo.getUserName() + "的用户名已存在");
            return RpcResponse.error(ErrorCode.USERNAME_EXIT_ERROR);
        }
        //若用户名可用，则进行注册
        int result = 0;
        try {
            result = userMapper.insertUserBasicInfo(userPo);
        } catch (Exception e) {
            logger.error("注册用户sql执行失败！！！，错误信息为" + e);
            return RpcResponse.error(ErrorCode.REGISTER_FAIL_ERROR);
        }
        if (1 == result) {
            logger.info(userPo.getUserName() + "注册成功，ID为" + userPo.getId());
            //把密码去掉，不返回密码！
            userPo.setPassWord("");
            return RpcResponse.success(userPo);
        }
        logger.warn(userPo.getUserName() + "注册失败");
        return RpcResponse.error(ErrorCode.REGISTER_FAIL_ERROR);
    }

    /**
     * 完善个人信息Service，会验证传过来的用户Id是否存在！！！
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
            try {
                result = userMapper.updateUserInfo(userPo);
            } catch (Exception e) {
                logger.error("完善用户信息sql执行失败！！！，错误信息为" + e);
                return RpcResponse.error(ErrorCode.COMPLETE_USERINFO_ERROR);
            }
            if (1 == result) {
                logger.info(userPo.getId() + "完善用户信息成功");
                return RpcResponse.success(userPo.getId());
            }
            logger.warn(userPo.getId() + "完善用户信息失败");
            return RpcResponse.error(ErrorCode.COMPLETE_USERINFO_ERROR);
        }
        logger.warn("ID为" + userPo.getId() + "的用户不存在！");
        return RpcResponse.error(ErrorCode.USER_NOTEXIT_ERROR);
    }

    /**
     * 分页demo
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<UserPo> getUserbyPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserPo> listUser = userMapper.getUserByCondition(new UserPo());
        PageInfo<UserPo> pageInfo = new PageInfo<UserPo>(listUser);
        return pageInfo;
    }
}
