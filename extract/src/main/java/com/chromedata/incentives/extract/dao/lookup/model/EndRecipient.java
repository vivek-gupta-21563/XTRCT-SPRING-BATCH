package com.chromedata.incentives.extract.dao.lookup.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Display class modeling a end recipient response object
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EndRecipient {

    @XmlAttribute
    private int id;
    @XmlElement(name = "Description")
    private String description;

    // Private no-arg constructor required by jax-b
    private EndRecipient() {
    }

    public EndRecipient(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
