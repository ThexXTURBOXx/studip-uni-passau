package de.femtopedia.studip.json;

import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A class representing a set of Courses.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
public class Courses {

	private Map<String, Course> collection;
	private Pagination pagination;

}
