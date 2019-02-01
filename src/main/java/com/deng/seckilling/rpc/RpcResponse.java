package com.deng.seckilling.rpc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Rpc返回值
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/1/29 11:01
 */
public class RpcResponse<T> implements Serializable {
    /**
     * rpc返回的编码
     */
    @Getter
    @Setter
    private int code;

    /**
     * rpc返回的消息
     */
    @Getter
    @Setter
    private String msg;

    /**
     * rpc返回的业务数据
     */
    @Getter
    @Setter
    private T data;

    /**
     * 无参构造器
     */
    public RpcResponse() {
    }

    /**
     * 构造器
     *
     * @param code Response的Code
     */
    private RpcResponse(int code) {
        this.code = code;
    }

    /**
     * 私有构造器
     *
     * @param errorInfo 错误提示信息{@code com.haodf.communal.support.response.ErrorInfo}
     */
    private RpcResponse(ErrorInfo errorInfo) {
        this.code = errorInfo.getCode();
        this.msg = errorInfo.getMessage();
    }

    /**
     * 私有构造器
     *
     * @param errorInfo 错误提示信息{@code com.haodf.communal.support.response.ErrorInfo}
     * @param data      rpc返回的数据
     */
    private RpcResponse(ErrorInfo errorInfo, T data) {
        this.code = errorInfo.getCode();
        this.msg = errorInfo.getMessage();
        this.data = data;
    }

    /**
     * 创建成功返回的RpcResponse
     *
     * @return RpcResponse实例
     */
    public static RpcResponse success() {
        return new RpcResponse<>(0);
    }

    /**
     * 创建成功返回的RpcResponse
     *
     * @param data rpc返回的数据
     * @param <T>  rpc返回的数据的泛型
     * @return RpcResponse实例
     */
    public static <T> RpcResponse<T> success(T data) {
        return new RpcResponse<>(new ErrorInfo(0, ""), data);
    }

    /**
     * 创建失败返回的RpcResponse
     *
     * @param errorInfo 错误提示信息{@code com.haodf.communal.support.response.ErrorInfo}
     * @param data      rpc返回的数据
     * @param <T>       rpc返回的数据的泛型
     * @return RpcResponse实例
     */
    public static <T> RpcResponse<T> error(ErrorInfo errorInfo, T data) {
        return new RpcResponse<>(errorInfo, data);
    }

    /**
     * 创建失败返回的RpcResponse
     *
     * @param errorInfo 错误提示信息{@code com.haodf.communal.support.response.ErrorInfo}
     * @return RpcResponse实例
     */
    public static RpcResponse error(ErrorInfo errorInfo) {
        return new RpcResponse<>(errorInfo);
    }

    /**
     * 返回当前RpcResponse的错误提示信息
     *
     * @return 当前RpcResponse的错误提示信息
     */
    @JsonIgnore
    public ErrorInfo getErrorInfo() {
        return new ErrorInfo(this.getCode(), this.getMsg());
    }
}
