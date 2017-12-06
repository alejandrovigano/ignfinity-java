package ar.com.vga.ignfinity.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.vga.ignfinity.core.domain.Ignfinity;

public interface IgnfinityRepository extends JpaRepository<Ignfinity, Long> {

}
