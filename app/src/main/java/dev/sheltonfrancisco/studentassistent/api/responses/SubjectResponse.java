package dev.sheltonfrancisco.studentassistent.api.responses;



public class SubjectResponse {

    private Integer id;
    private String name;
    private String teacher;
    private Integer grade;
    private String status;
    private String created_at;
    private Integer userId;


    public SubjectResponse(Integer id, String name, String teacher, Integer grade, String status, String created_at, Integer userId) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.grade = grade;
        this.status = status;
        this.created_at = created_at;
        this.userId = userId;
    }

    public SubjectResponse() {

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
