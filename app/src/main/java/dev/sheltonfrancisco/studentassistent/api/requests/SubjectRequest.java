package dev.sheltonfrancisco.studentassistent.api.requests;

public class SubjectRequest {
    private String name;
    private String teacher;
    private Integer grade;

    public SubjectRequest() {

    }

    public SubjectRequest(String name, String teacher, Integer grade) {
        this.name = name;
        this.teacher = teacher;
        this.grade = grade;
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
}
