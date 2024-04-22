package com.ruoyi.out.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class returnMsgHandler {
    Map<String,StringBuilder> MsgList;//消息map，以key为其标题即可
    private String frontFix="<p style='margin-left: 25px;'>";//消息前缀，带空格
    private String behindFix="<p/>";//消息前缀，带空格
    private String tab="    ";//制表符
    StringBuilder returnMsg= new StringBuilder();
    //接口方法
    public returnMsgHandler(){
        this.MsgList=new HashMap<>();
    }
    public void initReturnMsg(StringBuilder Msg){
        this.MsgList =new HashMap<>();//重置MsgList
        this.returnMsg= Msg;//将上级传来的Msg作为内置Msg
    }
    public void addMsg(String MsgType,String Msg){
        if (MsgList.containsKey(MsgType)){/*空*/}
        else MsgList.put(MsgType,new StringBuilder());
        MsgList.get(MsgType).append(frontFix+tab+Msg+behindFix);
    }
    public void turnToStr(){//将结果返回给消息父类的消息容器
        Set<Map.Entry<String, StringBuilder>> entrySet=MsgList.entrySet();
        for (Map.Entry<String, StringBuilder> entryItem:entrySet) {
            returnMsg.append(entryItem.getKey());//以key为标题
            returnMsg.append(entryItem.getValue());//将每一个消息类的内容传进去
            returnMsg.append(frontFix);//在此用<br/>空一行
        }
    }
}
