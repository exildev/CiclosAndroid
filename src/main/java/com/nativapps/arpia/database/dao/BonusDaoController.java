/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Bonus;
import java.util.List;

public class BonusDaoController extends EntityDao<Bonus, Long>
        implements BonusDao {

    private static final BonusDaoController INSTANCE = new BonusDaoController();

    private BonusDaoController() {
        super(Bonus.class);
    }

    public static BonusDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public Bonus findByCustomerId(Long customerId, Long id) {
        return executeNamedQuery("bonus.findByCustomerId", 
                new Parameter("customerId", customerId), 
                new Parameter("id", id));
    }

    @Override
    public List<Bonus> findAllByCustomerIdPag(int start, int size, Long customerId) {
        return executeNamedQueryForList("bonus.findAllByCustomerId", start, 
                size, new Parameter("customerId", customerId));
    }

    @Override
    public List<Bonus> findAllByCustomerId(Long customerId) {
        return executeNamedQueryForList("bonus.findAllByCustomerId", 
                new Parameter("customerId", customerId));
    }
}
