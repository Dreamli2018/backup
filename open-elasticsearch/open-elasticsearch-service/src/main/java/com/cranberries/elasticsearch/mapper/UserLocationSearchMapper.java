package com.cranberries.elasticsearch.mapper;

import com.cranberries.elasticsearch.entity.UserLocation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/07/13 16:23
 * @description ：
 */
@Mapper
public interface UserLocationSearchMapper {

    @Select("select *, user_id as userId, car_no as carNo, updated_time as updatedTime from user_location")
    List<UserLocation> queryAll();
}
