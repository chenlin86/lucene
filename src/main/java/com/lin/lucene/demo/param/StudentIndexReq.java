package com.lin.lucene.demo.param;

public class StudentIndexReq extends Student {

    public StudentIndexReq() {
        super();
    }
    
    public StudentIndexReq(long id, String name, String sex, String desciption) {
        super();
        super.setId(id);
        super.setName(name);
        super.setSex(sex);
        super.setDesciption(desciption);
    }

    @Override
    public String toString() {
        return "StudentIndexReq [ toString()=" + super.toString()+"]";
    }
}