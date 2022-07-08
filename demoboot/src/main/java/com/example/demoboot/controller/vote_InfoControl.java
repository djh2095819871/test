package com.example.demoboot.controller;

import com.example.demoboot.entity.vote_Info;
import com.example.demoboot.entity.vote_Record;
import com.example.demoboot.entity.vote_User;
import com.example.demoboot.service.vote_Infoservice;
import com.example.demoboot.service.vote_Recordservice;
import com.example.demoboot.service.vote_Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class vote_InfoControl {
    @Autowired
    private vote_Infoservice vote_infoservice;
    @Autowired
    private vote_Userservice vote_userservice;
    @Autowired
    private vote_Recordservice vote_recordservice;
    @Value("${file.staticAccessPath}")
    private String staticAccessPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @ResponseBody
    @RequestMapping("/pfindall")
    public List<vote_Info> findall() {
        return vote_infoservice.findall();
    }

    @ResponseBody
    @RequestMapping("/pfindBypid")
    public vote_Info findBypid(int pid){
        return vote_infoservice.findBypid(pid);
    }

//    @ResponseBody
    @RequestMapping("/pinsert")
    public String savevote_Info(vote_Info vote_info, MultipartFile uploadfile) throws IOException {
        //定义上传文件的前缀
        String pre = "";
        //保证文件上传后 存到服务器的文件名的唯一性
        pre = UUID.randomUUID()+"";
        //获取文件的后缀名
        String suffix = "";
        if(uploadfile != null){
            //.jpg
            String originalName = uploadfile.getOriginalFilename();
            suffix= originalName.substring(originalName.lastIndexOf("."));
        }
        //文件名
        String fileName = pre+suffix;

        //定义 文件上传的全路径
        String filePath = uploadFolder + "\\" + fileName ;

        //创建file对象
        File f = new File(filePath);
        //目录是否存在，不存在则创建
        if(!f.isDirectory()){
            f.mkdirs();
        }
        //上传文件
        uploadfile.transferTo(f);
        //上传到数据库的路径
        String url ="upload/"+fileName ;
        //设置图片的路径,保存到数据库
        vote_info.setImg(url);

        //时间把当前时间设置过去
        vote_info.setPdate(LocalDateTime.now());
        vote_infoservice.savevote_Info(vote_info);//插入数据
        return "redirect:/votepro";
//        return vote_infoservice.savevote_Info(vote_info);
    }

 //   @ResponseBody
    @RequestMapping("/pdelete")
    public String deletevote_Info(int pid) {
        vote_infoservice.deletevote_Info(pid);
        return "redirect:/votepro";
//        return vote_infoservice.deletevote_Info(pid);
    }

    @RequestMapping("/onepiao")
    public String addone(int pid,HttpSession session) {
        try{
            session.setAttribute("number",session.getAttribute("number"));
            vote_User vote_user=vote_userservice.findBynum((int)session.getAttribute("number"));
            vote_Info vote_info=vote_infoservice.findBypid(pid);
            vote_Record  vote_record=new vote_Record();
            vote_record.setVote_info(vote_info);
            vote_record.setVote_user(vote_user);
            vote_recordservice.savevote_Record(vote_record);
            vote_info.setCount(vote_info.getCount()+1);
            vote_infoservice.updatevote_Info(vote_info);
        }
        catch (Exception e){
            session.setAttribute("msg","请先登入！");
            return "dengru";
        }
        return "redirect:/toupiao?pid="+pid;
    }


    @RequestMapping("/pupdate")
    public String updatevote_Info(int pid,vote_Info vote_info) {
        //时间把当前时间设置过去
        vote_info.setPdate(LocalDateTime.now());
        vote_info.setPid(pid);
        vote_infoservice.updatevote_Info(vote_info);
        return "redirect:/votepro";
    }
}
