package com.learningtrack.learning_track.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learningtrack.learning_track.entity.Topic;
import com.learningtrack.learning_track.enums.Priority;
import com.learningtrack.learning_track.enums.Status;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    List<Topic> findByTitleContainingIgnoreCase(String keyword);

    List<Topic> findByStatus(Status status);

    List<Topic> findByPriority(Priority priority);

    Page<Topic> findAll(Pageable pageable);

}