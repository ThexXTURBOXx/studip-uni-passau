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
import de.femtopedia.studip.shib.CustomAccessHttpResponse;
import de.femtopedia.studip.shib.OAuthClient;
import de.femtopedia.studip.util.ScheduleHelper;
import java.io.IOException;
import oauth.signpost.exception.OAuthException;

/**
 * A simple-to-use API class to access the Stud.IP RestAPI.
 */
public class StudIPAPI {

	private static final String BASE_URL = "https://studip.uni-passau.de/studip/api.php/";
	private OAuthClient oAuthClient;
	private Gson gson;

	/**
	 * Initializes a new {@link StudIPAPI} instance.
	 *
	 * @param consumerKey    The Consumer Key to use
	 * @param consumerSecret The Consumer Secret to use
	 */
	public StudIPAPI(String consumerKey, String consumerSecret) {
		this.oAuthClient = new OAuthClient();
		this.oAuthClient.setupOAuth(consumerKey, consumerSecret);
		this.gson = new GsonBuilder().create();
	}

	/**
	 * Retrieves a Request Token and returns a authorization URL, which you have to open in a browser
	 * and approve access to your account.
	 * This is the first function to call in the OAuth process.
	 *
	 * @param callback A callback URI or {@link oauth.signpost.OAuth#OUT_OF_BAND}.
	 * @return The Authorization URL to call
	 * @throws OAuthException if any OAuth errors occur
	 */
	public String getAuthorizationUrl(String callback) throws OAuthException {
		return this.oAuthClient.getAuthorizationUrl(callback);
	}

	/**
	 * Retrieves a working Access Token and saves it.
	 * This is the second function to call in the OAuth process (call {@link StudIPAPI#getAuthorizationUrl(String)}
	 * first and authorize in a browser).
	 *
	 * @param verifyToken The Verification Code from the Authorization process.
	 * @throws OAuthException if any OAuth errors occur
	 */
	public void verifyAccess(String verifyToken) throws OAuthException {
		this.oAuthClient.verifyAccess(verifyToken);
	}

	/**
	 * Returns the currently used {@link OAuthClient} instance.
	 *
	 * @return the currently used {@link OAuthClient} instance
	 */
	public OAuthClient getOAuthClient() {
		return this.oAuthClient;
	}

	/**
	 * Returns, whether the current session is valid or you need to re-login.
	 *
	 * @return true if and only if the current session is valid
	 * @throws IOException    if reading errors occur
	 * @throws OAuthException if any OAuth errors occur
	 */
	public boolean isSessionValid() throws IOException, OAuthException {
		return this.oAuthClient.isSessionValid();
	}

	/**
	 * Performs a HTTP GET Request.
	 *
	 * @param url The URL to get
	 * @return A {@link CustomAccessHttpResponse} representing the result
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public CustomAccessHttpResponse get(String url)
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
		return this.oAuthClient.get(BASE_URL + url);
	}

	/**
	 * Performs a HTTP GET Request and converts the site's content into the given Object type using Gson.
	 *
	 * @param apiUrl   The URL to get
	 * @param objClass The class to cast to
	 * @param <T>      The wanted Generic type
	 * @return the parsed and converted object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public <T> T getData(String apiUrl, Class<T> objClass)
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
		CustomAccessHttpResponse response = null;
		try {
			response = this.get(apiUrl);
			//Need to hardcode, because server returns 200 anyway
			if (!response.getResponse().body().contentType().subtype().equals("json"))
				throw new IllegalAccessException("Session is not valid!");
			return gson.fromJson(response.readLine(), objClass);
		} finally {
			if (response != null)
				response.close();
		}
	}

	/**
	 * Returns the currently logged in user's data.
	 *
	 * @return the parsed and converted {@link User} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public User getCurrentUserData()
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
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
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public User getUserData(String userID)
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
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
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public Contacts getContacts(String userID)
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
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
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public Events getEvents(String userID)
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
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
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public Course getCourse(String courseID)
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
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
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public Courses getCourses(String userID)
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
		return this.getData("user/" + userID + "/courses", Courses.class);
	}

	/**
	 * Returns data about all semesters.
	 *
	 * @return the parsed and converted {@link Semesters} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public Semesters getSemesters()
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
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
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public Semester getSemester(String semesterID)
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
		return this.getData("semester/" + semesterID, Semester.class);
	}

	/**
	 * Returns data about the currently logged in user's schedule.
	 *
	 * @return the parsed and converted {@link de.femtopedia.studip.util.Schedule} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 * @throws OAuthException           if any OAuth errors occur
	 * @deprecated use {@link #getSchedule(String)} or {@link #getSchedule(String, String)}
	 * instead. This was only a temporary solution to an API problem.
	 */
	@Deprecated
	public de.femtopedia.studip.util.Schedule getSchedule()
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
		return ScheduleHelper.getData(this);
	}

	/**
	 * Returns data about the currently logged in user's schedule.
	 *
	 * @param userID The user to search for
	 * @return the parsed and converted {@link Schedule} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public Schedule getSchedule(String userID)
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
		return this.getData("user/" + userID + "/schedule", Schedule.class);
	}

	/**
	 * Returns data about the currently logged in user's schedule.
	 *
	 * @param userID     The user to search for
	 * @param semesterID The semester to search for
	 * @return the parsed and converted {@link Schedule} object
	 * @throws IOException              if reading errors occur
	 * @throws IllegalArgumentException if the header values are broken
	 * @throws IllegalAccessException   if the session isn't valid
	 * @throws OAuthException           if any OAuth errors occur
	 */
	public Schedule getSchedule(String userID, String semesterID)
			throws IOException, IllegalArgumentException, IllegalAccessException, OAuthException {
		return this.getData("user/" + userID + "/schedule/" + semesterID, Schedule.class);
	}

}
