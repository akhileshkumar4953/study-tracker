package com.learningtrack.learning_track.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.learningtrack.learning_track.dto.request.TopicRequest;
import com.learningtrack.learning_track.dto.response.TopicResponse;
import com.learningtrack.learning_track.enums.Priority;
import com.learningtrack.learning_track.enums.Status;

public interface TopicService {

    TopicResponse save(TopicRequest request);

    List<TopicResponse> getAll();

    TopicResponse getById(Long id);

    TopicResponse update(Long id, TopicRequest request);

    void delete(Long id);

    List<TopicResponse> search(String keyword);

    Page<TopicResponse> getAll(int page, int size);

    List<TopicResponse> getByStatus(Status status);

    List<TopicResponse> getByPriority(Priority priority);

    TopicResponse markCompleted(Long id);

}