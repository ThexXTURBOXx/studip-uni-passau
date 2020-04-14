package de.femtopedia.studip.json;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * A class representing a set of Courses.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@RequiredArgsConstructor
@Getter
@ToString
public class Courses {

    private final Map<String, Course> collection;
    private final Pagination pagination;

}
