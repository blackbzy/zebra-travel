package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.dao.SellerMapper;
import com.itheima.pojo.Seller;
import com.itheima.service.SellerService;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements SellerService {
}
