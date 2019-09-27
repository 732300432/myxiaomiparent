package com.cp.service.impl;

import com.cp.mapper.UserMapper;
import com.cp.pojo.User;
import com.cp.service.UserService;
import com.cp.utils.EmailUtils;
import com.cp.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * cp 2019-09-10  15:43
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void register(User user) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        user.setPassword(MD5Utils.md5(user.getPassword()));
        //密码加密
        userMapper.add(user);
        //发送邮件
        EmailUtils.sendEmail(user);
//        sqlSession.commit();
//        sqlSession.close();
    }

    @Override
    public User checkUsername(String username) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findByUserName(username);
//        sqlSession.close();
        return user;
    }

    @Override
    public User login(String username, String password) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //密码加密在比对
        User user = userMapper.findByUserNameAndPassword(username, MD5Utils.md5(password));
//        sqlSession.close();
        return user;
    }

    @Override
    public List<User> findAllUser() {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.findAll();
//        sqlSession.close();
        return users;
    }


    @Override
    public void deleteUserById(int id) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.deleteById(id);
//        sqlSession.commit();
//        sqlSession.close();
    }

    @Override
    public List<User> findUserByUsernameAndGender(String username, String gender) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.findUserByUsernameAndGender(username, gender);
//        sqlSession.close();
        return list;
    }
}
