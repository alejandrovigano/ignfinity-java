package ar.com.vga.ignfinity.core.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.vga.ignfinity.core.domain.ScheduledTask;
import ar.com.vga.ignfinity.core.service.task.TaskState;

public interface ScheduledTaskRepository extends JpaRepository<ScheduledTask, Long> {

	@Query("SELECT max(st.startTime) FROM ScheduledTask st")
	Date findMaxStartTime();

	List<ScheduledTask> findByStartTimeBetweenAndState(Date now, Date end, TaskState planned);
}
