package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.MobileNumber;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class MobileNumberDaoController extends EntityDao<MobileNumber, Long>
        implements MobileNumberDao {

    private static final MobileNumberDaoController INSTANCE
            = new MobileNumberDaoController();

    private MobileNumberDaoController() {
        super(MobileNumber.class);
    }

    public static MobileNumberDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public MobileNumber findMaxNumber(long OperationId) {
        return executeNamedQuery("mobileNumber.avaliable",
                new Parameter("operationId", OperationId));
    }

}
