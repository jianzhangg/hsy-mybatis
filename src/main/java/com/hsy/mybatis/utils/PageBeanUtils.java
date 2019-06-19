package com.hsy.mybatis.utils;

import java.util.List;

import com.github.pagehelper.Page;
import com.hsy.common.exception.BLogicException;
import com.hsy.core.utils.BeanUtils;

/**
 * @author 张梓枫
 * @date 2019年5月22日上午11:57:37
 * @description
 */
public final class PageBeanUtils {

	public static <S, T> List<T> copy(List<S> sList, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		return copyPage(sList, clazz);
	}

	private static <S, T> Page<T> copyPage(List<S> sList, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		if (!(sList instanceof Page)) {
			throw new BLogicException("list unable convert mybatis page.");
		}
		Page<S> page = (Page<S>) sList;
		Page<T> tPage = new Page<T>(page.getPageNum(), page.getPageSize());
		tPage.setTotal(page.getTotal());
		tPage.setPages(page.getPages());
		List<T> tList = BeanUtils.copy(sList, clazz);
		tPage.addAll(tList);
		return tPage;
	}
}
