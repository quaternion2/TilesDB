
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ken
 */
@Result(name = "success", type = "json", params = {"root", "model", "excludeProperties", "${excludeProperties}"})
public class DemoAction extends ActionSupport{
    private String excludeProperties = "myCollection";
    //private Model model = new MyModel();
    
    //getters setters
}
