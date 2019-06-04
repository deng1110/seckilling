package com.deng.seckilling.rpc.util;

import com.github.pagehelper.PageInfo;
import org.springframework.ui.Model;

/**
 * 分页返回值
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/4 17:22
 */
public class FenyeUtils {

    /**
     * 向Model中增加分页返回信息
     *
     */
    public static <T> void setFenyeValue(Model model, PageInfo<T> pageInfo) {
        model.addAttribute("data", pageInfo.getList());
        model.addAttribute("totalPage", pageInfo.getPages());
        model.addAttribute("totalData", pageInfo.getTotal());
        model.addAttribute("pageNum", pageInfo.getPageNum());
        model.addAttribute("pageSize", pageInfo.getPageSize());
        model.addAttribute("currentPageSize", pageInfo.getSize());
        model.addAttribute("startRow", pageInfo.getStartRow());
        model.addAttribute("endRow", pageInfo.getEndRow());
        model.addAttribute("prePage", pageInfo.getPrePage());
        model.addAttribute("nextPage", pageInfo.getNextPage());
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        model.addAttribute("hasPreviousPage", pageInfo.isHasPreviousPage());
        model.addAttribute("hasNextPage", pageInfo.isHasNextPage());
        model.addAttribute("lastPage", pageInfo.getLastPage());
        model.addAttribute("firstPage", pageInfo.getFirstPage());

    }

}
