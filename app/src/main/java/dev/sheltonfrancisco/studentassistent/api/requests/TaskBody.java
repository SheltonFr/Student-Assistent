package dev.sheltonfrancisco.studentassistent.api.requests;

public class TaskBody {
    private String title;
    private String description;
    private String deadline;
    private Integer subject;

    public TaskBody() {

    }
    public TaskBody(String title, String description, String deadline, Integer subject) {
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.subject = subject;
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

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }
}
