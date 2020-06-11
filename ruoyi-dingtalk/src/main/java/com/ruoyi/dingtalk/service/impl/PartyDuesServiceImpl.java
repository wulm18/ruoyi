package com.ruoyi.dingtalk.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dingtalk.mapper.PartyDuesMapper;
import com.ruoyi.dingtalk.domain.PartyDues;
import com.ruoyi.dingtalk.service.IPartyDuesService;
import com.ruoyi.common.core.text.Convert;

/**
 * 党费缴纳Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
@Service
public class PartyDuesServiceImpl implements IPartyDuesService 
{
    @Autowired
    private PartyDuesMapper partyDuesMapper;

    /**
     * 查询党费缴纳
     * 
     * @param partyDueId 党费缴纳ID
     * @return 党费缴纳
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public PartyDues selectPartyDuesById(Long partyDueId)
    {
        return partyDuesMapper.selectPartyDuesById(partyDueId);
    }

    /**
     * 查询党费缴纳列表
     * 
     * @param partyDues 党费缴纳
     * @return 党费缴纳
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<PartyDues> selectPartyDuesList(PartyDues partyDues)
    {
        return partyDuesMapper.selectPartyDuesList(partyDues);
    }

    /**
     * 查询当月是否上传过
     *
     * @return 党费缴纳集合
     */
    public List<PartyDues> selectPartyDuesListByCreateTime(String loginName) {
        return partyDuesMapper.selectPartyDuesListByCreateTime(loginName);
    }

    /**
     * 新增党费缴纳
     * 
     * @param partyDues 党费缴纳
     * @return 结果
     */
    @Override
    public int insertPartyDues(PartyDues partyDues)
    {
        partyDues.setCreateTime(DateUtils.getNowDate());
        return partyDuesMapper.insertPartyDues(partyDues);
    }

    /**
     * 修改党费缴纳
     * 
     * @param partyDues 党费缴纳
     * @return 结果
     */
    @Override
    public int updatePartyDues(PartyDues partyDues)
    {
        partyDues.setCreateTime(DateUtils.getNowDate());
        return partyDuesMapper.updatePartyDues(partyDues);
    }

    /**
     * 删除党费缴纳对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePartyDuesByIds(String ids)
    {
        return partyDuesMapper.deletePartyDuesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除党费缴纳信息
     * 
     * @param partyDueId 党费缴纳ID
     * @return 结果
     */
    @Override
    public int deletePartyDuesById(Long partyDueId)
    {
        return partyDuesMapper.deletePartyDuesById(partyDueId);
    }
}
