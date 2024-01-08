package org.jeecg.modules.tbOfflineMessage.entity;

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
 * @Description: 离线信息表
 * @Author: jeecg-boot
 * @Date: 2024-01-08
 * @Version: V1.0
 */
@Data
@TableName("tb_offline_message")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "tb_offline_message对象", description = "离线信息表")
public class TbOfflineMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
    /**
     * 所属部门
     */
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
    /**
     * 发送者
     */
    @Excel(name = "发送者", width = 15)
    @ApiModelProperty(value = "发送者")
    private java.lang.String sender;
    /**
     * 接收者
     */
    @Excel(name = "接收者", width = 15)
    @ApiModelProperty(value = "接收者")
    private java.lang.String receiver;
    /**
     * 消息主题
     */
    @Excel(name = "消息主题", width = 15)
    @ApiModelProperty(value = "消息主题")
    private java.lang.String messageBody;
    /**
     * 图片id
     */
    @Excel(name = "图片id", width = 15)
    @ApiModelProperty(value = "图片id")
    private java.lang.String picId;
    /**
     * 文件id
     */
    @Excel(name = "文件id", width = 15)
    @ApiModelProperty(value = "文件id")
    private java.lang.String fileId;
}
