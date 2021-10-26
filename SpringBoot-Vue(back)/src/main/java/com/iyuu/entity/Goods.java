package com.iyuu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("goods")
@Data
public class Goods {
    @TableId(value = "gid",type = IdType.AUTO)
    private Integer gid;
    private String gname;
    private String gprice;
    private String gdesc;

}