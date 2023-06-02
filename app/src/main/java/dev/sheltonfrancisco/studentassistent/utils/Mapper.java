package dev.sheltonfrancisco.studentassistent.utils;

import dev.sheltonfrancisco.studentassistent.api.responses.SubjectResponse;
import dev.sheltonfrancisco.studentassistent.models.Subject;

public class Mapper {

    public static Subject mapToSubject(SubjectResponse response) {
        return new Subject(response.getId(), response.getName(), response.getTeacher(), response.getGrade());
    }
}
