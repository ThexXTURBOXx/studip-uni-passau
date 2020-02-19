package de.femtopedia.studip.json;

import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Course.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class Course {

    private String course_id;
    private String number;
    private String title;
    private String subtitle;
    private String type;
    private String description;
    private String location;
    private Map<String, Contact> lecturers;
    private Members members;
    private String start_semester;
    private String end_semester;
    private Modules modules;
    private int group;

}
