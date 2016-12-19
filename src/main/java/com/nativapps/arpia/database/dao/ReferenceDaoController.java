package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Reference;
import java.util.List;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public class ReferenceDaoController extends EntityDao<Reference, Long>
        implements ReferenceDao {

    private static final ReferenceDaoController INSTANCE
            = new ReferenceDaoController();

    private ReferenceDaoController() {
        super(Reference.class);
    }

    public static ReferenceDaoController getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Reference> getAllBycurriculumVitaeId(Long curriculumVitaeId) {
        return executeNamedQueryForList("reference.getAllBycurriculumVitaeId",
                new Parameter("curriculumVitaeId", curriculumVitaeId));
    }

}
