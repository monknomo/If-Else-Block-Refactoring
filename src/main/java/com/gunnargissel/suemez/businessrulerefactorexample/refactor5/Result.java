/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample.refactor5;

/**
 *
 * @author monknomo
 */
public class Result<T> {
    String errors;
    T result;

    Result(String message) {
        this.errors = message;
    }
    
    Result(T message) {
        this.result = message;
    }
    
    public boolean hasErrors(){
        return null!=errors && errors.length()>0;
    }
    
    public T getResult(){
        return result;
    }
    
    public String getErrors(){
        return errors;
    }
}
