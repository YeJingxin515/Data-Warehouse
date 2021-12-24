package com.dwh.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.dwh.backend.model.FilmGenre;
import com.dwh.backend.repository.FilmGenreRepository;
import com.dwh.backend.repository.GenreRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("Genre")
public class SearchGenreController {
    @Resource
    FilmGenreRepository filmGenreRepository;
    @Resource
    GenreRepository genreRepository;

    //查找所有电影类型
    @RequestMapping(value = "All",method = RequestMethod.GET)
    public Object getAllGenre(){
        return genreRepository.findAll();
    }

    //按照电影类型查找电影
    @RequestMapping(method = RequestMethod.GET)
    public Object searchGenre(String name) {
        JSONObject jsonObject = new JSONObject();
        long start=System.nanoTime();
        List<FilmGenre> result = filmGenreRepository.findByGenreName(name);
        long end =System.nanoTime();
        jsonObject.put("data",result);
        jsonObject.put("time",end-start);
        return jsonObject;
    }

}
