package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 用户钱包金额变动明细表
 * @TableName wallet_log
 */
@TableName(value ="wallet_log")
@Data
public class WalletLog implements Serializable {
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 变动类型（1：充值/2：消费/3：退款/4：提现）
     */
    private Byte type;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 钱包余额
     */
    private BigDecimal balance;

    /**
     * 创建时间
     */
    private Date createdTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}