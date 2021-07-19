package com.cranberries.elasticsearch.controller;

import com.cranberries.elasticsearch.entity.QueryNearbyPeopleDTO;
import com.cranberries.elasticsearch.entity.UserLocation;
import com.cranberries.elasticsearch.service.UserLocationSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/07/13 16:15
 * @description ：用户位置
 */

@RestController
@RequestMapping("/search/user/location")
public class UserLocationSearchController {

    @Autowired
    private UserLocationSearchService userLocationSearchService;

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(){

        return this.userLocationSearchService.save();

    }


    @PostMapping("/queryNearbyPeoples")
    public ResponseEntity<List> queryNearbyPeoples(@Valid @RequestBody QueryNearbyPeopleDTO queryNearbyPeopleDTO){

        return this.userLocationSearchService.queryNearbyPeoples(queryNearbyPeopleDTO);

    }
}
