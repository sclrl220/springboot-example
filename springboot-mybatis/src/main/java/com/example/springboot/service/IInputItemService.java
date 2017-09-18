package com.example.springboot.service;

import com.example.springboot.domain.InputItem;

import java.util.List;

/**
 * @author lantuLrl
 * @description TODO
 * @create Create By:2017-09-18 18:02
 **/
public interface IInputItemService {

    /**
     * 分页查询数据
     *
     * @param pageNum  当前页
     * @param pageSize 每页显示条数
     * @return
     */
    List<InputItem> pageInputItems(Integer pageNum, Integer pageSize);

}
