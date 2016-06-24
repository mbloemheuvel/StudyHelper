package com.example.mick.studyhelper;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by Mick on 22-Jun-16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Set implements Serializable{
    @JsonProperty("id")
    private int id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("title")
    private String title;
    @JsonProperty("created_by")
    private String createdBy;
    @JsonProperty("term_count")
    private int termCount;
    @JsonProperty("has_access")
    private String hasAccess;
    @JsonProperty("description")
    private String description;
    @JsonProperty("lang_terms")
    private String langTerms;
    @JsonProperty("lang_definitions")
    private String langDefinitions;

    public Set(){

    }

    public Set(int id, String url, String title, String createdBy, int termCount, String hasAccess, String description, String langTerms, String langDefinitions) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.createdBy = createdBy;
        this.termCount = termCount;
        this.hasAccess = hasAccess;
        this.description = description;
        this.langTerms = langTerms;
        this.langDefinitions = langDefinitions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public int getTermCount() {
        return termCount;
    }

    public void setTermCount(int termCount) {
        this.termCount = termCount;
    }

    public String getHasAccess() {
        return hasAccess;
    }

    public void setHasAccess(String hasAccess) {
        this.hasAccess = hasAccess;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLangTerms() {
        return langTerms;
    }

    public void setLangTerms(String langTerms) {
        this.langTerms = langTerms;
    }

    public String getLangDefinitions() {
        return langDefinitions;
    }

    public void setLangDefinitions(String langDefinitions) {
        this.langDefinitions = langDefinitions;
    }
}
