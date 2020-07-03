package com.github.alenfive.dataway2.datasource;

import com.github.alenfive.dataway2.entity.ApiInfo;
import com.github.alenfive.dataway2.entity.ApiParams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Copyright: Copyright (c) 2019  ALL RIGHTS RESERVED.
 * @Author: 米华军
 * @CreateDate: 2020/5/27 17:16
 * @UpdateDate: 2020/5/27 17:16
 * @UpdateRemark: init
 * @Version: 1.0
 * @menu 数据源方言抽象类
 */
public abstract class DataSourceDialect {

    protected boolean storeApi = false;

    public boolean isStoreApi() {
        return storeApi;
    }

    abstract String listApiInfoScript();
    abstract String getApiInfoScript();
    abstract String saveApiInfoScript();
    abstract String updateApiInfoScript();
    abstract String deleteApiInfoScript();
    abstract String saveApiExampleScript();
    abstract String lastApiExampleScript();
    abstract String deleteExampleScript();

    //查询对象
    abstract List<Map<String,Object>> find(StringBuilder script, ApiInfo apiInfo, ApiParams apiParams) throws Exception;

    //返回影响的行数
    abstract Long update(StringBuilder script, ApiInfo apiInfo, ApiParams apiParams) throws Exception;

    //返回影响的行数
    abstract Long remove(StringBuilder script, ApiInfo apiInfo, ApiParams apiParams) throws Exception;

    //返回主键
    abstract Object insert(StringBuilder script, ApiInfo apiInfo, ApiParams apiParams) throws Exception;

    /**
     * 替换key
     * @param map
     * @return
     */
    protected Map<String,Object> toReplaceKeyLow(Map<String,Object> map){
        Map<String,Object> result = new HashMap<>(map.size());
        for(String key : map.keySet()){
            result.put(underlineToCamel(key),map.get(key));
        }
        return result;
    }

    /**
     * 下划线转驼峰
     * @param name
     * @return
     */
    protected String underlineToCamel(String name){
        StringBuilder sb = new StringBuilder(name.length());
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if ('_' == c) {
                if (++i < name.length()){
                    sb.append(Character.toUpperCase(name.charAt(i)));
                }
            }else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


}
