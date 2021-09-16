package com.du.controller;

import com.du.bean.AdminInfo;
import com.du.bean.Dog;
import com.du.bean.Lover;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/admin")
public class AdminController {
    //注册成功后，如果是单体项目---就要跳转到登陆页面，这个跳转后台的转发，重定向，总之是
    //后台负责跳转，携带数据的跳转页面的
    //如果是 新型的项目，即前后端分离的，那么后台 只负责 返回给前端json数据，
    //跳转是前端来处理的，前端根据后台返回的 code代码，进行跳转
    //如果前端负责跳转，他会起一个好听的名字，叫做 路由！

    //什么是前后端分离 即：项目上的分离 和 数据上的分离
    //项目上的分离，前端一个项目，后台一个项目 2个项目，他们的认证是 token/jwt + redis
    //数据上的分离：还是一个项目，只不过前后端用 json 来交互数据
    //很少在用 jstl/el 表达式 来写项目，他们的认证 是 session

    //layui 在ssm/boot 框架的使用，其实是数据上的分离，也可以项目上的分类。
    //那么他就是json交互的，那么 后台只需要给他 返回json数据就可以了。

    //以前 在servlet中，resp.getWriter().print (new JSONObject.toString(map))。输出json
    //现在，我们用mvc框架的，高级了...
    //adminName:423424 //以前参数 req.getParamter("adminName")
    //adminPwd:234324
    //adminPwdR:234324
    //第一种收取参数方式：数据类型接收参数！

    @RequestMapping("/reg")//layui版本的
    @ResponseBody //这个注解就是 返回给前端的json数据！！
    public Map reg(String adminName, String adminPwd, String adminPwdR, String adminTime) {
        System.out.println("adminPwdR = " + adminPwdR);
        System.out.println("adminTime = " + adminTime);
        Map codeMap = new HashMap();
        if (!adminPwd.equals(adminPwdR)) {
            codeMap.put("code", "4401");
            codeMap.put("msg", "你输入的密码和重复密码不一致，注册失败");
            return codeMap;
        }
        if (adminName.length() <= 0) {
            codeMap.put("code", "4202");
            codeMap.put("msg", "adminName表单写完整");
            return codeMap;
        }
        if (adminPwd.length() <= 0) {
            codeMap.put("code", "4202");
            codeMap.put("msg", "adminPwd表单写完整");
            return codeMap;
        }
//        if (adminPwdR.length() <=0) {
//            codeMap.put("code", "4202");
//            codeMap.put("msg", "adminPwdR表单写完整");
//            return codeMap;
//        }
        codeMap.put("code", "0");
        codeMap.put("msg", "注册成功");
        return codeMap;

    }

    //传统版本的 不返回json，跳转使用转发或者重定向
    @RequestMapping("/regForm")
    public String regForm(String adminName, String adminPwd) {
        System.out.println("adminName = " + adminName);
        System.out.println("adminPwd = " + adminPwd);

        //注册成功跳转到登陆页
        return "loginForm";
    }

    //第二种接收参数的方式，用的叫做 实体类 收参数
    @RequestMapping("/regByBean")
    @ResponseBody
    public Map regByBean(AdminInfo adminInfo) {
        System.out.println("adminInfo = " + adminInfo);
        Map codeMap = new HashMap();
        codeMap.put("code", 0);
        codeMap.put("msg", "注册成功");
        return codeMap;
    }

    //ajax接收 数据/集合
    @RequestMapping("/ajax03") // /api/admin/ajax03
    @ResponseBody
    public Map ajax03(@RequestParam("ids[]") List<Integer> ids) { //前端 ids[] 后台ids
                                   //当前后端 参数不一样的时候 那么就需要使用注解
        for (Integer id : ids) {
            System.out.println("id = " + id);
        }
        Map codeMap = new HashMap();
        codeMap.put("code", 0);
        codeMap.put("msg", "请求访问成功");
        codeMap.put("data", ids);
        return codeMap;
    }
    //ajax 接收JSON对象
    @RequestMapping("/ajax04")
    @ResponseBody
    public Map ajax04(@RequestBody AdminInfo adminInfo){ //@RequestBody 注解就是指的 前端用json请求
        System.out.println("adminInfo = " + adminInfo);
        Map codeMap = new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","请求访问成功");
        codeMap.put("data",adminInfo);
        return  codeMap;
    }
    @RequestMapping("/ajax05")
    @ResponseBody
    public Map ajax05(@ModelAttribute Lover lover, @ModelAttribute Dog dog){
        Map codemap = new HashMap();
        codemap.put("code",0);
        codemap.put("msg","请求访问成功！");
        codemap.put("data","");
        return codemap;
    }
    //ajax06 json 收取多个对象
    @GetMapping("/ajax06")
    @ResponseBody  //@GetMapping 和 @RequestBody 同时使用，等着被开除，不可以同时使用
    public Map ajax06(@RequestBody List<Lover> loverList){ //@RequestBody 他是方法体中拿取的数据的，所以不能用GET请求！！
        for (Lover lover : loverList){
            System.out.println("lover = " + lover);
        }
        Map codeMap = new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","请求访问成功！");
        codeMap.put("data",loverList);
        return codeMap;
    }

    @RequestMapping("/ajax07")
    @ResponseBody  //十分常用，还记得我们servlet 多表的动态参数，就是用的map 07能搞定一切
    public Map ajax07(@RequestBody Map map){
        System.out.println("map的adminName = " + map.get("adminName"));
        Map codeMap = new HashMap();
        codeMap.put("code",0);
        codeMap.put("msg","请求访问成功！");
        codeMap.put("data",map);
        return codeMap;
    }

    @RequestMapping("/ajax08")
    @ResponseBody   //很常用 动态的分页查询
    public Map ajax08(Lover lover, @RequestParam( value = "limit", required = false,defaultValue = "5") Integer pageSize,Integer page){
        System.out.println("lover = " + lover);
        System.out.println("page = " + page);
        System.out.println("pageSize = " + pageSize);
        return null;
    }

    //前端传过来多个对象 需要根据 请求的前缀 进行绑定
    @InitBinder("lover")
    public void binding01(WebDataBinder webDataBinder){ //WebDataBinder 网络数据的绑定 也就是前端传过来的数据
        webDataBinder.setFieldDefaultPrefix("lover");
    }
    @InitBinder("dog")
    public void binding02(WebDataBinder webDataBinder){ //WebDataBinder 网络数据的绑定 也就是前端传过来的数据
        webDataBinder.setFieldDefaultPrefix("dog");
    }

    //以上是 前后端分离 最新项目用到的知识点，那么也有 传统项目，后台负责跳到另一个界面

    //第一种 springMVC的传值方式！ 原始方式：request + session + request转发
    //传统的mvc的方法（不返回json数据，不使用@ResponseBody）,他要跳转jsp的方式1，返回值是String
    //页面传值： 即： 四大作用域 request session applation page..
    @RequestMapping("/yuansheng") //什么页面传值 登陆页(admin ， 123456) ---》yuansheng() ----
    public String yuansheng(HttpSession session, HttpServletRequest request){ // /api/admin/yuansheng
        System.out.println("原生方式 页面传值");
        //System.out.println("adminInfo = " + adminInfo);
        //登录如果 验证成功，那么就需要把 登录信息 放入到session域当中
        //session.setAttribute("adminInfo",adminInfo);
       String adminName = request.getParameter("adminName");
       String adminPwd = request.getParameter("adminPwd");
        request.setAttribute("adminName",adminName);
        request.setAttribute("adminPwd",adminPwd);
        //request.getRequestDispatcher("home.jsp").forward(request,response); //servlet 的转发
        //return "home"; //这个 和 PagesController 里的 查找 jsp 的方式没联系
        //return  "forward:/WEB-INF/pages/home.jsp"; // springMVC中的转发
        //return  "forward:/pages/home"; // springMVC中的转发
        //重定向，servlet response.sendDirect("/ww.baidu.com")
        //return "redirect:https://www.baidu.com"; // 不带/ 和带 / 的区别
        return "redirect:/https://www.baidu.com"; // 不带/ 和带 / 的区别  http://localhost:8080/https://www.baidu.com
    }

    //第二种 springMVC的传值方式！  modelAndView
    @RequestMapping("/modelAndView")
    public ModelAndView modelAndView(AdminInfo adminInfo){
        //modele和 视图 通俗讲就是 数据和显示 , ModelAndView 可以代替 转发功能，更强大了！
        ModelAndView mv = new ModelAndView();
        mv.addObject("adminName",adminInfo.getAdminName());
        mv.addObject("adminPwd",adminInfo.getAdminPwd());
        System.out.println("以上model的绑定，即数据的绑定");
        mv.setViewName("home");
        return mv;
    }

    //第三种 springMVC的传值方式！ model 谢经理很喜欢，因为代码少
    @RequestMapping("/model")
    public String model(AdminInfo adminInfo, Model model){
        model.addAttribute("adminName",adminInfo.getAdminName());
        model.addAttribute("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }

    //第四种 springMVC的传值方式！ modelMap
    @RequestMapping("/modelMap")
    public String modelMap(AdminInfo adminInfo, ModelMap modelMap){
        modelMap.put("adminName",adminInfo.getAdminName());
        modelMap.put("adminPwd",adminInfo.getAdminPwd());
        return "home";

    }

    //第五种 springMVC的传值方式！ map 灵活
    @RequestMapping("/map")
    public String map(AdminInfo adminInfo,Map<String,Object> map){
        map.put("adminName",adminInfo.getAdminName());
        map.put("adminPwd",adminInfo.getAdminPwd());
        return "home";
    }
}

