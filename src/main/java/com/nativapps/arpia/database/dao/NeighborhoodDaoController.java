/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Neighborhood;
import java.util.List;


public class NeighborhoodDaoController extends EntityDao<Neighborhood, Long> 
        implements NeighborhoodDao {

    private static final NeighborhoodDaoController INSTANCE 
            = new NeighborhoodDaoController();
    
    private NeighborhoodDaoController() {
        super(Neighborhood.class);
    }

    public static NeighborhoodDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Neighborhood> findAll(int start, int size) {
        return executeNamedQueryForList("neighborhood.findAll", start, size);
    }

    @Override
    public Neighborhood findByName(String name) {
        return executeNamedQuery("neighborhood.findByName", 
                new Parameter("name", name));
    }
}
