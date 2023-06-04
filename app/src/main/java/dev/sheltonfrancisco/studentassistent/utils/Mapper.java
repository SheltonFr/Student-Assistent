package dev.sheltonfrancisco.studentassistent.utils;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import dev.sheltonfrancisco.studentassistent.api.responses.SubjectResponse;
import dev.sheltonfrancisco.studentassistent.api.responses.TaskResponse;
import dev.sheltonfrancisco.studentassistent.models.Subject;
import dev.sheltonfrancisco.studentassistent.models.Task;

public class Mapper {

    public static Subject mapToSubject(SubjectResponse response) {
        return new Subject(response.getId(), response.getName(), response.getTeacher(), response.getGrade());
    }

    public static Task mapToTask(TaskResponse response) {

        return new Task(
                response.getId(),
                response.getTitle(),
                response.getDescription(),
                null,
                response.getSubjectId()
        );
    }
}
