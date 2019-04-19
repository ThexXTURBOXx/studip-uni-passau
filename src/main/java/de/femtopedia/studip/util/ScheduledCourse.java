package de.femtopedia.studip.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a ScheduledCourse.
 *
 * @deprecated This was only a temporary solution to an API problem.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
@Deprecated
public class ScheduledCourse {

	String event_id;
	int start;
	int end;
	String title;
	String content;
	String color;

}
