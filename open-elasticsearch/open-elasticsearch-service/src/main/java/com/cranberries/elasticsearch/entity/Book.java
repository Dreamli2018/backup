package com.cranberries.elasticsearch.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/06/15 18:18
 * @description ：
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "index_book")
public class Book implements Serializable {

    @Field(name = "id")
    @ApiModelProperty(value = "主键-pk", example = "0000001")
    private Integer id;

    @Field(name = "name", type = FieldType.Text, analyzer = "ik_max_word")
    @ApiModelProperty(value = "书名", example = "围城")
    @Pattern(regexp = "[\\p{P}]", message = "不能包含标点符号")
    private String name;

    @Field(name = "author", type = FieldType.Text, analyzer = "ik_max_word")
    @ApiModelProperty(value = "作者", example = "钱钟书")
    private String author;

    @Field(name = "price")
    @ApiModelProperty(value = "价格", example = "17.88")
    private Double price;

    @Field(name = "country", type = FieldType.Keyword, analyzer = "cjk")
    @ApiModelProperty(value = "国家", example = "中国")
    private String country;

    @Field(name = "imageUrl", type = FieldType.Keyword)
    @ApiModelProperty(value = "图片链接", example = "/Users/dreamli/image/dog.jpg")
    private String imageUrl;

    @Field(name = "type")
    @ApiModelProperty(value = "类型", example = "1-文学、2-计算机")
    private Integer type;

    @Field(name = "publicationDate", index = false)
    @ApiModelProperty(value = "出版日期", example = "2018-10-20")
    private Date publicationDate;
}
