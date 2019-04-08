package com.deng.seckilling.service;

import com.deng.seckilling.constant.Rank;
import com.deng.seckilling.constant.Sex;
import com.deng.seckilling.constant.Status;
import com.deng.seckilling.dao.UserMapper;
import com.deng.seckilling.dto.BaseUserInfoDTO;
import com.deng.seckilling.po.User;
import com.deng.seckilling.rpc.RpcCommonUtil;
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
     * @return 当前用户信息
     */
    public User verifyUserService(String userName, String passWord) {
        List<User> userList = userMapper.listUser(new User(userName, RpcCommonUtil.encryptMd5(passWord), Status.NORMAL.getValue()));
        if (RpcCommonUtil.isEmpty(userList)) {
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
        user.setPassWord(RpcCommonUtil.encryptMd5(user.getPassWord()));
        List<User> userList = userMapper.listUser(user);
        if (RpcCommonUtil.isEmpty(userList)) {
            return null;
        }
        return userList;
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
        List<User> userList = new ArrayList<User>();
        PageHelper.startPage(pageNum, pageSize);
        userList = queryUsersByConditionService(user);
        if (RpcCommonUtil.isEmpty(userList)) {
            return null;
        }
        return new PageInfo<User>(userList);
    }

    /**
     * 用户基础信息注册service
     *
     * @param baseUserInfoDTO 参数实体
     * @return 注册成功的用户信息
     */
    public User registerUserService(BaseUserInfoDTO baseUserInfoDTO) {
        baseUserInfoDTO.setPassWord(RpcCommonUtil.encryptMd5(baseUserInfoDTO.getPassWord()));
        User user = new User();
        RpcCommonUtil.entityTransform(baseUserInfoDTO, user);
        user.setStatus(Status.NORMAL.getValue());
        user.setRank(RpcCommonUtil.getEnumValueByCode(Rank.class, baseUserInfoDTO.getRank()));
        user.setSex(RpcCommonUtil.getEnumValueByCode(Sex.class, baseUserInfoDTO.getSex()));
        int result = userMapper.insertUser(user);
        if (1 == result) {
            return user;
        }
        return null;
    }

    /**
     * 根据ID完善/更改个人信息Service，验证ID存在
     *
     * @param user 参数实体
     * @return 完善成功的用户信息
     */
    public User completeUserInfoService(User user) {
        if (false == isExist(user.getId())) {
            log.warn("===>id:{} not exist", user.getId());
            return null;
        }
        if (RpcCommonUtil.isEmpty(user.getPassWord())) {
            user.setPassWord(RpcCommonUtil.encryptMd5(user.getPassWord()));
        }
        user.setRank(null);
        user.setStatus(null);
        int result = userMapper.updateUser(user);
        if (1 == result) {
            return queryUsersByConditionService(new User(user.getId())).get(0);
        }
        return null;
    }

    /**
     * 根据ID作废用户账户Service
     *
     * @param id 待作废的用户账户Id
     * @return 作废成功的用户账户Id
     */
    public Long invalidUserByIdService(Long id) {
        User user = new User(id);
        user.setStatus(RpcCommonUtil.getEnumValueByCode(Status.class, 3));
        int result = userMapper.updateUser(user);
        if (1 == result) {
            return id;
        }
        return null;
    }

    /**
     * 根据ID冻结用户账户Service
     *
     * @param id 待冻结的用户Id
     * @return 冻结成功的用户Id
     */
    public Long frozenUserByIdService(Long id) {
        if (false == isExist(id)) {
            log.warn("===>id:{} not exist", id);
            return null;
        }
        User user = new User(id);
        user.setStatus(RpcCommonUtil.getEnumValueByCode(Status.class, 2));
        int result = userMapper.updateUser(user);
        if (1 == result) {
            return id;
        }
        return null;
    }

    /**
     * 解冻用户Service
     * 会验证该用户之前的状态是否为冻结状态
     *
     * @param id 待冻结的用户Id
     * @return 冻结成功后的用户Id
     */
    public Long unfrozenUserByIdService(Long id) {
        if (false == isExist(id)) {
            log.warn("===>id:{} not exist", id);
            return null;
        }
        List<User> userList = queryUsersByConditionService(new User(id));
        if (RpcCommonUtil.isEmpty(userList)) {
            return null;
        }
        String status = RpcCommonUtil.getEnumValueByCode(Status.class, 2);
        if (userList.get(0).getStatus().equals(status)) {
            log.warn("===>user status is not frozen");
            return null;
        }
        User user = new User(id);
        user.setStatus(RpcCommonUtil.getEnumValueByCode(Status.class, 1));
        int result = userMapper.updateUser(user);
        if (1 == result) {
            return id;
        }
        return null;
    }

    /**
     * 私有方法：判断ID是否存在
     *
     * @param id 用户Id
     * @return boolean:是否存在
     */
    public boolean isExist(Long id) {
        if (RpcCommonUtil.isEmpty(id)) {
            return false;
        }
        List<User> userList = queryUsersByConditionService(new User(id));
        if (RpcCommonUtil.isEmpty(userList)) {
            return false;
        }
        return true;
    }

    /**
     * 私有方法：判断name是否存在
     *
     * @param name 用户name
     * @return boolean:是否存在
     */
    public boolean isExist(String name) {
        if (RpcCommonUtil.isEmpty(name)) {
            return true;
        }
        List<User> userList = queryUsersByConditionService(new User(name));
        if (RpcCommonUtil.isEmpty(userList)) {
            return false;
        }
        return true;
    }
}
