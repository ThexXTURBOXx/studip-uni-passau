package de.femtopedia.studip.json;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Scheduled Course.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class ScheduledCourse {

	private int start;
	private int end;
	private String content;
	private String title;
	private String color;
	private String type;

}
