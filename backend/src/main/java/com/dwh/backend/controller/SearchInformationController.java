package com.dwh.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.dwh.backend.model.FilmGenre;
import com.dwh.backend.model.FilmMerged;
import com.dwh.backend.repository.FilmMergedRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("Information")
public class SearchInformationController {
    //用来查询版本
    @Resource
    FilmMergedRepository filmMergedRepository;

    //查找所有电影类型
    @RequestMapping(value = "All",method = RequestMethod.GET)
    public Object getAll(){
        return filmMergedRepository.findAll();
    }

    //查找某一部电影
    @RequestMapping(value = "SearchFormat",method = RequestMethod.GET)
    public Object searchFormat(String name){
        JSONObject jsonObject = new JSONObject();
        long start=System.nanoTime();
        List<FilmMerged> result = filmMergedRepository.findByTitle(name);
        long end =System.nanoTime();
        jsonObject.put("data",result);
        jsonObject.put("time",end-start);
        return jsonObject;
    }



}
