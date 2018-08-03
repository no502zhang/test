package com.wiseyq.face.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faces")
public class FaceController {

    @Value("${huawei.ak}")
    private String ak;
    @Value("${huawei.sk}")
    private String sk;
    
    
}
