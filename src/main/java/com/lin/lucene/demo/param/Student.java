package com.lin.lucene.demo.param;

public class Student {
    
    private long id;
    private String name;
    private String sex;
    private String desciption;
    
    public Student() {
        
    }
    
    public Student(long id, String name, String sex, String desciption) {
        super();
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.desciption = desciption;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getDesciption() {
        return desciption;
    }
    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", sex=" + sex + ", desciption=" + desciption + "]";
    }
    
}
