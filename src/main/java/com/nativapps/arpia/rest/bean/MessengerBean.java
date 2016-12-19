package com.nativapps.arpia.rest.bean;

import com.nativapps.arpia.model.OrderType;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class MessengerBean {

    @QueryParam("start")
    private int start;

    @QueryParam("size")
    private Integer size;

    @QueryParam("orderType")
    private OrderType orderType;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

}
