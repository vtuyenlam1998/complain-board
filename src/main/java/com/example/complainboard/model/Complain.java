package com.example.complainboard.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Complain {
    private Long id;
    private String title;
    private String comment;
    private Boolean active;
    private LocalDateTime timeCreation;
}
