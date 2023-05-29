package dev.sheltonfrancisco.studentassistent.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dev.sheltonfrancisco.studentassistent.models.Task;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();
    @Query("SELECT * FROM task WHERE id = (:id) ")
    Task getOne(int id);
    @Insert
    void insertOne(Task task);
    @Insert
    void insertMany(Task ...tasks);
    @Delete
    void deleteOne(Task task);
    @Update
    void updateTask(Task task);
}
