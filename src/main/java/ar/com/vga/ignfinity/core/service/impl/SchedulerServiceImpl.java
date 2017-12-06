package ar.com.vga.ignfinity.core.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.vga.ignfinity.core.domain.ScheduledTask;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.endpoint.entities.media.Media;
import ar.com.vga.ignfinity.core.repository.ScheduledTaskRepository;
import ar.com.vga.ignfinity.core.repository.SchedulerConfigRepository;
import ar.com.vga.ignfinity.core.service.SchedulerService;
import ar.com.vga.ignfinity.core.service.TaskService;
import ar.com.vga.ignfinity.core.service.task.LikeMediaTask;
import ar.com.vga.ignfinity.core.service.task.Task;
import ar.com.vga.ignfinity.core.service.task.TaskState;

@Component
public class SchedulerServiceImpl implements SchedulerService {

	@Autowired
	private SchedulerConfigRepository repository;

	@Autowired
	private ScheduledTaskRepository taskRepository;

	@Autowired
	private TaskService taskService;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private ScheduledTaskRepository scheduledTaskRepository;

	@Override
	public synchronized List<ScheduledTask> scheduleLikeMedia(List<Media> media, IgnfinityContext context,
			Integer minsDelay) {
		return scheduleTask(media, (s, m) -> {
			LikeMediaTask task = new LikeMediaTask(m);
			task.setUserid(context.getUser().getId());
			return task;
		}, minsDelay);
	}
	
	

	private List<ScheduledTask> scheduleTask(List<Media> media, BiFunction<ScheduledTask, Media, Task> getTask,
			Integer minsDelay) {

		List<ScheduledTask> scheduledTasks = new ArrayList<>();

		try {

			Date baseStartTime = taskRepository.findMaxStartTime();
			if (baseStartTime == null) {
				baseStartTime = new Date();
			}

			for (Media media2 : media) {

				ScheduledTask scheduledTask = new ScheduledTask();
				// agrego un delay entre request
				scheduledTask.setStartTime(addMinutes(baseStartTime, 1));
				scheduledTask.setState(TaskState.PLANNED);

				Task task = getTask.apply(scheduledTask, media2);
				task.setStartTime(scheduledTask.getStartTime());
				String taskJson;
				taskJson = mapper.writeValueAsString(task);

				scheduledTask.setTaskJson(taskJson);
				scheduledTask.setType(task.getType());

				taskRepository.save(scheduledTask);

				scheduledTasks.add(scheduledTask);
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		taskRepository.findAll();

		return scheduledTasks;
	}

	@Override
	public void run(int range) {
		try {
			Date now = new Date();
			Date end = addMinutes(now, range);

			List<ScheduledTask> tasks = taskRepository.findByStartTimeBetweenAndState(now, end, TaskState.PLANNED);
			
			for (ScheduledTask scheduledTask : tasks) {
				scheduledTask.setState(TaskState.SCHEDULED);
				scheduledTaskRepository.save(scheduledTask);
				taskService.run(scheduledTask);
			}
		} catch (Exception e) {
			System.err.println("ERROR");
		}
	}

	private Date addMinutes(Date baseStartTime, Integer minsDelay) {
		Calendar date = Calendar.getInstance();
		long t = date.getTimeInMillis();
		return new Date(t + (minsDelay * 1000 * 60));
	}

}
