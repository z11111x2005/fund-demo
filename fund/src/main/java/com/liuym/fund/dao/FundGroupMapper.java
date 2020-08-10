package com.liuym.fund.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuym.fund.dto.FundGroupDTO;
import com.liuym.fund.entity.FundGroup;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author liuym
 * @date 2020/8/10 9:29
 * @description
 */
public interface FundGroupMapper extends BaseMapper<FundGroup> {

    /**
     * 查询登录人的分组信息
     *
     * @param userId
     * @return
     */
    @Select("SELECT " +
            "fg.group_id," +
            "fg.group_name " +
            "FROM " +
            "fund_group_user_relation fgur " +
            "LEFT JOIN fund_group fg ON fgur.group_id = fg.group_id " +
            "WHERE " +
            "fgur.user_id = #{userId}")
    List<FundGroupDTO> listMe(Integer userId);
}
