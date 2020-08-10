package com.liuym.fund.service;

import com.liuym.fund.dto.FundInfoDTO;
import com.liuym.fund.dto.FundRealTimeDTO;

import java.util.List;

/**
 * @author liuym
 * @date 2020/8/6 14:18
 * @description 基金接口
 */
public interface FundService {

    /**
     * 获取基金列表
     *
     * @return
     */
    List<FundInfoDTO> listFund();

    /**
     * 获取基金实时信息
     *
     * @param code
     * @return
     */
    FundRealTimeDTO getFundRealTimeInfo(String code);
}
