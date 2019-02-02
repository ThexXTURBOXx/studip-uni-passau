package de.femtopedia.studip;

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
import de.femtopedia.studip.shib.ShibHttpResponse;
import de.femtopedia.studip.shib.ShibbolethClient;
import de.femtopedia.studip.util.ScheduleHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.cookie.Cookie;

/**
 * A simple-to-use API class to access the Stud.IP RestAPI.
 */
public class StudIPAPI {

	private static final String BASE_URL = "https://studip.uni-passau.de/studip/api.php/";
	private ShibbolethClient sc;
	private Gson gson;

	/**
	 * Initializes a new {@link StudIPAPI} instance.
	 */
	public StudIPAPI() {
		this(null, "");
	}

	/**
	 * Initializes a new {@link StudIPAPI} instance.
	 *
	 * @param keyStore A custom KeyStore as {@link InputStream} to set or null
	 * @param password The KeyStore's password
	 */
	public StudIPAPI(InputStream keyStore, String password) {
		this.sc = new ShibbolethClient(keyStore, password);
		this.gson = new GsonBuilder().create();
	}

	/**
	 * Initializes a new {@link StudIPAPI} instance with cookies.
	 *
	 * @param cookies  the cookies to load into the Cookie Store
	 * @param keyStore A custom KeyStore as {@link InputStream} to set or null
	 * @param password The KeyStore's password
	 */
	public StudIPAPI(List<Cookie> cookies, InputStream keyStore, String password) {
		this(keyStore, password);
		for (Cookie c : cookies)
			this.sc.getCookieStore().addCookie(c);
	}

	/**
	 * Authenticates against the Shibboleth SSO.
	 *
	 * @param username The username to login
	 * @param password The password to login
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the user credentials are not correct
	 * @throws IllegalStateException    if cookies don't match the server
	 */
	public void authenticate(String username, String password) throws IOException, IllegalArgumentException, IllegalAccessException, IllegalStateException {
		this.sc.authenticate(username, password);
	}

	/**
	 * Returns the currently used {@link ShibbolethClient} instance.
	 *
	 * @return the currently used {@link ShibbolethClient} instance
	 */
	public ShibbolethClient getShibbolethClient() {
		return this.sc;
	}

	/**
	 * Helper method to convert an InputStream into a single String (using UTF-8).
	 *
	 * @param stream The {@link InputStream} to convert.
	 * @return the converted String
	 * @throws IOException if reading errors occur
	 */
	private String getString(InputStream stream) throws IOException {
		StringBuilder result = new StringBuilder();
		for (String line : ShibbolethClient.readLines(stream))
			result.append(line);
		stream.close();
		return result.toString();
	}

	/**
	 * Performs a HTTP GET Request.
	 *
	 * @param url The URL to get
	 * @return A {@link ShibHttpResponse} representing the result
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public ShibHttpResponse get(String url) throws IOException, IllegalArgumentException, IllegalAccessException {
		return this.sc.get(BASE_URL + url);
	}

	/**
	 * Performs a HTTP GET Request and converts the site's content into the given Object type using Gson.
	 *
	 * @param api_url   The URL to get
	 * @param obj_class The class to cast to
	 * @param <T>       The wanted Generic type
	 * @return the parsed and converted object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public <T> T getData(String api_url, Class<T> obj_class) throws IOException, IllegalArgumentException, IllegalAccessException {
		ShibHttpResponse r = this.get(api_url);
		try {
			return gson.fromJson(getString(r.getResponse().getEntity().getContent()), obj_class);
		} finally {
			r.close();
		}
	}

	/**
	 * Returns the currently logged in user's data.
	 *
	 * @return the parsed and converted {@link User} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public User getCurrentUserData() throws IOException, IllegalArgumentException, IllegalAccessException {
		return this.getData("user", User.class);
	}

	/**
	 * Returns data about the searched user.
	 *
	 * @param userID The user to search for
	 * @return the parsed and converted {@link User} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public User getUserData(String userID) throws IOException, IllegalArgumentException, IllegalAccessException {
		return this.getData("user/" + userID, User.class);
	}

	/**
	 * Returns data about the searched user's contacts.
	 *
	 * @param userID The user to search for
	 * @return the parsed and converted {@link Contacts} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public Contacts getContacts(String userID) throws IOException, IllegalArgumentException, IllegalAccessException {
		return this.getData("user/" + userID + "/contacts", Contacts.class);
	}

	/**
	 * Returns data about the searched user's events.
	 *
	 * @param userID The user to search for
	 * @return the parsed and converted {@link Events} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public Events getEvents(String userID) throws IOException, IllegalArgumentException, IllegalAccessException {
		return this.getData("user/" + userID + "/events", Events.class);
	}

	/**
	 * Returns data about the searched course.
	 *
	 * @param courseID The course to search for
	 * @return the parsed and converted {@link Course} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public Course getCourse(String courseID) throws IOException, IllegalArgumentException, IllegalAccessException {
		return this.getData("course/" + courseID, Course.class);
	}

	/**
	 * Returns data about the searched user's courses.
	 *
	 * @param userID The user to search for
	 * @return the parsed and converted {@link Courses} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public Courses getCourses(String userID) throws IOException, IllegalArgumentException, IllegalAccessException {
		return this.getData("user/" + userID + "/courses", Courses.class);
	}

	/**
	 * Returns data about all semesters.
	 *
	 * @return the parsed and converted {@link Semesters} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public Semesters getSemesters() throws IOException, IllegalArgumentException, IllegalAccessException {
		return this.getData("semesters", Semesters.class);
	}

	/**
	 * Returns data about the searched semester.
	 *
	 * @param semesterID The semester to search for
	 * @return the parsed and converted {@link Semester} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public Semester getSemester(String semesterID) throws IOException, IllegalArgumentException, IllegalAccessException {
		return this.getData("semester/" + semesterID, Semester.class);
	}

	/**
	 * Returns data about the currently logged in user's schedule.
	 * THIS METHOD WILL GET REMOVED, WHEN THE RESTAPI SCHEDULE IS FIXED.
	 * THIS IS A TEMPORARY METHOD, THAT HARDCODED PARSES THE HTML TEXT.
	 * IT'S VERY UNSAFE AND CAN BREAK EASILY!!!
	 *
	 * @return the parsed and converted {@link de.femtopedia.studip.util.Schedule} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public de.femtopedia.studip.util.Schedule getSchedule() throws IOException, IllegalArgumentException, IllegalAccessException {
		return ScheduleHelper.getData(this);
	}

	/**
	 * Returns data about the currently logged in user's schedule.
	 * NOT FUNCTIONAL RIGHT NOW, BECAUSE OF PROBLEMS WITH THE RESTAPI.
	 * USE {@link StudIPAPI#getSchedule()} INSTEAD UNTIL THE PROBLEM IS FIXED!
	 *
	 * @param userID The user to search for
	 * @return the parsed and converted {@link Schedule} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public Schedule getSchedule(String userID) throws IOException, IllegalArgumentException, IllegalAccessException {
		return this.getData("user/" + userID + "/schedule", Schedule.class);
	}

	/**
	 * Returns data about the currently logged in user's schedule.
	 * NOT FUNCTIONAL RIGHT NOW, BECAUSE OF PROBLEMS WITH THE RESTAPI.
	 * USE {@link StudIPAPI#getSchedule()} INSTEAD UNTIL THE PROBLEM IS FIXED!
	 *
	 * @param userID     The user to search for
	 * @param semesterID The semester to search for
	 * @return the parsed and converted {@link Schedule} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 */
	public Schedule getSchedule(String userID, String semesterID) throws IOException, IllegalArgumentException, IllegalAccessException {
		return this.getData("user/" + userID + "/schedule/" + semesterID, Schedule.class);
	}

	/**
	 * Shuts down the {@link ShibbolethClient}.
	 */
	public void shutdown() {
		this.sc.shutdown();
	}

}
