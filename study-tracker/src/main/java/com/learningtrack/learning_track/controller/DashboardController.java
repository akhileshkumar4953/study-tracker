package com.learningtrack.learning_track.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learningtrack.learning_track.dto.DashboardResponse;
import com.learningtrack.learning_track.enums.Status;
import com.learningtrack.learning_track.repository.TopicRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final TopicRepository repository;

    @GetMapping
    public DashboardResponse dashboard() {

        long total = repository.count();

        long completed = repository.findAll()
                .stream()
                .filter(topic -> topic.getStatus() == Status.COMPLETED)
                .count();

        double averageProgress = repository.findAll()
                .stream()
                .mapToInt(topic -> topic.getProgress() == null ? 0 : topic.getProgress())
                .average()
                .orElse(0);

        return DashboardResponse.builder()
                .totalTopics(total)
                .completedTopics(completed)
                .progressPercentage(averageProgress)
                .build();
    }

}