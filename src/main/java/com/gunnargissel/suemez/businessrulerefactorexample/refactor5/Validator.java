/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gunnargissel.suemez.businessrulerefactorexample.refactor5;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author monknomo
 */
public class Validator<T> {
    
    Map<Predicate<T>, String> rules = new HashMap<>();
    
    public void addRule(Predicate<T> rule, String errorMessage){
        rules.put(rule, errorMessage);
    }   
    
    public Result<T> validate(T obj){
        String errors = rules.keySet().stream()
                .filter(rule -> rule.test(obj))
                .map(rule -> rules.get(rule))
                .collect(Collectors.joining());
        if(Objects.isNull(errors) || errors.isEmpty()){
            return new Result<>(obj);
        }
        return new Result<>(errors);
    }
}
