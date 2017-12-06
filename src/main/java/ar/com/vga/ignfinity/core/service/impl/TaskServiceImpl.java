package ar.com.vga.ignfinity.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.vga.ignfinity.core.domain.ScheduledTask;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;
import ar.com.vga.ignfinity.core.repository.ScheduledTaskRepository;
import ar.com.vga.ignfinity.core.service.ContextService;
import ar.com.vga.ignfinity.core.service.LikeService;
import ar.com.vga.ignfinity.core.service.TaskService;
import ar.com.vga.ignfinity.core.service.task.LikeMediaTask;
import ar.com.vga.ignfinity.core.service.task.Task;
import ar.com.vga.ignfinity.core.service.task.TaskRunnable;
import ar.com.vga.ignfinity.core.service.task.TaskState;

@Component
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskScheduler taskscheduler;

	@Autowired
	private LikeService likeService;

	@Autowired
	private ContextService contextService;

	@Autowired
	private ScheduledTaskRepository scheduledTaskRepository;

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void run(ScheduledTask scheduledTask) {
		try {
			Task task = mapper.readValue(scheduledTask.getTaskJson(), Task.class);
			TaskRunnable<Task> run = task.accept(this);

			runTask(scheduledTask, task, run);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public TaskRunnable<Task> run(LikeMediaTask likeMediaTask) {
		return (s, t, c) -> likeService.like(likeMediaTask.getMedia(), c);
	}

	private void runTask(ScheduledTask scheduledTask, Task task, TaskRunnable<Task> taskRun) {
		IgnfinityContext context = contextService.findByUserId(task.getUserid());
		taskscheduler.schedule(() -> {
			try {
				doSyncTask(scheduledTask, task, taskRun, context);
			} catch (IgnfinityException e) {
				this.fail(task);
			}
		}, task.getStartTime());
	}

	private <T extends Task> void doSyncTask(ScheduledTask scheduledTask, T task, TaskRunnable<T> taskRun,
			IgnfinityContext context) throws IgnfinityException {
		scheduledTask.setState(TaskState.RUNNING);
		scheduledTaskRepository.save(scheduledTask);
		synchronized (context) {
			try {
				taskRun.doTask(scheduledTask, task, context);
				scheduledTask.setState(TaskState.COMPLETED);
				scheduledTaskRepository.save(scheduledTask);
			} catch (Exception e) {
				scheduledTask.setState(TaskState.FAILED);
				scheduledTaskRepository.save(scheduledTask);
			}
		}
	}

	private void fail(Task task) {
		System.err.println("ERROR");
	}
}
