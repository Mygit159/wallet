package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Wallet;
import com.example.demo.service.WalletService;
import com.example.demo.mapper.WalletMapper;
import org.springframework.stereotype.Service;

/**
 * 钱包业务层实现类
 */
@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet>
    implements WalletService{

}




