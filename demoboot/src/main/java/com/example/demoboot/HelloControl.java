package com.example.demoboot;

import com.example.demoboot.entity.*;
import com.example.demoboot.service.Categoryservice;
import com.example.demoboot.service.vote_Infoservice;
import com.example.demoboot.service.vote_Recordservice;
import com.example.demoboot.service.vote_Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloControl {
    @Autowired
    private Categoryservice categoryservice;
    @Autowired
    private vote_Recordservice vote_recordservice;
    @Autowired
    private vote_Userservice vote_userservice;
    @Autowired
    private vote_Infoservice infoservice;

    @RequestMapping("/index")
    public String index(HttpSession session,Model model) {
        session.setAttribute("msg", "");
        List<vote_Info> vote_infos=infoservice.findallsort();
        List<vote_Info> vote_infoList=vote_infos.subList(0,5);
        vote_infos=vote_infos.subList(0,4);
        model.addAttribute("vote_infos",vote_infos);
        model.addAttribute("vote_infolist",vote_infoList);
        return "index";
    }

    @RequestMapping("/")
    public String index2(HttpSession session,Model model){
        session.setAttribute("uinfo",1);
        session.setAttribute("msg", "");
        session.setAttribute("ms", null);
        List<vote_Info> vote_infos=infoservice.findallsort();
        List<vote_Info> vote_infoList=vote_infos.subList(0,5);
        vote_infos=vote_infos.subList(0,4);
        model.addAttribute("vote_infos",vote_infos);
        model.addAttribute("vote_infolist",vote_infoList);
        return "forward:index";
    }

//    @RequestMapping("/")
//    public String index2(HttpSession session) {
//        return "votepro";
//    }

    @RequestMapping("/dengru")
    public String dengru(HttpSession session) {
        session.setAttribute("msg", "");
        //session.setAttribute("newmsg", 0);
        return "dengru";
    }

    @RequestMapping("/register")
    public String register(HttpSession session) {
        session.setAttribute("msg", "");
        return "register";
    }

    @RequestMapping("/lianxi")
    public String index5() {
        return "lianxi";
    }

    @RequestMapping("/tuijian")
    public String index6(Model model) {
        List<vote_Info> vote_infos=infoservice.findallsort();
        vote_infos=vote_infos.subList(0,8);
        model.addAttribute("vote_infos",vote_infos);
        return "tuijian";
    }

    @RequestMapping("/toupiao")
    public String index8(HttpSession session,int pid) {
        try{
            vote_User vote_user= (vote_User) session.getAttribute("ms");
            session.setAttribute("number",vote_user.getNumber());
            List<vote_Record> vote_record=vote_recordservice.findbynum((int)session.getAttribute("number"));
            for(vote_Record record : vote_record){
                if(record.getVote_info().getPid()==pid){
                    session.setAttribute("piao",true);
                    break;
                }else {
                    session.setAttribute("piao",false);
                }
            }
        }
        catch (Exception e)
        {
            session.setAttribute("piao",false);
        }
        vote_Info vote_info=infoservice.findBypid(pid);
        session.setAttribute("oneinfo",vote_info);
        return "toupiao";
    }


    @RequestMapping("/about")
    public String index7() {
        return "about";
    }

//    @RequestMapping("oneuser")
//    public String index0(HttpSession session,int number) {
////        session.setAttribute("ms",session.getAttribute("ms"));
////        vote_User user=(vote_User) session.getAttribute("ms");
////        int number=user.getNumber();
//        vote_User user=vote_userservice.findBynum(number);
//        session.setAttribute("oneuser",user);
//        return "oneuser";
//    }

    @RequestMapping("onexinxi")
    public String indexx(Model model,int number) {
//        session.setAttribute("ms",session.getAttribute("ms"));
//        vote_User user=(vote_User) session.getAttribute("ms");
//        int number=user.getNumber();
        vote_User user=vote_userservice.findBynum(number);
        model.addAttribute("oneuser",user);
        return "onexinxi";
    }

    @RequestMapping("lookuser")
    public String indexl(Model model,int number) {
        vote_User user=vote_userservice.findBynum(number);
        model.addAttribute("ms",user);
        return "oneuser";
    }

    @RequestMapping("nopassword")
    public String indexpass(HttpSession session) {
        session.setAttribute("ms",session.getAttribute("ms"));
        vote_User user=(vote_User) session.getAttribute("ms");
        int number=user.getNumber();
        user=vote_userservice.findBynum(number);
        session.setAttribute("oneuser",user);
        session.setAttribute("msgpassword",1);
        return "nopassword";
    }

    @RequestMapping("xitong")
    public String index1(HttpSession session) {
        session.setAttribute("ms",session.getAttribute("ms"));
        try{
            vote_User user=(vote_User) session.getAttribute("ms");
            vote_userservice.login(user.getNumber());
            if (user.getRole()==0){
                return "userxitong";
            }
        }
        catch (Exception e){
            session.setAttribute("msg","请先登入！");
            return "index";
        }
        List<Category> category=categoryservice.findall();
        session.setAttribute("categories",category);
        return "xitong";
    }


    @RequestMapping("home")
    public String index2() {
        return "home";
    }

    @RequestMapping("votepro")
    public String index3(Model model) {
        List<vote_Info> vote_infoList=infoservice.findall();
        model.addAttribute("vote_infos",vote_infoList);
        return "votepro";
    }

    @RequestMapping("toupiaopro")
    public String indext(Model model,HttpSession session) {
        session.setAttribute("ms",session.getAttribute("ms"));
        vote_User vote_user= (vote_User) session.getAttribute("ms");
        List<vote_Record> vote_records=vote_recordservice.findbynum(vote_user.getNumber());
        List<vote_Info> vote_infoList =new  ArrayList<>();
        for(vote_Record vote_record:vote_records){
            vote_infoList.add(infoservice.findBypid(vote_record.getVote_info().getPid()));
        }
        model.addAttribute("tvote_infos",vote_infoList);
        return "toupiaopro";
    }

    @RequestMapping("notoupiaopro")
    public String indexnt(Model model,HttpSession session) {
        session.setAttribute("ms",session.getAttribute("ms"));
        vote_User vote_user= (vote_User) session.getAttribute("ms");
        List<vote_Record> vote_records=vote_recordservice.findbynum(vote_user.getNumber());
        List<vote_Info> vote_infoList=infoservice.findall();
        List<vote_Info> vote_infos=new ArrayList<>();
        vote_infos.addAll(vote_infoList);
            for(vote_Info info:vote_infos){
                for(vote_Record vote_record:vote_records){
                    int a=info.getPid();
                    int b=vote_record.getVote_info().getPid();
                if(a==b){
                    vote_infoList.remove(info);
                    break;
                }
            }
        }
        model.addAttribute("tvote_infos",vote_infoList);
        return "notoupiaopro";
    }

    @RequestMapping("userpro")
    public String indexu(Model model) {
        List<vote_User> vote_userList=vote_userservice.findall();
        model.addAttribute("vote_users",vote_userList);
        return "userpro";
    }

    @RequestMapping("shouquan")
    public String indexs(Model model,int number) {
        model.addAttribute("number",number);
        return "shouquan";
    }


    @RequestMapping("lookcategory")
    public String indexlc(Model model) {
        List<Category> categoryList = categoryservice.findall();
        List<vote_Info> vote_infos=infoservice.findall();
        List<categorylook> categorylookList=new ArrayList<>();
        for(Category category:categoryList){
            categorylook categorylook=new categorylook();
            categorylook.setCname(category.getCname());
            int count=0;
            for(vote_Info vote_info:vote_infos){
                if(vote_info.getCategory().getCname().equals(category.getCname())){
                    count+=vote_info.getCount();
                }
            }
            categorylook.setCount(count);
            categorylookList.add(categorylook);
        }
        model.addAttribute("clcategory",categorylookList);
        return "lookcategory";
    }

    @RequestMapping("categorypro")
    public String indexc(HttpSession session,int cid) {
        List<vote_Info> vote_infos=infoservice.findall();
        List<vote_Info> vote_infoList=new ArrayList<>();
        for(vote_Info vote_info:vote_infos){
            if(vote_info.getCategory().getCid()==cid){
                vote_infoList.add(vote_info);
            }
        }
        session.setAttribute("vote_infos",vote_infoList);
        return "categorypro";
    }

    @RequestMapping("/addcategory")
    public String indexadd(){
        return "addcategory";
    }


    @RequestMapping("/infosave")
    public String index4(Model model) {
        List<Category> categoryList = categoryservice.findall();
        model.addAttribute("categories", categoryList);
        return "infosave";
    }

    @RequestMapping("/vote_info")
    public String vote_info(Model model) {
        List<Category> categoryList = categoryservice.findall();
        model.addAttribute("categories", categoryList);
        return "vote_info";
    }

    @RequestMapping("/infoupdate")
    public String infoupdate(int pid,Model model){
        List<Category> categoryList = categoryservice.findall();
        model.addAttribute("categories", categoryList);
        vote_Info infolist=infoservice.findBypid(pid);
        model.addAttribute("infos",infolist);
        return "infoupdate";
    }
    @RequestMapping("/cakang")
    public String xiangqing(int pid,Model model) {
        vote_Info infolist = infoservice.findBypid(pid);
        List<vote_Record> vote_records=vote_recordservice.findbypid(pid);

        int []sex=new int[2];
        sex[0]=0;
        sex[1]=0;
        for(vote_Record vote_record:vote_records){
            if(vote_userservice.findBynum(vote_record.getVote_user().getNumber()).getSex().equals("男")){
                sex[0]+=1;
            }else{
                sex[1]+=1;
            }
        }
        model.addAttribute("infos",infolist);
        model.addAttribute("sex",sex);
        return "xiangqing";
    }
}
