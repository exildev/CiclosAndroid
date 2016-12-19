package com.nativapps.arpia.database.dao;

import com.nativapps.arpia.database.EntityDao;
import com.nativapps.arpia.database.entity.Person;

/**
 *
 * @author Gustavo Pacheco <ryctabo@gmail.com>
 * @version 1.0
 */
public class PersonDaoController extends EntityDao<Person, Long>
        implements PersonDao {

    public PersonDaoController() {
        super(Person.class);
    }

}
