package com.ruoyi.web.controller.dingtalk;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.dingtalk.domain.PartyDues;
import com.ruoyi.dingtalk.service.IPartyDuesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 党费缴纳Controller
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/dingtalk/dues")
public class PartyDuesController extends BaseController
{
    private String prefix = "dingtalk/dues";

    @Autowired
    private IPartyDuesService partyDuesService;
    private Global RuoYiConfig;

    @RequiresPermissions("dingtalk:dues:view")
    @GetMapping()
    public String dues()
    {
        return prefix + "/dues";
    }

    /**
     * 查询党费缴纳列表
     */
    @RequiresPermissions("dingtalk:dues:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PartyDues partyDues)
    {
        startPage();
        List<PartyDues> list = partyDuesService.selectPartyDuesList(partyDues);
        return getDataTable(list);
    }

    /**
     * 导出党费缴纳列表
     */
    @RequiresPermissions("dingtalk:dues:export")
    @Log(title = "党费缴纳", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PartyDues partyDues)
    {
        List<PartyDues> list = partyDuesService.selectPartyDuesList(partyDues);
        ExcelUtil<PartyDues> util = new ExcelUtil<PartyDues>(PartyDues.class);
        return util.exportExcel(list, "dues");
    }

    /**
     * 新增党费缴纳
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存党费缴纳
     */
    @RequiresPermissions("dingtalk:dues:add")
    @Log(title = "党费缴纳", businessType = BusinessType.INSERT)
    @PostMapping("/add")
   // @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file, PartyDues partyDues) throws IOException
    {
        String loginName = ShiroUtils.getSysUser().getLoginName();
        List<PartyDues> list = partyDuesService.selectPartyDuesListByCreateTime(loginName);
        if(list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                partyDuesService.deletePartyDuesById(list.get(i).getPartyDueId());
            }
        }
        // 上传文件路径
        String filePath = RuoYiConfig.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        partyDues.setDueFile(fileName);

        partyDues.setCreateBy(ShiroUtils.getLoginName());
        partyDues.setCreateByName(ShiroUtils.getSysUser().getUserName());
        partyDues.setFlag("1");
        partyDues.setDeptId(ShiroUtils.getSysUser().getDeptId());
        return toAjax(partyDuesService.insertPartyDues(partyDues));

    }

    /**
     * 修改党费缴纳
     */
    @GetMapping("/edit/{partyDueId}")
    public String edit(@PathVariable("partyDueId") Long partyDueId, ModelMap mmap)
    {
        PartyDues partyDues = partyDuesService.selectPartyDuesById(partyDueId);
        mmap.put("partyDues", partyDues);
        return prefix + "/edit";
    }

    /**
     * 修改保存党费缴纳
     */
    @RequiresPermissions("dingtalk:dues:edit")
    @Log(title = "党费缴纳", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestParam("file") MultipartFile file,PartyDues partyDues)  throws IOException
    {
        // 上传文件路径
        String filePath = RuoYiConfig.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        partyDues.setDueFile(fileName);
        return toAjax(partyDuesService.updatePartyDues(partyDues));
    }

    /**
     * 删除党费缴纳
     */
    @RequiresPermissions("dingtalk:dues:remove")
    @Log(title = "党费缴纳", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(partyDuesService.deletePartyDuesByIds(ids));
    }
}
