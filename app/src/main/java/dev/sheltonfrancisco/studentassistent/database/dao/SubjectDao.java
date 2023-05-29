package dev.sheltonfrancisco.studentassistent.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import dev.sheltonfrancisco.studentassistent.models.Subject;
import dev.sheltonfrancisco.studentassistent.models.Task;

@Dao
public interface SubjectDao {

    @Query("SELECT * FROM subject")
    List<Subject> getAll();
    @Query("SELECT * FROM subject WHERE id = (:id) ")
    Task getOne(int id);
    @Insert
    void insertOne(Subject subject);
    @Insert
    void insertMany(Subject ...subject);
    @Delete
    void deleteOne(Subject subject);
    @Update
    void updateTask(Subject subject);
}
