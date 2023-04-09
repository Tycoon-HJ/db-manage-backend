package org.jeecg.modules.fieldmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.fieldmanage.entity.TbFieldManager;
import org.jeecg.modules.fieldmanage.service.ITbFieldManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;

 /**
 * @Description: 字段管理
 * @Author: jeecg-boot
 * @Date:   2023-03-23
 * @Version: V1.0
 */
@Api(tags="字段管理")
@RestController
@RequestMapping("/tbFieldManager/tbFieldManager")
@Slf4j
public class TbFieldManagerController extends JeecgController<TbFieldManager, ITbFieldManagerService> {
	 @Autowired
	 private ITbFieldManagerService tbFieldManagerService;

	 /**
	  * 分页列表查询
	  *
	  * @param tbFieldManager
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "字段管理-分页列表查询")
	 @ApiOperation(value="字段管理-分页列表查询", notes="字段管理-分页列表查询")
	 @GetMapping(value = "/list")
	 public Result<?> queryPageList(TbFieldManager tbFieldManager,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 QueryWrapper<TbFieldManager> queryWrapper = QueryGenerator.initQueryWrapper(tbFieldManager, req.getParameterMap());
		 Page<TbFieldManager> page = new Page<>(pageNo, pageSize);
		 return Result.OK(tbFieldManagerService.page(page, queryWrapper));
	 }

	 /**
	  *   添加
	  *
	  * @param tbFieldManager
	  * @return
	  */
	 @AutoLog(value = "字段管理-添加")
	 @ApiOperation(value="字段管理-添加", notes="字段管理-添加")
	 @PostMapping(value = "/add")
	 public Result<?> add(@RequestBody @Valid TbFieldManager tbFieldManager) {
		 tbFieldManagerService.save(tbFieldManager);
		 return Result.OK("添加成功！");
	 }

	 /**
	  *  编辑
	  *
	  * @param tbFieldManager
	  * @return
	  */
	 @AutoLog(value = "字段管理-编辑")
	 @ApiOperation(value="字段管理-编辑", notes="字段管理-编辑")
	 @PutMapping(value = "/edit")
	 public Result<?> edit(@RequestBody TbFieldManager tbFieldManager) {
		 tbFieldManagerService.updateById(tbFieldManager);
		 return Result.OK("编辑成功!");
	 }

	 /**
	  *   通过id删除
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "字段管理-通过id删除")
	 @ApiOperation(value="字段管理-通过id删除", notes="字段管理-通过id删除")
	 @DeleteMapping(value = "/delete")
	 public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		 tbFieldManagerService.removeById(id);
		 return Result.OK("删除成功!");
	 }

	 /**
	  *  批量删除
	  *
	  * @param ids
	  * @return
	  */
	 @AutoLog(value = "字段管理-批量删除")
	 @ApiOperation(value="字段管理-批量删除", notes="字段管理-批量删除")
	 @DeleteMapping(value = "/deleteBatch")
	 public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		 this.tbFieldManagerService.removeByIds(Arrays.asList(ids.split(",")));
		 return Result.OK("批量删除成功!");
	 }

	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "字段管理-通过id查询")
	 @ApiOperation(value="字段管理-通过id查询", notes="字段管理-通过id查询")
	 @GetMapping(value = "/queryById")
	 public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		 TbFieldManager tbFieldManager = tbFieldManagerService.getById(id);
		 if(tbFieldManager==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.OK(tbFieldManager);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param tbFieldManager
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TbFieldManager tbFieldManager) {
        return super.exportXls(request, tbFieldManager, TbFieldManager.class, "字段管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TbFieldManager.class);
    }

}
