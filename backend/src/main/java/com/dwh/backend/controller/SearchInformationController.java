package com.dwh.backend.controller;

import com.dwh.backend.model.FilmActorMain;
import com.dwh.backend.model.FilmMerged;
import com.dwh.backend.repository.FilmMergedRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.support.NullValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("information")
public class SearchInformationController {
    @Resource
    FilmMergedRepository filmMergedRepository;

    //搜索人物主演的电影
    @RequestMapping(value = "genre/{search}", method = RequestMethod.GET)
    @ResponseBody
    public List<FilmMerged> searchGenre(@PathVariable List<String> search) {
        List<FilmMerged> result = new ArrayList<>();
        List<FilmMerged> allFilm = filmMergedRepository.findAll();
        for (FilmMerged fm : allFilm) {
            Boolean flag = true;
            for (String genre : search) {
                if (fm.getGenre().contains(genre) == false) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                result.add(fm);
            }
        }
        return result;
    }


    @RequestMapping(value="all",method = RequestMethod.GET)
    @ResponseBody
    public List<FilmMerged> getAll(){
        return filmMergedRepository.findAll();
    }
}
