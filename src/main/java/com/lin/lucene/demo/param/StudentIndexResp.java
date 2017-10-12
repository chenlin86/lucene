package com.lin.lucene.demo.param;

import org.apache.lucene.search.Explanation;

public class StudentIndexResp {
    
    private float score;
    private Student student;
    
    private Explanation explanation;
    public StudentIndexResp() {
        super();
    }
    
    public StudentIndexResp(Student student, float score) {
        super();
        this.student = student;
        this.score = score;
    }
   
    public float getScore() {
        return score;
    }
    
    public void setScore(float score) {
        this.score = score;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    public Explanation getExplanation() {
        return explanation;
    }

    public void setExplanation(Explanation explanation) {
        this.explanation = explanation;
    }

    @Override
    public String toString() {
        String start = "StudentIndexResp [score="+ score+ ", student="+student ;
        String end = "]";
        String middle = explanation==null?"":"\n"+explanation.toString();
        return start+middle+end;
    }
}
