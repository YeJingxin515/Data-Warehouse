package com.dwh.backend.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "film_format")
@Entity
public class FilmFormat {
    @EmbeddedId
    private FilmFormatId id;

    @Column(name = "film_title", length = 50)
    private String filmTitle;

    @Column(name = "format_name")
    private String formatName;

    @Column(name = "source")
    private String source;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public void setFilmTitle(String filmTitle) {
        this.filmTitle = filmTitle;
    }

    public FilmFormatId getId() {
        return id;
    }

    public void setId(FilmFormatId id) {
        this.id = id;
    }
}