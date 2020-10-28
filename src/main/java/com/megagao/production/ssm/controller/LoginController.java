package com.megagao.production.ssm.controller;

import static com.megagao.production.ssm.common.Constants.VALIDATE_CODE;

import java.net.InetAddress;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.domain.LoginLog;
import com.megagao.production.ssm.domain.authority.SysUser;
import com.megagao.production.ssm.service.LoginLogService;
import com.megagao.production.ssm.service.UserService;
import com.megagao.production.ssm.util.CollectionsFactory;

/**
 * created on 2016年9月6日
 *
 * @author megagao
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	LoginLogService loginLogService;

	/**
	 * shiro ajax登录
	 */
	@RequestMapping(value = "/Login")
	@ResponseBody
	public Map<String, Object> ajaxLogin(@RequestParam String username,
			@RequestParam String password,
			@RequestParam(required = false) String randomcode,
			HttpSession session) throws Exception {

		Map<String, Object> map = CollectionsFactory.newHashMap();

		if (randomcode != null && !randomcode.equals("")) {
			// 取出session的验证码（正确的验证码）
			String validateCode = (String) session.getAttribute(VALIDATE_CODE);
			// 页面中输入的验证和session中的验证进行对比
			if (validateCode != null && !randomcode.equals(validateCode)) {
				// 如果校验失败，将验证码错误失败信息放入map中
				map.put("msg", "randomcode_error");
				// 直接返回，不再校验账号和密码
				return map;
			}
		}
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username,
					password);
			try {
				currentUser.login(token);
			} catch (UnknownAccountException ex) {
				map.put("msg", "account_error");
			} catch (IncorrectCredentialsException ex) {
				map.put("msg", "password_error");
			} catch (AuthenticationException ex) {
				map.put("msg", "authentication_error");
			}
		}

		// new Date()为获取当前系统时间
		Date come_time = new Date(System.currentTimeMillis());

		// 在windows环境: 使用InetAddress.getLocalHost()方法即可 获取本机IP
		InetAddress addr = InetAddress.getLocalHost();
		String ip = addr.getHostAddress();

		// 获取 sysUser 的 user_id
		SysUser sysUser = userService.loadByUserName(username);

		LoginLog loginLog = new LoginLog(come_time, ip, sysUser, username);

		// System.out.println(loginLog);

		// 添加到数据库
		loginLogService.insert(loginLog);
		SysUser sysUser2 = new SysUser(username);
		session.setAttribute("sysUser2", sysUser2);

		// 返回json数据
		return map;
	}
}
