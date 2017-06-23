package com.xy;

import com.xy.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiayun on 2017/6/1.
 */
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public String getList(){
        return userDao.getList();
    }
}
