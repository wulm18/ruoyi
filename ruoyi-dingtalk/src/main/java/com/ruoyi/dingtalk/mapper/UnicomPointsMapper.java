package com.ruoyi.dingtalk.mapper;

import java.util.List;
import com.ruoyi.dingtalk.domain.UnicomPoints;

/**
 * 联通先锋Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
public interface UnicomPointsMapper 
{
    /**
     * 查询联通先锋
     * 
     * @param unicomId 联通先锋ID
     * @return 联通先锋
     */
    public UnicomPoints selectUnicomPointsById(Long unicomId);

    /**
     * 查询联通先锋列表
     * 
     * @param unicomPoints 联通先锋
     * @return 联通先锋集合
     */
    public List<UnicomPoints> selectUnicomPointsList(UnicomPoints unicomPoints);

    /**
     * 查询联通先锋列表
     *
     * @return 联通先锋集合
     */
    public List<UnicomPoints> selectUnicomPointsListByCreateTime(String loginName);

    /**
     * 新增联通先锋
     * 
     * @param unicomPoints 联通先锋
     * @return 结果
     */
    public int insertUnicomPoints(UnicomPoints unicomPoints);

    /**
     * 修改联通先锋
     * 
     * @param unicomPoints 联通先锋
     * @return 结果
     */
    public int updateUnicomPoints(UnicomPoints unicomPoints);

    /**
     * 删除联通先锋
     * 
     * @param unicomId 联通先锋ID
     * @return 结果
     */
    public int deleteUnicomPointsById(Long unicomId);

    /**
     * 批量删除联通先锋
     * 
     * @param unicomIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteUnicomPointsByIds(String[] unicomIds);
}
