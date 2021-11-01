package com.liserabackend.controllers;

import com.liserabackend.services.SchoolServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders ="*")
@RequestMapping("/api/school")
public class SchoolController {
    private final SchoolServiceImpl schoolService;
}
