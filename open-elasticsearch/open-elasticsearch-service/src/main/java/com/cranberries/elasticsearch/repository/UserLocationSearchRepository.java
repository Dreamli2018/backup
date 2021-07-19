package com.cranberries.elasticsearch.repository;

import com.cranberries.elasticsearch.entity.UserLocation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/07/13 16:33
 * @description ：用户位置
 */
public interface UserLocationSearchRepository extends ElasticsearchRepository<UserLocation, Long> {
}
