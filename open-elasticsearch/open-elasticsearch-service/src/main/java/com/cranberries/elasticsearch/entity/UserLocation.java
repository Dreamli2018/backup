package com.cranberries.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：Dream Li
 * @version ：1.0.0
 * @program ：cranberries
 * @date ：Created in 2021/07/13 16:10
 * @description ：用户位置信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "user_locations", type = "_doc", createIndex = false)
public class UserLocation implements Serializable {

    private static final long serialVersionUID = -6129104805287779207L;

    /**
     * pk
     */
    private Integer id;

    /**
     * 用户id
     */
    @Field(value = "user_id")
    private Integer userId;

    /**
     * 用户id
     */
    @Field(value = "user_name")
    private String userName;

    /**
     * 用户位置经度
     */
    @Field(value = "lon")
    private Double lon;

    /**
     * 用户位置维度
     */
    @Field(value = "lat")
    private Double lat;

    /**
     * 用户地址名称
     */
    @Field(value = "address")
    private String address;

    /**
     * 用户手机号
     */
    @Field(value = "phone")
    private String phone;

    /**
     * 车牌号
     */
    @Field(value = "car_no")
    private String carNo;

    /**
     * 状态
     */
    @Field(value = "status")
    private Integer status;

    /**
     * 状态
     */
    @Field(value = "accept")
    private Integer accept;

    /**
     * 更新时间
     */
    @Field(value = "updated_time")
    private Date updatedTime;

    @GeoPointField
    @Field(value = "location")
    private GeoPoint location;

}
