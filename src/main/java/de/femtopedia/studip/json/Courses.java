package de.femtopedia.studip.json;

import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a set of Courses.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@NoArgsConstructor
@Getter
@ToString
public class Courses {

    private Map<String, Course> collection;
    private Pagination pagination;

}
