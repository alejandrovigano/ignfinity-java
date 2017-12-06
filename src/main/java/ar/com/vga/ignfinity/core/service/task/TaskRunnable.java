package ar.com.vga.ignfinity.core.service.task;

import ar.com.vga.ignfinity.core.domain.ScheduledTask;
import ar.com.vga.ignfinity.core.endpoint.entities.IgnfinityContext;
import ar.com.vga.ignfinity.core.exception.IgnfinityException;

@FunctionalInterface
public interface TaskRunnable<T extends Task> {

	void doTask(ScheduledTask scheduledTask, T task, IgnfinityContext context) throws IgnfinityException;

}
