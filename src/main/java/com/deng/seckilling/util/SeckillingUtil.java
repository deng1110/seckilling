package com.deng.seckilling.util;

import com.deng.seckilling.constant.Rank;
import com.deng.seckilling.constant.Status;
import com.deng.seckilling.rpc.RpcCommonUtil;

/**
 * Seckilling工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/2 18:24
 */
public class SeckillingUtil {

    public static boolean isCommonRank(Integer rank) {
        if (RpcCommonUtil.isEmpty(rank)) {
            return false;
        }
        return rank == Rank.BUYER.getCode() || rank == Rank.SELLER.getCode();
    }

}
