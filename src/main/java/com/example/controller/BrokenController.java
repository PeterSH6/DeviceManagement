package com.example.controller;

import com.example.dao.BrokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrokenController {

    @Autowired
    private BrokenRepository brokenRepository;
}
