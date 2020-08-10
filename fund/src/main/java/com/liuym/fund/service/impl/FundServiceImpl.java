package com.liuym.fund.service.impl;

import com.liuym.fund.dto.FundInfoDTO;
import com.liuym.fund.dto.FundRealTimeDTO;
import com.liuym.fund.service.FundService;
import com.liuym.fund.util.JsonUtil;
import com.liuym.fund.util.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuym
 * @date 2020/8/6 14:19
 * @description
 */
@Service
@Slf4j
public class FundServiceImpl implements FundService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<FundInfoDTO> listFund() {
        String url = "http://fund.eastmoney.com/js/fundcode_search.js";
        String result = OkHttpUtil.get(url);
        Object[] resultObj = JsonUtil.toArray(result.substring(result.indexOf("[")).replace("];", "]"), String.class);
        List<FundInfoDTO> fundInfoList = new ArrayList<>();
        for (Object obj : resultObj) {
            String[] fundInfo = obj.toString().replace("[", "").replace("]", "").replaceAll("\"", "").split(",");
            FundInfoDTO fundInfoDTO = new FundInfoDTO();
            fundInfoDTO.setCode(fundInfo[0]);
            fundInfoDTO.setFundPY(fundInfo[1]);
            fundInfoDTO.setFundName(fundInfo[2]);
            fundInfoDTO.setType(fundInfo[3]);
            fundInfoDTO.setFundPinYin(fundInfo[4]);
            fundInfoList.add(fundInfoDTO);
        }
        return fundInfoList;
    }

    @Override
    public FundRealTimeDTO getFundRealTimeInfo(String code) {
        String url = "http://fundgz.1234567.com.cn/js/CODE.js?rt=TIME";
        String requestUrl = url.replace("CODE", code).replace("TIME", String.valueOf(Instant.now().getEpochSecond()));
        String result = OkHttpUtil.get(requestUrl);
        if(result == null || result.contains("<html>") || result.contains("not found")){
            return null;
        }
        return JsonUtil.toBean(result.replace("jsonpgz(", "").replace(");", ""), FundRealTimeDTO.class);
    }
}
