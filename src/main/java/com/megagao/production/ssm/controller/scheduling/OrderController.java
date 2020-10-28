package com.megagao.production.ssm.controller.scheduling;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megagao.production.ssm.domain.COrder;
import com.megagao.production.ssm.domain.authority.SysOperationLog;
import com.megagao.production.ssm.domain.authority.SysUser;
import com.megagao.production.ssm.domain.customize.CustomResult;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.domain.vo.COrderVO;
import com.megagao.production.ssm.service.OperationLogService;
import com.megagao.production.ssm.service.OrderService;
import com.megagao.production.ssm.service.UserService;
import com.megagao.production.ssm.util.Log;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OperationLogService operationLogService;

	@RequestMapping("/get/{orderId}")
	@ResponseBody
	public COrderVO getItemById(@PathVariable String orderId) throws Exception {
		COrderVO cOrder = orderService.get(orderId);
		return cOrder;
	}

	@RequestMapping("/get_data")
	@ResponseBody
	public List<COrderVO> getData() throws Exception {
		List<COrderVO> list = orderService.find();
		return list;
	}

	@RequestMapping("/find")
	public String find() throws Exception {
		return "order_list";
	}

	@RequestMapping("/add")
	public String add() throws Exception {
		return "order_add";
	}

	@RequestMapping("/edit")
	public String edit() throws Exception {
		return "order_edit";
	}

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows, COrderVO cOrder)
			throws Exception {
		EUDataGridResult result = orderService.getList(page, rows, cOrder);
		return result;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid COrder cOrder,
			BindingResult bindingResult, HttpServletRequest request)
			throws Exception {
		CustomResult result;
		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			System.out.println(fieldError.getDefaultMessage());
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		if (orderService.get(cOrder.getOrderId()) != null) {
			result = new CustomResult(0, "该订单编号已经存在，请更换订单编号！", null);
		} else {
			result = orderService.insert(cOrder);
		}
		// new Date()为获取当前系统时间
		Date come_time = new Date(System.currentTimeMillis());
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute("sysUser2");

		SysUser sysUser1 = userService.loadByUserName(sysUser.getUsername());
		String l_desc = "创建订单 --> 编号为 : " + cOrder.getOrderId() + " ";
		SysOperationLog sysOperationLog = new SysOperationLog(come_time,
				sysUser1, sysUser.getUsername());
		sysOperationLog.setL_desc(l_desc);
		System.out.println(sysOperationLog);
		operationLogService.insert(sysOperationLog);
		return result;
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	private CustomResult update(@Valid COrder cOrder,
			BindingResult bindingResult, HttpServletRequest request)
			throws Exception {

		// new Date()为获取当前系统时间
		Date come_time = new Date(System.currentTimeMillis());
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute("sysUser2");

		SysUser sysUser1 = userService.loadByUserName(sysUser.getUsername());
		String l_desc = "更新订单 --> 编号为 : " + cOrder.getOrderId() + " ";
		SysOperationLog sysOperationLog = new SysOperationLog(come_time,
				sysUser1, sysUser.getUsername());
		sysOperationLog.setL_desc(l_desc);
		System.out.println(sysOperationLog);
		operationLogService.insert(sysOperationLog);

		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return orderService.update(cOrder);
	}

	@RequestMapping(value = "/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid COrder cOrder,
			BindingResult bindingResult, HttpServletRequest request)
			throws Exception {

		// new Date()为获取当前系统时间
		Date come_time = new Date(System.currentTimeMillis());
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute("sysUser2");

		SysUser sysUser1 = userService.loadByUserName(sysUser.getUsername());
		String l_desc = "更新订单 --> 编号为 : " + cOrder.getOrderId() + " ";
		SysOperationLog sysOperationLog = new SysOperationLog(come_time,
				sysUser1, sysUser.getUsername());
		sysOperationLog.setL_desc(l_desc);
		System.out.println(sysOperationLog);
		operationLogService.insert(sysOperationLog);
		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return orderService.updateAll(cOrder);
	}

	@RequestMapping(value = "/update_note")
	@ResponseBody
	private CustomResult updateNote(@Valid COrder cOrder,
			BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			return CustomResult.build(100, fieldError.getDefaultMessage());
		}
		return orderService.updateNote(cOrder);
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	private CustomResult delete(String id, HttpServletRequest request,
			COrder cOrder) throws Exception {
		// new Date()为获取当前系统时间
		Date come_time = new Date(System.currentTimeMillis());
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute("sysUser2");

		SysUser sysUser1 = userService.loadByUserName(sysUser.getUsername());
		String l_desc = "删除订单 --> 编号为 : " + cOrder.getOrderId() + " ";
		SysOperationLog sysOperationLog = new SysOperationLog(come_time,
				sysUser1, sysUser.getUsername());
		sysOperationLog.setL_desc(l_desc);
		System.out.println(sysOperationLog);
		operationLogService.insert(sysOperationLog);
		CustomResult result = orderService.delete(id);
		return result;
	}

	@RequestMapping(value = "/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(String[] ids, HttpServletRequest request)
			throws Exception {

		// new Date()为获取当前系统时间
		Date come_time = new Date(System.currentTimeMillis());
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute("sysUser2");

		SysUser sysUser1 = userService.loadByUserName(sysUser.getUsername());
		String idd = "";
		for (String string : ids) {
			if (string != "" && string != null) {
				idd += string;
			}
		}
		String l_desc = "删除订单 --> 编号为 : " + idd + " ";
		SysOperationLog sysOperationLog = new SysOperationLog(come_time,
				sysUser1, sysUser.getUsername());
		sysOperationLog.setL_desc(l_desc);
		System.out.println(sysOperationLog);
		operationLogService.insert(sysOperationLog);
		CustomResult result = orderService.deleteBatch(ids);
		return result;
	}

	@RequestMapping(value = "/change_status")
	@ResponseBody
	public CustomResult changeStatus(String[] ids) throws Exception {
		CustomResult result = orderService.changeStatus(ids);
		return result;
	}

	// 根据订单id查找
	@RequestMapping("/search_order_by_orderId")
	@ResponseBody
	public EUDataGridResult searchOrderByOrderId(Integer page, Integer rows,
			String searchValue) throws Exception {
		EUDataGridResult result = orderService.searchOrderByOrderId(page, rows,
				searchValue);
		return result;
	}

	// 根据客户名称查找
	@RequestMapping("/search_order_by_orderCustom")
	@ResponseBody
	public EUDataGridResult searchOrderByOrderCustom(Integer page,
			Integer rows, String searchValue) throws Exception {
		EUDataGridResult result = orderService.searchOrderByCustomName(page,
				rows, searchValue);
		return result;
	}

	// 根据产品名称查找
	@RequestMapping("/search_order_by_orderProduct")
	@ResponseBody
	public EUDataGridResult searchOrderByProductName(Integer page,
			Integer rows, String searchValue) throws Exception {
		EUDataGridResult result = orderService.searchOrderByProductName(page,
				rows, searchValue);
		return result;
	}
}
