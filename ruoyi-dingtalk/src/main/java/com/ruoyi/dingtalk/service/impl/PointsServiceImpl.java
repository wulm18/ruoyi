package com.ruoyi.dingtalk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dingtalk.mapper.PointsMapper;
import com.ruoyi.dingtalk.domain.Points;
import com.ruoyi.dingtalk.service.IPointsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 学习强国Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
@Service
public class PointsServiceImpl implements IPointsService 
{
    @Autowired
    private PointsMapper pointsMapper;

    /**
     * 查询学习强国
     * 
     * @param pointId 学习强国ID
     * @return 学习强国
     */
    @Override
    public Points selectPointsById(Long pointId)
    {
        return pointsMapper.selectPointsById(pointId);
    }

    /**
     * 查询学习强国列表
     * 
     * @param points 学习强国
     * @return 学习强国
     */
    @Override
    public List<Points> selectPointsList(Points points)
    {
        return pointsMapper.selectPointsList(points);
    }

    /**
     * 新增学习强国
     * 
     * @param points 学习强国
     * @return 结果
     */
    @Override
    public int insertPoints(Points points)
    {
        points.setCreateTime(DateUtils.getNowDate());
        return pointsMapper.insertPoints(points);
    }

    /**
     * 修改学习强国
     * 
     * @param points 学习强国
     * @return 结果
     */
    @Override
    public int updatePoints(Points points)
    {
        points.setCreateTime(DateUtils.getNowDate());
        return pointsMapper.updatePoints(points);
    }

    /**
     * 删除学习强国对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePointsByIds(String ids)
    {
        return pointsMapper.deletePointsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学习强国信息
     * 
     * @param pointId 学习强国ID
     * @return 结果
     */
    @Override
    public int deletePointsById(Long pointId)
    {
        return pointsMapper.deletePointsById(pointId);
    }
}
