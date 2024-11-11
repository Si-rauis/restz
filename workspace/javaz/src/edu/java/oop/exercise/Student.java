package edu.java.oop.exercise;

import edu.java.oop.Date;

public class Student extends Person{

    private int id;
    private Date birthDate;

    public Student(String name, String telNum, int id) {
        super(name, telNum);
        this.id = id;
    }

    public Student(String name, int id, Date birthDate) {
        super(name);
        this.id = id;
        this.birthDate = birthDate;
    }

    public Student(String name, int id) {
        super(name);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "id: " + id + ", Person name : " + getName() + ", 생년월일: " + getTelNum();
    }
}
