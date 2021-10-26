package com.iyuu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.List;


@TableName("user")
@Data
public class User {
    @TableId(value="uid",type = IdType.AUTO)
    private Integer uid;
    private String uname;
    private String usex;
    private String uphone;

}