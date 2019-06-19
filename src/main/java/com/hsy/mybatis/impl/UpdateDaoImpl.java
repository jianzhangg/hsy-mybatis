package com.hsy.mybatis.impl;

import java.text.MessageFormat;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.hsy.common.utils.ObjectUtils;
import com.hsy.mybatis.UpdateDao;
import com.hsy.mybatis.exception.IllegalClassTypeException;

/**
 * @author 张梓枫
 * @Description
 * @date: 2019年1月3日 下午2:12:54
 */
@Repository
public class UpdateDaoImpl extends SqlSessionDaoSupport implements UpdateDao {

    private static Log log = LogFactory.getLog(UpdateDaoImpl.class);

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public int execute(String sqlId, Object params) {
        if (log.isDebugEnabled()) {
            log.debug("execute Start.");
        }
		if (ObjectUtils.isEmpty(sqlId)) {
			throw new IllegalClassTypeException("sqlId can not be empty.");
		}
        log.info(MessageFormat.format("sqlId[{0}]，输入参数：[{1}]", sqlId, params));
        StopWatch stopWatch = new StopWatch(sqlId);
        stopWatch.start();
        SqlSession sqlSession = this.getSqlSession();
        int row = sqlSession.update(sqlId, params);
        if (log.isDebugEnabled()) {
            log.debug("execute End. success count:" + row);
        }
        stopWatch.stop();
        log.info("----------sqlId：" + sqlId + "执行完毕，耗时" + stopWatch.getTotalTimeMillis() + "毫秒----------");
        return row;
    }

}
