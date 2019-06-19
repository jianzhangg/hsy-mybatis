package com.hsy.mybatis.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.github.pagehelper.PageHelper;
import com.hsy.common.bean.PageInfo;
import com.hsy.common.exception.BLogicException;
import com.hsy.common.utils.ObjectUtils;
import com.hsy.mybatis.QueryDao;
import com.hsy.mybatis.exception.IllegalClassTypeException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 张梓枫
 * @Description
 * @date: 2019年1月3日 上午9:54:03
 */
@Slf4j
@Repository
public class QueryDaoImpl extends SqlSessionDaoSupport implements QueryDao {
    
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public <E> E executeForObject(String sqlId, Object params, Class<E> clazz) {
        if (log.isDebugEnabled()) {
            log.debug("executeForObject Start.");
        }
		if (ObjectUtils.isEmpty(sqlId)) {
			throw new IllegalClassTypeException("sqlId can not be empty.");
		}
        log.info(MessageFormat.format("sqlId[{0}]，输入参数：[{1}]", sqlId, params));
        StopWatch stopWatch = new StopWatch(sqlId);
        stopWatch.start();

        SqlSession sqlSession = this.getSqlSession();
        Object obj = sqlSession.selectOne(sqlId, params);

        if (log.isDebugEnabled() && ObjectUtils.isNotEmpty(obj)) {
            log.debug("Return type:" + obj.getClass().getName());
        }

        E entity = null;
        try {
            if (ObjectUtils.isNotEmpty(clazz) && ObjectUtils.isNotEmpty(obj)) {
                entity = clazz.cast(obj);
            }
        } catch (ClassCastException e) {
            log.error("The illegal Class Type of the argument.");
            throw new IllegalClassTypeException(e);
        }

        if (log.isDebugEnabled()) {
            log.debug("executeForObject End.");
        }
        stopWatch.stop();
        log.info("----------sqlId：" + sqlId + "执行完毕，耗时" + stopWatch.getTotalTimeMillis() + "毫秒----------");
        log.debug(MessageFormat.format("sqlId[{0}]，返回值：[{1}]", sqlId, entity));
        return entity;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Map<String, Object> executeForMap(String sqlId, Object params) {
        if (log.isDebugEnabled()) {
            log.debug("executeForMap Start.");
        }
        Map rObj = (Map) this.executeForObject(sqlId, params, Map.class);
        if (log.isDebugEnabled()) {
            log.debug("executeForMap End.");
        }

        return rObj;
    }

    @Override
    public <E> List<E> executeForObjectList(String sqlId, Object params) {
        if (log.isDebugEnabled()) {
            log.debug("executeForObjectList Start.");
        }
		if (ObjectUtils.isEmpty(sqlId)) {
			throw new IllegalClassTypeException("sqlId can not be empty.");
		}
        log.info(MessageFormat.format("sqlId[{0}]，输入参数：[{1}]", sqlId, params));

        StopWatch stopWatch = new StopWatch(sqlId);
        stopWatch.start();

        SqlSession sqlSession = this.getSqlSession();
        List<E> list = sqlSession.selectList(sqlId, params);

        if (log.isDebugEnabled()) {
            log.debug("executeForObjectList End.");
        }

        if (log.isDebugEnabled()) {
            log.debug("executeForObject End.");
        }
        stopWatch.stop();
        log.info("----------sqlId：" + sqlId + "执行完毕，耗时" + stopWatch.getTotalTimeMillis() + "毫秒----------");
        log.info(MessageFormat.format("sqlId[{0}]，返回值：[{1}]", sqlId, list));
        return list;
    }

    @Override
    public <E> List<E> executeForObjectListByPage(String sqlId, Object params, PageInfo pageInfo) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("executeForObjectListByPage Start.");
        }
		if (ObjectUtils.isEmpty(sqlId)) {
			throw new IllegalClassTypeException("sqlId can not be empty.");
		}
        log.info(MessageFormat.format("sqlId[{0}]，输入参数：[{1}]", sqlId, params));
        if (ObjectUtils.isEmpty(pageInfo)) {
            throw new BLogicException("sqlId：" + sqlId + "的参数pageInfo对象不能为空!");
        }

        Integer pageNum = pageInfo.getPageNum();
        if (ObjectUtils.isEmpty(pageNum) || pageNum <= 0) {
            throw new BLogicException("sqlId：" + sqlId + "的参数的pageNum属性不能为空，并且必须大于0!");
        }
        Integer pageSize = pageInfo.getPageSize();
        if (ObjectUtils.isEmpty(pageSize) || pageSize <= 0) {
            throw new BLogicException("sqlId：" + sqlId + "的参数的pageSize属性不能为空，并且必须大于0!");
        }
        StopWatch stopWatch = new StopWatch(sqlId);
        stopWatch.start();

        SqlSession sqlSession = this.getSqlSession();
        PageHelper.startPage(pageNum, pageSize);
        String orderBy = pageInfo.getOrderBy();
        if (ObjectUtils.isNotEmpty(orderBy)) {
            PageHelper.orderBy(orderBy);
        }
        List<E> list = sqlSession.selectList(sqlId, params);
        if (log.isDebugEnabled()) {
            log.debug("executeForObjectListByOrderPage End.");
        }
        stopWatch.stop();
        log.info("----------sqlId：" + sqlId + "执行完毕，耗时" + stopWatch.getTotalTimeMillis() + "毫秒----------");
        log.info(MessageFormat.format("sqlId[{0}]，返回值：[{1}]", sqlId, list));
        return list;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<Map<String, Object>> executeForMapList(String sqlId, Object params) {
        if (log.isDebugEnabled()) {
            log.debug("executeForMapList Start.");
        }
        log.info(MessageFormat.format("sqlId[{0}]，输入参数：[{1}]", sqlId, params));

        List mapList = this.executeForObjectList(sqlId, params);
        if (log.isDebugEnabled()) {
            log.debug("executeForMapList End.");
        }

        return mapList;
    }

    @SuppressWarnings({"rawtypes", "unchecked" })
    @Override
    public List<Map<String, Object>> executeForMapListByPage(String sqlId, Object params, PageInfo pageInfo)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("executeForMapListByPage Start.");
        }
        log.info(MessageFormat.format("sqlId[{0}]，输入参数：[{1}]", sqlId, params));
        List mapList = this.executeForObjectListByPage(sqlId, params, pageInfo);
        return mapList;
    }

}
