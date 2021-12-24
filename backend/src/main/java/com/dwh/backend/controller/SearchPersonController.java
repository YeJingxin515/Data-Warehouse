package com.dwh.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.dwh.backend.model.*;
import com.dwh.backend.repository.FilmActorRepository;
import com.dwh.backend.repository.FilmDirectorRepository;
import com.dwh.backend.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("Person")
public class SearchPersonController {
    @Resource
    FilmDirectorRepository filmDirectorRepository;
    @Resource
    FilmActorRepository filmActorRepository;
    @Resource
    PersonRepository personRepository;

    //查找所有人名
    @RequestMapping(value= "All",method = RequestMethod.GET)
    public Object getAll(){
        return personRepository.findAll();
    }

    //查找所有所有导演
    @RequestMapping(value= "AllDirector",method = RequestMethod.GET)
    public Object getAllDirector(){
        JSONObject jsonObject = new JSONObject();
        long start=System.nanoTime();
        List<FilmDirector> targetFilm = filmDirectorRepository.findAll();
        long end=System.nanoTime();
        jsonObject.put("data",targetFilm);
        jsonObject.put("time",end-start);
        return jsonObject;
    }

    //查找所有演员
    @RequestMapping(value= "AllActor",method = RequestMethod.GET)
    public Object getAllActor(){
        JSONObject jsonObject = new JSONObject();
        long start=System.nanoTime();
        List<FilmActor> targetFilm =  filmActorRepository.findAll();
        long end=System.nanoTime();
        jsonObject.put("data",targetFilm);
        jsonObject.put("time",end-start);
        return jsonObject;
    }

    //按照导演姓名查找电影
    @RequestMapping(value= "Director",method = RequestMethod.GET)
    public Object getByDirector(String name){
        JSONObject jsonObject = new JSONObject();
        long start=System.nanoTime();
        List<FilmDirector> targetFilm = filmDirectorRepository.findByDirectorName(name);
        long end=System.nanoTime();
        jsonObject.put("data",targetFilm);
        jsonObject.put("time",end-start);
        return jsonObject;
    }

    //按照演员姓名查找电影
    @RequestMapping(value= "Actor",method = RequestMethod.GET)
    public Object getByActor(String name){
        JSONObject jsonObject = new JSONObject();
        long start=System.nanoTime();
        List<FilmActor> targetFilm = filmActorRepository.findByActorName(name);
        long end=System.nanoTime();
        jsonObject.put("data",targetFilm);
        jsonObject.put("time",end-start);
        return jsonObject;
    }


}
