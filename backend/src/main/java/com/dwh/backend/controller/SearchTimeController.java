package com.dwh.backend.controller;

import com.dwh.backend.model.FilmTime;
import com.dwh.backend.repository.FilmTimeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("time")
public class SearchTimeController {
    @Resource
    FilmTimeRepository filmTimeRepository;

    @RequestMapping(value= "year/{year}",method = RequestMethod.GET)
    @ResponseBody
    public List<FilmTime> getByYear(@PathVariable String year){
        return filmTimeRepository.findByYear(Integer.parseInt(year.trim()));
    }

    @RequestMapping(value= "yearandmonth/{year}/{month}",method = RequestMethod.GET)
    @ResponseBody
    public List<FilmTime> getByYearAndMonth(@PathVariable String year,@PathVariable String month){
        return filmTimeRepository.findByYearAndMonth(Integer.parseInt(year.trim()),month);
    }

    @RequestMapping(value="yearandquarter/{year}/{quarter}",method = RequestMethod.GET)
    @ResponseBody
    public List<FilmTime> getByYearAndQuarter(@PathVariable String year,@PathVariable String quarter){
        return filmTimeRepository.findByYearAndQuarter(Integer.parseInt(year.trim()),Integer.parseInt(quarter.trim()));
    }

    @RequestMapping(value="all",method = RequestMethod.GET)
    @ResponseBody
    public List<FilmTime> getAll(){
        return filmTimeRepository.findAll();
    }
}
