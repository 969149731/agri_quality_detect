package com.ruoyi.out.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class returnMsgHandler {
    Map<String,StringBuilder> MsgList;//消息map，以key为其标题即可
    Map<String,String> MsgTitleList;//消息map，以key为其标题即可
    private String frontFix="<p style='margin-left: 25px;'>";//消息前缀，带空格
    private String behindFix="<p/>";//消息前缀，带空格
    private String tab="    ";//制表符
    StringBuilder returnMsg= new StringBuilder();
    //接口方法
    public returnMsgHandler(){
        this.MsgList=new HashMap<>();
        this.MsgTitleList = new HashMap<>();
        this.MsgTitleList.put("信息有误","部分信息有误,请在定量检测导入表中检查下列样本的信息:");
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
    public void addMsg(String Msg){//不使用分类，直接输出信息
        if(returnMsg!=null){
            returnMsg.append(Msg);
        }
    }
    public void turnToStr(){//将结果返回给消息父类的消息容器
        Set<Map.Entry<String, StringBuilder>> entrySet=MsgList.entrySet();
        for (Map.Entry<String, StringBuilder> entryItem:entrySet) {

            if(MsgTitleList.containsKey(entryItem.getKey())){//如果有标题信息，使用标题
                returnMsg.append(MsgTitleList.get(entryItem.getKey()));
            }else {
                returnMsg.append(entryItem.getKey());//以key为标题
            }
            returnMsg.append(entryItem.getValue());//将每一个消息类的内容传进去
        }
    }

    public void addMsgTitle(String MsgType,String Title){
        if (MsgList.containsKey(MsgType)){/*空*/}
        else MsgList.put(MsgType,new StringBuilder());
    }
}
