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
import com.ruoyi.dingtalk.domain.Points;
import com.ruoyi.dingtalk.service.IPointsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 学习强国Controller
 * 
 * @author ruoyi
 * @date 2020-05-26
 */
@Controller
@RequestMapping("/dingtalk/points")
public class PointsController extends BaseController
{
    private String prefix = "dingtalk/points";

    @Autowired
    private IPointsService pointsService;
    private Global RuoYiConfig;

    @RequiresPermissions("dingtalk:points:view")
    @GetMapping()
    public String points()
    {
        return prefix + "/points";
    }

    /**
     * 上次学习强国积分
     */
    public Long getPoint(){
       Points point = pointsService.selectPointsByCreateBy(ShiroUtils.getLoginName());
       return point.getLatestPoint();
    }
    /**
     * 查询学习强国列表
     */
    @RequiresPermissions("dingtalk:points:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Points points)
    {
        startPage();
        List<Points> list = pointsService.selectPointsList(points);
        return getDataTable(list);
    }

    /**
     * 导出学习强国列表
     */
    @RequiresPermissions("dingtalk:points:export")
    @Log(title = "学习强国", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Points points)
    {
        List<Points> list = pointsService.selectPointsList(points);
        ExcelUtil<Points> util = new ExcelUtil<Points>(Points.class);
        return util.exportExcel(list, "points");
    }

    /**
     * 新增学习强国
     *
    @GetMapping("/add")
    */
    /*public String add()
    {

        return prefix + "/add";
    }*/
    @GetMapping("/add")
    public String add( ModelMap mmap)
    {
        Points points = pointsService.selectPointsByCreateBy(ShiroUtils.getLoginName());
        System.out.println("累计积分："+points.getLatestPoint());
        mmap.put("historyPoint", points.getLatestPoint());
        return prefix + "/add";
    }
    /**
     * 新增保存学习强国
     */
    @RequiresPermissions("dingtalk:points:add")
    @Log(title = "学习强国", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file, Points points) throws IOException
    {
        List<Points> list = pointsService.selectPointsList(points);
        if (list.size() != 0){
            for(int i = 0; i < list.size(); i++){
                pointsService.deletePointsById(list.get(i).getPointId());
            }
        }
        // 上传文件路径
        String filePath = RuoYiConfig.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        points.setPointFile(fileName);

        points.setCreateBy(ShiroUtils.getLoginName());
        points.setUserName(ShiroUtils.getSysUser().getUserName());
        points.setPointFlag("1");
        return toAjax(pointsService.insertPoints(points));
    }

    /**
     * 修改学习强国
     */
    @GetMapping("/edit/{pointId}")
    public String edit(@PathVariable("pointId") Long pointId, ModelMap mmap)
    {
        Points points = pointsService.selectPointsById(pointId);
        mmap.put("points", points);
        return prefix + "/edit";
    }

    /**
     * 修改保存学习强国
     */
    @RequiresPermissions("dingtalk:points:edit")
    @Log(title = "学习强国", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Points points)
    {
        return toAjax(pointsService.updatePoints(points));
    }

    /**
     * 删除学习强国
     */
    @RequiresPermissions("dingtalk:points:remove")
    @Log(title = "学习强国", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(pointsService.deletePointsByIds(ids));
    }
}
