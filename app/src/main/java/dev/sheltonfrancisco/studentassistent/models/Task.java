package dev.sheltonfrancisco.studentassistent.models;

import java.time.LocalDateTime;

public class Task {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private Integer subjectId;

    public Task() {

    }

    public Task(Integer id, String title, String description, LocalDateTime deadline, Integer subjectId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.subjectId = subjectId;
    }

    public Task(String title, String description, LocalDateTime deadline, Integer subjectId) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.subjectId = subjectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
