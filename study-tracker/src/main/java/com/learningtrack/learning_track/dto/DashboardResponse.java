package com.learningtrack.learning_track.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private long totalTopics;

    private long completedTopics;

    private double progressPercentage;

}