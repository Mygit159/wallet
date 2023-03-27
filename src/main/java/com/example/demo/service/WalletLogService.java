package com.example.demo.service;

import com.example.demo.entity.WalletLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 钱包流水日志业务接口
 */
public interface WalletLogService extends IService<WalletLog> {

    List<WalletLog> findByUserIdOrderByCreatedTimeDesc(int userId);
}
