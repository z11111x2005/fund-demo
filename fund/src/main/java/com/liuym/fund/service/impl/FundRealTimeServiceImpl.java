package com.liuym.fund.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuym.fund.dao.FundRealTimeMapper;
import com.liuym.fund.dto.FundInfoDTO;
import com.liuym.fund.dto.FundRealTimeDTO;
import com.liuym.fund.entity.FundGroupRelation;
import com.liuym.fund.entity.FundRealTime;
import com.liuym.fund.service.FundGroupRelationService;
import com.liuym.fund.service.FundRealTimeService;
import com.liuym.fund.service.FundService;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;


/**
 * @author liuym
 * @date 2020/8/6 17:23
 * @description
 */
@Slf4j
@Service
public class FundRealTimeServiceImpl implements FundRealTimeService {

    @Autowired
    private FundService fundService;
    @Resource
    private FundRealTimeMapper fundRealTimeMapper;
    @Autowired
    private FundGroupRelationService fundGroupRelationService;

    @Override
    public void pushAllFund() {
        List<FundInfoDTO> allFund = fundService.listFund();
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                processors + 1,
                2 * processors + 1,
                20,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(12000),
                new DefaultThreadFactory("fund-info"));
        allFund.forEach(fundInfoDTO -> {
            threadPoolExecutor.execute(() -> {
                pushByFundCode(fundInfoDTO.getCode(), fundInfoDTO.getType());
            });
        });
    }

    /**
     * 更新基金实时数据
     *
     * @param code
     */
    @Override
    public FundRealTime pushByFundCode(String code, String fundType) {
        log.info("当前基金, code={}, fundType={}", code, fundType);
        FundRealTimeDTO fundRealTimeInfo = fundService.getFundRealTimeInfo(code);
        if (fundRealTimeInfo != null) {
            log.info("当前基金实时数据,fundRealTime={}", fundRealTimeInfo);
            FundRealTime oldFundRealTime = fundRealTimeMapper.selectById(code);
            FundRealTime fundRealTime = new FundRealTime();
            BeanUtils.copyProperties(fundRealTimeInfo, fundRealTime);
            fundRealTime.setFundName(fundRealTimeInfo.getName());
            fundRealTime.setCreateTime(new Date());
            if (null != oldFundRealTime) {
                fundRealTime.setType(Optional.ofNullable(fundType).orElse(oldFundRealTime.getType()));
                fundRealTimeMapper.updateById(fundRealTime);
            } else {
                fundRealTime.setType(fundType);
                fundRealTimeMapper.insert(fundRealTime);
            }
            return fundRealTime;
        }
        return null;
    }

    @Override
    public List<FundRealTime> listByGroupId(Integer groupId) {
        List<FundGroupRelation> fundGroupRelationList = fundGroupRelationService.listByGroupId(groupId);
        List<String> fundCodeList = fundGroupRelationList
                .stream()
                .map(FundGroupRelation::getFundCode)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(fundCodeList)) {
            return Collections.emptyList();
        }
        QueryWrapper<FundRealTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("fund_code", fundCodeList);
        return fundRealTimeMapper.selectList(queryWrapper);
    }
}
