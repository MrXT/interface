
package com.karakal.service.order;

import com.karakal.controller.order.vo.OrderVo;


public interface OrderService {

    Object query(OrderVo order);

    Object delete(OrderVo order);

    Object save(OrderVo order);

}

