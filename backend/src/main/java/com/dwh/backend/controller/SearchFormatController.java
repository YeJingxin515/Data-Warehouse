package com.dwh.backend.controller;

import com.dwh.backend.model.FilmFormat;
import com.dwh.backend.model.Format;
import com.dwh.backend.repository.FilmFormatRepository;
import com.dwh.backend.repository.FormatRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("format")
public class SearchFormatController {
    @Resource
    FilmFormatRepository filmFormatRepository;
    @Resource
    FormatRepository formatRepository;

    @RequestMapping(value="searchFormat/{formatName}",method = RequestMethod.GET)
    @ResponseBody
    public List<FilmFormat> getByFormatName(@PathVariable String formatName){
        return filmFormatRepository.findByFormatName(formatName);
    }

    @RequestMapping(value="searchFilm/{filmTitle}",method = RequestMethod.GET)
    @ResponseBody
    public List<FilmFormat> getByFilmName(@PathVariable String filmTitle){
        return filmFormatRepository.findByFilmTitle(filmTitle);
    }

    @RequestMapping(value="alldata",method = RequestMethod.GET)
    @ResponseBody
    public List<FilmFormat> getAll(){
        return filmFormatRepository.findAll();
    }

    @RequestMapping(value="allformat",method = RequestMethod.GET)
    @ResponseBody
    public List<Format> getAllFormat(){
        return formatRepository.findAll();
    }
}
