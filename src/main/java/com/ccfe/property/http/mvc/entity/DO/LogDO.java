package com.ccfe.property.http.mvc.entity.DO;

import net.sf.json.JSONObject;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.Map;

@Entity(value = "log", noClassnameStored = true)
public class LogDO {

    @Id
    private String id;

    @Property("user")
    private String user;

    @Property("update_time")
    private long updateTime;

    @Property("operation")
    private String operation;

    @Property("field")
    private String field;

    @Property("value")
    private String value;

    @Property("documents")
    private List<Map<String, String>> documents;

    @Property("original")
    private List<Map<String, Object>> original;

    public LogDO() {
    }

    public LogDO(String user, long updateTime, String operation, String field, String value, List<Map<String, String>> documents, List<Map<String, Object>> original) {
        this.user = user;
        this.updateTime = updateTime;
        this.operation = operation;
        this.field = field;
        this.value = value;
        this.documents = documents;
        this.original = original;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Map<String, String>> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Map<String, String>> documents) {
        this.documents = documents;
    }

    public List<Map<String, Object>> getOriginal() {
        return original;
    }

    public void setOriginal(List<Map<String, Object>> original) {
        this.original = original;
    }
}
