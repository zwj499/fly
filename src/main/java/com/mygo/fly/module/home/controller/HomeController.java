package com.mygo.fly.module.home.controller;

import com.mygo.fly.base.response.JsonResponse;
import com.mygo.fly.module.home.service.Info;
import com.mygo.fly.module.sys.model.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author zwj * @since 1.0
 */
@RestController
public class HomeController {

    @PostMapping("/login")
    public JsonResponse<String> login(SysUser user) {
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(),
                user.getPassword()
        );
        try {
            subject.login(usernamePasswordToken);

        } catch (UnknownAccountException e) {
            return new JsonResponse<>(JsonResponse.ERROR_CODE,JsonResponse.ERROR_MESSAGE, "无效用户名");
        } catch (AuthorizationException e) {
            return new JsonResponse<>(JsonResponse.ERROR_CODE,JsonResponse.ERROR_MESSAGE,"授权异常");
        }
        return new JsonResponse<>(JsonResponse.SUCCESS_CODE,JsonResponse.SUCCESS_MESSAGE, "登录成功");
    }

    @GetMapping("/logout")
    public JsonResponse logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return JsonResponse.success();
    }

    @GetMapping("/getInfo")
    public JsonResponse<Info> getInfo() {
        Info info = new Info();
        info.setName("zwj");
        String[] roles = {
                "Home",
                "Dashbord",
                "Driver",
                "Driver-index",
                "Permission",
                "PageUser",
                "Table",
                "BaseTable",
                "ComplexTable",
                "Icons",
                "Icons-index",
                "Components",
                "Sldie-yz",
                "Upload",
                "Carousel",
                "Echarts",
                "Sldie-chart",
                "Dynamic-chart",
                "Map-chart",
                "Excel",
                "Excel-out",
                "Excel-in",
                "Mutiheader-out",
                "Error",
                "Page404",
                "Github",
                "NavTest",
                "Nav1",
                "Nav2",
                "Nav2-1",
                "Nav2-2",
                "Nav2-2-1",
                "Nav2-2-2",
                "*404"};
        info.setRoles(Arrays.asList(roles));
        info.setIntriducec("dd");
        return new JsonResponse<>(JsonResponse.SUCCESS_CODE, JsonResponse.ERROR_MESSAGE, info);
    }

}
