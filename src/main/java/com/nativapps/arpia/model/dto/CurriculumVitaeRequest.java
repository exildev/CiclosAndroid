package com.nativapps.arpia.model.dto;

import com.nativapps.arpia.database.entity.AcademicLevel;
import com.nativapps.arpia.database.entity.CivilStatus;
import java.util.List;

/**
 *
 * @author Cristobal Romero Rossi <cristobal@nativapps.mobi>
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 2.0
 */
public class CurriculumVitaeRequest extends CurriculumVitaeData {

    private List<ReferenceRequest> references;

    public CurriculumVitaeRequest() {
    }

    public CurriculumVitaeRequest(List<ReferenceRequest> references,
            SocialSecurityData socialSecurity, HomeData home,
            CivilStatus civilStatus, String birthCity,
            AcademicLevel academicLevel, String militaryCard, String district) {
        super(socialSecurity, home, civilStatus, birthCity, academicLevel,
                militaryCard, district);
        this.references = references;
    }

    public List<ReferenceRequest> getReferences() {
        return references;
    }

    public void setReferences(List<ReferenceRequest> references) {
        this.references = references;
    }

}
