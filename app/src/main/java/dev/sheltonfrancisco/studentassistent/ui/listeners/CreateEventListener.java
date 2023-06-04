package dev.sheltonfrancisco.studentassistent.ui.listeners;

import dev.sheltonfrancisco.studentassistent.models.Subject;
import dev.sheltonfrancisco.studentassistent.models.Task;

public interface CreateEventListener {

    void createSubject(Subject subject);
    void createTask(Task task);
}
