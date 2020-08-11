package com.liuym.fund.service.impl;

import com.liuym.fund.dao.FundGroupMapper;
import com.liuym.fund.dto.FundGroupFormDTO;
import com.liuym.fund.entity.FundGroup;
import com.liuym.fund.service.FundGroupRelationService;
import com.liuym.fund.service.FundGroupService;
import com.liuym.fund.service.FundGroupUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author liuym
 * @date 2020/8/10 9:30
 * @description
 */
@Service
public class FundGroupServiceImpl implements FundGroupService {

    @Resource
    private FundGroupMapper fundGroupMapper;
    @Autowired
    private FundGroupUserRelationService fundGroupUserRelationService;
    @Autowired
    private FundGroupRelationService fundGroupRelationService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(FundGroupFormDTO formDTO) {
        FundGroup fundGroup = new FundGroup();
        fundGroup.setGroupName(formDTO.getGroupName());
        fundGroup.setGroupType(formDTO.getGroupType());
        fundGroupMapper.insert(fundGroup);

        // 新增用户与分组关系
        fundGroupUserRelationService.add(fundGroup.getGroupId(), formDTO.getUserId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByGroupId(Integer groupId, Integer userId) {
        fundGroupUserRelationService.deleteByGroupId(groupId, userId);
        fundGroupMapper.deleteById(groupId);
        fundGroupRelationService.deleteByGroupId(groupId);
    }
}
