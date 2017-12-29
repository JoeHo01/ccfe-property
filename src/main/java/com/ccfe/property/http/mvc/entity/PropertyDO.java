package com.ccfe.property.http.mvc.entity;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Map;

@Embedded
public class PropertyDO {
    /**
     * The key.
     */
    @Property("key")
    private String key;

    /**
     * The value.
     */
    @Property("value")
    private String value;

    /**
     * The type.
     */
    @Property("type")
    private String type;

    /**
     * The tag.
     */
    @Property("tag")
    private String tay;

    /**
     * The description.
     */
    @Property("description")
    private String description;

    /**
     * The updated time.
     */
    @Property(value = "updated_time")
    private Long updatedTime;

    /**
     * The sub properties.
     */
    @Embedded("sub_properties")
    private Map<String, PropertyDO> subProperties;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTay() {
        return tay;
    }

    public void setTay(String tay) {
        this.tay = tay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Map<String, PropertyDO> getSubProperties() {
        return subProperties;
    }

    public void setSubProperties(Map<String, PropertyDO> subProperties) {
        this.subProperties = subProperties;
    }
}
