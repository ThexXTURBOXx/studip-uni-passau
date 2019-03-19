package de.femtopedia.studip.json;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A class representing a Schedule.
 */
@SuppressWarnings("MemberName")
@NoArgsConstructor
@Getter
@ToString
public class Schedule {

	@SerializedName("1")
	private Map<String, ScheduledCourse> monday;
	@SerializedName("2")
	private Map<String, ScheduledCourse> tuesday;
	@SerializedName("3")
	private Map<String, ScheduledCourse> wednesday;
	@SerializedName("4")
	private Map<String, ScheduledCourse> thursday;
	@SerializedName("5")
	private Map<String, ScheduledCourse> friday;
	@SerializedName("6")
	private Map<String, ScheduledCourse> saturday;
	@SerializedName("7")
	private Map<String, ScheduledCourse> sunday;

}
