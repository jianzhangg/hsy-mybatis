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
 * @date 2019年5月22日下午1:58:20
 * @description 新增数据时公用字段信息
 */
@Getter
@Setter
public class SqlInsertInputBean extends IdBean<Integer> {

	private static final long serialVersionUID = -335880269306629105L;

	/**
	 * 创建人
	 */
	private Integer createBy = ObjectUtils.isNotEmpty(ContextHolder.getUser()) ? ContextHolder.getUser().getId() : null;

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = DateUtils.PATTERN_NORMAL, timezone = CommonConstant.TIMEZONE)
	private Date createDate = DateUtils.nowDate();

	/**
	 * 最后更新人
	 */
	private Integer lastModifiedBy = ObjectUtils.isNotEmpty(ContextHolder.getUser()) ? ContextHolder.getUser().getId() : null;

	/**
	 * 最后更新时间
	 */
	@JsonFormat(pattern = DateUtils.PATTERN_NORMAL, timezone = CommonConstant.TIMEZONE)
	private Date lastModifiedDate = DateUtils.nowDate();

	/**
	 * 删除标识，0-未删除，1-删除
	 */
	private Integer isDelete = 0;
}
