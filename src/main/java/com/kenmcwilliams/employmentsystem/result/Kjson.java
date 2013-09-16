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
        String[] excludeParams = (String[]) valueStack.findValue("excludeParams");
        //create json and put it into response stream
        JSONSerializer serializer = new JSONSerializer();
        if (includeParams != null && includeParams.length > 0) {
            serializer = serializer.include(includeParams);
        }
        if (excludeParams != null && excludeParams.length > 0){
            serializer = serializer.exclude(excludeParams).exclude("excludeParams");
        }
        responseStream.println(serializer.exclude("class").serialize(jsonModel));
    }
}
