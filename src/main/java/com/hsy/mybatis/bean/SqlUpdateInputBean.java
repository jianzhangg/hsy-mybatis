package com.hsy.mybatis.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hsy.common.bean.IdBean;
import com.hsy.common.constant.CommonConstant;
import com.hsy.common.context.ContextHolder;
import com.hsy.common.utils.DateUtils;
import com.hsy.common.utils.ObjectUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 张梓枫
 * @date 2019年5月22日下午2:04:03
 * @description 数据更新公用字段
 */
@Getter
@Setter
public class SqlUpdateInputBean extends IdBean<Integer> {

	private static final long serialVersionUID = -1828043834392016292L;

	/**
	 * 最后更新人
	 */
	private Integer lastModifiedBy = ObjectUtils.isNotEmpty(ContextHolder.getUser()) ? ContextHolder.getUser().getId() : null;

	/**
	 * 最后更新时间
	 */
	@JsonFormat(pattern = DateUtils.PATTERN_NORMAL, timezone = CommonConstant.TIMEZONE)
	private Date lastModifiedDate = DateUtils.nowDate();
}
