package com.nea.myjournal;

import java.util.HashMap;
import java.util.Map;

public class Source {

    private String id;
    private String name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Source() {
    }

    public Source(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}