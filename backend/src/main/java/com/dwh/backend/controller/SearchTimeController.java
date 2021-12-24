package com.dwh.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.dwh.backend.model.FilmTime;
import com.dwh.backend.repository.FilmTimeRepository;
import lombok.Data;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("Time")
public class SearchTimeController{
    @Resource
    FilmTimeRepository filmTimeRepository;

    //根据年份查电影
    @RequestMapping(value= "Year",method = RequestMethod.GET)
    public Object getByYear(TimeObject time){
        JSONObject jsonObject = new JSONObject();
        long start=System.nanoTime();
        List<FilmTime> targetFilm = filmTimeRepository.findByYear(Integer.parseInt(time.getYear()));
        long end=System.nanoTime();
        jsonObject.put("data",targetFilm);
        jsonObject.put("time",end-start);
        return jsonObject;
    }

    //根据年份和月份查电影
    @RequestMapping(value= "YearAndMonth",method = RequestMethod.GET)
    public Object getByYearAndMonth(@RequestBody TimeObject time){
        JSONObject jsonObject = new JSONObject();
        long start=System.nanoTime();
        List<FilmTime> targetFilm = filmTimeRepository.findByYearAndMonth(Integer.parseInt(time.getYear()), Integer.parseInt(time.getMonth()));
        long end=System.nanoTime();
        if (targetFilm.isEmpty()) {
            jsonObject.put("message", "您查找的电影不存在不存在，查找信息失败!");
            jsonObject.put("status", -1);
        } else {
            jsonObject.put("data",targetFilm);
            jsonObject.put("time",end-start);
        }
        return jsonObject;
    }

    //根据年份和月份查电影
    @RequestMapping(value= "YearAndQuarter",method = RequestMethod.GET)
    public Object getByYearAndQuarter(@RequestBody TimeObject time){
        JSONObject jsonObject = new JSONObject();
        long start=System.nanoTime();
        List<FilmTime> targetFilm = filmTimeRepository.findByYearAndQuarter(Integer.parseInt(time.getYear()),Integer.parseInt(time.getQuarter()));
        long end=System.nanoTime();
        if (targetFilm.isEmpty()) {
            jsonObject.put("message", "您查找的电影不存在不存在，查找信息失败!");
            jsonObject.put("status", -1);
        } else {
            jsonObject.put("data",targetFilm);
            jsonObject.put("time",end-start);
        }
        return jsonObject;
    }

    //表格所有数据
    @RequestMapping(value="All",method = RequestMethod.GET)
    public Object getAll(){
        JSONObject jsonObject = new JSONObject();
        long start=System.nanoTime();
        List<FilmTime> targetFilm = filmTimeRepository.findAll();
        long end=System.nanoTime();
        jsonObject.put("data",targetFilm);
        jsonObject.put("time",end-start);
        return jsonObject;
    }


    //时间搜索
    @Data
    public static class TimeObject{
        private String year;
        private String month;
        private String quarter;
    }

}
