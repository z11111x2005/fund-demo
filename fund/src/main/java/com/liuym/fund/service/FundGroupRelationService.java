package com.liuym.fund.service;

import com.liuym.fund.dto.FundGroupRelationFormDTO;
import com.liuym.fund.entity.FundGroupRelation;

import java.util.List;

/**
 * @author liuym
 * @date 2020/8/10 15:29
 * @description
 */
public interface FundGroupRelationService {

    /**
     * 通过groupId获取关联信息
     *
     * @param groupId
     * @return
     */
    List<FundGroupRelation> listByGroupId(Integer groupId);

    /**
     * 批量关联
     *
     * @param formDTO
     */
    void batchRelation(FundGroupRelationFormDTO formDTO);

    void deleteByGroupId(Integer groupId);
}
