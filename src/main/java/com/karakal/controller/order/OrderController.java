
package com.karakal.controller.order;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.karakal.controller.order.vo.OrderVo;
import com.karakal.exception.BusinessException;
import com.karakal.service.order.OrderService;

@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    /**
     * 我的申请单
     * @param order
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public Object query(OrderVo order){
        return orderService.query(order);
    }
    /**
     * 删除申请单
     * @param order
     * @return
     */
    @RequestMapping("del")
    @ResponseBody
    public Object del(OrderVo order){
        if(StringUtils.isBlank(order.getOrderId())){
            throw new BusinessException("必要的参数不能为空！");
        }
        return orderService.delete(order);
    }
    /**
     * 删除申请单
     * @param order
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public Object save(OrderVo order){
        if(StringUtils.isBlank(order.getUserId())|| StringUtils.isBlank(order.getServicetypeId())){
            throw new BusinessException("必要的参数不能为空！");
        }
        return orderService.save(order);
    }
}

