package com.example.r9fire;

public class Student {
   private String sId;
   private String sName;
   private String sSem;

    public Student(String sId, String sName, String sSem) {
        this.sId = sId;
        this.sName = sName;
        this.sSem = sSem;
    }

    public Student() {
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsSem() {
        return sSem;
    }

    public void setsSem(String sSem) {
        this.sSem = sSem;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", sSem='" + sSem + '\'' +
                '}';
    }
}
