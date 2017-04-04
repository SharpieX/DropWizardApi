package com.routify.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.routify.core.Task;

public class TaskDb {

	public static HashMap<Integer, Task> tasks = new HashMap<>();
	static {
		tasks.put(1, new Task(1, "A POST api to put a todo task in database",
				"Raunak", "Ambuj"));
		tasks.put(2, new Task(2, "A GET api to fetch all todos from database",
				"Raunak", "Ambuj"));
		tasks.put(3, new Task(3,
				"A DELETE api to delete a todo task using id of the task",
				"Raunak", "Ambuj"));
	}

	public static List<Task> getAllTasks() {
		return new ArrayList<Task>(tasks.values());
	}

	
	public static Integer getMaxId() {
		int max = 0;
		for (Task task : tasks.values()) {
			if (task.getId() > max) {
				max = task.getId();
			}
		}
		return max;
	}
	
	public static Task getTask(Integer id) {
		return tasks.get(id);
	}

	public static void updateTasks(Integer id, Task task) {
		tasks.put(id, task);
	}

	public static void removeTask(Integer id) {
		tasks.remove(id);
	}
}
