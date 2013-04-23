package old;

/*
 * Purpose to provide generic CRUD for entity classes
 */


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

/**
 *
 * @author ken
 */
@Action(value = "entity/{crudOperation}/{entity}",
        results={@Result(name = "success", location = "/WEB-INF/views/devices/edit.ftl")}
) 
public class Entity{
    private String crudOperation;
    private String entity;
    
    
    /**
     * @return the crudOperation
     */
    public String getCrudOperation() {
        return crudOperation;
    }

    /**
     * @param crudOperation the crudOperation to set
     */
    public void setCrudOperation(String crudOperation) {
        this.crudOperation = crudOperation;
    }

    /**
     * @return the entity
     */
    public String getEntity() {
        return entity;
    }

    /**
     * @param entity the entity to set
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }
    
}
