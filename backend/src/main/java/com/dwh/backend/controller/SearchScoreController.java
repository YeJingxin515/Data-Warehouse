package com.dwh.backend.controller;

import com.alibaba.fastjson.JSONObject;
import com.dwh.backend.model.FilmMerged;
import com.dwh.backend.model.FilmScore;
import com.dwh.backend.repository.FilmScoreRepository;
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
    FilmScoreRepository filmScoreRepository;

    //获取某一区间的分数段的电影
    @RequestMapping(method = RequestMethod.POST)
    public Object searchFormat(@RequestBody Scale scale){
        JSONObject jsonObject = new JSONObject();
        List<FilmScore> result = new ArrayList<>();
        List<FilmScore> all = filmScoreRepository.findAll();
        long start=System.nanoTime();
        Double high= Double.valueOf(scale.getHigh());
        Double low= Double.valueOf(scale.getLow());
        for(FilmScore fm:all){
            if(fm.getAvgOverall()>=low&&fm.getAvgOverall()<=high)
                result.add(fm);
        }
        long end =System.nanoTime();
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
