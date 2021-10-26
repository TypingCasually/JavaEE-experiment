package com.iyuu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("order")
@Data
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer oid;
    private Integer uid;
    private String  otime;
    private String odesc;
    private String goodsdesc;

}