package by.slizh.tutorsweb._main;

import java.util.*;

public class A {
    {
        System.out.println("localInit");
    }
    int a;
    static {
        System.out.println("staticInit");
    }

    public A(){
        System.out.println("constructor");
    }
}
