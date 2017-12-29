package com.ccfe.property.http.mvc.entity;

import java.util.Map;

public class PropertyDO {
    /**
     * The key.
     */
    private String key;

    /**
     * The value.
     */
    private String value;

    /**
     * The type.
     */
    private String type;

    /**
     * The tag.
     */
    private String tay;

    /**
     * The description.
     */
    private String description;

    /**
     * The updated time.
     */
    private Long updatedTime;

    /**
     * The sub properties.
     */
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
