package dev.sheltonfrancisco.studentassistent.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import dev.sheltonfrancisco.studentassistent.models.enums.SubjectStatus;

@Entity
public class Subject {
    @PrimaryKey
    private int _id;
    private int userId;
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
