package com.nativapps.arpia.database.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@Entity
@Table(name = "PARTICULAR_PARAMETER")
@PrimaryKeyJoinColumn(referencedColumnName = "PARAMETER_ID")
public class ParticularParam extends CustomerParameter {

    public ParticularParam() {
        super();
    }
}
