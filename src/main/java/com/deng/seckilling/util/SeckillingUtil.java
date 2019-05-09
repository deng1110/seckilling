package com.deng.seckilling.util;

import com.deng.seckilling.constant.Rank;
import com.deng.seckilling.rpc.util.CheckDataUtils;

/**
 * Seckilling工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/2 18:24
 */
public class SeckillingUtil {

    public static boolean isCommonRank(Integer rank) {
        if (CheckDataUtils.isEmpty(rank)) {
            return false;
        }
        return Rank.BUYER.getCode().equals(rank) || Rank.SELLER.getCode().equals(rank);
    }

}
