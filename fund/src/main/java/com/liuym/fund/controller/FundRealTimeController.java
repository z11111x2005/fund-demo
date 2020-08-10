package com.liuym.fund.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuym.fund.dao.FundRealTimeMapper;
import com.liuym.fund.entity.FundRealTime;
import com.liuym.fund.service.FundRealTimeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuym
 * @date 2020/8/6 16:21
 * @description
 */
@RestController
@RequestMapping("/fund/real/time")
public class FundRealTimeController {

    @Autowired
    private FundRealTimeService fundRealTimeService;
    @Resource
    private FundRealTimeMapper fundRealTimeMapper;

    @GetMapping("/list")
    public Page<FundRealTime> listFundRealTime(@RequestParam(required = false, value = "current", defaultValue = "1") Integer current,
                                               @RequestParam(required = false, value = "size", defaultValue = "15") Integer size,
                                               @RequestParam(required = false, value = "fundName") String fundName,
                                               @RequestParam(required = false, value = "fundCode") String fundCode,
                                               @RequestParam(required = false, value = "type") String type) {
        Page<FundRealTime> fundRealTimePage = new Page<>(current, size);
        QueryWrapper<FundRealTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight(StringUtils.isNotBlank(fundName), "fund_name", fundName);
        queryWrapper.likeRight(StringUtils.isNotBlank(fundCode), "fund_code", fundCode);
        queryWrapper.eq(StringUtils.isNotBlank(type), "type", type);
        return fundRealTimeMapper.selectPage(fundRealTimePage, queryWrapper);
    }

    @GetMapping("/list/{groupId}")
    public List<FundRealTime> listByGroupId(@PathVariable("groupId") Integer groupId) {
        return fundRealTimeService.listByGroupId(groupId);
    }

    @GetMapping("/push/all")
    public void pushAll() {
        fundRealTimeService.pushAllFund();
    }

    @GetMapping("/push/{fundCode}")
    public FundRealTime pushByFundCode(@PathVariable("fundCode") String fundCode) {
        return fundRealTimeService.pushByFundCode(fundCode, null);
    }

    @GetMapping("/list/all/type")
    public List<FundRealTime> listAllType() {
        QueryWrapper<FundRealTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy("type");
        return fundRealTimeMapper.selectList(queryWrapper);
    }

    @GetMapping("/gszzl/top10")
    public List<FundRealTime> gszzlTop10() {
        QueryWrapper<FundRealTime> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("gszzl")
                .last("limit 10");
        return fundRealTimeMapper.selectList(queryWrapper);
    }

    @GetMapping("/dwjz/top10")
    public List<FundRealTime> dwjzTop10() {
        QueryWrapper<FundRealTime> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("dwjz")
                .last("limit 10");
        return fundRealTimeMapper.selectList(queryWrapper);
    }
}
