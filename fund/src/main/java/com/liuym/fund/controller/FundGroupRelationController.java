package com.liuym.fund.controller;

import com.liuym.fund.dto.FundGroupRelationFormDTO;
import com.liuym.fund.service.FundGroupRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author liuym
 * @date 2020/8/10 16:08
 * @description
 */
@RestController
@RequestMapping("/fund/group/relation")
public class FundGroupRelationController {

    @Autowired
    private FundGroupRelationService fundGroupRelationService;
    @PostMapping("/add")
    public void add(@Valid @RequestBody FundGroupRelationFormDTO formDTO) {
        fundGroupRelationService.batchRelation(formDTO);
    }
}
