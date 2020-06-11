package com.ruoyi.dingtalk.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dingtalk.mapper.UnicomPointsMapper;
import com.ruoyi.dingtalk.domain.UnicomPoints;
import com.ruoyi.dingtalk.service.IUnicomPointsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 联通先锋Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
@Service
public class UnicomPointsServiceImpl implements IUnicomPointsService 
{
    @Autowired
    private UnicomPointsMapper unicomPointsMapper;

    /**
     * 查询联通先锋
     * 
     * @param unicomId 联通先锋ID
     * @return 联通先锋
     */
    @Override
    public UnicomPoints selectUnicomPointsById(Long unicomId)
    {
        return unicomPointsMapper.selectUnicomPointsById(unicomId);
    }

    /**
     * 查询联通先锋列表
     * 
     * @param unicomPoints 联通先锋
     * @return 联通先锋
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<UnicomPoints> selectUnicomPointsList(UnicomPoints unicomPoints)
    {
        return unicomPointsMapper.selectUnicomPointsList(unicomPoints);
    }
    /**
     * 查询联通先锋列表
     *
     * @return 联通先锋集合
     */
    @Override
    public List<UnicomPoints> selectUnicomPointsListByCreateTime(String loginName) {
        return unicomPointsMapper.selectUnicomPointsListByCreateTime(loginName);
    }

    /**
     * 新增联通先锋
     * 
     * @param unicomPoints 联通先锋
     * @return 结果
     */
    @Override
    public int insertUnicomPoints(UnicomPoints unicomPoints)
    {
        unicomPoints.setCreateTime(DateUtils.getNowDate());
        return unicomPointsMapper.insertUnicomPoints(unicomPoints);
    }

    /**
     * 修改联通先锋
     * 
     * @param unicomPoints 联通先锋
     * @return 结果
     */
    @Override
    public int updateUnicomPoints(UnicomPoints unicomPoints)
    {
        unicomPoints.setCreateTime(DateUtils.getNowDate());
        return unicomPointsMapper.updateUnicomPoints(unicomPoints);
    }

    /**
     * 删除联通先锋对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUnicomPointsByIds(String ids)
    {
        return unicomPointsMapper.deleteUnicomPointsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除联通先锋信息
     * 
     * @param unicomId 联通先锋ID
     * @return 结果
     */
    @Override
    public int deleteUnicomPointsById(Long unicomId)
    {
        return unicomPointsMapper.deleteUnicomPointsById(unicomId);
    }
}
