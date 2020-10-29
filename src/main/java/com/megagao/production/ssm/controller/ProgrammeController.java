package com.megagao.production.ssm.controller;

import com.megagao.production.ssm.domain.Programme;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/programme")
public class ProgrammeController {
    @Autowired
    ProgrammeService programmeService;

    @RequestMapping("/find")
    public String find() throws Exception {
//       List<Programme> list = programmeService.find();
//        for (Programme pro:list) {
//            System.out.println(pro.getMeeting().getType()+"========================================================");
//        }
        return "programme_list";

    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getList(Integer page, Integer rows)
            throws Exception {
        EUDataGridResult result = programmeService.getList(page, rows);
        return result;
    }
}
