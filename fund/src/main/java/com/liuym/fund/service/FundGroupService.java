package com.liuym.fund.service;

import com.liuym.fund.dto.FundGroupFormDTO;

/**
 * @author liuym
 * @date 2020/8/10 9:30
 * @description
 */
public interface FundGroupService {

    /**
     * 新增分组
     *
     * @param formDTO
     */
    void add(FundGroupFormDTO formDTO);

    /**
     * 通过groupId删除数据
     *
     * @param groupId
     */
    void deleteByGroupId(Integer groupId);
}
