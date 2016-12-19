package com.nativapps.arpia.rest.bean;

import com.nativapps.arpia.database.entity.Gender;
import com.nativapps.arpia.model.OrderType;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class ParticularBean {

    @QueryParam("start")
    private int start;
    
    @QueryParam("size")
    private Integer size;
    
    @QueryParam("search")
    private String search;
    
    @QueryParam("orderBy")
    private String orderBy;
    
    @QueryParam("orderType")
    private OrderType orderType;
    
    @QueryParam("gender")
    private Gender gender;

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

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
}
