package ar.com.vga.ignfinity.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import ar.com.vga.ignfinity.core.service.task.TaskState;
import ar.com.vga.ignfinity.core.service.task.TaskType;

@Entity
public class ScheduledTask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Date startTime;
	private TaskState state;
	private TaskType type;

	@Column(columnDefinition = "LONGBLOB")
	private String taskJson;

	public String getTaskJson() {
		return taskJson;
	}

	public void setTaskJson(String taskJson) {
		this.taskJson = taskJson;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public TaskState getState() {
		return state;
	}

	public void setState(TaskState state) {
		this.state = state;
	}

	public TaskType getType() {
		return type;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

}
