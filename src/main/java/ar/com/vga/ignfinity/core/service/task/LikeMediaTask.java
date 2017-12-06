package ar.com.vga.ignfinity.core.service.task;

import ar.com.vga.ignfinity.core.endpoint.entities.media.Media;
import ar.com.vga.ignfinity.core.service.TaskService;

public class LikeMediaTask extends Task {

	private Media media;

	public LikeMediaTask() {
	}

	public LikeMediaTask(Media media) {
		super();
		this.media = media;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	@Override
	public TaskRunnable<Task> accept(TaskService service) {
		return service.run(this);
	}

	@Override
	public TaskType getType() {
		return TaskType.LIKE;
	}

}
