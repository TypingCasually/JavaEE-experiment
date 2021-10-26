package com.iyuu.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iyuu.commom.Result;
import com.iyuu.entity.Goods;
import com.iyuu.entity.User;
import com.iyuu.mapper.GoodsMapper;
import com.iyuu.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goods")
public class GoodsController  {

    @Resource
    GoodsMapper goodsMapper;

    @PostMapping
    public Result<?> save(@RequestBody Goods goods) {
        goodsMapper.insert(goods);
        return Result.success();
    }

    @DeleteMapping("/{gid}")
    public Result<?> delete(@PathVariable Long gid) {
        goodsMapper.deleteById(gid);
        return Result.success();
    }


    @PutMapping
    public Result<?> update(@RequestBody Goods goods) {
        goodsMapper.updateById(goods);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10")Integer pageSize,
                              @RequestParam (defaultValue = "")String search) {

        //模糊查询
        LambdaQueryWrapper<Goods> wrapper = Wrappers.<Goods>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            wrapper.like(Goods::getGname,search);
        }
        Page<Goods> goodsPage=goodsMapper.selectPage(new Page<>(pageNum,pageSize), wrapper);
        return Result.success(goodsPage);
    }

}