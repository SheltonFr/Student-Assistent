package dev.sheltonfrancisco.studentassistent.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import dev.sheltonfrancisco.studentassistent.models.enums.SubjectStatus;

@Entity
public class Subject {
    @PrimaryKey
    private int _id;
    private int userId;
    private String name;
    private String teacher;
    private int Grade;
    private Long createdAt;
    private SubjectStatus status;

    public Subject() {}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getUserId() {
        return userId;
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

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public SubjectStatus getStatus() {
        return status;
    }

    public void setStatus(SubjectStatus status) {
        this.status = status;
    }
}
