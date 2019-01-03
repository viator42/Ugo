package com.viator42.ugo.module.dev;

public class Employee {
    private String headimg;
    private String name;
    private int age;

    public Employee(String headimg, String name, int age) {
        this.headimg = headimg;
        this.name = name;
        this.age = age;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
