package dev.sheltonfrancisco.studentassistent.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey private int _id;
    private String firebaseid;
    private String username;
    private String email;

  public User(){}

    public int get_id() {
      return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFirebaseid() {
        return firebaseid;
    }

    public void setFirebaseid(String firebaseid) {
        this.firebaseid = firebaseid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
