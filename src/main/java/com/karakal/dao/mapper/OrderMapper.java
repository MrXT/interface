package com.karakal.dao.mapper;

import java.util.List;
import java.util.Map;

import com.karakal.controller.order.vo.OrderVo;
import com.karakal.entity.Order;
import com.karakal.util.MyMapper;

public interface OrderMapper extends MyMapper<Order> {

    List<Map<String, Object>> queryByCondition(OrderVo order);
}