package de.femtopedia.studip.util;

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
import de.femtopedia.studip.util.colors.Color;
import de.femtopedia.studip.util.colors.ColorTypeAdapter;
import de.femtopedia.studip.util.fix.EmptyArrayAdapterFactory;

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
        registerFix(builder, Contact.class);
        registerFix(builder, Contacts.class);
        registerFix(builder, Course.class);
        registerFix(builder, Courses.class);
        registerFix(builder, DataField.class);
        registerFix(builder, Event.class);
        registerFix(builder, Events.class);
        registerFix(builder, Folder.class);
        registerFix(builder, Links.class);
        registerFix(builder, Members.class);
        registerFix(builder, Modules.class);
        registerFix(builder, Name.class);
        registerFix(builder, Pagination.class);
        registerFix(builder, Schedule.class);
        registerFix(builder, ScheduledCourse.class);
        registerFix(builder, Semester.class);
        registerFix(builder, Semesters.class);
        registerFix(builder, SubFile.class);
        registerFix(builder, SubFolder.class);
        registerFix(builder, User.class);

        builder.registerTypeAdapter(Color.class, new ColorTypeAdapter());
    }

    private static <T> void registerFix(GsonBuilder builder, Class<T> clazz) {
        builder.registerTypeAdapterFactory(
                new EmptyArrayAdapterFactory<>(clazz));
    }

}
