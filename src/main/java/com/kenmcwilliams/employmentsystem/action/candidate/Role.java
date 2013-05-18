/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import java.util.ArrayList;

/**
 *
 * @author ken
 */
public class Role {
    
        private String companyName;
        private String role;
        private String dateWorked;
        private ArrayList<String> details = new ArrayList();

        /**
         * @return the companyName
         */
        public String getCompanyName() {
            return companyName;
        }

        /**
         * @param companyName the companyName to set
         */
        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        /**
         * @return the role
         */
        public String getRole() {
            return role;
        }

        /**
         * @param role the role to set
         */
        public void setRole(String role) {
            this.role = role;
        }

        /**
         * @return the dateWorked
         */
        public String getDateWorked() {
            return dateWorked;
        }

        /**
         * @param dateWorked the dateWorked to set
         */
        public void setDateWorked(String dateWorked) {
            this.dateWorked = dateWorked;
        }

        /**
         * @return the details
         */
        public ArrayList<String> getDetails() {
            return details;
        }

        /**
         * @param details the details to set
         */
        public void setDetails(ArrayList<String> details) {
            this.details = details;
        }
}
