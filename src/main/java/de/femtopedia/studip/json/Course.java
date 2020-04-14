package de.femtopedia.studip.json;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Course.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class Course {

    private final String course_id;
    private final String number;
    private final String title;
    private final String subtitle;
    private final String type;
    private final String description;
    private final String location;
    private final Map<String, Contact> lecturers;
    private final Members members;
    private final String start_semester;
    private final String end_semester;
    private final Modules modules;
    private final int group;

}
