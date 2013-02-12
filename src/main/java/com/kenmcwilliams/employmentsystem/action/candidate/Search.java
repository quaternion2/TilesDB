/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.candidate;

import com.kenmcwilliams.employmentsystem.orm.Candidate;
import com.kenmcwilliams.employmentsystem.service.CriteriaConstraints;
import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.opensymphony.xwork2.ActionSupport;
import flexjson.JSONSerializer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Search makes the following assumptions
 *
 * @author ken
 */
@Result(name = "success", location = "/WEB-INF/content/candidate/list.jsp")
public class Search extends ActionSupport {

    private static final Logger log = Logger.getLogger(Search.class.getName());
    @Autowired
    private CrudService crudService;
    @PersistenceContext
    private EntityManager em;
    private java.util.List<Object> candidateList;
    private Long count;
    private Candidate candidate;
    private Map<String, Map<CriteriaConstraints, java.util.List>> constraints = new HashMap<>();
    //private Map<CriteriaConstraints, java.util.List> fnameMap = new HashMap<>();
    //private Map<CriteriaConstraints, java.util.List> lnameMap = new HashMap<>();

    //public void prepare() {
    //constraints = new HashMap<>();
    //fnameMap = 
    //constraints.put("fname", new HashMap());
    //}
    @Override
    public String execute() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        EntityType<Candidate> entityType = em.getMetamodel().entity(Candidate.class);
        Set<Attribute<? super Candidate, ?>> attributes = entityType.getAttributes();

        //TODO I'm using like for all string attributes, when I should be taking in a description of how to handle
        for (Attribute attribute : attributes) {
            String attrName = attribute.getName();
            Class attrType = attribute.getJavaType();
            Object propertyValue = PropertyUtils.getProperty(candidate, attrName);

            if (attrType == String.class) {
                String strValue = (String) propertyValue;
                if (strValue != null && !strValue.isEmpty()) {
                    ArrayList parameters = new ArrayList();
                    parameters.add("%" + propertyValue + "%");
                    addConstraint(attrName, CriteriaConstraints.Like, parameters);
                }
            } else if (attrType == Integer.class) {
            } else if (attrType == Float.class) {
                Float floatValue = (Float) propertyValue;
                if (floatValue != null && !floatValue.isNaN()) {
                    ArrayList parameters = new ArrayList();
                    parameters.add(propertyValue);
                    addConstraint(attrName, CriteriaConstraints.LtOrEq, parameters);
                }
            }
        }
        /*
         * if (candidate.getFname() != null && !candidate.getFname().isEmpty())
         * { ArrayList parameters = new ArrayList(); //parameters.add("%");
         * parameters.add("%" + candidate.getFname() + "%");
         * //parameters.add("%");//these values will be concatinated by the
         * service, it could be done as one string "%namevalue%" but I want to
         * check this addConstraint("fname", CriteriaConstraints.Like,
         * parameters); } if (candidate.getLname() != null &&
         * candidate.getLname().isEmpty() == false) { ArrayList parameters = new
         * ArrayList(); parameters.add("%");
         * parameters.add(candidate.getLname()); parameters.add("%");//these
         * values will be concatinated by the service, it could be done as one
         * string "%namevalue%" but I want to check this addConstraint("lname",
         * CriteriaConstraints.Like, parameters); }
         *
         */
        //public List<Object> search(Class clazz, Object entity, Map<String, Map<CriteriaConstraints, List>> constraints)
        log.log(Level.INFO, "\nSerch execute()\n constraints: {0}\n", new JSONSerializer().serialize(constraints));
        candidateList = crudService.search(Candidate.class, candidate, constraints);
        count = (long) candidateList.size();
        return SUCCESS;
    }

    /**
     * @return the candidateList
     */
    public java.util.List<Object> getCandidateList() {
        return candidateList;
    }

    public Long getCount() {
        return count;
    }

    /**
     * @return the model
     */
    public Candidate getCandidate() {
        return candidate;
    }

    /**
     * @param model the model to set
     */
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    private void addConstraint(String fieldName, CriteriaConstraints criteriaConstraint, ArrayList parameters) {
        Map<CriteriaConstraints, java.util.List> fieldConstraints = new HashMap<>();
        fieldConstraints.put(criteriaConstraint, parameters);
        log.log(Level.INFO,
                "\nSerch addConstraint()\n fieldName: {0} \ncriteriaConstraint: {1}\nparameters:{2}\n",
                new Object[]{
                    fieldName,
                    new JSONSerializer().serialize(criteriaConstraint),
                    new JSONSerializer().serialize(parameters)});
        constraints.put(fieldName, fieldConstraints);
    }
}
