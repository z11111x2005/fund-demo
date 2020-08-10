package com.liuym.fund.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuym.fund.dao.FundGroupUserRelationMapper;
import com.liuym.fund.entity.FundGroupUserRelation;
import com.liuym.fund.service.FundGroupUserRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuym
 * @date 2020/8/10 10:01
 * @description
 */
@Service
public class FundGroupUserRelationServiceImpl implements FundGroupUserRelationService {

    @Resource
    private FundGroupUserRelationMapper fundGroupUserRelationMapper;

    @Override
    public List<FundGroupUserRelation> getGroupIdByUserId(Integer userId) {
        QueryWrapper<FundGroupUserRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(userId != null, "user_id", userId);
        return fundGroupUserRelationMapper.selectList(queryWrapper);
    }

    @Override
    public void add(Integer groupId, Integer userId) {
        FundGroupUserRelation fundGroupUserRelation = new FundGroupUserRelation();
        fundGroupUserRelation.setGroupId(groupId);
        fundGroupUserRelation.setUserId(userId);
        fundGroupUserRelationMapper.insert(fundGroupUserRelation);
    }

    @Override
    public void deleteByGroupId(Integer groupId) {
        QueryWrapper<FundGroupUserRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("group_id", groupId);
        fundGroupUserRelationMapper.delete(queryWrapper);
    }

}
