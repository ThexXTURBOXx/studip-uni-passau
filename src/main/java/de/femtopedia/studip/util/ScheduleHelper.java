package de.femtopedia.studip.util;

import de.femtopedia.studip.StudIPAPI;
import de.femtopedia.studip.shib.ShibbolethClient;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.HttpResponse;

public class ScheduleHelper {

	public static Schedule getData(StudIPAPI api) throws IllegalAccessException, IOException {
		Schedule schedule = new Schedule();
		HttpResponse response = api.getShibbolethClient().getIfValid("https://studip.uni-passau.de/studip/dispatch.php/calendar/schedule");
		int day = -1;
		List<List<ScheduledCourse>> courses = new ArrayList<>(Collections.nCopies(7, null));
		List<ScheduledCourse> current = new ArrayList<>();
		ScheduledCourse course = null;
		boolean flag = false;
		int flag1 = -1;
		for (String line : ShibbolethClient.readLines(response.getEntity().getContent())) {
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
				} else if (line.contains("<dt style=\"background-color: #")) {
					int io = line.indexOf("<dt style=\"background-color: #") + 30;
					course.color = Color.decode("0x" + line.substring(io, io + 6));
					flag = true;
				} else if (flag) {
					String[] split = line.split(", <b>");
					int io = indexOfAnyBut(split[0], " ");
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
					} else {
						int io = indexOfAnyBut(line, " ");
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

		schedule.monday = courses.get(0);
		schedule.tuesday = courses.get(1);
		schedule.wednesday = courses.get(2);
		schedule.thursday = courses.get(3);
		schedule.friday = courses.get(4);
		schedule.saturday = courses.get(5);
		schedule.sunday = courses.get(6);

		return schedule;
	}

	private static int indexOfAnyBut(String str, String searchChars) {
		if (isEmpty(str) || isEmpty(searchChars)) {
			return -1;
		}
		for (int i = 0; i < str.length(); i++) {
			if (searchChars.indexOf(str.charAt(i)) < 0) {
				return i;
			}
		}
		return -1;
	}

	private static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

}
