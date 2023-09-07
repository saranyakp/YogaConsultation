package com.springboot.couchbase.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.YogaRepository;
import com.me.entity.Yoga;
import com.springboot.couchbase.exception.EntityNotFound;

//spring annotation
@Service
public class YogaService {

    @Autowired
    YogaRepository repository;

    //save new yoga in the couchbase
    public void save(final Yoga e) {
        repository.save(e);
    }

    //get total count in the couchbase
    public long count() {
        return repository.count();
    }

    //get all yoga from the couchbase
    public List<Yoga> getYogas() {
        final Iterable<Yoga> yogaIterable = repository.findAll();
        return StreamSupport.stream(yogaIterable.spliterator(), false).collect(Collectors.toList());
    }

    //get yoga by id from the couchbase
    public Yoga getYoga(final int id) throws EntityNotFound {
        return repository.findById(id).orElseThrow(EntityNotFound::new);
    }

  //insert yoga by id from the couchbase
    public Yoga insertYoga(final Yoga yoga) throws EntityNotFound {
        return repository.save(yoga);
    }
    
  //update yoga by id from the couchbase
    public Yoga updateYoga(final Yoga yoga) throws EntityNotFound {
        return repository.save(yoga);
    }
    
  //delete yoga by id from the couchbase
    public void deleteYoga(final int yogaId) throws EntityNotFound{
        repository.deleteById(yogaId);
    }
    //get yoga by email from the couchbase
    public List<Yoga> getYogaByEmail(final String email) {
        return repository.findAllByEmail(email);
    }
}
