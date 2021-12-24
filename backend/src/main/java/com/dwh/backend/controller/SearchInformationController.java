package com.dwh.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.dwh.backend.model.FilmGenre;
import com.dwh.backend.model.FilmMerged;
import com.dwh.backend.repository.FilmMergedRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    //查找电影类型+好评组合查询
    @RequestMapping(value = "SearchCombination",method = RequestMethod.GET)
    public Object searchCombination(@RequestBody Film film) {
        JSONObject jsonObject = new JSONObject();
        List<String>genres=film.getGenres();
        Integer rate= Integer.parseInt(film.getRate());
        if(genres==null&&rate!=-1){//只查询好评
            long start=System.nanoTime();
            List<FilmMerged> result = filmMergedRepository.findByRate(rate);
            long end =System.nanoTime();
            jsonObject.put("data",result);
            jsonObject.put("time",end-start);
        }else if(rate==-1&&genres!=null) {//查询组合类型的电影类型
            List<FilmMerged> result = new ArrayList<>();
            List<FilmMerged> allFilm = filmMergedRepository.findAll();
            long start=System.nanoTime();
            for (FilmMerged fm : allFilm) {
                Boolean flag = true;
                for (String genre :genres) {
                    if (fm.getGenre().contains(genre) == false) {
                        flag = false;
                        break;
                    }
                }
                if (flag == true) {
                    result.add(fm);
                }
            }
            long end=System.nanoTime();
            jsonObject.put("data",result);
            jsonObject.put("time",end-start);
        }else if(rate!=-1&&genres!=null){
            List<FilmMerged> result = new ArrayList<>();
            long start=System.nanoTime();
            List<FilmMerged> matchRate = filmMergedRepository.findByRate(rate);
            for (FilmMerged fm : matchRate) {
                Boolean flag = true;
                for (String genre :genres) {
                    if (fm.getGenre().contains(genre) == false) {
                        flag = false;
                        break;
                    }
                }
                if (flag == true) {
                    result.add(fm);
                }
            }
            long end =System.nanoTime();
            jsonObject.put("data",result);
            jsonObject.put("time",end-start);
        }else{
            jsonObject.put("message","请输入查询内容！");
        }
        return jsonObject;
    }


    @Data
    private static class Film{
        private List<String> genres;
        private String rate;
    }

}
