package com.swp.ncloud.common.core.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.swp.ncloud.common.core.exception.BaseException;
import com.swp.ncloud.common.core.exception.ErrorType;
import com.swp.ncloud.common.core.exception.SystemErrorType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@ApiModel("请求结果封装类")
@Getter
public class Result<T> {

    public static final String SUCCESS_CODE = "000000";
    public static final String SUCCESS_MESG = "请求成功";

    /**
     * 结果编码
     */
    @ApiModelProperty("请求结果编码")
    private String code;
    /**
     * 结果信息
     */
    @ApiModelProperty("请求结果信息")
    private String mesg;
    /**
     * 返回时间
     */
    @ApiModelProperty("请求时间")
    private String time;
    /**
     * 返回数据
     */
    @ApiModelProperty("请求返回数据")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    Result(){
        this.time = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    Result(ErrorType errorType){
        this.code = errorType.getCode();
        this.mesg = errorType.getMesg();
        this.time = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    Result(ErrorType errorType, T data) {
        this(errorType);
        this.data = data;
    }

    Result(String code, String  mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
        this.time = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 请求成功并返回结果
     * @param data
     * @return
     */
    public static Result success(Object data){
        return new Result(SUCCESS_CODE, SUCCESS_MESG, data);
    }

    /**
     * 请求成功不返回结果
     * @return
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 系统异常
     * @return
     */
    public static Result fail(){
        return new Result(SystemErrorType.SYSTEM_ERROR);
    }

    /**
     * 系统异常并返回数据
     * @return
     */
    public static Result fail(Object data){
        return new Result(SystemErrorType.SYSTEM_ERROR, data);
    }

    /**
     * 请求出错
     * @param errorType
     * @return
     */
    public static Result fail(ErrorType errorType) {
        return Result.fail(errorType, null);
    }

    /**
     * 请求出错并返回数据
     * @param errorType
     * @param data
     * @return
     */
    public static Result fail(ErrorType errorType, Object data){
        return new Result(errorType, data);
    }

    /**
     * 请求异常
     * @param exception
     * @return
     */
    public static Result fail(BaseException exception) {
        return Result.fail(exception, null);
    }

    /**
     * 请求异常并返回数据
     * @param exception
     * @param data
     * @return
     */
    public static Result fail(BaseException exception, Object data) {
        return new Result(exception.getErrorType(), data);
    }

    /**
     * 是否成功
     * @return
     */
    @JsonIgnore
    public Boolean isSuccess(){
        return SUCCESS_CODE.equals(this.code);
    }

    /**
     * 是否失败
     * @return
     */
    @JsonIgnore
    public Boolean isFail(){
        return !isSuccess();
    }

}
