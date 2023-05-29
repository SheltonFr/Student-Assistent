package dev.sheltonfrancisco.studentassistent.database;

import androidx.room.TypeConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import dev.sheltonfrancisco.studentassistent.models.enums.SubjectStatus;
import dev.sheltonfrancisco.studentassistent.models.enums.TaskStatus;

public class DataTypeConverters {

    @TypeConverter
    public static LocalDateTime fromTimestamp(Long value) {
        return value == null ? null : LocalDateTime.
                from(new Date(value).toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

    @TypeConverter
    public static Long localDateTimeToTimeStamp(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return  localDateTime == null ? null : calendar.getTimeInMillis();
    }

    @TypeConverter
    public static String  taskStatus(TaskStatus taskStatus) {
       return taskStatus == null ? null : String.valueOf(taskStatus);
    }

    @TypeConverter
    public static TaskStatus  stringTaskStatus(String status) {
        return status == null ? null : TaskStatus.valueOf(status);
    }

    @TypeConverter
    public static String  subjectStatus(SubjectStatus subjectStatus) {
        return subjectStatus == null ? null : String.valueOf(subjectStatus);
    }

    @TypeConverter
    public static SubjectStatus stringSubjectStatus(String status) {
        return status == null ? null : SubjectStatus.valueOf(status);
    }
}
