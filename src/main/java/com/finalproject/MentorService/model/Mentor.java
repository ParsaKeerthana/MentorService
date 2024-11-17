package com.finalproject.MentorService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "mentors")
public class Mentor {
    @Id
    private String id;
    private String name;
    private String email;
    private String location;
    private List<String> skills;
    private String profilePicture;
    private String specialty;
    private String designation;
    private String company;
}

