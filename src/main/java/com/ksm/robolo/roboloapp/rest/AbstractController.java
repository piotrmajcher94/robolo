package com.ksm.robolo.roboloapp.rest;

import com.ksm.robolo.roboloapp.services.AbstractService;
import com.ksm.robolo.roboloapp.services.util.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//TODO let's not use it at all, just the Abstrac
@RestController
public abstract class AbstractController<T> implements Controller<T> {
    protected AbstractService<T> service;
    //TODO we don't need json parser, we will use ResponseEntity<> - it translates objects to json automatically
    // Remove it from the class
    protected JsonParser jsonParser;


    @Autowired
    public void setJsonParser(JsonParser jsonParser){
        this.jsonParser = jsonParser;
    }

    @Override
    @PostMapping("add")
    public String addItem(@RequestBody T item) {
        return jsonParser.toJson(service.addItem(item));
    }

    @Override
    @PostMapping("add-many")
    public String addItems(@RequestBody Iterable<T> items) {
        return jsonParser.toJson(service.addItems(items));
    }



    @Override
    @PostMapping("get-by-id")
    public String getItemById(@RequestBody long id) {
        return jsonParser.toJson(service.getItemById(id));
    }

    @Override
    @PostMapping("get-many-by-id")
    public String getItemsByIds(@RequestBody Iterable<Long> ids) {
        return jsonParser.toJson(service.getItemsByIds(ids));
    }

    @Override
    @DeleteMapping("delete")
    public void removeItem(@RequestBody T item) {
        service.removeItem(item);
    }

    @Override
    @DeleteMapping("delete-many")
    public void removeItems(@RequestBody Iterable<T> items) {
        service.removeItems(items);
    }

}