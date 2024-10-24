package com.demo.Controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Entity.DTO.CreateGroupRequestDTO;
import com.demo.Service.GroupService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/groups")
public class GroupController {
    
    @Autowired
    GroupService groupService;

    @PostMapping("/add")
    public ResponseEntity<?> createGroup(@Valid @RequestBody CreateGroupRequestDTO groupDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors()
                                    .stream()
                                    .map(error -> error.getDefaultMessage())
                                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(groupService.createGroup(groupDTO), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
