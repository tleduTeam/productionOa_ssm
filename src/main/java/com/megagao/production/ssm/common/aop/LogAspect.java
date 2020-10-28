package com.megagao.production.ssm.common.aop;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.megagao.production.ssm.domain.authority.SysOperationLog;
import com.megagao.production.ssm.domain.authority.SysUser;
import com.megagao.production.ssm.mapper.authority.SysOperationLogMapper;
import com.megagao.production.ssm.util.Log;

/**
 * 日志记录
 * 
 * @author yingfing
 *
 */
public class LogAspect {

	@Autowired
	private SysOperationLogMapper sysOperationLogMapper;

	// 当前时间
	private Date come_time = null;

	private SysUser sysUser = null;

	private String l_desc = null;

	private String username = null;

	private HttpServletRequest request = null;

	/**
	 * @param joinPoint
	 * @Description 气质通知 方法调用前触发 记录开始时间,从session中获取操作人
	 */
	public void before(JoinPoint joinPoint) {
		come_time = new Date(System.currentTimeMillis());
	}

	/**
	 * @param joinPoint
	 * @Description 获取入参方法参数
	 * @return
	 */
	public Map<String, Object> getNameAndValue(JoinPoint joinPoint) {
		Map<String, Object> param = new HashMap<>();
		Object[] paramValues = joinPoint.getArgs();
		String[] paramNames = ((CodeSignature) joinPoint.getSignature())
				.getParameterNames();
		for (int i = 0; i < paramNames.length; i++) {
			if (paramValues[i] instanceof Integer
					|| paramValues[i] instanceof String) {
				param.put(paramNames[i], paramValues[i]);
			}
		}
		return param;
	}

	/**
	 * @param joinPoint
	 * @Description 后置通知 方法调用后触发 记录结束时间 ,操作人 ,入参等
	 */
	public void after(JoinPoint joinPoint) {
		System.out.println(66666);
		request = getHttpServletRequest();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = null;
		try {
			targetClass = Class.forName(targetName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Method[] methods = targetClass.getMethods();
		String title;
		String action;
		Class<?>[] clazzs;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				clazzs = method.getParameterTypes();
				if (clazzs != null && clazzs.length == arguments.length
						&& method.getAnnotation(Log.class) != null) {
					request = getHttpServletRequest();
					SysUser sysUser = (SysUser) request.getSession()
							.getAttribute("sysUser2");
					username = sysUser.getUsername();
					title = method.getAnnotation(Log.class).title();
					action = method.getAnnotation(Log.class).action();
					come_time = new Date(System.currentTimeMillis());
					SysOperationLog sysOperationLog = new SysOperationLog(
							come_time, sysUser, sysUser.getUsername());
					String l_desc = getNameAndValue(joinPoint).toString() + " "
							+ title + action;
					sysOperationLog.setL_desc(l_desc);
					sysOperationLogMapper.writeLog(sysOperationLog);
					System.out.println(5555);
					break;
				}
			}
		}
	}

	/**
	 * @Description: 获取request
	 */
	public HttpServletRequest getHttpServletRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		HttpServletRequest request = sra.getRequest();
		return request;
	}

	/**
	 * @param joinPoint
	 * @return 环绕通知
	 * @throws Throwable
	 */
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		return null;
	}

	/**
	 * @param joinPoint
	 * @Description 异常通知
	 */
	public void throwing(JoinPoint joinPoint) {
		System.out.println("异常通知");
	}

}
