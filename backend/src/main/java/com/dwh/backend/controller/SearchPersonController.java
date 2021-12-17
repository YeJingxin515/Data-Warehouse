package com.dwh.backend.controller;

import com.dwh.backend.model.*;
import com.dwh.backend.repository.FilmActorMainRepository;
import com.dwh.backend.repository.FilmActorRepository;
import com.dwh.backend.repository.FilmDirectorRepository;
import com.dwh.backend.repository.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("person")
public class SearchPersonController {
    @Resource
    FilmActorRepository filmActorRepository;
    @Resource
    FilmActorMainRepository filmActorMainRepository;
    @Resource
    FilmDirectorRepository filmDirectorRepository;
    @Resource
    PersonRepository personRepository;

    //搜索人物主演的电影
    @RequestMapping(value="mainactor/{actorName}",method = RequestMethod.GET)
    @ResponseBody
    public List<FilmActorMain> getByActorMain(@PathVariable String actorName){
        return filmActorMainRepository.findByActorName(actorName);
    }

    //搜索人物参演的电影
    @RequestMapping(value="actor/{actorName}",method = RequestMethod.GET)
    @ResponseBody
    public List<FilmActor> getByActor(@PathVariable String actorName){
        return filmActorRepository.findByActorName(actorName);
    }

    //搜索人物导演的电影
    @RequestMapping(value="director/{directorName}",method = RequestMethod.GET)
    @ResponseBody
    public List<FilmDirector> getByDirector(@PathVariable String directorName){
        return filmDirectorRepository.findByDirectorName(directorName);
    }

    //所有人物
    @RequestMapping(value="all",method = RequestMethod.GET)
    @ResponseBody
    public List<Person> getAll(){
        return personRepository.findAll();
    }

}
