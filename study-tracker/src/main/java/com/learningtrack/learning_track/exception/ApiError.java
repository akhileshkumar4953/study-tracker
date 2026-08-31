package com.learningtrack.learning_track.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApiError {

    private int status;

    private String message;

    private LocalDateTime timestamp;

}