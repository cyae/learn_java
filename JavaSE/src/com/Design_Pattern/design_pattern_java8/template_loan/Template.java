package com.Design_Pattern.design_pattern_java8.template_loan;

public class Template {
 
    public static void main(String[] args) {
        Resource.use(resource -> 
            resource.op1()
                    .op2()
        );
    }
}
