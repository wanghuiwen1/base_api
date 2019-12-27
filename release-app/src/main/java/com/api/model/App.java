package com.api.model;

import java.io.Serializable;
import javax.persistence.*;

public class App implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "index_url")
    private String indexUrl;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return index_url
     */
    public String getIndexUrl() {
        return indexUrl;
    }

    /**
     * @param indexUrl
     */
    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }
}