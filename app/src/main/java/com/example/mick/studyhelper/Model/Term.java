package com.example.mick.studyhelper.Model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by Mick on 25-Jun-16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Term implements Serializable{
    @JsonProperty("id")
    private int id;
    @JsonProperty("term")
    private String term;
    @JsonProperty("definition")
    private String definition;
    @JsonProperty("image")
    private Image image;

}
