package org.jeecg.modules.tbDbManage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Description: 数据库字段管理
 * @Author: jeecg-boot
 * @Date:   2023-03-21
 * @Version: V1.0
 */
@Data
@TableName("tb_db_manage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tb_db_manage对象", description="数据库字段管理")
public class TbDbManage implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**字段名称*/
	@Excel(name = "字段名称", width = 15)
    @ApiModelProperty(value = "字段名称")
    private java.lang.String fieldName;
	/**字段类型*/
	@Excel(name = "字段类型", width = 15)
    @ApiModelProperty(value = "字段类型")
    private java.lang.String fieldType;
	/**字段长度*/
	@Excel(name = "字段长度", width = 15)
    @ApiModelProperty(value = "字段长度")
    private java.lang.String fieldLength;
	/**主键*/
	@Excel(name = "主键", width = 15)
    @ApiModelProperty(value = "主键")
    private java.lang.String primaryKey;
	/**外键*/
	@Excel(name = "外键", width = 15)
    @ApiModelProperty(value = "外键")
    private java.lang.String foreignKey;
	/**索引*/
	@Excel(name = "索引", width = 15)
    @ApiModelProperty(value = "索引")
    private java.lang.String tbIndex;
	/**表名称*/
	@Excel(name = "表名称", width = 15)
    @ApiModelProperty(value = "表名称")
    private java.lang.String tbName;
	/**生成表SQL*/
	@Excel(name = "生成表SQL", width = 15)
    @ApiModelProperty(value = "生成表SQL")
    private java.lang.String tbSql;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**字段是否必填*/
	@Excel(name = "字段是否必填", width = 15)
    @ApiModelProperty(value = "字段是否必填")
    private java.lang.String fieldIsNull;
	/**字典ID*/
	@Excel(name = "字典ID", width = 15)
    @ApiModelProperty(value = "字典ID")
    private java.lang.String dictId;
}
