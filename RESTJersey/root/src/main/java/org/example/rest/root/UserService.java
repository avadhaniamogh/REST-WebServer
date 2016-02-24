package org.example.rest.root;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.example.rest.root.presenter.UserPresenter;
import org.example.root.model.User;

@Path("/user")
public class UserService {

	/**
	 * Method handling HTTP GET requests. The returned object will be sent
	 * to the client as "application/json" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/users")
	public List<User> getUsers() {
		List<User> userList = new ArrayList<>();
		UserPresenter presenetr = new UserPresenter();
		userList = presenetr.retrieveAllUsers();
		return userList;
	}

	/**
	 * Method handling HTTP POST requests.
	 */
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/insert/{id}/{firstname}/{lastname}/{age}")
	public String addUser(@PathParam("id") int id, @PathParam("firstname") String firstname, 
			@PathParam("lastname") String lastname, @PathParam("age") int age) {
		String outcome = "";
		UserPresenter presenter = new UserPresenter();
		User user = new User(id, firstname, lastname, age);
		boolean result = presenter.insertUser(user);
		outcome = String.valueOf(result);
		return outcome;
	}

}
