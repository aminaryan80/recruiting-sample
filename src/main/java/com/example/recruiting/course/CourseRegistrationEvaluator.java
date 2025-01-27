package com.example.recruiting.course;

import com.example.recruiting.course.dto.CourseRegistrationDto;

import java.time.LocalDate;
import java.util.*;

class CourseRegistrationEvaluatorSlower {
    public static List<String> getAllCurrentValidCourseParticipantNames(List<CourseRegistrationDto> reservationEntries) {
        HashMap<String, LocalDate> lastRegistrationDateForStudents = new HashMap<>();
        HashMap<String, CourseRegistrationDto.RegistrationStatus> lastRegistrationStatusForStudents = new HashMap<>();

        for (CourseRegistrationDto record : reservationEntries) {
            String courseParticipantName = record.courseParticipantName();

            if (lastRegistrationDateForStudents.containsKey(courseParticipantName)) {
                LocalDate lastRecordDate = lastRegistrationDateForStudents.get(courseParticipantName);

                if (record.registrationDate().isAfter(lastRecordDate)) {
                    lastRegistrationDateForStudents.put(courseParticipantName, record.registrationDate());
                    lastRegistrationStatusForStudents.put(courseParticipantName, record.registrationStatus());
                }

            } else {
                lastRegistrationDateForStudents.put(courseParticipantName, record.registrationDate());
                lastRegistrationStatusForStudents.put(courseParticipantName, record.registrationStatus());
            }
        }

        List<String> result = new ArrayList<>();

        for (String studentName : lastRegistrationStatusForStudents.keySet()) {
            if (lastRegistrationStatusForStudents.get(studentName) == CourseRegistrationDto.RegistrationStatus.ENROLLED) {
                result.add(studentName);
            }
        }

        return result;
    }
}


public class CourseRegistrationEvaluator {
    public static List<String> getAllCurrentValidCourseParticipantNames(List<CourseRegistrationDto> reservationEntries) {
        HashMap<String, LocalDate> lastRegistrationDateForStudents = new HashMap<>();
        Set<String> result = new HashSet<>();

        for (CourseRegistrationDto record : reservationEntries) {
            String courseParticipantName = record.courseParticipantName();

            if (lastRegistrationDateForStudents.containsKey(courseParticipantName)) {
                LocalDate lastRecordDate = lastRegistrationDateForStudents.get(courseParticipantName);

                if (record.registrationDate().isAfter(lastRecordDate)) {
                    lastRegistrationDateForStudents.put(courseParticipantName, record.registrationDate());

                    if (record.registrationStatus() == CourseRegistrationDto.RegistrationStatus.ENROLLED)
                        result.add(courseParticipantName);
                    else
                        result.remove(courseParticipantName);
                }

            } else {
                lastRegistrationDateForStudents.put(courseParticipantName, record.registrationDate());

                if (record.registrationStatus() == CourseRegistrationDto.RegistrationStatus.ENROLLED)
                    result.add(courseParticipantName);
            }
        }

        return new ArrayList<>(result);
    }
}
