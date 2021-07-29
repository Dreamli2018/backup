package com.cranberries.elasticsearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.cranberries.elasticsearch.entity.QueryNearbyPeopleDTO;
import com.cranberries.elasticsearch.entity.UserLocation;
import com.cranberries.elasticsearch.mapper.UserLocationSearchMapper;
import com.cranberries.elasticsearch.repository.UserLocationSearchRepository;
import com.cranberries.elasticsearch.service.UserLocationSearchService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/07/13 16:19
 * @description ：用户位置
 */

@Service
@Slf4j
public class UserLocationSearchServiceImpl implements UserLocationSearchService {

    @Resource
    private UserLocationSearchMapper userLocationSearchMapper;

    @Resource
    private UserLocationSearchRepository userLocationSearchRepository;

    @Override
    public ResponseEntity<Boolean> save() {
        Boolean flag = true;
        try {
            long start = System.currentTimeMillis();
            // 查询所有用户的位置信息
            List<UserLocation> userLocations = this.userLocationSearchMapper.queryAll();
            userLocations.forEach(item -> {
                GeoPoint geoPoint = new GeoPoint(item.getLat(), item.getLon());
                item.setLocation(geoPoint);
            });
//            log.info("保存司机位置信息到es中所有数据:{}", JSON.toJSONString(userLocations));
//            long start = System.currentTimeMillis();
            // 将用户的位置信息保存到es中
            Iterable<UserLocation> result = this.userLocationSearchRepository.saveAll(userLocations);
            long end = System.currentTimeMillis();
            log.info("保存车辆位置信息到es中耗时:{}", (end - start) + "毫秒");
        } catch (Exception e) {
            flag = false;
            log.error("保存用户位置到es异常,异常信息:{}", e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok(flag);
    }

    @Override
    public ResponseEntity<List> queryNearbyPeoples(QueryNearbyPeopleDTO nearbyPeople) {
        log.info("查询用户附近的人接口请求参数:{}", JSON.toJSONString(nearbyPeople));
        long start = System.currentTimeMillis();
        List<UserLocation> userLocationList;
        Double lat = nearbyPeople.getLat();
        Double lon = nearbyPeople.getLon();
        Double distance = nearbyPeople.getDistance();
        // 地理位置距离查询
        String name = "location";
        log.info("查询es中附近的人请求参数，距离:{}米，维度:{},经度:{}", distance, lat, lon);
//        org.elasticsearch.common.geo.GeoPoint topLeft  = new org.elasticsearch.common.geo.GeoPoint(31.293072, 121.192078);
//        org.elasticsearch.common.geo.GeoPoint bottomRight = new org.elasticsearch.common.geo.GeoPoint(lat, lon);
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("status", 1))
                // 地理距离过滤器
                .filter(QueryBuilders.geoDistanceQuery(name)
                        .geoDistance(GeoDistance.PLANE)// 计算方式
                        .distance(distance, DistanceUnit.METERS)// 圆半径
                        .point(lat, lon));// 中心点坐标
            //地理坐标盒模型过滤器
//        .filter(QueryBuilders.geoBoundingBoxQuery(name).setCorners(topLeft, bottomRight));
        // 执行搜索
        Iterable<UserLocation> userLocations = this.userLocationSearchRepository.search(queryBuilder);
        long end = System.currentTimeMillis();
        log.info("查询附近车辆耗时:{}", (end - start) + "毫秒");
        // 处理搜索结果
        userLocationList = this.handle(userLocations);
        log.info("查询es中附近的人响应结果:{}", JSON.toJSONString(userLocationList));
        return ResponseEntity.ok(userLocationList);
    }

    /**
     * 处理结果
     *
     * @param userLocations
     * @return
     */
    private List<UserLocation> handle(Iterable<UserLocation> userLocations) {
        List<UserLocation> userLocationList = new ArrayList<>();
        userLocations.forEach(item -> {
            userLocationList.add(item);
        });
        return userLocationList;
    }


}
