package de.femtopedia.studip.json;

import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A class representing a set of Semesters.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
public class Semesters {

	private Map<String, Semester> collection;
	private Pagination pagination;

}
