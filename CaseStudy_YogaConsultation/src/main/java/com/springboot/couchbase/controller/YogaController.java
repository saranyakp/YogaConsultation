package com.springboot.couchbase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.couchbase.entity.Yoga;
import com.springboot.couchbase.exception.EntityNotFound;
import com.springboot.couchbase.service.YogaService;

import lombok.extern.slf4j.Slf4j;

//lombok annotation
@Slf4j
//spring annotations
@RestController
@RequestMapping("/api")
public class YogaController {

    @Autowired
    YogaService service;

    //URL - http://localhost:9300/api/Yogas
    @GetMapping("/Yogas")
    @ResponseStatus(HttpStatus.OK)
    public List<Yoga> getYogas() {
        return service.getYogas();
    }

    //URL - http://localhost:9300/api/Yoga/<eid>
    //Example - http://localhost:9300/api/Yoga/73a02968-70a8-426a-b6d8-deaa96a597df
    @GetMapping("/Yoga/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Yoga getYogaById(@PathVariable String id)
            throws EntityNotFound {
        return service.getYoga(id);
    }

    //URL - http://localhost:9300/api/Yoga/<email>
    //Example - http://localhost:9300/api/Yogas/Health
    @GetMapping("/Yogas/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<Yoga> getYogaByEmail(@PathVariable(name = "email") String email) {
        return service.getYogasByEmail(email);
    }

    //URL - http://localhost:9300/api/Yogas/count
    @GetMapping("/Yogas/count")
    @ResponseStatus(HttpStatus.OK)
    public long getTotalYogas() {
        return service.count();
    }
    
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public Yoga registerYoga(@RequestBody Yoga Yoga) throws EntityNotFound {
        return service.insertYoga(Yoga);
    }
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public Yoga updateYoga(@RequestBody Yoga Yoga) throws EntityNotFound {
        return service.updateYoga(Yoga);
    }
    
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteYoga(@PathVariable("id") String id) throws EntityNotFound {
        service.deleteYoga(id);
        
        return "Yoga deleted successfully";
    }
}
