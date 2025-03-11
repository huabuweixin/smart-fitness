package com.ruoyi.common.core.domain.model;

/**
 * 用户注册对象
 * 
 * @author ruoyi
 */
public class RegisterBody extends LoginBody
{
    private  String userType;
    public String getUserType(){return userType;}
    public void setUserType(String userType){
        this.userType=userType;
    }


}
