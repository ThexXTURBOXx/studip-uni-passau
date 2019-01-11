package de.femtopedia.studip;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.femtopedia.studip.json.Contacts;
import de.femtopedia.studip.json.Course;
import de.femtopedia.studip.json.Courses;
import de.femtopedia.studip.json.Events;
import de.femtopedia.studip.json.Semester;
import de.femtopedia.studip.json.Semesters;
import de.femtopedia.studip.json.User;
import de.femtopedia.studip.shib.ShibbolethClient;
import de.femtopedia.studip.util.Schedule;
import de.femtopedia.studip.util.ScheduleHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.cookie.Cookie;

public class StudIPAPI {

	private static final String BASE_URL = "https://studip.uni-passau.de/studip/api.php/";
	private ShibbolethClient sc;
	private Gson gson;

	public StudIPAPI() {
		this.sc = new ShibbolethClient();
		this.gson = new GsonBuilder().create();
	}

	public StudIPAPI(List<Cookie> cookies) {
		this();
		for (Cookie c : cookies)
			this.sc.getCookieStore().addCookie(c);
	}

	public void authenticate(String username, String password) throws IOException, IllegalArgumentException, IllegalAccessException, IllegalStateException {
		this.sc.authenticateIfNeeded(username, password);
	}

	public ShibbolethClient getShibbolethClient() {
		return this.sc;
	}

	private String getString(InputStream stream) throws IOException {
		StringBuilder result = new StringBuilder();
		for (String line : ShibbolethClient.readLines(stream))
			result.append(line);
		return result.toString();
	}

	public HttpResponse get(String url) throws IOException, IllegalAccessException {
		return this.sc.getIfValid(BASE_URL + url);
	}

	public <T> T getData(String api_url, Class<T> obj_class) throws IOException, IllegalAccessException {
		HttpResponse r = this.get(api_url);
		return gson.fromJson(getString(r.getEntity().getContent()), obj_class);
	}

	public User getCurrentUserData() throws IOException, IllegalAccessException {
		return this.getData("user", User.class);
	}

	public User getUserData(String userID) throws IOException, IllegalAccessException {
		return this.getData("user/" + userID, User.class);
	}

	public Contacts getContacts(String userID) throws IOException, IllegalAccessException {
		return this.getData("user/" + userID + "/contacts", Contacts.class);
	}

	public Events getEvents(String userID) throws IOException, IllegalAccessException {
		return this.getData("user/" + userID + "/events", Events.class);
	}

	public Course getCourse(String courseID) throws IOException, IllegalAccessException {
		return this.getData("course/" + courseID, Course.class);
	}

	public Courses getCourses(String userID) throws IOException, IllegalAccessException {
		return this.getData("user/" + userID + "/courses", Courses.class);
	}

	public Semesters getSemesters() throws IOException, IllegalAccessException {
		return this.getData("semesters", Semesters.class);
	}

	public Semester getSemester(String semesterID) throws IOException, IllegalAccessException {
		return this.getData("semester/" + semesterID, Semester.class);
	}

	public Schedule getSchedule() throws IOException, IllegalAccessException {
		return ScheduleHelper.getData(this);
	}

	/*public Schedule getSchedule(String userID) throws IOException, IllegalAccessException {
		return this.getData("user/" + userID + "/schedule", Schedule.class);
	}

	public Schedule getSchedule(String userID, String semesterID) throws IOException, IllegalAccessException {
		return this.getData("user/" + userID + "/schedule/" + semesterID, Schedule.class);
	}*/

	public void shutdown() {
		this.sc.shutdown();
	}

}
