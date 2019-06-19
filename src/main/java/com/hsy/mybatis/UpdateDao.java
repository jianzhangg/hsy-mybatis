package com.hsy.mybatis;

/**
 * @author 张梓枫
 * @Description insert,update,delete操作接口
 * @date:   2019年1月3日 下午2:11:00
 */
public interface UpdateDao {

    /**
     * @author 张梓枫
     * @Description: insert,update,delete操作接口
     * @param @param sqlId 
     * @param @param params
     * @param @return
     * @return int
     * @throws Exception 
     */
    int execute(String sqlId, Object params);
}
