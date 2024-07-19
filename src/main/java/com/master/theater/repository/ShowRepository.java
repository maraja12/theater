package com.master.theater.repository;

import com.master.theater.domain.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
