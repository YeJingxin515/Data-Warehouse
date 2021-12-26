package com.dwh.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.dwh.backend.model.FilmRate;
import com.dwh.backend.repository.FilmRateRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("Score")
public class SearchScoreController {
    @Resource
    FilmRateRepository filmRateRepository;

    //获取某一区间的分数段的电影
    @RequestMapping(value= "Scale",method = RequestMethod.POST)
    public Object searchScale(@RequestBody Scale scale){
        JSONObject jsonObject = new JSONObject();
        List<FilmRate> result = new ArrayList<>();
        List<FilmRate> all = filmRateRepository.findAll();
        long start=System.currentTimeMillis();
        Double high= Double.valueOf(scale.getHigh());
        Double low= Double.valueOf(scale.getLow());
        for(FilmRate fm:all){
            if(fm.getOverall()>=low&&fm.getOverall()<=high)
                result.add(fm);
        }
        long end =System.currentTimeMillis();
        jsonObject.put("data",result);
        jsonObject.put("time",end-start);
        return jsonObject;
    }

    //获取某一评分的电影
    @RequestMapping(value= "Overall",method = RequestMethod.GET)
    public Object searchOverall(String overall){
        JSONObject jsonObject = new JSONObject();
        List<FilmRate> result = new ArrayList<>();
        long start=System.currentTimeMillis();
        result =filmRateRepository.findByOverall(Double.valueOf(overall));
        long end =System.currentTimeMillis();
        jsonObject.put("data",result);
        jsonObject.put("time",end-start);
        return jsonObject;
    }

    //获取正面评价的电影
    @RequestMapping(value= "Rate",method = RequestMethod.GET)
    public Object searchRate(String rate){
        JSONObject jsonObject = new JSONObject();
        List<FilmRate> result = new ArrayList<>();
        long start=System.currentTimeMillis();
        result=filmRateRepository.findByRate(Integer.parseInt(rate));
        long end =System.currentTimeMillis();
        jsonObject.put("data",result);
        jsonObject.put("time",end-start);
        return jsonObject;
    }

    @Data
    private static class Scale{
        private String low;
        private String high;
    }
}
