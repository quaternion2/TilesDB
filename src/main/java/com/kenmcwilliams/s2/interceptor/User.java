/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.s2.interceptor;

import com.kenmcwilliams.employmentsystem.orm.Person;
import com.kenmcwilliams.employmentsystem.orm.Roles;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 *
 * @author ken
 */
public class User implements Serializable{
    private static final Logger log = Logger.getLogger(User.class.getName());
    
    private String name;
    private Integer id;
    private Set<String> roles;

    public User(String name) {
        this.name = name;
    }

    public User(Person person) {
        roles = new HashSet();
        this.id = person.getId();
        this.name = person.getName();
        for(Roles role : person.getRolesCollection()){
            this.roles.add(role.getName());
        }
    }

    /**
     * @return the userName
     */
    public String getName() {
        return name;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    
    //TODO: Use Enum for Roles?
    public boolean hasRole(String role){
        return this.roles.contains(role);
    }
}
