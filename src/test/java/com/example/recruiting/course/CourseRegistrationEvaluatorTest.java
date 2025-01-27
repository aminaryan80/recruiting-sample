package com.example.recruiting.course;

import com.example.recruiting.course.dto.CourseRegistrationDto;
import com.example.recruiting.course.helper.CourseRegistrationHolder;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Collections.shuffle;


/**
 * @author juergen.windhaber
 */
class CourseRegistrationEvaluatorTest {

    @Test
    void getAllDriverNamesWithAValidReservation() {

        List<CourseRegistrationDto> registrations = new ArrayList<>(CourseRegistrationHolder.REGISTRATIONS);
        shuffle(registrations);

        List<String> participantsWithAValidReservation = CourseRegistrationEvaluator.getAllCurrentValidCourseParticipantNames(registrations);

        assertThat(participantsWithAValidReservation).isNotNull();
        assertThat(toUpper(participantsWithAValidReservation)).containsExactlyElementsIn(Arrays.asList("CARL", "JOEY", "ANNE"));

    }

    private static List<String> toUpper(List<String> someList) {
        return someList.stream().map(StringUtils::upperCase).collect(Collectors.toList());
    }
}
