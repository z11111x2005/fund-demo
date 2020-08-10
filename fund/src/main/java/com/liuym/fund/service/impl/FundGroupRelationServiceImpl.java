package com.liuym.fund.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuym.fund.dao.FundGroupRelationMapper;
import com.liuym.fund.dto.FundGroupRelationFormDTO;
import com.liuym.fund.entity.FundGroupRelation;
import com.liuym.fund.service.FundGroupRelationService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuym
 * @date 2020/8/10 15:29
 * @description
 */
@Service
public class FundGroupRelationServiceImpl implements FundGroupRelationService {

    @Resource
    private FundGroupRelationMapper fundGroupRelationMapper;

    @Override
    public List<FundGroupRelation> listByGroupId(Integer groupId) {
        QueryWrapper<FundGroupRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(groupId != null, "group_id", groupId);
        return fundGroupRelationMapper.selectList(queryWrapper);
    }

    @Override
    public void batchRelation(FundGroupRelationFormDTO formDTO) {
        Integer groupId = formDTO.getGroupId();
        List<String> fundCodes = formDTO.getFundCodes();
        if (groupId == null || CollectionUtils.isEmpty(fundCodes)) {
            return;
        }
        fundCodes.forEach(fundCode -> {
            QueryWrapper<FundGroupRelation> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("group_id", groupId);
            queryWrapper.eq("fund_code", fundCode);
            if (fundGroupRelationMapper.selectCount(queryWrapper) == 0) {
                FundGroupRelation fundGroupRelation = new FundGroupRelation();
                fundGroupRelation.setGroupId(groupId);
                fundGroupRelation.setFundCode(fundCode);
                fundGroupRelationMapper.insert(fundGroupRelation);
            }
        });
    }

    @Override
    public void deleteByGroupId(Integer groupId) {
        QueryWrapper<FundGroupRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", groupId);
        fundGroupRelationMapper.delete(queryWrapper);
    }
}
