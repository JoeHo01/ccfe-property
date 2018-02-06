package com.ccfe.property.http.mvc.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ccfe.property.common.response.Page;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * The Class MongoDaoSupport.
 */
@Repository
public class MongoSupport {

    /**
     * The datastore.
     */
    private final Datastore datastore;

    @Autowired
    public MongoSupport(Datastore datastore) {
        this.datastore = datastore;
    }

    /**
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> findAll(final Class<T> clazz) {
        return datastore.createQuery(clazz).asList();
    }

    public <T> T findById(final Class<T> clazz, String id) {
        Query<T> query = datastore.createQuery(clazz).filter(Mapper.ID_KEY + " in", formatId(id));
        return query.get();
    }

    /**
     * Count.
     *
     * @param <T>   the generic type
     * @param query the query
     * @return the long
     */
    public <T> long count(final Query<T> query) {
        return datastore.getCount(query);
    }

    public <T> Key<T> addDocument(T document) {
        return datastore.save(document);
    }

    /**
     * Update By Id
     *
     * @param ids
     * @param clazz
     * @param field
     * @param value
     * @param <T>
     * @return
     */
    public <T> UpdateResults update(Class<T> clazz, String field, Object value, String... ids) {
        Query<T> query = datastore.createQuery(clazz).filter(Mapper.ID_KEY + " in", formatId(ids));
        UpdateOperations<T> update = datastore.createUpdateOperations(clazz).set(field, value);
        return datastore.update(query, update);
    }

    /**
     * @param clazz
     * @param field
     * @param value
     * @param ids
     * @param <T>
     * @return
     */
    public <T> UpdateResults addToArray(Class<T> clazz, String field, List<Object> value, String... ids) {
        Query<T> query = datastore.createQuery(clazz).filter(Mapper.ID_KEY + " in", formatId(ids));
        UpdateOperations<T> update = datastore.createUpdateOperations(clazz).addToSet(field, value);
        return datastore.update(query, update);
    }

    /**
     * @param clazz
     * @param field
     * @param value
     * @param ids
     * @param <T>
     * @return
     */
    public <T> UpdateResults addToArray(Class<T> clazz, String field, Object value, String... ids) {
        Query<T> query = datastore.createQuery(clazz).filter(Mapper.ID_KEY + " in", formatId(ids));
        UpdateOperations<T> update = datastore.createUpdateOperations(clazz).addToSet(field, value);
        return datastore.update(query, update);
    }

    /**
     * @param clazz
     * @param field
     * @param value
     * @param ids
     * @param <T>
     * @return
     */
    public <T> UpdateResults removeFromArray(Class<T> clazz, String field, List<Object> value, String... ids) {
        Query<T> query = datastore.createQuery(clazz).filter(Mapper.ID_KEY + " in", formatId(ids));
        UpdateOperations<T> update = datastore.createUpdateOperations(clazz).removeAll(field, value);
        return datastore.update(query, update);
    }

    /**
     * @param clazz
     * @param field
     * @param value
     * @param ids
     * @param <T>
     * @return
     */
    public <T> UpdateResults removeFromArray(Class<T> clazz, String field, Object value, String... ids) {
        Query<T> query = datastore.createQuery(clazz).filter(Mapper.ID_KEY + " in", formatId(ids));
        UpdateOperations<T> update = datastore.createUpdateOperations(clazz).removeAll(field, value);
        return datastore.update(query, update);
    }

    /**
     * Update first.
     *
     * @param <T>   the generic type
     * @param query the query
     * @param ops   the ops
     * @return the updateProperties results
     */
    public <T> UpdateResults updateFirst(final Query<T> query, final UpdateOperations<T> ops) {
        return datastore.updateFirst(query, ops);
    }

    /**
     * Query : Single field with value included
     *
     * @param clazz
     * @param field
     * @param in
     * @param <T>
     * @return
     */
    public <T> List<T> query(Class<T> clazz, String field, List<Object> in) {
        Query<T> query = datastore.createQuery(clazz).filter(field + " in", in);
        return query.asList();
    }

    /**
     * Query : Multi fields with value included or excepted
     *
     * @param clazz
     * @param in
     * @param nin
     * @param <T>
     * @return
     */
    public <T> List<T> query(Class<T> clazz, Map<String, List<Object>> in, Map<String, List<Object>> nin) {
        Query<T> query = datastore.createQuery(clazz);
        for (String key : in.keySet()) {
            query.filter(key + " in", in.get(key));
        }
        for (String key : nin.keySet()) {
            query.filter(key + " nin", nin.get(key));
        }
        return query.asList();
    }

    public <T> List<T> query(Class<T> clazz, String field, String value){
        return datastore.createQuery(clazz).field(field).contains(value).asList();
    }

    /**
     * Query : Multi fields with value fuzzy matching
     *
     * @param clazz
     * @param conditions
     * @param <T>
     * @return
     */
    public <T> List<T> query(Class<T> clazz, Map<String, String> conditions){
        Query<T> query = datastore.createQuery(clazz);
        for (String key : conditions.keySet()){
            query.field(key).contains(conditions.get(key));
        }
        return query.asList();
    }

    private ObjectId[] formatId(String... ids) {
        ObjectId[] objectIds = new ObjectId[ids.length];
        for (int i = 0; i < ids.length; i++) {
            objectIds[i] = new ObjectId(ids[i]);
        }
        return objectIds;
    }
}