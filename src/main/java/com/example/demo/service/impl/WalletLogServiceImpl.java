package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.WalletLog;
import com.example.demo.service.WalletLogService;
import com.example.demo.mapper.WalletLogMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  钱包流水日志业务层实现类
 */
@Service
public class WalletLogServiceImpl extends ServiceImpl<WalletLogMapper, WalletLog>
    implements WalletLogService{

    @Override
    public List<WalletLog> findByUserIdOrderByCreatedTimeDesc(int userId) {
        LambdaQueryWrapper<WalletLog> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(WalletLog::getUserId,userId)
                .orderByDesc(WalletLog::getCreatedTime);
        return baseMapper.selectList(queryWrapper);
    }
}




