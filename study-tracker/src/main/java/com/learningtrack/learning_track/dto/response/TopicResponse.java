package com.learningtrack.learning_track.dto.response;

import com.learningtrack.learning_track.enums.Priority;
import com.learningtrack.learning_track.enums.Status;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TopicResponse {

    private Long id;

    private String title;

    private String category;

    private Status status;

    private Priority priority;

    private Integer progress;

    private String notes;

}