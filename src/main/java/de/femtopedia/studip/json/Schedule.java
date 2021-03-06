package de.femtopedia.studip.json;

import com.google.gson.annotations.SerializedName;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * A class representing a Schedule.
 */
@SuppressWarnings({"MemberName", "JavadocVariable"})
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Schedule {

    @SerializedName("0")
    private final Map<String, ScheduledCourse> monday;
    @SerializedName("1")
    private final Map<String, ScheduledCourse> tuesday;
    @SerializedName("2")
    private final Map<String, ScheduledCourse> wednesday;
    @SerializedName("3")
    private final Map<String, ScheduledCourse> thursday;
    @SerializedName("4")
    private final Map<String, ScheduledCourse> friday;
    @SerializedName("5")
    private final Map<String, ScheduledCourse> saturday;
    @SerializedName("6")
    private final Map<String, ScheduledCourse> sunday;

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

}
