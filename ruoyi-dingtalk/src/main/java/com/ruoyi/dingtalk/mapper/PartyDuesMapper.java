package com.ruoyi.dingtalk.mapper;

import java.util.List;
import com.ruoyi.dingtalk.domain.PartyDues;

/**
 * 党费缴纳Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
public interface PartyDuesMapper 
{
    /**
     * 查询党费缴纳
     * 
     * @param partyDueId 党费缴纳ID
     * @return 党费缴纳
     */
    public PartyDues selectPartyDuesById(Long partyDueId);

    /**
     * 查询党费缴纳列表
     * 
     * @param partyDues 党费缴纳
     * @return 党费缴纳集合
     */
    public List<PartyDues> selectPartyDuesList(PartyDues partyDues);

    /**
     * 查询当月是否上传过
     * @return  党费缴纳集合
     */
    public List<PartyDues> selectPartyDuesListByCreateTime();
    /**
     * 新增党费缴纳
     * 
     * @param partyDues 党费缴纳
     * @return 结果
     */
    public int insertPartyDues(PartyDues partyDues);

    /**
     * 修改党费缴纳
     * 
     * @param partyDues 党费缴纳
     * @return 结果
     */
    public int updatePartyDues(PartyDues partyDues);

    /**
     * 删除党费缴纳
     * 
     * @param partyDueId 党费缴纳ID
     * @return 结果
     */
    public int deletePartyDuesById(Long partyDueId);

    /**
     * 批量删除党费缴纳
     * 
     * @param partyDueIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePartyDuesByIds(String[] partyDueIds);
}
