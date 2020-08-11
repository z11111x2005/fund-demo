package com.liuym.fund.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuym.fund.dao.FundGroupMapper;
import com.liuym.fund.dto.FundGroupDTO;
import com.liuym.fund.dto.FundGroupFormDTO;
import com.liuym.fund.entity.FundGroup;
import com.liuym.fund.entity.FundGroupUserRelation;
import com.liuym.fund.service.FundGroupService;
import com.liuym.fund.service.FundGroupUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author liuym
 * @date 2020/8/10 11:51
 * @description
 */
@RestController
@RequestMapping("/fund/group")
public class FundGroupController {

    @Autowired
    private FundGroupService fundGroupService;
    @Resource
    private FundGroupMapper fundGroupMapper;

    @PostMapping("add")
    public void add(@Valid @RequestBody FundGroupFormDTO formDTO) {
        fundGroupService.add(formDTO);
    }

    @GetMapping("/list/me")
    public List<FundGroupDTO> listMe(@RequestParam("userId") Integer userId) {
        return fundGroupMapper.listMe(userId);
    }

    @PostMapping("/delete/{groupId}/{userId}")
    public void delete(@PathVariable("groupId") Integer groupId,
                       @PathVariable("userId") Integer userId){
        fundGroupService.deleteByGroupId(groupId, userId);
    }
}
