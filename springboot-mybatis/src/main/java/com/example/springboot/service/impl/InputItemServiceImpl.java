package com.example.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.springboot.dao.InputItemMapper;
import com.example.springboot.domain.InputItem;
import com.example.springboot.domain.InputItemExample;
import com.example.springboot.service.IInputItemService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * @author lantuLrl
 * @description TODO
 * @create Create By:2017-09-18 18:03
 **/
public class InputItemServiceImpl implements IInputItemService{

    @Autowired
    private InputItemMapper inputItemMapper;

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public List<InputItem> pageInputItems(Integer pageNum, Integer pageSize) {
        String items = jedisCluster.get("items123");
        if (StringUtils.isEmpty(items)) {
            PageHelper.startPage(0, 10);
            List<InputItem> inputItems = inputItemMapper.selectByExample(new InputItemExample());
            jedisCluster.set("items123", JSON.toJSONString(inputItems));
            return inputItems;
        }

        return JSON.parseObject(items, new TypeReference<List<InputItem>>() {
        });
    }
}
