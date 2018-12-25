package com.jt.common.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;

@ControllerAdvice
public class GlobleHandleException {
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public JsonResult doHandleRuntimeException(RuntimeException t) {
		t.printStackTrace();
		return new JsonResult(t);
	}
}
