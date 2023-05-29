package dev.sheltonfrancisco.studentassistent.database.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import dev.sheltonfrancisco.studentassistent.models.Task;
import dev.sheltonfrancisco.studentassistent.models.User;

public class UserWithTask {
    @Embedded public User user;
    @Relation(parentColumn = "_id", entityColumn = "userId")
    public List<Task> tasks;

}
