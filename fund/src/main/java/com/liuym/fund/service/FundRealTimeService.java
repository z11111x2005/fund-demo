package com.liuym.fund.service;


import com.liuym.fund.entity.FundRealTime;

import java.util.List;

/**
 * @author liuym
 * @date 2020/8/6 17:20
 * @description
 */
public interface FundRealTimeService {

    /**
     * 刷新基金信息
     */
    void pushAllFund();

    /**
     * 通过code更新RealTime
     *
     * @param code
     * @param fundType
     */
    FundRealTime pushByFundCode(String code, String fundType);

    /**
     * 通过groupId获取基金集合
     *
     * @param groupId
     * @return
     */
    List<FundRealTime> listByGroupId(Integer groupId);
}
