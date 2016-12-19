package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.DataAccessObject;
import com.nativapps.arpia.database.entity.Reference;
import java.util.List;

/**
 * @author Cristóbal Romero Rossi <cristobal@nativapps.mobi>
 * @version 1.0
 */
public interface ReferenceDao extends DataAccessObject<Reference, Long> {

    /**
     * Returns a list of reference with the messenger ID provided
     *
     * @param curriculumVitaeId
     * @return List of references
     */
    List<Reference> getAllBycurriculumVitaeId(Long curriculumVitaeId);

}
