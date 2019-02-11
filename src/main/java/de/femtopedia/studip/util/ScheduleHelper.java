package de.femtopedia.studip.util;

import de.femtopedia.studip.StudIPAPI;
import de.femtopedia.studip.shib.CustomAccessHttpResponse;
import de.femtopedia.studip.shib.OAuthClient;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import oauth.signpost.exception.OAuthException;

/**
 * A helper class to parse the HTML of the schedule.
 */
public class ScheduleHelper {

	/**
	 * Helper method to parse the HTML of the schedule.
	 * THIS METHOD WILL GET REMOVED, WHEN THE RESTAPI SCHEDULE IS FIXED.
	 * THIS IS A TEMPORARY METHOD, THAT HARDCODED PARSES THE HTML TEXT.
	 * IT'S VERY UNSAFE AND CAN BREAK EASILY!!!
	 *
	 * @param api The {@link StudIPAPI} to use.
	 * @return the parsed and converted {@link Schedule} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public static Schedule getData(StudIPAPI api)
			throws IllegalAccessException, IllegalArgumentException, IOException, OAuthException {
		Schedule schedule = new Schedule();
		CustomAccessHttpResponse response = api.getOAuthClient().get("https://studip.uni-passau.de/studip/dispatch.php/calendar/schedule");
		try {
			int day = -1;
			List<List<ScheduledCourse>> courses = new ArrayList<>(Collections.nCopies(7, null));
			List<ScheduledCourse> current = new ArrayList<>();
			ScheduledCourse course = null;
			boolean flag = false;
			int flag1 = -1;
			InputStream stream = response.getResponse().getEntity().getContent();
			for (String line : OAuthClient.readLines(stream)) {
				if (day == -1) {
					if (line.contains("calendar_view_1_column_0"))
						day = 0;
				} else {
					if (line.contains("calendar_view_1_column_")) {
						if (course != null)
							current.add(course);
						course = null;
						courses.set(day, current);
						current = new ArrayList<>();
						day = Integer.parseInt(line.charAt(line.indexOf("calendar_view_1_column_") + 23) + "");
					} else if (line.contains("schedule_entry_")) {
						if (course != null)
							current.add(course);
						course = new ScheduledCourse();
						int io = line.indexOf("schedule_entry_") + 48;
						course.event_id = line.substring(io, io + 32);
					} else if (line.contains("<dt style=\"background-color: #") && course != null) {
						int io = line.indexOf("<dt style=\"background-color: #") + 30;
						course.color = line.substring(io, io + 6);
						flag = true;
					} else if (flag && course != null) {
						String[] split = line.split(", <b>");
						int io = indexOfAnyButSpaces(split[0]);
						String[] clock = split[0].substring(io).split(" - ");
						course.start = Integer.parseInt(clock[0].replace(":", ""));
						course.end = Integer.parseInt(clock[1].replace(":", ""));
						int io1 = split[1].indexOf("</b>");
						course.title = split[1].substring(0, io1);
						flag = false;
						flag1 = 0;
					} else if (flag1 != -1) {
						if (flag1 != 1) {
							flag1++;
						} else if (course != null) {
							int io = indexOfAnyButSpaces(line);
							course.content = line.substring(io, line.lastIndexOf("<br>"));
							flag1 = -1;
						}
					} else if (line.contains("</tbody>")) {
						if (course != null)
							current.add(course);
						courses.set(day, current);
						break;
					}
				}
			}
			stream.close();

			schedule.monday = courses.get(0);
			schedule.tuesday = courses.get(1);
			schedule.wednesday = courses.get(2);
			schedule.thursday = courses.get(3);
			schedule.friday = courses.get(4);
			schedule.saturday = courses.get(5);
			schedule.sunday = courses.get(6);

			return schedule;
		} finally {
			response.close();
		}
	}

	/**
	 * Helper method to get the index of the first char
	 * in a String, that's not a space.
	 *
	 * @param str The string to check
	 * @return the index of the first char
	 * in the String, that's not a space or -1.
	 */
	private static int indexOfAnyButSpaces(String str) {
		if (isEmpty(str)) {
			return -1;
		}
		for (int i = 0; i < str.length(); i++) {
			if (" ".indexOf(str.charAt(i)) < 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Checks, if a String is empty.
	 *
	 * @param str The String to check
	 * @return whether the String should be treated as "empty".
	 */
	private static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

}
