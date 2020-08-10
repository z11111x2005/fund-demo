package com.liuym.fund.service;

import com.liuym.fund.entity.FundGroupUserRelation;

import java.util.List;

/**
 * @author liuym
 * @date 2020/8/10 10:01
 * @description
 */
public interface FundGroupUserRelationService {

    /**
     * 通过user_id获取group_id
     *
     * @param userId
     * @return
     */
    List<FundGroupUserRelation> getGroupIdByUserId(Integer userId);


    void add(Integer groupId, Integer userId);

    void deleteByGroupId(Integer groupId);
}
