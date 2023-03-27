package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.entity.Wallet;
import com.example.demo.entity.WalletLog;
import com.example.demo.service.WalletLogService;
import com.example.demo.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
public class WalletController {
    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletLogService walletLogService;

    // 查询用户钱包余额
    @PostMapping("/getWalletBalance/{userId}")
    public BigDecimal getWalletBalance(@PathVariable int userId) {
        Wallet wallet = walletService.getById(userId);
        if (wallet == null) {
            throw new RuntimeException("用户钱包不存在");
        }
        return wallet.getBalance();
    }

    // 用户消费100元的接口
    @PostMapping("/consume/{userId}/{amount}")
    public void consume(@PathVariable int userId, @PathVariable BigDecimal amount) {
        Wallet wallet = walletService.getById(userId);
        if (wallet == null) {
            throw new RuntimeException("用户钱包不存在");
        }
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("用户钱包余额不足");
        }
        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletService.updateById(wallet);

//        记录钱包流水日志存入数据库
        WalletLog walletLog = new WalletLog();
        walletLog.setUserId(userId);
        walletLog.setType((byte) 2);
        walletLog.setAmount(amount);
        walletLog.setBalance(wallet.getBalance());
        walletLogService.save(walletLog);
    }

    // 用户退款20元接口
    @PostMapping("/refund/{userId}/{amount}")
    public void refund(@PathVariable int userId, @PathVariable BigDecimal amount) {
        Wallet wallet = walletService.getById(userId);
        if (wallet == null) {
            throw new RuntimeException("用户钱包不存在");
        }
        wallet.setBalance(wallet.getBalance().add(amount));
//        更新钱包金额
        walletService.updateById(wallet);
//       记录日志
        WalletLog walletLog = new WalletLog();
        walletLog.setUserId(userId);
        walletLog.setType((byte) 3);
        walletLog.setAmount(amount);
        walletLog.setBalance(wallet.getBalance());
        walletLogService.save(walletLog);
    }


    // 查询用户钱包金额变动明细的接口
    @PostMapping("/getWalletLogs/{userId}")
    public List getWalletLogs(@PathVariable int userId) {
        return walletLogService.findByUserIdOrderByCreatedTimeDesc(userId);
    }

}
