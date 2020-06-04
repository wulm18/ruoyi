package com.ruoyi.dingtalk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 联通先锋对象 unicom_points
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
public class UnicomPoints extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long unicomId;

    /** 本月积分 */
    @Excel(name = "本月积分")
    private Long mouthPoints;

    /** 积分截图 */
    private String pointsFile;

    /** 创建者姓名 */
    @Excel(name = "创建者姓名")
    private String userName;

    /** 标志位（0待办 1已办） */
    @Excel(name = "标志位", readConverterExp = "0=待办,1=已办")
    private String unicomFlag;

    /** 部门ID */
    private Long deptId;

    public void setUnicomId(Long unicomId) 
    {
        this.unicomId = unicomId;
    }

    public Long getUnicomId() 
    {
        return unicomId;
    }
    public void setMouthPoints(Long mouthPoints) 
    {
        this.mouthPoints = mouthPoints;
    }

    public Long getMouthPoints() 
    {
        return mouthPoints;
    }
    public void setPointsFile(String pointsFile) 
    {
        this.pointsFile = pointsFile;
    }

    public String getPointsFile() 
    {
        return pointsFile;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }

    public void setUnicomFlag(String unicomFlag) { this.unicomFlag = unicomFlag; }

    public String getUnicomFlag() { return unicomFlag; }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("unicomId", getUnicomId())
            .append("mouthPoints", getMouthPoints())
            .append("pointsFile", getPointsFile())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("userName", getUserName())
            .append("unicomFlag", getUnicomFlag())
            .append("deptId", getDeptId())
            .toString();
    }
}
