package com.example.demo2.repository;

import com.example.demo2.model.NodeItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface  NodeRepository extends MongoRepository <NodeItem , String > {

    @Query ("{hostname:'?0'}")
    NodeItem findItemByName (String hostname);

    @Query(value="{dataCentre:'?0'}", fields="{'hostname' : 1, 'dataCentre' : 1}")
    List<NodeItem> findAll(String dataCentre);


    //public long count();

}
