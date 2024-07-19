package com.master.theater.repository;

import com.master.theater.domain.Actor;
import com.master.theater.domain.Engagement;
import com.master.theater.domain.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EngagementRepository extends JpaRepository<Engagement, Long> {

    Optional<Engagement> findByActorAndShow(Actor actor, Show show);
    Optional<List<Engagement>> findByActor (Actor actor);
    Optional<List<Engagement>> findByShow(Show show);
    Optional<List<Engagement>> findByRoleAndShow(String role, Show show);
}
