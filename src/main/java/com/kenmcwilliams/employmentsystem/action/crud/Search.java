/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.action.crud;

import com.kenmcwilliams.employmentsystem.service.CriteriaConstraints;
import com.kenmcwilliams.employmentsystem.service.CrudService;
import com.kenmcwilliams.employmentsystem.service.EntityInspectorService;
import com.kenmcwilliams.employmentsystem.util.ActionUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import flexjson.JSON;
import flexjson.JSONSerializer;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Search makes the following assumptions
 *
 * @author ken
 */
//@ParentPackage("package-kjson")
@ParentPackage("staticParams-prepare-parms")
@Namespace("/crud/{entityName}")
@Result(type = "kjson")
public class Search extends ActionSupport implements Preparable {

    private static final Logger log = Logger.getLogger(Search.class.getName());
    @Autowired
    private CrudService crudService;
    @Autowired
    private EntityInspectorService formatService;
    @PersistenceContext
    private EntityManager em; //used for meta model access, not DB access.
    private java.util.List<Object> entityList;
    private Long count;
    private Object model;
    private Collection<String> ordinals = new LinkedList();//map of field names to location
    private Map<String, Map<CriteriaConstraints, java.util.List>> constraints = new HashMap<>();
    private Object jsonModel;
    private String entityName;
    private Class clazz;

    @Override
    public String execute() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException {
        //EntityType<Candidate> entityType = em.getMetamodel().entity(Candidate.class);
        //Set<Attribute<? super Candidate, ?>> attributes = entityType.getAttributes();
        
        Collection<String> attributes = this.formatService.getUnmodifiablePropertiesByEntity(clazz);
        this.ordinals = attributes; //TODO: NOTING IS DONE WITH THIS
        //TODO I'm using like for all string attributes, when I should be taking in a description of how to handle
        for (String attribute : attributes) {
            log.log(Level.INFO, "attr name: {0}\n", attribute);
            //Class attrType = attribute.getJavaType();
            Object propertyValue = PropertyUtils.getProperty(model, attribute);
            Class attrType = PropertyUtils.getPropertyType(model, attribute);
            log.log(Level.INFO, "propertyValue: {0}", propertyValue);

            if (attrType == String.class) {
                String strValue = (String) propertyValue;
                if (strValue != null && !strValue.isEmpty()) {
                    ArrayList parameters = new ArrayList();
                    parameters.add("%" + propertyValue + "%");
                    addConstraint(attribute, CriteriaConstraints.Like, parameters);
                }
            } else if (attrType == Integer.class) {
            } else if (attrType == Float.class) {
                Float floatValue = (Float) propertyValue;
                if (floatValue != null && !floatValue.isNaN()) {
                    ArrayList parameters = new ArrayList();
                    parameters.add(propertyValue);
                    addConstraint(attribute, CriteriaConstraints.LtOrEq, parameters);
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
        entityList = crudService.search(clazz, model, constraints);
        count = (long) entityList.size();
        //setup the json model
        //HashMap<String,  tempModel = new HashMap<>();
        //tempModel.put("count", jsonModel);
        //tempModel.put("candidateList", candidateList);
        //tempModel.put("ordinals", ordinals);
        //jsonModel = tempModel;
        jsonModel = new Object() {

            public Long getCount() {
                return count;
            }

            @JSON
            public Collection<String> getOrdinals() {
                return ordinals;
            }

            @JSON
            public java.util.List<Object> getEntityList() {
                return entityList;
            }
        };
        return SUCCESS;
    }

    /**
     * @return the jsonModel
     */
    public Object getJsonModel() {
        return jsonModel;
    }

    /**
     * @return the model
     */
    //public Candidate getCandidate() {
    //    return candidate;
   //}

    /**
     * @param model the model to set
     */
    //public void setCandidate(Candidate candidate) {
    //    this.candidate = candidate;
    //}

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

    @Override
    public void prepare() throws Exception {
        clazz = ActionUtils.initClazz(getEntityName());
        model = clazz.newInstance();
        if (model == null)
            throw new Exception("Created null model");
        else
            log.info("created model for search successfully");
    }
    
    public Object getModel(){
        return model;
    }
    
    public void setModel(Object model){
        this.model = model;
    }

    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @param entityName the entityName to set
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * @return the ordinals
     */
    //public Collection<String> getOrdinals() {
    //    return ordinals;
    //}

    /**
     * @param ordinals the ordinals to set
     */
    //public void setOrdinals(Collection<String> ordinals) {
    //    this.ordinals = ordinals;
    //}
}
