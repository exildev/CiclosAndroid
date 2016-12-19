/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class CurriculumVitaeResponse extends CurriculumVitaeData {

    private List<ReferenceResponse> references;

    public CurriculumVitaeResponse() {
    }

    public CurriculumVitaeResponse(List<ReferenceResponse> references,
            SocialSecurityData socialSecurity, HomeData home,
            CivilStatus civilStatus, String birthCity,
            AcademicLevel academicLevel, String militaryCard, String district) {
        super(socialSecurity, home, civilStatus, birthCity, academicLevel,
                militaryCard, district);
        this.references = references;
    }

    public List<ReferenceResponse> getReferences() {
        return references;
    }

    public void setReferences(List<ReferenceResponse> references) {
        this.references = references;
    }

}
