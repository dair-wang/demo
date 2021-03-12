package com.dair.demo.excel.validate;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class WeightContext {

    /**
     * 大数据容器
     */
    private final Map<Object, Object> containers = new HashMap<>();

    /**
     * 获取大数据容器值
     *
     * @param key 大数据容器key
     * @return 大数据容器值
     */
    public Object getContainerByKey(Object key) {

        return containers.get(key);
    }

    /**
     * 添加大数据容器值
     *
     * @param key       大数据容器key
     * @param container 大数据容器值
     */
    public void putContainerByKey(Object key, Object container) {

        containers.put(key, container);
    }
}
