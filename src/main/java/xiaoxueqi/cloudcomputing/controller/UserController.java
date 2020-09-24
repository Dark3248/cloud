package xiaoxueqi.cloudcomputing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xiaoxueqi.cloudcomputing.dto.LoginResponse;
import xiaoxueqi.cloudcomputing.dto.RegisterResponse;
import xiaoxueqi.cloudcomputing.entity.User;
import xiaoxueqi.cloudcomputing.service.ContainerService;
import xiaoxueqi.cloudcomputing.service.UserService;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ContainerService containerService;

    /**
     * 登录接口
     * @param username 登录用户名
     * @param password 登录密码
     * @return (code=200,id,type,username) 成功, code=400 失败
     */
    @PostMapping("/login")
    public LoginResponse login(@RequestParam String username,
                               @RequestParam String password) {
        User user = userService.login(username, password);
        LoginResponse response = new LoginResponse();
        if(user == null) {
            response.setCode(400);
        } else {
            response.setCode(200);
            response.setType(user.getType());
            response.setUsername(user.getUsername());
            response.setId(Integer.parseInt(user.getId()));
        }
        return response;
    }

    /**
     * 注册接口
     * @param username 注册用户名
     * @param password 注册密码
     * @return code=200 成功, code=400 失败
     */
    @PostMapping("/register")
    public RegisterResponse register(@RequestParam String username,
                                     @RequestParam String password) {
        RegisterResponse response = new RegisterResponse();
        if(userService.register(username, password)) {
            response.setCode(200);
        } else {
            response.setCode(400);
        }
        return response;
    }

    /**
     * 通过容器id获取用户名
     *
     * @param cid 容器id
     * @return 用户名
     */
    @GetMapping("/user/getUsername/{cid}")
    public String getUsernameByCid(@PathVariable String cid) {
        return containerService.getUsernameByCid(cid);
    }

    @PostMapping("/user/changePassword")
    public String changePassword(@RequestParam int id,
                               @RequestParam String oldPassword,
                               @RequestParam String newPassword) {

        if(!userService.judge(id, oldPassword)) {
            return "wrong password";
        } else {
            if(userService.changePassword(id, newPassword))
                return "success";
            else
                return "fail";
        }
    }

}
