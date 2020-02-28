package com.mygo.fly.module.sys.service;

import com.mygo.fly.module.sys.model.SysRole;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zwj * @since 1.0
 */
@Service
public class SysRoleService {

    public List<SysRole> findByUsername(String username) {
        if (username.equals("zwj")) {
            return Lists.newArrayList(new SysRole("admin"));
        }
        return Lists.newArrayList();
    }

}
