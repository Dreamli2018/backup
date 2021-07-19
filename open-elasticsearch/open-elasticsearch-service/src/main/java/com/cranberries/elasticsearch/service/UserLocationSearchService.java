package com.cranberries.elasticsearch.service;

import com.cranberries.elasticsearch.entity.QueryNearbyPeopleDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/07/13 16:18
 * @description ：用户位置
 */
public interface UserLocationSearchService {

    ResponseEntity<Boolean> save();

    ResponseEntity<List> queryNearbyPeoples(QueryNearbyPeopleDTO userLocation);
}
