package de.femtopedia.studip;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.femtopedia.studip.json.Contacts;
import de.femtopedia.studip.json.Course;
import de.femtopedia.studip.json.Courses;
import de.femtopedia.studip.json.Events;
import de.femtopedia.studip.json.User;
import de.femtopedia.studip.shib.ShibbolethClient;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class StudIPAPI {

	private ShibbolethClient sc;
	private CloseableHttpClient client;
	private Gson gson;

	public StudIPAPI(String username, String password) throws IOException, IllegalArgumentException, IllegalAccessException, IllegalStateException {
		this.sc = new ShibbolethClient();
		this.sc.authenticate(username, password);
		this.client = this.sc.client;
		this.gson = new GsonBuilder().create();
	}

	public ShibbolethClient getShibbolethClient() {
		return this.sc;
	}

	public CloseableHttpClient getClient() {
		return this.client;
	}

	private String getString(InputStream stream) throws IOException {
		StringBuilder result = new StringBuilder();
		for (String line : ShibbolethClient.readLines(stream))
			result.append(line);
		return result.toString();
	}

	public User getCurrentUserData() throws IOException {
		CloseableHttpResponse r = this.sc.get("https://studip.uni-passau.de/studip/api.php/user");
		HttpEntity e = r.getEntity();
		User user = gson.fromJson(getString(e.getContent()), User.class);
		EntityUtils.consume(e);
		r.close();
		return user;
	}

	public User getUserData(String userID) throws IOException {
		CloseableHttpResponse r = this.sc.get("https://studip.uni-passau.de/studip/api.php/user/" + userID);
		HttpEntity e = r.getEntity();
		User user = gson.fromJson(getString(e.getContent()), User.class);
		EntityUtils.consume(e);
		r.close();
		return user;
	}

	public Contacts getContacts(String userID) throws IOException {
		CloseableHttpResponse r = this.sc.get("https://studip.uni-passau.de/studip/api.php/user/" + userID + "/contacts");
		HttpEntity e = r.getEntity();
		Contacts contacts = gson.fromJson(getString(e.getContent()), Contacts.class);
		EntityUtils.consume(e);
		r.close();
		return contacts;
	}

	public Events getEvents(String userID) throws IOException {
		CloseableHttpResponse r = this.sc.get("https://studip.uni-passau.de/studip/api.php/user/" + userID + "/events");
		HttpEntity e = r.getEntity();
		Events events = gson.fromJson(getString(e.getContent()), Events.class);
		EntityUtils.consume(e);
		r.close();
		return events;
	}

	public Course getCourse(String courseID) throws IOException {
		CloseableHttpResponse r = this.sc.get("https://studip.uni-passau.de/studip/api.php/course/" + courseID);
		HttpEntity e = r.getEntity();
		Course course = gson.fromJson(getString(e.getContent()), Course.class);
		EntityUtils.consume(e);
		r.close();
		return course;
	}

	public Courses getCourses(String userID) throws IOException {
		CloseableHttpResponse r = this.sc.get("https://studip.uni-passau.de/studip/api.php/user/" + userID + "/courses");
		HttpEntity e = r.getEntity();
		Courses courses = gson.fromJson(getString(e.getContent()), Courses.class);
		EntityUtils.consume(e);
		r.close();
		return courses;
	}

}
