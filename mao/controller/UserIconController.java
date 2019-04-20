package com.mao.controller;

import com.alibaba.fastjson.JSON;
import com.mao.model.UserEntity;
import com.mao.model.UserIconEntity;
import com.mao.service.UserIconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/icon")
public class UserIconController {
    @Autowired
    UserIconService userIconService;

    /**
     * Handling image Upload event
     */
    @RequestMapping(value = "/upLoadIcon")
    @ResponseBody
    @SuppressWarnings("all")
    public String UploadImage(@RequestParam(value = "description", required = false) String description,
                              @RequestParam(value = "myFile", required = false) MultipartFile myFile,
                              HttpSession session, HttpServletRequest request) throws IOException, NullPointerException {
        // 获得当前程序的发布路径下的 /uploadImage/headImage/路径
        String realPath = request.getServletContext().getRealPath("/uploadImage/headImage/");
        System.out.println(realPath);
        // 判断是否为空文件
        if (myFile.isEmpty()) {
            return JSON.toJSONString("emptyUploading");
        }

        // 获取图片的名字
        String imgName = myFile.getOriginalFilename();
        // 图片的正则表达式
        String imgReg = ".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG)$";
        // 编译图片的正则表达式
        Pattern imgPattern = Pattern.compile(imgReg);

        // 获取上传文件扩展名
        String fileExt = imgName.substring(imgName.lastIndexOf(".") + 1, imgName.length());
        // 若图片不符合正则表达式则返回提示字符串
        if(!imgPattern.matcher(fileExt).matches()){
            JSON.toJSONString("wrongFormat");
        }

        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        String fileName = userEntity.getUserid() + "_headImage.jpg";

        // 拼接图片地址为当前的相对路径
        String illustrationPath = "/uploadImage/headImage/" + fileName;

        UserIconEntity userIconEntity = new UserIconEntity();
        userIconEntity.setUserid(userEntity.getUserid());
        userIconEntity.setImagesource(illustrationPath);


        File targetFile = new File(realPath, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //upload
        try {
            // 生成目标文件
            myFile.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //判断是否当前用户是否拥有头像
        Boolean hasIcon = userIconService.whetherHasIcon(userEntity.getUserid());
        // 如果没有头像
        if (!hasIcon){ // 判断头像是否成功插入数据库
            if (!userIconService.insertUserIcon(userIconEntity)) {
                return JSON.toJSONString("upLoadFailing");
            }else
                return JSON.toJSONString("successfullyUpLoading");
        }else{
            //有头像，判断头像路径是否成功插入数据库
            if(!userIconService.updateUserIcon(userIconEntity)){
                return JSON.toJSONString("upLoadFailing");
            }else{
                // 如果更新成功，则重设session中的用户头像
                session.removeAttribute("userIcon");
                session.setAttribute("userIcon", request.getContextPath() + illustrationPath);
                return JSON.toJSONString("successfullyUpLoading");
            }
        }
    }


    @RequestMapping("/showUserIcon")
    @ResponseBody
    public String showUserIconService(HttpSession session, HttpServletRequest request) {
        // 从session中寻找 userIcon的路径
        String userIcon = (String)session.getAttribute("userIcon");
        // 若没有路径，则从数据库中查询
        if (userIcon == null) {
            UserEntity userEntity = (UserEntity) session.getAttribute("user");
            String imageSource = userIconService.getUserIcon(userEntity.getUserid());
            // 若该用户没有设置头像则返回相应的提示字符串
            if(imageSource == null){
                return JSON.toJSONString("noIcon");
            }
            session.setAttribute("userIcon", request.getContextPath() + imageSource);
            return JSON.toJSONString(request.getContextPath() + imageSource);
        }else{
            return JSON.toJSONString(userIcon);
        }
    }
}
