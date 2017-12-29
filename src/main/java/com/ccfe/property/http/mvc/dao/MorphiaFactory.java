package com.ccfe.property.http.mvc.dao;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MorphiaFactory {

    private final Mongo mongo;

    private final MongoProperties mongoProperties;

    private final String entitiesPackage = "com.ccfe.property.http.mvc.entity";

    @Autowired
    public MorphiaFactory(Mongo mongo, MongoProperties mongoProperties) {
        this.mongo = mongo;
        this.mongoProperties = mongoProperties;
    }

    /**
     * Initial MongoDB configuration.
     *
     */
    @Bean
    public Datastore init() {
        Morphia morphia = new Morphia();
        morphia.getMapper().getOptions().setMapSubPackages(true);
        morphia.mapPackage(entitiesPackage);
        return morphia.createDatastore((MongoClient)mongo,mongoProperties.getDatabase());
    }
}
