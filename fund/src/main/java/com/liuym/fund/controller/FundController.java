package com.liuym.fund.controller;

import com.liuym.fund.dto.FundInfoDTO;
import com.liuym.fund.service.FundService;
import com.liuym.fund.util.RedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liuym
 * @date 2020/8/10 10:55
 * @description
 */
@RestController
@RequestMapping("/fund")
public class FundController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private FundService fundService;

    @GetMapping("/sync/cache")
    public void syncCache(){
        redisTemplate.delete(RedisConstant.FUND_LIST);
        List<FundInfoDTO> fundInfoList = fundService.listFund();
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        listOperations.rightPushAll(RedisConstant.FUND_LIST, fundInfoList);
    }

    @GetMapping("/get/cache")
    public List<Object> getCache(){
        ListOperations<String, Object> listOperations = redisTemplate.opsForList();
        System.out.println(listOperations.range(RedisConstant.FUND_LIST,0, -1));
        return listOperations.range(RedisConstant.FUND_LIST,0, -1);
    }
}
