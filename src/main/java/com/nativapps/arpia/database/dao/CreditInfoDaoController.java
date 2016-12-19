package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.CreditInfo;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
public class CreditInfoDaoController extends EntityDao<CreditInfo, Long> 
        implements CreditInfoDao {

    private static final CreditInfoDaoController INSTANCE 
            = new CreditInfoDaoController();
    
    private CreditInfoDaoController() {
        super(CreditInfo.class);
    }

    public static CreditInfoDaoController getInstance() {
        return INSTANCE;
    }
    
}
