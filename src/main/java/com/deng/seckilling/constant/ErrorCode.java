package com.deng.seckilling.constant;

import com.deng.seckilling.rpc.constant.ErrorInfo;

/**
 * ErrorCode常量表
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 15:25
 */
public class ErrorCode {

    public static final ErrorInfo SYSTEM_ERROR = new ErrorInfo(9999, "系统繁忙，请稍后再试");
    public static final ErrorInfo PERMISSION_DENIED_ERROR = new ErrorInfo(1000, "权限不足");
    public static final ErrorInfo SECKILLING_PARAMS_ERROR = new ErrorInfo(1001, "入参不能为空或格式错误");
    public static final ErrorInfo USERLOGIN_FAIL_ERROR = new ErrorInfo(1002, "用户名或者密码错误");
    public static final ErrorInfo QUERYUSER_NULL_ERROR = new ErrorInfo(1003, "查询成功，但查出零条数据");
    public static final ErrorInfo SHOPNAME_EXIST_ERROR = new ErrorInfo(1004, "已存在相同店铺名称");
    public static final ErrorInfo USERNAME_EXIT_ERROR = new ErrorInfo(1005, "用户名已存在");
    public static final ErrorInfo USER_NOTEXIT_ERROR = new ErrorInfo(1006, "该用户不存在");
    public static final ErrorInfo COMPLETE_USERINFO_ERROR = new ErrorInfo(1007, "完善个人信息失败");
    public static final ErrorInfo INVALID_USER_ERROR = new ErrorInfo(1009, "作废用户账户失败");
    public static final ErrorInfo FROZEN_USER_ERROR = new ErrorInfo(1010, "冻结用户账户失败");
    public static final ErrorInfo NOTNORMALUSER_CANNOT_FROZEN = new ErrorInfo(1011, "用户账户状态非正常不允许冻结");
    public static final ErrorInfo NOTFROZENUSER_CANNOT_UNFROXEN = new ErrorInfo(1012, "用户账户之前非冻结状态不允许解冻");
    public static final ErrorInfo SAVE_SHOPINFO_ERROR = new ErrorInfo(1013, "保存商铺信息失败");
    public static final ErrorInfo MODIFYSHOPNAME_SAME_ERROR = new ErrorInfo(1014, "您所修改的店铺名称和之前的相同");
    public static final ErrorInfo UNFROZEN_USER_ERROR = new ErrorInfo(1015, "解冻用户失败");
    public static final ErrorInfo SHOPINFO_NOTEXIST_ERROR = new ErrorInfo(1016, "该店铺ID不存在");
    public static final ErrorInfo REGIEST_FAIL_ERROR = new ErrorInfo(1017, "注册失败");
    public static final ErrorInfo NOT_LOGIN_ERROR = new ErrorInfo(1018, "请先登录");
    public static final ErrorInfo BRAND_NOTEXIST_ERROR = new ErrorInfo(1019, "该品牌不存在");
    public static final ErrorInfo CATEGORY_NOTEXIST_ERROR = new ErrorInfo(1020, "该分类不存在 ");
    public static final ErrorInfo SPUNO_EXIST_ERROR = new ErrorInfo(1021, "SPU编号已存在");
    public static final ErrorInfo SAVE_SPU_ERROR = new ErrorInfo(1022, "新增Spu失败");
    public static final ErrorInfo CATEGORY_EXIST_ERROR = new ErrorInfo(1021, "该分类名称已存在 ");
    public static final ErrorInfo SAVE_CATEGORY_ERROR = new ErrorInfo(1022, "增加分类失败");
    public static final ErrorInfo BRAND_EXIST_ERROR = new ErrorInfo(1023, "该品牌名称已存在");
    public static final ErrorInfo SAVE_BRAND_ERROR = new ErrorInfo(1024, "保存品牌名称失败");
    public static final ErrorInfo SPEC_EXIST_ERROR = new ErrorInfo(1025, "规格名称已存在");
    public static final ErrorInfo SPECNO_EXIST_ERROR = new ErrorInfo(1026, "规格编号已存在");
    public static final ErrorInfo SAVE_SPEC_ERROR = new ErrorInfo(1027,"保存规格失败");
    public static final ErrorInfo SPUID_NOTEXIST_ERROR = new ErrorInfo(1028,"该SPU不存在");
    public static final ErrorInfo SPECID_NOTEXIST_ERROR = new ErrorInfo(1029,"该规格不存在");
    public static final ErrorInfo SAVE_SPUSPEC_ERROR = new ErrorInfo(1030, "保存SPU和规格关联失败");
}
