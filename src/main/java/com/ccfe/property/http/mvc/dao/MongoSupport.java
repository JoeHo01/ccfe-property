package com.ccfe.property.http.mvc.dao;

import java.util.Collection;
import java.util.List;

import com.mongodb.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

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
     * Gets the datastore.
     *
     * @return the datastore
     */
    public Datastore getDatastore() {
        return datastore;
    }

    /**
     * Count.
     *
     * @param <T>   the generic type
     * @param clazz the clazz
     * @return the long
     */
    public <T> long count(final Class<T> clazz) {
        return datastore.getCount(clazz);
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

    /**
     * Creates the query.
     *
     * @param <T>   the generic type
     * @param clazz the clazz
     * @return the query
     */
    public <T> Query<T> createQuery(final Class<T> clazz) {
        return datastore.createQuery(clazz);
    }

    /**
     * Creates the update operations.
     *
     * @param <T>   the generic type
     * @param clazz the clazz
     * @return the update operations
     */
    public <T> UpdateOperations<T> createUpdateOperations(final Class<T> clazz) {
        return datastore.createUpdateOperations(clazz);
    }

    /**
     * Delete.
     *
     * @param <T>    the generic type
     * @param entity the entity
     * @return the write result
     */
    public <T> WriteResult delete(final T entity) {
        return datastore.delete(entity);
    }

    /**
     * Delete.
     *
     * @param <T>    the generic type
     * @param entity the entity
     * @param wc     the wc
     * @return the write result
     */
    public <T> WriteResult delete(final T entity, final WriteConcern wc) {
        return datastore.delete(entity, wc);
    }

    /**
     * Delete by id.
     *
     * @param <T>   the generic type
     * @param clazz the clazz
     * @param id    the id
     * @return the write result
     */
    public <T> WriteResult deleteById(final Class<T> clazz, final String id) {
        return datastore.delete(clazz, id);
    }

    /**
     * Delete by query.
     *
     * @param <T>   the generic type
     * @param query the query
     * @return the write result
     */
    public <T> WriteResult deleteByQuery(final Query<T> query) {
        return datastore.delete(query);
    }

    /**
     * Delete.
     *
     * @param <T>   the generic type
     * @param clazz the clazz
     * @param ids   the ids
     * @return the write result
     */
    public <T> WriteResult delete(final Class<T> clazz, final Collection<ObjectId> ids) {
        return datastore.delete(clazz, ids);
    }

    /**
     * Ensure indexes.
     *
     * @param <T>   the generic type
     * @param clazz the clazz
     */
    public <T> void ensureIndexes(final Class<T> clazz) {
        datastore.ensureIndexes(clazz);
    }

    /**
     * Exists.
     *
     * @param <T>   the generic type
     * @param clazz the clazz
     * @param key   the key
     * @param value the value
     * @return true, if successful
     */
    public <T> boolean exists(final Class<T> clazz, final String key, final Object value) {
        return exists(datastore.find(clazz, key, value));
    }

    /**
     * Exists.
     *
     * @param <T>   the generic type
     * @param query the query
     * @return true, if successful
     */
    public <T> boolean exists(final Query<T> query) {
        return datastore.getCount(query) > 0;
    }

    /**
     * Find all.
     *
     * @param <T>   the generic type
     * @param clazz the clazz
     * @return the list
     */
    public <T> List<T> findAll(final Class<T> clazz) {
        return createQuery(clazz).asList();
    }

    /**
     * Find.
     *
     * @param <T>      the generic type
     * @param clazz    the clazz
     * @param property the property
     * @param value    the value
     * @return the query
     */
    public <T> Query<T> find(final Class<T> clazz, String property, Object value) {
        return datastore.find(clazz, property, value);
    }

    /**
     * Find all.
     *
     * @param <T>   the generic type
     * @param query the query
     * @return the list
     */
    public <T> List<T> findAll(final Query<T> query) {
        return query.asList();
    }

    /**
     * Find one.
     *
     * @param <T>   the generic type
     * @param clazz the clazz
     * @param key   the key
     * @param value the value
     * @return the t
     */
    public <T> T findOne(final Class<T> clazz, final String key, final Object value) {
        return find(clazz, key, value).get();
    }

    /**
     * Find one.
     *
     * @param <T>   the generic type
     * @param query the query
     * @return the t
     */
    public <T> T findOne(final Query<T> query) {
        return query.get();
    }

    /**
     * Gets the.
     *
     * @param <T>   the generic type
     * @param clazz the clazz
     * @param id    the id
     * @return the t
     */
    public <T> T get(final Class<T> clazz, final String id) {
        return datastore.get(clazz, new ObjectId(id));
    }

    /**
     * Gets the collection.
     *
     * @param <T>   the generic type
     * @param clazz the clazz
     * @return the collection
     */
    public <T> DBCollection getCollection(final Class<T> clazz) {
        return datastore.getCollection(clazz);
    }

    /**
     * Save or update.
     *
     * @param <T>    the generic type
     * @param entity the entity
     * @return the key
     */
    public <T> Key<T> saveOrUpdate(final T entity) {
        return datastore.save(entity);
    }

    /**
     * Save or update.
     *
     * @param <T>    the generic type
     * @param entity the entity
     * @param wc     the wc
     * @return the key
     */
    public <T> Key<T> saveOrUpdate(final T entity, final WriteConcern wc) {
        return datastore.save(entity, wc);
    }

    /**
     * Update.
     *
     * @param <T>   the generic type
     * @param query the query
     * @param ops   the ops
     * @return the update results
     */
    public <T> UpdateResults update(final Query<T> query, final UpdateOperations<T> ops, boolean ifCreate) {
        return datastore.update(query, ops, ifCreate);
    }

    /**
     * Update first.
     *
     * @param <T>   the generic type
     * @param query the query
     * @param ops   the ops
     * @return the update results
     */
    public <T> UpdateResults updateFirst(final Query<T> query, final UpdateOperations<T> ops) {
        return datastore.updateFirst(query, ops);
    }

}