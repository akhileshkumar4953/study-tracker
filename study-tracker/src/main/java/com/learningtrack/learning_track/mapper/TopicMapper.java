package com.learningtrack.learning_track.mapper;

import com.learningtrack.learning_track.dto.request.TopicRequest;
import com.learningtrack.learning_track.dto.response.TopicResponse;
import com.learningtrack.learning_track.entity.Topic;

public class TopicMapper {

    public static Topic toEntity(TopicRequest request) {

        return Topic.builder()
                .title(request.getTitle())
                .category(request.getCategory())
                .status(request.getStatus())
                .priority(request.getPriority())
                .progress(request.getProgress())
                .notes(request.getNotes())
                .build();

    }

    public static TopicResponse toResponse(Topic topic) {

        return TopicResponse.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .category(topic.getCategory())
                .status(topic.getStatus())
                .priority(topic.getPriority())
                .progress(topic.getProgress())
                .notes(topic.getNotes())
                .build();

    }

}