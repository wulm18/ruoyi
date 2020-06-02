package com.ruoyi.dingtalk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 党费缴纳对象 party_dues
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
public class PartyDues extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    @Excel(name = "序号")
    private Long partyDueId;

    /** 缴纳截图 */
    private String dueFile;

    /** 创建者姓名 */
    @Excel(name = "创建者姓名")
    private String createByName;

    /** 标志位（0待办 1已办） */
    private String flag;


    public void setPartyDueId(Long partyDueId) 
    {
        this.partyDueId = partyDueId;
    }

    public Long getPartyDueId() 
    {
        return partyDueId;
    }
    public void setDueFile(String dueFile) 
    {
        this.dueFile = dueFile;
    }

    public String getDueFile() 
    {
        return dueFile;
    }
    public void setCreateByName(String createByName) 
    {
        this.createByName = createByName;
    }

    public String getCreateByName() 
    {
        return createByName;
    }

    public void setFlag(String flag) { this.flag = flag; }

    public String getFlag() { return flag; }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("partyDueId", getPartyDueId())
            .append("dueFile", getDueFile())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("createByName", getCreateByName())
            .append("flag", getFlag())
            .toString();
    }
}
