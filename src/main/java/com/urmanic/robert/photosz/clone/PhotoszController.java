package com.urmanic.robert.photosz.clone;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@RestController
public class PhotoszController {

    private final PhotoszService photoszService;

    public PhotoszController(PhotoszService photoszService) {
        this.photoszService = photoszService;
    }

    @GetMapping("/")
    public String hello() {
        return "<h1>Hello world</h1>";
    }

    @GetMapping("/photoz")
    public Collection<Photo> get() {
        return photoszService.get();
    }

    @GetMapping("/photoz/{id}")
    public Photo getSpecificPhoto(@PathVariable String id) throws FileNotFoundException{
        Photo photo = photoszService.get(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photoz/remove/{id}")
    public void deleteSpecificPhoto(@PathVariable String id){
        Photo photo = photoszService.remove(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    //@PostMapping("/photoz/")
    //public Photo create(@RequestBody @Valid Photo photo){
    //    photo.setId(UUID.randomUUID().toString());
    //    db.put(photo.getId(), photo);
    //    return photo;

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photoszService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }





























}
