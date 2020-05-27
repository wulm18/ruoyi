package com.ruoyi.dingtalk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 学习强国对象 points
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
public class Points extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long pointId;

    /** 上次积分 */
    private Long historyPoint;

    /** 本次积分 */
    @Excel(name = "本次积分")
    private Long latestPoint;

    /** 完成积分 */
    @Excel(name = "完成积分")
    private Long finishPoint;

    /** 积分截图 */
    private String pointFile;

    /** 创建者姓名 */
    @Excel(name = "创建者姓名")
    private String userName;

    public void setPointId(Long pointId) 
    {
        this.pointId = pointId;
    }

    public Long getPointId() 
    {
        return pointId;
    }
    public void setHistoryPoint(Long historyPoint) 
    {
        this.historyPoint = historyPoint;
    }

    public Long getHistoryPoint() 
    {
        return historyPoint;
    }
    public void setLatestPoint(Long latestPoint) 
    {
        this.latestPoint = latestPoint;
    }

    public Long getLatestPoint() 
    {
        return latestPoint;
    }
    public void setFinishPoint(Long finishPoint) 
    {
        this.finishPoint = finishPoint;
    }

    public Long getFinishPoint() 
    {
        return finishPoint;
    }
    public void setPointFile(String pointFile) 
    {
        this.pointFile = pointFile;
    }

    public String getPointFile() 
    {
        return pointFile;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pointId", getPointId())
            .append("historyPoint", getHistoryPoint())
            .append("latestPoint", getLatestPoint())
            .append("finishPoint", getFinishPoint())
            .append("pointFile", getPointFile())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("userName", getUserName())
            .toString();
    }
}
