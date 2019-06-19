package com.hsy.mybatis.utils;

import java.util.List;

import com.github.pagehelper.Page;
import com.hsy.common.exception.BLogicException;
import com.hsy.core.bean.ResultInfo;
import com.hsy.resource.enums.ResultCode;

/**
 * @author 张梓枫
 * @date 2019年5月22日下午1:14:58
 * @description
 */
public final class PageResultUtils {

	public static <T> ResultInfo<List<T>> createRet(List<T> list) {
		if (!(list instanceof Page)) {
			throw new BLogicException("list unable convert mybatis page.");
		}
		ResultInfo<List<T>> bean = new ResultInfo<>(ResultCode.SUCCESS);
		Page<T> page = (Page<T>) list;
		bean.setDatas(page.getResult());
		bean.setPageNum(page.getPageNum());
		bean.setPageSize(page.getPageSize());
		bean.setTotal(page.getTotal());
		bean.setPageTotals(page.getPages());
		return bean;
	}
}
