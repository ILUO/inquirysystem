package com.gluxen.util.constants;

/**
 * @author: hxy
 * @date: 2017/10/24 10:16
 */
public enum ErrorEnum {
    /*
    * 错误信息
    * */
    E_400("400", "请求处理异常，请稍后再试"),
    E_500("500", "请求方式有误,请检查 GET/POST"),
    E_501("501", "请求路径不存在"),
    E_502("502", "权限不足"),
    E_10008("10008", "角色删除失败,尚有用户属于此角色"),

    E_20011("20011", "登陆已过期,请重新登陆"),

    E_90003("90003", "缺少必填参数"),
    E_90002("90002","病史更改/添加失败"),
    E_90004("90004","获取病人新id失败"),
    E_90005("90005","新增病人失败"),
    E_90006("90006","新增当日病人失败"),
    E_90007("90007","新增病人主药方失败"),
    E_90008("90008","新增病人检查指标失败"),
    E_90009("90009","新增病人二级药方失败"),
    E_90010("90010","新增病人药方细节失败"),
    E_90011("90011","删除病人家属失败"),
    E_90012("90012","删除病人检查指标失败"),
    E_90013("90013","删除病人病史失败"),
    E_90014("90014","删除药方失败"),
    E_90015("90015","删除小药方失败"),
    E_90016("90016","删除药方细节失败"),
    E_90017("90017","图片添加失败"),
    E_90018("90018","删除图片失败"),
    E_90019("90019","获取Id失败");;

    private String errorCode;

    private String errorMsg;

    ErrorEnum() {
    }

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
