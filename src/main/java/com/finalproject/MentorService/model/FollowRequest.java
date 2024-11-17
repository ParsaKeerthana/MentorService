package com.finalproject.MentorService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "followRequests")
public class FollowRequest {
    @Id
    private String id;
    private String menteeId;
    private String mentorId;
    private String status;
    private String message;

    public void setStatus(String status) {
        this.status = status;
    }
}
