package de.femtopedia.studip.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a ScheduledCourse.
 * TEMPORARY UNTIL THE STUDIP RESTAPI IS FIXED!
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class ScheduledCourse {

	String event_id;
	int start;
	int end;
	String title;
	String content;
	String color;

}
