package ar.com.vga.ignfinity.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.vga.ignfinity.core.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

	@Query("select count(l) from like_ l where l.mediaId = ?1")
	int countByMediaId(String mediaId);
}
