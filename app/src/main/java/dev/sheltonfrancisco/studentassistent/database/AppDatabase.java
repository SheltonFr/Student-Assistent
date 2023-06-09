package dev.sheltonfrancisco.studentassistent.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import dev.sheltonfrancisco.studentassistent.database.dao.TaskDao;
import dev.sheltonfrancisco.studentassistent.database.dao.UserDao;
import dev.sheltonfrancisco.studentassistent.models.Subject;
import dev.sheltonfrancisco.studentassistent.models.Task;
import dev.sheltonfrancisco.studentassistent.models.User;

@Database(entities = {Task.class, User.class, Subject.class}, version = 1)
@TypeConverters({DataTypeConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract TaskDao taskDao();
//    public abstract SubjectDao subjectDao();
}
