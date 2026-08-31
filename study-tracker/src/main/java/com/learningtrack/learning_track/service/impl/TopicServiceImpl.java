package com.learningtrack.learning_track.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learningtrack.learning_track.dto.request.TopicRequest;
import com.learningtrack.learning_track.dto.response.TopicResponse;
import com.learningtrack.learning_track.entity.Topic;
import com.learningtrack.learning_track.enums.Priority;
import com.learningtrack.learning_track.enums.Status;
import com.learningtrack.learning_track.exception.ResourceNotFoundException;
import com.learningtrack.learning_track.mapper.TopicMapper;
import com.learningtrack.learning_track.repository.TopicRepository;
import com.learningtrack.learning_track.service.TopicService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository repository;

    @Override
    public TopicResponse save(TopicRequest request) {

        Topic topic = TopicMapper.toEntity(request);

        Topic savedTopic = repository.save(topic);

        return TopicMapper.toResponse(savedTopic);
    }

    @Override
    public List<TopicResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(TopicMapper::toResponse)
                .toList();
    }

    @Override
    public TopicResponse getById(Long id) {

        Topic topic = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic Not Found"));

        return TopicMapper.toResponse(topic);
    }

    @Override
    public TopicResponse update(Long id, TopicRequest request) {

        Topic topic = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic Not Found"));

        topic.setTitle(request.getTitle());
        topic.setCategory(request.getCategory());
        topic.setStatus(request.getStatus());
        topic.setPriority(request.getPriority());
        topic.setProgress(request.getProgress());
        topic.setNotes(request.getNotes());

        Topic updatedTopic = repository.save(topic);

        return TopicMapper.toResponse(updatedTopic);
    }

    @Override
    public void delete(Long id) {

        Topic topic = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic Not Found"));

        repository.delete(topic);
    }

    @Override
    public List<TopicResponse> search(String keyword) {

        return repository.findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(TopicMapper::toResponse)
                .toList();
    }

    @Override
    public Page<TopicResponse> getAll(int page, int size) {

        return repository.findAll(PageRequest.of(page, size))
                .map(TopicMapper::toResponse);
    }

    @Override
    public List<TopicResponse> getByStatus(Status status) {

        return repository.findByStatus(status)
                .stream()
                .map(TopicMapper::toResponse)
                .toList();
    }

    @Override
    public List<TopicResponse> getByPriority(Priority priority) {

        return repository.findByPriority(priority)
                .stream()
                .map(TopicMapper::toResponse)
                .toList();
    }

    @Override
    public TopicResponse markCompleted(Long id) {

        Topic topic = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic Not Found"));

        topic.setStatus(Status.COMPLETED);
        topic.setProgress(100);

        Topic updatedTopic = repository.save(topic);

        return TopicMapper.toResponse(updatedTopic);
    }

}