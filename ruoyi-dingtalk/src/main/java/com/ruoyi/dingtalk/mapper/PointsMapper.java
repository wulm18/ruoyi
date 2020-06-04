package com.ruoyi.dingtalk.mapper;

import java.util.List;
import com.ruoyi.dingtalk.domain.Points;

/**
 * 学习强国Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
public interface PointsMapper 
{
    /**
     * 查询学习强国
     * 
     * @param pointId 学习强国ID
     * @return 学习强国
     */
    public Points selectPointsById(Long pointId);

    /**
     * 查询最新学习强国
     *
     * @param CreateBy 学习强国ID
     * @return 学习强国
     */
    public Points selectPointsByCreateBy( String CreateBy);

    /**
     * 查询学习强国列表
     * 
     * @param points 学习强国
     * @return 学习强国集合
     */
    public List<Points> selectPointsList(Points points);

    /**
     * 查询当月学习强国列表
     *
     * @return 学习强国集合
     */
    public List<Points> selectPointsListByCreateTime();

    /**
     * 新增学习强国
     * 
     * @param points 学习强国
     * @return 结果
     */
    public int insertPoints(Points points);

    /**
     * 修改学习强国
     * 
     * @param points 学习强国
     * @return 结果
     */
    public int updatePoints(Points points);

    /**
     * 删除学习强国
     * 
     * @param pointId 学习强国ID
     * @return 结果
     */
    public int deletePointsById(Long pointId);

    /**
     * 批量删除学习强国
     * 
     * @param pointIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePointsByIds(String[] pointIds);
}
