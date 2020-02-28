package com.mygo.fly.module.sys.service;

import com.mygo.fly.module.sys.model.SysUser;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Sets;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author zwj * @since 1.0
 */
@Service
public class SysUserService {

    private static List<SysUser> userList = Lists.newArrayList();

    static {
        userList.add(new SysUser("zwj", "123456"));
        userList.add(new SysUser("admin", "123456"));
    }

    public SysUser findByName(String name) {
        return userList.stream().filter(u -> name.equals(u.getUsername())).findFirst().orElse(null);
    }

    public Set<String> queryPermissionNames(String name) {
        Set<String> permissions = Sets.newHashSet();

        permissions.add("Home");
        permissions.add("Dashbord");
        permissions.add("Personal");
        permissions.add("Personal-index");
        permissions.add("Table");
        permissions.add("BaseTable");
        permissions.add("ComplexTable");
        return permissions;
    }
}
