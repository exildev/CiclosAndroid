/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.CustomerParameter;


public class CustomerParamDaoController 
        extends EntityDao<CustomerParameter, Long> implements CustomerParamDao {

    private static final CustomerParamDaoController INSTANCE 
            = new CustomerParamDaoController();
    
    private CustomerParamDaoController() {
        super(CustomerParameter.class);
    }

    public static CustomerParamDaoController getInstance() {
        return INSTANCE;
    }    
}
