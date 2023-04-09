package org.jeecg.modules.fieldmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.modules.aspect.CO00001;
import org.jeecg.modules.aspect.Idict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 字段管理
 * @Author: jeecg-boot
 * @Date:   2023-03-23
 * @Version: V1.0
 */
@Data
@TableName("tb_field_manager")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tb_field_manager对象", description="字段管理")
public class TbFieldManager implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**字段名称*/
	@Excel(name = "字段名称", width = 15)
    @ApiModelProperty(value = "字段名称")
    private String fieldName;
	/**字段是否是字典*/
	@Excel(name = "字段是否是字典", width = 15, dicCode = "is_dict")
    @Idict(code = CO00001.class)
    @Dict(dicCode = "is_dict")
    @ApiModelProperty(value = "字段是否是字典")
    private String fieldIsDictFlag;
	/**字段中文名*/
	@Excel(name = "字段中文名", width = 15)
    @ApiModelProperty(value = "字段中文名")
    private String fieldNameCn;
	/**字段id对应数据字典的code值*/
	@Excel(name = "字段id", width = 15)
    @ApiModelProperty(value = "字段id")
    private String fieldId;
	/**字段长度*/
	@Excel(name = "字段长度", width = 15)
    @ApiModelProperty(value = "字段长度")
    @Length(max = 3)
    private String fieldLength;
}
