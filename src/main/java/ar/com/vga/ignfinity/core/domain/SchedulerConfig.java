package ar.com.vga.ignfinity.core.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SchedulerConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Integer maxLikes;
	private Integer minLikes;

	private Integer minMinsDelayLikes;
	private Integer maxMinsDelayLikes;

	private Integer maxLikesPerDay;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMaxLikesPerDay() {
		return maxLikesPerDay;
	}

	public void setMaxLikesPerDay(Integer maxLikesPerDay) {
		this.maxLikesPerDay = maxLikesPerDay;
	}

	public Integer getMaxLikes() {
		return maxLikes;
	}

	public void setMaxLikes(Integer maxLikes) {
		this.maxLikes = maxLikes;
	}

	public Integer getMinLikes() {
		return minLikes;
	}

	public void setMinLikes(Integer minLikes) {
		this.minLikes = minLikes;
	}

	public Integer getMinMinsDelayLikes() {
		return minMinsDelayLikes;
	}

	public void setMinMinsDelayLikes(Integer minMinsDelayLikes) {
		this.minMinsDelayLikes = minMinsDelayLikes;
	}

	public Integer getMaxMinsDelayLikes() {
		return maxMinsDelayLikes;
	}

	public void setMaxMinsDelayLikes(Integer maxMinsDelayLikes) {
		this.maxMinsDelayLikes = maxMinsDelayLikes;
	}

}
