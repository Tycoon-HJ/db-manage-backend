package org.jeecg.modules.dbmanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.aspect.annotation.Message;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.dbmanage.entity.TbDbManage;
import org.jeecg.modules.dbmanage.service.ITbDbManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: 数据库字段管理
 * @Author: jeecg-boot
 * @Date:   2023-03-21
 * @Version: V1.0
 */
@Api(tags="数据库字段管理")
@RestController
@RequestMapping("/tbDbManage/tbDbManage")
@Slf4j
public class TbDbManageController extends JeecgController<TbDbManage, ITbDbManageService> {
	@Autowired
	private ITbDbManageService tbDbManageService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tbDbManage
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "数据库字段管理-分页列表查询")
	@ApiOperation(value="数据库字段管理-分页列表查询", notes="数据库字段管理-分页列表查询")
	@GetMapping(value = "/list")
    public Result<?> queryPageList(@Message TbDbManage tbDbManage,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TbDbManage> queryWrapper = QueryGenerator.initQueryWrapper(tbDbManage, req.getParameterMap());
		Page<TbDbManage> page = new Page<TbDbManage>(pageNo, pageSize);
		IPage<TbDbManage> pageList = tbDbManageService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tbDbManage
	 * @return
	 */
	@AutoLog(value = "数据库字段管理-添加")
	@ApiOperation(value="数据库字段管理-添加", notes="数据库字段管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TbDbManage tbDbManage) {
		tbDbManageService.save(tbDbManage);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tbDbManage
	 * @return
	 */
	@AutoLog(value = "数据库字段管理-编辑")
	@ApiOperation(value="数据库字段管理-编辑", notes="数据库字段管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TbDbManage tbDbManage) {
		tbDbManageService.updateById(tbDbManage);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "数据库字段管理-通过id删除")
	@ApiOperation(value="数据库字段管理-通过id删除", notes="数据库字段管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tbDbManageService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "数据库字段管理-批量删除")
	@ApiOperation(value="数据库字段管理-批量删除", notes="数据库字段管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tbDbManageService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "数据库字段管理-通过id查询")
	@ApiOperation(value="数据库字段管理-通过id查询", notes="数据库字段管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TbDbManage tbDbManage = tbDbManageService.getById(id);
		if(tbDbManage==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tbDbManage);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tbDbManage
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TbDbManage tbDbManage) {
        return super.exportXls(request, tbDbManage, TbDbManage.class, "数据库字段管理");
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
        return super.importExcel(request, response, TbDbManage.class);
    }

}
