package com.JNF;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.ArrayList;

import com.Util.Person;

@Target({ ElementType.TYPE_PARAMETER, ElementType.TYPE_USE })
public @interface GenericAnno {

}

@SuppressWarnings("unused")
class Generic<@GenericAnno T> {
    public void show() throws @GenericAnno Exception {
        ArrayList<@GenericAnno Person> list = new ArrayList<Person>();

        int num = (@GenericAnno int) 10L;
    }
}