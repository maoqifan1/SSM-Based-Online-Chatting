package com.mao.controller;

import com.alibaba.fastjson.JSON;
import com.mao.model.UserEntity;
import com.mao.pojo.VUsersConversionsPojo;
import com.mao.service.ConversionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/conversion")
public class ConversionsController {
    @Autowired
    ConversionServices conversionServices;

    /**
     * @param tUserId 表示会话对象的用户id
     * @param session 表示会话对象
     * @return 返回JSON格式的提示字符串
     * */
    @RequestMapping("/setConversionData")
    @ResponseBody
    public String setConversionData(@RequestParam(required = false) String tUserId,
                                    HttpSession session){

       UserEntity userEntity = (UserEntity)session.getAttribute("user");

       // 获得当前用户的id
       int fUserIdNum = userEntity.getUserid();

       // 转换会话对象id的类型
        int tUserIdNum = Integer.parseInt(tUserId);

        if (tUserIdNum == 0){
            return JSON.toJSONString("emptyId");
        }

        // 将值映射到map中
        Map<String,Integer> conversionMap = new HashMap<>();
        conversionMap.put("fUserId",fUserIdNum);
        conversionMap.put("tUserId",tUserIdNum);

        Boolean result = conversionServices.insertConversionData(conversionMap);

        // 当result为true时，则表示插入数据库成功，返回表示成功的提示字符串
        // 当result 为 false时，则表示插入数据库失败，返回表示失败的提示字符串
        return result?JSON.toJSONString("success"):JSON.toJSONString("error");

    }

    @RequestMapping("/getConversionData")
    @ResponseBody
    @SuppressWarnings("all")
    public String getConversionData(HttpSession session){

        // 从 session中获取conversionData
        List<VUsersConversionsPojo> userConversionList = null;
        userConversionList = (List<VUsersConversionsPojo>) session.getAttribute("conversionData");
        // 如果session中没有conversionData,则从数据库中查询
        if (userConversionList == null){
            // 获取session中的用户实体对象
            UserEntity userEntity = (UserEntity) session.getAttribute("user");
            // 获取当前用户的id
            int userId = userEntity.getUserid();
            // 通过当前用户的id从数据库中获取与当前用户有关的会话的集合
            userConversionList = conversionServices.getConversionDataById(userId);
            // 如果集合为空，则表示没有与当前用户有关的会话，返回相应提示
            if(userConversionList == null){
                return JSON.toJSONString("noSuchConversion");
            }else{
                // 将userConversionList存储在session中
                session.setAttribute("conversionData",userConversionList);
                return JSON.toJSONString(userConversionList);
            }
            // 如果session中有conversionData 则直接使用
        }else{
            return JSON.toJSONString(userConversionList);
        }
    }
}
