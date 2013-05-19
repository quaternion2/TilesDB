/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.result;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.util.ValueStack;
import flexjson.JSONSerializer;
import java.io.PrintWriter;
import org.apache.struts2.ServletActionContext;

public class Kjson implements Result {

    public static final String DEFAULT_PARM = "classAlias";
    String classAlias;

    public String getClassAlias() {
        return classAlias;
    }

    public void setClassAlias(String classAlias) {
        this.classAlias = classAlias;
    }

    @Override
    public void execute(ActionInvocation invocation) throws Exception {
        ServletActionContext.getResponse().setContentType("text/plain");
        PrintWriter responseStream = ServletActionContext.getResponse().getWriter();
        ValueStack valueStack = invocation.getStack();
        Object jsonModel = valueStack.findValue("jsonModel");
        String[] includeParams = (String[]) valueStack.findValue("includeParams");
        //create json and put it into response stream
        if (includeParams != null && includeParams.length > 0) {
            responseStream.println(new JSONSerializer().include(includeParams).exclude("class").serialize(jsonModel));
        } else {
            responseStream.println(new JSONSerializer().exclude("class").serialize(jsonModel));
        }
    }
}
