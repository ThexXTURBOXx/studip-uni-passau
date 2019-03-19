package de.femtopedia.studip.util;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Schedule.
 * TEMPORARY UNTIL THE STUDIP RESTAPI IS FIXED!
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class Schedule {

	List<ScheduledCourse> monday;
	List<ScheduledCourse> tuesday;
	List<ScheduledCourse> wednesday;
	List<ScheduledCourse> thursday;
	List<ScheduledCourse> friday;
	List<ScheduledCourse> saturday;
	List<ScheduledCourse> sunday;

}
