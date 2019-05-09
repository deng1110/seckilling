package com.deng.seckilling.service;

import com.deng.seckilling.constant.Rank;
import com.deng.seckilling.constant.Sex;
import com.deng.seckilling.constant.Status;
import com.deng.seckilling.dao.UserMapper;
import com.deng.seckilling.dto.BaseUserInfoDTO;
import com.deng.seckilling.domain.User;
import com.deng.seckilling.rpc.util.CheckDataUtils;
import com.deng.seckilling.rpc.util.DataUtils;
import com.deng.seckilling.rpc.util.EnumUtils;
import com.deng.seckilling.rpc.util.Md5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
        List<User> userList = userMapper.listUser(new User(userName, Md5Utils.encryptMd5(passWord), Status.NORMAL.getValue()));
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
        user.setPassWord(Md5Utils.encryptMd5(user.getPassWord()));
        return userMapper.listUser(user);
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
        List<User> userList = queryUsersByConditionService(new User(user.getId()));
        if (CheckDataUtils.isEmpty(userList)) {
            log.warn("===>id:{} not exist", user.getId());
            return null;
        }
        if (false == CheckDataUtils.isEmpty(user.getPassWord())) {
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
}
