package com.example.demo2.http;


import com.example.demo2.model.NodeItem;
import com.example.demo2.repository.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class HttpRestController {

    @Autowired
    NodeRepository nodeRepo;

    @RequestMapping(method = RequestMethod.GET, path = "/api/hello-world")
    @ResponseBody
    public String sayHello(){
        return "Hello World!";
    }

    // ------------ Retrieve Host Details  ------------

    @RequestMapping(method = RequestMethod.GET, path = "/api/getnodedetails/{hostname}")
    @ResponseBody
    public String getHostdetails(@PathVariable  String hostname){
        return (nodeRepo.findItemByName(hostname).toString());
    }

}
