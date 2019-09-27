package com.cp.service.impl;

import com.cp.mapper.AddressMapper;
import com.cp.pojo.Address;
import com.cp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * cp 2019-09-12  10:09
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper mapper;

    @Override
    public List<Address> findAddressByUid(Integer uid) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);
        List<Address> list = mapper.findAddressByUid(uid);
//        sqlSession.close();
        return list;
    }

    @Override
    public void addAddress(Address address) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);
        mapper.addAddress(address);
//        sqlSession.commit();
//        sqlSession.close();
    }

    @Override
    public void updateDefaultAdd(Integer id, Integer aid) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);
        mapper.updateDefaultAdd(id,aid);
//        sqlSession.commit();
//        sqlSession.close();
    }

    @Override
    public void deleteAddressById(Integer aid) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);
        mapper.deleteAddressById(aid);
//        sqlSession.commit();
//        sqlSession.close();
    }

    @Override
    public void updateAddress(Address address) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        AddressMapper mapper = sqlSession.getMapper(AddressMapper.class);
        mapper.updateAddress(address);
//        sqlSession.commit();
//        sqlSession.close();
    }
}
