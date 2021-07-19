package com.cranberries.elasticsearch.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/07/13 17:11
 * @description ：
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryNearbyPeopleDTO implements Serializable {

    private static final long serialVersionUID = -6904004087166973687L;

    @ApiModelProperty(value = "经度", required = true)
    private Double lon;

    @ApiModelProperty(value = "维度", required = true)
    private Double lat;

    @ApiModelProperty(value = "距离", required = true)
    @Min(value = 100, message = "最小半径为100米")
    @Max(value = 30000, message = "最大半径3公里")
    private Double distance;
}
