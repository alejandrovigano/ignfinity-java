package ar.com.vga.ignfinity.core.service;

import ar.com.vga.ignfinity.core.domain.ScheduledTask;
import ar.com.vga.ignfinity.core.service.task.LikeMediaTask;
import ar.com.vga.ignfinity.core.service.task.Task;
import ar.com.vga.ignfinity.core.service.task.TaskRunnable;

public interface TaskService {

	TaskRunnable<Task> run(LikeMediaTask likeMediaTask);

	void run(ScheduledTask scheduledTask);

}
