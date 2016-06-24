package com.example.mick.studyhelper.Model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by Mick on 25-Jun-16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card implements Serializable{
    @JsonProperty("id")
    private Long id;
    @JsonProperty("term")
    private String term;
    @JsonProperty("definition")
    private String definition;
    @JsonProperty("image")
    private Image image;

    public String getTerm(){
        return term;
    }

}
