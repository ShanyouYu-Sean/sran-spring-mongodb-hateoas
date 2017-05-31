package com.hongshen.exception;

/**
 * Created by a7289 on 2017/3/28 0028.
 */
public class SranError {
    public static final String UNKNOWN_ERROR = "未知错误";
    public static final String USER_NOT_FOUND = "用户名不存在";
    public static final String USER_NAME_PASS_WORD_WRONG = "用户名或密码错误";
    public static final String USER_HAS_NO_ROLE_4G = "用户未设定4G权限，请联系管理员为用户配置权限";
    public static final String ROLE_4G_NOT_FOUND = "4G权限名不存在";
    public static final String ROLE_4G_HAS_NO_ROLE_TAC = "4G权限配置为空，请联系管理员检查权限配置";
    public static final String ROLE_4G_HAS_WRONG_ROLE_TAC = "4G权限配置错误，请联系管理员检查权限配置";
    public static final String NO_TAC_4G_FOUND_UNDER_THIS_ROLE_4G = "4G权限配置错误，请联系管理员检查权限配置";
    public static final String NO_NODE_FOUND_UNDER_THIS_TAC = "所查看TAC下不包含任何NODE";
    public static final String NO_CELL_FOUND_UNDER_THIS_NODE = "所查看NODE下不包含任何CELL";
    public static final String NODE_NOT_FOUND = "所查看NODE不存在";
    public static final String WRONG_NODE_UNDER_THIS_TAC = "所查看NODE不属于所选TAC";
    public static final String WRONG_TAC_UNDER_THIS_USER = "您没有此TAC的权限";
    public static final String NO_ALARM_FOUND_UNDER_THIS_NODE = "所查看NODE下不包含任何告警";
    public static final String USER_HAS_NO_ROLE = "用户未设定系统总体权限，请联系管理员为用户配置权限";
}
