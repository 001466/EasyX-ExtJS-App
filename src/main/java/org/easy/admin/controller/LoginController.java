package org.easy.admin.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.easy.common.ApplicationAttribute;
import org.easy.common.BaseController;
import org.easy.tool.support.CacheMap;
import org.easy.tool.web.R;
import org.easy.user.entity.User;
import org.easy.user.service.IUserService;
import org.easy.user.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author winall
 */
@RestController
@RequestMapping(path = {"/auth"})
@Api(tags = "认证管理", position = 0)
public class LoginController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    IUserService userService;

    @GetMapping(path = {"/login"})
    @ApiIgnore
    public R<UserVO> login2(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
        return login1(username,password,request,response);
    }


    @PostMapping(path = {"/login"})
    @ApiOperation(value = "用户登陆", notes = "用户登陆")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name="username",value="username",required=true,paramType="form"),
                    @ApiImplicitParam(name="password",value="password",required=true,paramType="form")
            }
    )
    public R<UserVO> login1(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {

        User user=userService.valid(username,password);
        if(user==null){
            if(request.getMethod().equals("OPTIONS")){
                response.setStatus(HttpServletResponse.SC_OK);
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
            return R.fail();
        }else{
            String token=new StringBuilder().append("AID.").append(System.currentTimeMillis()).append(".").append(System.currentTimeMillis()).toString();   //String.valueOf(IDGen.next()+"."+ System.currentTimeMillis());
            UserVO vo=new UserVO(user.getAccount(),token);
            vo.setId(user.getId());
            request.getSession().setAttribute(token,vo);
            return  R.success(vo);
        }
    }


    @GetMapping(path = {"/logout"})
    @ApiIgnore
    public R<UserVO> logout1(@RequestHeader String Authorization, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(Authorization);
        return  R.success();
    }

    @PostMapping(path = {"/logout"})
    @ApiIgnore
    public R<UserVO> logout2(@RequestHeader String Authorization, HttpServletRequest request, HttpServletResponse response) {
        return logout1(Authorization,request,response);
    }



}
