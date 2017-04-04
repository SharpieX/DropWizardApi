package com.routify.resources;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.routify.core.Task;
import com.routify.db.TaskDb;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResources {
 
    private final Validator validator;
 
    public TaskResources(Validator validator) {
        this.validator = validator;
    }
 
    @GET
    public Response getAllTasks() {
        return Response.ok(TaskDb.getAllTasks()).build();
    }

    @GET
    @Path("/{id}")
    public Response getTaskById(@PathParam("id") Integer id) {
        Task task = TaskDb.getTask(id);
        if (task != null)
            return Response.ok(task).build();
        else
            return Response.status(Status.NOT_FOUND).build();
    }

	@POST
	public Response createTask(Task task) throws URISyntaxException {
		// validation
		Set<ConstraintViolation<Task>> violations = validator.validate(task);
		Task e = TaskDb.getTask(task.getId());
		int maxId = TaskDb.getMaxId();

		if (violations.size() > 0) {
			ArrayList<String> validationMessages = new ArrayList<String>();
			for (ConstraintViolation<Task> violation : violations) {
				validationMessages.add(violation.getPropertyPath().toString()
						+ ": " + violation.getMessage());
			}
			return Response.status(Status.BAD_REQUEST)
					.entity(validationMessages).build();
		}
		if (e != null) {
			TaskDb.updateTasks(task.getId(), task);
			return Response.created(new URI("/tasks/" + task.getId())).build();
		} else if (task.getId() == null) {
			task.setId(++maxId);
			TaskDb.updateTasks(++maxId, task);
			return Response.created(new URI("/tasks/" + task.getId())).build();

		}
		return Response.status(Status.NOT_FOUND).build();
	}
 
 
    @DELETE
    @Path("/{id}")
    public Response removeTaskById(@PathParam("id") Integer id) {
        Task task = TaskDb.getTask(id);
        if (task != null) {
            TaskDb.removeTask(id);
            return Response.ok().build();
        } else
            return Response.status(Status.NOT_FOUND).build();
    }
}
