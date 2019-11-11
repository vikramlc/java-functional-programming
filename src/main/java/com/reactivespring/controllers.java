package com.reactivespring;

import com.reactivespring.exceptions.DataNotFoundException;
import com.reactivespring.model.ResponseStructure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controllers {

    @GetMapping("/test")
    public ResponseEntity<ResponseStructure> getTest() {
        throw new DataNotFoundException("No data found.");
    }

}
