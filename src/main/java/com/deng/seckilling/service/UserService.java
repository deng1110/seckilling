package com.deng.seckilling.service;

import com.deng.seckilling.constant.*;
import com.deng.seckilling.dao.UserMapper;
import com.deng.seckilling.domain.UserCookie;
import com.deng.seckilling.dto.BaseUserInfoDTO;
import com.deng.seckilling.domain.User;
import com.deng.seckilling.rpc.constant.RpcResponse;
import com.deng.seckilling.rpc.exception.ExecuteException;
import com.deng.seckilling.rpc.exception.RpcBizException;
import com.deng.seckilling.rpc.redis.RedisClient;
import com.deng.seckilling.rpc.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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
    private UserMapper userMapper;

    @Resource
    private RedisClient redisClient;

    /**
     * 用户基础信息注册service
     *
     * @param baseUserInfoDTO 参数实体
     * @return 注册成功的用户信息
     */
    public User registerUserService(BaseUserInfoDTO baseUserInfoDTO) {
        baseUserInfoDTO.setPassWord(Md5Utils.encryptMd5(baseUserInfoDTO.getPassWord()));
        User user = new User();
        DataUtils.entityTransform(baseUserInfoDTO, user);
        user.setStatus(Status.NORMAL.getValue());
        user.setRank(EnumUtils.getEnumValueByCode(Rank.class, baseUserInfoDTO.getRank()));
        user.setSex(EnumUtils.getEnumValueByCode(Sex.class, baseUserInfoDTO.getSex()));
        int result = userMapper.insertUser(user);
        if (1 == result) {
            return user;
        }
        return null;
    }

    /**
     * 验证用户的用户名密码Service
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return 当前用户信息
     */
    public User verifyUserService(String userName, String passWord) {
        List<User> userList = userMapper.listUser(new User(userName, passWord, Status.NORMAL.getValue()));
        if (CheckDataUtils.isEmpty(userList)) {
            return null;
        }
        return userList.get(0);
    }

    /**
     * 根据条件查询符合要求的用户集合Service
     *
     * @param user 参数实体
     * @return RpcResponse 查询到的用户集合
     */
    public List<User> queryUsersByConditionService(User user) {
        if (!CheckDataUtils.isEmpty(user.getPassWord())) {
            user.setPassWord(Md5Utils.encryptMd5(user.getPassWord()));
        }
        return userMapper.listUser(user);
    }

    /**
     * 用户踢下线
     */
    public void logOutService() {
        redisClient.del(DefaultValue.TOKEN);
    }

    /**
     * 根据条件分页查询符合要求的用户集合Service
     *
     * @param pageNum  第几页
     * @param pageSize 每页的数据项
     * @param user     参数实体
     * @return RpcResponse 查询到的用户集合
     */
    public PageInfo<User> queryUsersByConditionService(Integer pageNum, Integer pageSize, User user) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<User>(queryUsersByConditionService(user));
    }

    /**
     * 根据ID完善/更改个人信息Service，验证ID存在
     *
     * @param user 参数实体
     * @return 完善成功的用户信息
     */
    public User completeUserInfoService(User user) {
        if (!CheckDataUtils.isEmpty(user.getPassWord())) {
            user.setPassWord(Md5Utils.encryptMd5(user.getPassWord()));
        }
        user.setUserName(null);
        user.setRank(null);
        user.setStatus(null);
        return 1 == userMapper.updateUser(user) ? queryUsersByConditionService(new User(user.getId())).get(0) : null;
    }

    /**
     * 根据ID冻结用户账户Service
     *
     * @param id 待冻结的用户Id
     * @return 冻结成功的用户Id
     */
    public Long frozenUserByIdService(Long id) {
        User user = new User(id);
        user.setStatus(Status.FROZEN.getValue());
        return 1 == userMapper.updateUser(user) ? id : null;
    }

    /**
     * 解冻用户Service
     * 会验证该用户之前的状态是否为冻结状态
     *
     * @param id 待冻结的用户Id
     * @return 冻结成功后的用户Id
     */
    public Long unfrozenUserByIdService(Long id) {
        User user = new User(id);
        user.setStatus(Status.NORMAL.getValue());
        return 1 == userMapper.updateUser(user) ? id : null;
    }

    /**
     * 根据ID作废用户账户Service
     *
     * @param id 待作废的用户账户Id
     * @return 作废成功的用户账户Id
     */
    public Long invalidUserByIdService(Long id) {
        User user = new User(id);
        user.setStatus(Status.INVALID.getValue());
        return 1 == userMapper.updateUser(user) ? id : null;
    }

    /**
     * 判断name是否存在
     *
     * @param name 用户name
     * @return boolean:是否存在
     */
    public boolean isExist(String name) {
        if (CheckDataUtils.isEmpty(name)) {
            return true;
        }
        List<User> userList = queryUsersByConditionService(new User(name));
        return false == CheckDataUtils.isEmpty(userList);
    }

    /**
     * 验证登录者身份
     */
    public void isLoginOrRoot(Long id) {
        UserCookie userCookie = getUserFromRequest();
        if (CheckDataUtils.isEmpty(userCookie)) {
            throw new ExecuteException("not login.");
        }
        if (!Rank.ADMIN.getValue().equals(userCookie.getRank())) {
            if (!id.equals(userCookie.getId())) {
                throw new ExecuteException("Insufficient permissions");
            }
        }
    }

    /**
     * 从request读出User信息
     */
    public UserCookie getUserFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String token = getCookieValue(request);
        UserCookie userCookie = getUserByToken(response, token);
        return userCookie;
    }

    /**
     * 向response中增加cookie
     */
    public void setCookie(HttpServletResponse response, UserCookie userCookie) {
        String token = DefaultValue.TOKEN;
        userCookie.setLoginTimen(new Date());
        redisClient.set(token, JsonUtils.toJson(userCookie));
        Cookie cookie = new Cookie(DefaultValue.COOKIE_NAME, token);
        cookie.setMaxAge(DefaultValue.COOKIE_EXPIRE_TIME);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取出request中的cookie中的token
     */
    private String getCookieValue(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (DefaultValue.COOKIE_NAME.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * 根据Token从redis中取对应user实体，如果存在则延长cookie有效期
     */
    private UserCookie getUserByToken(HttpServletResponse response, String token) {
        if (CheckDataUtils.isEmpty(token)) {
            return null;
        }
        String userStr = redisClient.get(token);
        if (CheckDataUtils.isEmpty(userStr)) {
            return null;
        }
        UserCookie userCookie = JsonUtils.toObject(userStr, UserCookie.class);

        //延长cookie有效期
        setCookie(response, userCookie);
        return userCookie;
    }

}
