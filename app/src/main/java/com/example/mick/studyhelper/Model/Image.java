package com.example.mick.studyhelper.Model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by Mick on 25-Jun-16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image implements Serializable{
    @JsonProperty("url")
    private String url;
    @JsonProperty("height")
    private int height;
    @JsonProperty("width")
    private int width;
}
