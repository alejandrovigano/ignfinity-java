package ar.com.vga.ignfinity.core.service.task;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ar.com.vga.ignfinity.core.service.TaskService;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class Task {

	private String userid;

	private Date startTime;

	public abstract TaskRunnable<Task> accept(TaskService service);

	public abstract TaskType getType();

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

}
