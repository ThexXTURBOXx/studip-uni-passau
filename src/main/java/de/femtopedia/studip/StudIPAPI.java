package de.femtopedia.studip;

import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.femtopedia.studip.json.Contacts;
import de.femtopedia.studip.json.Course;
import de.femtopedia.studip.json.Courses;
import de.femtopedia.studip.json.Events;
import de.femtopedia.studip.json.Schedule;
import de.femtopedia.studip.json.Semester;
import de.femtopedia.studip.json.Semesters;
import de.femtopedia.studip.json.User;
import de.femtopedia.studip.shib.ShibbolethClient;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.cookie.Cookie;

public class StudIPAPI {

	private static final String BASE_URL = "https://studip.uni-passau.de/studip/api.php/";
	private ShibbolethClient sc;
	private ApacheHttpTransport client;
	private Gson gson;

	public StudIPAPI(String username, String password) throws IOException, IllegalArgumentException, IllegalAccessException, IllegalStateException {
		this.sc = new ShibbolethClient();
		this.sc.authenticateIfNeeded(username, password);
		this.client = this.sc.client;
		this.gson = new GsonBuilder().create();
	}

	public StudIPAPI(String username, String password, List<Cookie> cookies) throws IOException, IllegalArgumentException, IllegalAccessException, IllegalStateException {
		this.sc = new ShibbolethClient(cookies);
		this.sc.authenticateIfNeeded(username, password);
		this.client = this.sc.client;
		this.gson = new GsonBuilder().create();
	}

	public ShibbolethClient getShibbolethClient() {
		return this.sc;
	}

	public ApacheHttpTransport getClient() {
		return this.client;
	}

	private String getString(InputStream stream) throws IOException {
		StringBuilder result = new StringBuilder();
		for (String line : ShibbolethClient.readLines(stream))
			result.append(line);
		return result.toString();
	}

	public HttpResponse get(String url) throws IOException {
		return this.sc.get(BASE_URL + url);
	}

	public User getCurrentUserData() throws IOException {
		HttpResponse r = this.get("user");
		HttpEntity e = r.getEntity();
		return gson.fromJson(getString(e.getContent()), User.class);
	}

	public User getUserData(String userID) throws IOException {
		HttpResponse r = this.get("user/" + userID);
		HttpEntity e = r.getEntity();
		return gson.fromJson(getString(e.getContent()), User.class);
	}

	public Contacts getContacts(String userID) throws IOException {
		HttpResponse r = this.get("user/" + userID + "/contacts");
		HttpEntity e = r.getEntity();
		return gson.fromJson(getString(e.getContent()), Contacts.class);
	}

	public Events getEvents(String userID) throws IOException {
		HttpResponse r = this.get("user/" + userID + "/events");
		HttpEntity e = r.getEntity();
		return gson.fromJson(getString(e.getContent()), Events.class);
	}

	public Course getCourse(String courseID) throws IOException {
		HttpResponse r = this.get("course/" + courseID);
		HttpEntity e = r.getEntity();
		return gson.fromJson(getString(e.getContent()), Course.class);
	}

	public Courses getCourses(String userID) throws IOException {
		HttpResponse r = this.get("user/" + userID + "/courses");
		HttpEntity e = r.getEntity();
		return gson.fromJson(getString(e.getContent()), Courses.class);
	}

	public Semesters getSemesters() throws IOException {
		HttpResponse r = this.get("semesters");
		HttpEntity e = r.getEntity();
		return gson.fromJson(getString(e.getContent()), Semesters.class);
	}

	public Semester getSemester(String semesterID) throws IOException {
		HttpResponse r = this.get("semester/" + semesterID);
		HttpEntity e = r.getEntity();
		return gson.fromJson(getString(e.getContent()), Semester.class);
	}

	public Schedule getSchedule(String userID) throws IOException {
		HttpResponse r = this.get("user/" + userID + "/schedule");
		HttpEntity e = r.getEntity();
		return gson.fromJson(getString(e.getContent()), Schedule.class);
	}

	public Schedule getSchedule(String userID, String semesterID) throws IOException {
		HttpResponse r = this.get("user/" + userID + "/schedule/" + semesterID);
		HttpEntity e = r.getEntity();
		return gson.fromJson(getString(e.getContent()), Schedule.class);
	}

}
