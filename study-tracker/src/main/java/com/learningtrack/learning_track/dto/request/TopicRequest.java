package com.learningtrack.learning_track.dto.request;

import com.learningtrack.learning_track.enums.Priority;
import com.learningtrack.learning_track.enums.Status;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String category;

    private Status status;

    private Priority priority;

    @Min(0)
    @Max(100)
    private Integer progress;

    private String notes;

}