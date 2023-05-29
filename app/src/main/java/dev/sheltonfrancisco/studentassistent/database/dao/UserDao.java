package dev.sheltonfrancisco.studentassistent.database.dao;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import dev.sheltonfrancisco.studentassistent.database.relations.UserWithSubject;
import dev.sheltonfrancisco.studentassistent.database.relations.UserWithTask;
import dev.sheltonfrancisco.studentassistent.models.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user where _id = (:id)")
    User getUserById(int id);

    @Query("SELECT * FROM user where firebaseid = (:id)")
    User getUserById(String id);

    @Query("SELECT * FROM user")
    List<UserWithTask> getUserWithTasks();

    @Query("SELECT * FROM user")
    List<UserWithSubject> getUserWithSubjects();
}
