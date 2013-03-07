/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.s2.interceptor;

/**
 *
 * @author ken
 */
public class User {

    private String name;
    private String id;

    public User(String name) {
        this.name = name;
    }

    /**
     * @return the userName
     */
    public String getName() {
        return name;
    }

    /**
     * @param userName the userName to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
   
}
