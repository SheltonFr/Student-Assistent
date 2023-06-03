package dev.sheltonfrancisco.studentassistent.models;

public class Subject {
    private Integer id;
    private String name;
    private String teacher;
    private Integer grade;

    public Subject(Integer id, String name, String teacher, Integer grade) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.grade = grade;
    }

    public Subject(String name, String teacher, Integer grade) {
        this.name = name;
        this.teacher = teacher;
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
