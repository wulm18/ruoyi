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
import com.ruoyi.dingtalk.domain.UnicomPoints;
import com.ruoyi.dingtalk.service.IUnicomPointsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 联通先锋Controller
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/dingtalk/unicom")
public class UnicomPointsController extends BaseController
{
    private String prefix = "dingtalk/unicom";

    @Autowired
    private IUnicomPointsService unicomPointsService;
    private Global RuoYiConfig;

    @RequiresPermissions("dingtalk:unicom:view")
    @GetMapping()
    public String unicom()
    {
        return prefix + "/unicom";
    }

    /**
     * 查询联通先锋列表
     */
    @RequiresPermissions("dingtalk:unicom:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UnicomPoints unicomPoints)
    {
        startPage();
        List<UnicomPoints> list = unicomPointsService.selectUnicomPointsList(unicomPoints);
        return getDataTable(list);
    }

    /**
     * 导出联通先锋列表
     */
    @RequiresPermissions("dingtalk:unicom:export")
    @Log(title = "联通先锋", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UnicomPoints unicomPoints)
    {
        List<UnicomPoints> list = unicomPointsService.selectUnicomPointsList(unicomPoints);
        ExcelUtil<UnicomPoints> util = new ExcelUtil<UnicomPoints>(UnicomPoints.class);
        return util.exportExcel(list, "unicom");
    }

    /**
     * 新增联通先锋
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存联通先锋
     */
    @RequiresPermissions("dingtalk:unicom:add")
    @Log(title = "联通先锋", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file, UnicomPoints unicomPoints) throws IOException
    {
        List<UnicomPoints> list = unicomPointsService.selectUnicomPointsList(unicomPoints);
        if (list.size() != 0){
            for(int i = 0; i < list.size(); i++){
                unicomPointsService.deleteUnicomPointsById(list.get(i).getUnicomId());
            }
        }
        // 上传文件路径
        String filePath = RuoYiConfig.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        unicomPoints.setPointsFile(fileName);
        unicomPoints.setCreateBy(ShiroUtils.getLoginName());
        unicomPoints.setUserName(ShiroUtils.getSysUser().getUserName());
        unicomPoints.setUnicomFlag("1");
        return toAjax(unicomPointsService.insertUnicomPoints(unicomPoints));
    }

    /**
     * 修改联通先锋
     */
    @GetMapping("/edit/{unicomId}")
    public String edit(@PathVariable("unicomId") Long unicomId, ModelMap mmap)
    {
        UnicomPoints unicomPoints = unicomPointsService.selectUnicomPointsById(unicomId);
        mmap.put("unicomPoints", unicomPoints);
        return prefix + "/edit";
    }

    /**
     * 修改保存联通先锋
     */
    @RequiresPermissions("dingtalk:unicom:edit")
    @Log(title = "联通先锋", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UnicomPoints unicomPoints)
    {
        return toAjax(unicomPointsService.updateUnicomPoints(unicomPoints));
    }

    /**
     * 删除联通先锋
     */
    @RequiresPermissions("dingtalk:unicom:remove")
    @Log(title = "联通先锋", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(unicomPointsService.deleteUnicomPointsByIds(ids));
    }
}
