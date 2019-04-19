package de.femtopedia.studip.json;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * A class representing a Schedule.
 */
@SuppressWarnings("MemberName")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@JsonAdapter(Schedule.ScheduleAdapter.class)
public class Schedule {

	private Map<String, ScheduledCourse> monday;
	private Map<String, ScheduledCourse> tuesday;
	private Map<String, ScheduledCourse> wednesday;
	private Map<String, ScheduledCourse> thursday;
	private Map<String, ScheduledCourse> friday;
	private Map<String, ScheduledCourse> saturday;
	private Map<String, ScheduledCourse> sunday;

	/**
	 * Helper method for getting the day via Integers.
	 *
	 * @param day The day to get (0 = monday, ..., 6 = sunday)
	 * @return the schedule of the given day
	 */
	public Map<String, ScheduledCourse> getDay(int day) {
		switch (day % 7) {
			case 0:
				return monday;
			case 1:
				return tuesday;
			case 2:
				return wednesday;
			case 3:
				return thursday;
			case 4:
				return friday;
			case 5:
				return saturday;
			case 6:
				return sunday;
			default:
				return null;
		}
	}

	/**
	 * Simple JsonAdapter for {@link Schedule}s.
	 */
	class ScheduleAdapter extends TypeAdapter<Schedule> {

		@Override
		public void write(JsonWriter jsonWriter, Schedule schedule) throws IOException {
			jsonWriter.beginArray();
			for (int i = 0; i < 7; i++) {
				Map<String, ScheduledCourse> day = schedule.getDay(i);
				jsonWriter.beginObject();
				for (Map.Entry<String, ScheduledCourse> entry : day.entrySet()) {
					jsonWriter.name(entry.getKey());
					jsonWriter.beginObject();
					ScheduledCourse c = entry.getValue();
					jsonWriter.name("start");
					jsonWriter.value(c.getStart());
					jsonWriter.name("end");
					jsonWriter.value(c.getEnd());
					jsonWriter.name("content");
					jsonWriter.value(c.getContent());
					jsonWriter.name("title");
					jsonWriter.value(c.getTitle());
					jsonWriter.name("color");
					jsonWriter.value(c.getColor());
					jsonWriter.name("type");
					jsonWriter.value(c.getType());
					jsonWriter.endObject();
				}
				jsonWriter.endObject();
			}
			jsonWriter.endArray();
		}

		@Override
		public Schedule read(JsonReader jsonReader) throws IOException {
			Schedule sched = new Schedule(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>(),
					new HashMap<>(), new HashMap<>(), new HashMap<>());
			jsonReader.beginArray();
			int day = 0;
			while (jsonReader.hasNext()) {
				Map<String, ScheduledCourse> daySched = sched.getDay(day);
				boolean object = jsonReader.peek() == JsonToken.BEGIN_OBJECT;
				if (object)
					jsonReader.beginObject();
				else
					jsonReader.beginArray();
				while (jsonReader.hasNext()) {
					String name = jsonReader.nextName();
					jsonReader.beginObject();
					jsonReader.nextName();
					int start = jsonReader.nextInt();
					jsonReader.nextName();
					int end = jsonReader.nextInt();
					jsonReader.nextName();
					String content = jsonReader.nextString();
					jsonReader.nextName();
					String title = jsonReader.nextString();
					jsonReader.nextName();
					String color = jsonReader.nextString();
					jsonReader.nextName();
					String type = jsonReader.nextString();
					daySched.put(name, new ScheduledCourse(
							start,
							end,
							content,
							title,
							color,
							type));
					jsonReader.endObject();
				}
				if (object)
					jsonReader.endObject();
				else
					jsonReader.endArray();
				day++;
			}
			jsonReader.endArray();
			return sched;
		}

	}

}
