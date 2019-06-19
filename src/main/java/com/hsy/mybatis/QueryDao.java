package com.hsy.mybatis;

import java.util.List;
import java.util.Map;

import com.hsy.common.bean.PageInfo;

/**
 * @author 张梓枫
 * @Description mybatis查询类
 * @date:   2019年1月3日 上午9:40:11
 */
public interface QueryDao {
    
    /**
     * @author 张梓枫
     * @Description: 查询数据库返回对象
     * @param @param sqlId mybatis sqlId
     * @param @param params 查询数据库参数
     * @param @param clazz 返回对象泛型类
     * @param @return
     * @return E
     * @throws Exception 
     */
    <E> E executeForObject(String sqlId,Object params,Class<E> clazz);
    
    /**
     * @author 张梓枫
     * @Description:查询数据库返回MAP
     * @param @param sqlId mybatis sqlId
     * @param @param params 查询数据库参数
     * @param @return
     * @return Map<String,Object>
     * @throws Exception 
     */
    Map<String, Object> executeForMap(String sqlId, Object params);

    /**
     * @author 张梓枫
     * @Description:查询数据库返回对象集合
     * @param @param sqlId mybatis sqlId
     * @param @param params 查询数据库参数
     * @param @param clazz 返回对象泛型类
     * @param @return
     * @return E[]
     * @throws Exception 
     */
    <E> List<E> executeForObjectList(String sqlId, Object params);
    
    /**
     * @author 张梓枫
     * @Description:分页查询数据库返回对象集合
     * @param @param sqlId mybatis sqlId
     * @param @param params 查询数据库参数
     * @param @param pageInfo 分页参数
     * @param @return
     * @param @throws Exception
     * @return List<E>
     * @throws Exception 
     */
    <E> List<E> executeForObjectListByPage(String sqlId, Object params, PageInfo pageInfo) throws Exception;

    /**
     * @author 张梓枫
     * @Description:查询数据库返回Map集合
     * @param @param sqlId mybatis sqlId
     * @param @param params 查询数据库参数
     * @param @return
     * @param @throws Exception
     * @return List<E>
     * @throws Exception 
     */
    List<Map<String, Object>> executeForMapList(String sqlId, Object params);

    /**
     * @author 张梓枫
     * @Description:分页查询数据库返回Map集合
     * @param @param sqlId mybatis sqlId
     * @param @param params 查询数据库参数
     * @param @param pageInfo 分页参数
     * @param @return
     * @param @throws Exception
     * @return List<E>
     * @throws Exception 
     */
    List<Map<String, Object>> executeForMapListByPage(String sqlId, Object params, PageInfo pageInfo) throws Exception;
}
