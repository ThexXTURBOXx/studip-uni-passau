package de.femtopedia.studip.json;

import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Courses {

	private Map<String, Course> collection;
	private Pagination pagination;

}
