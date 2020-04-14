package de.femtopedia.studip;

import com.google.gson.GsonBuilder;
import de.femtopedia.studip.json.Contact;
import de.femtopedia.studip.json.Contacts;
import de.femtopedia.studip.json.Course;
import de.femtopedia.studip.json.Courses;
import de.femtopedia.studip.json.DataField;
import de.femtopedia.studip.json.Event;
import de.femtopedia.studip.json.Events;
import de.femtopedia.studip.json.Folder;
import de.femtopedia.studip.json.Links;
import de.femtopedia.studip.json.Members;
import de.femtopedia.studip.json.Modules;
import de.femtopedia.studip.json.Name;
import de.femtopedia.studip.json.Pagination;
import de.femtopedia.studip.json.Schedule;
import de.femtopedia.studip.json.ScheduledCourse;
import de.femtopedia.studip.json.Semester;
import de.femtopedia.studip.json.Semesters;
import de.femtopedia.studip.json.SubFile;
import de.femtopedia.studip.json.SubFolder;
import de.femtopedia.studip.json.User;

/**
 * Utility class for registering all JSON Objects.
 */
public final class JsonObjectRegister {

    private JsonObjectRegister() {
        throw new UnsupportedOperationException();
    }

    /**
     * Registers all JSON Objects to the given {@link GsonBuilder}.
     *
     * @param builder The builder to register the objects to.
     */
    public static void init(GsonBuilder builder) {
        register(builder, Contact.class);
        register(builder, Contacts.class);
        register(builder, Course.class);
        register(builder, Courses.class);
        register(builder, DataField.class);
        register(builder, Event.class);
        register(builder, Events.class);
        register(builder, Folder.class);
        register(builder, Links.class);
        register(builder, Members.class);
        register(builder, Modules.class);
        register(builder, Name.class);
        register(builder, Pagination.class);
        register(builder, Schedule.class);
        register(builder, ScheduledCourse.class);
        register(builder, Semester.class);
        register(builder, Semesters.class);
        register(builder, SubFile.class);
        register(builder, SubFolder.class);
        register(builder, User.class);
    }

    private static <T> void register(GsonBuilder builder, Class<T> clazz) {
        builder.registerTypeAdapterFactory(
                new EmptyArrayAdapterFactory<>(clazz));
    }

}
