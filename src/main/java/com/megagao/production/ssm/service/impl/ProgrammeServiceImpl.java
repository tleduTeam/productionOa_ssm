package com.megagao.production.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.megagao.production.ssm.domain.Programme;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;
import com.megagao.production.ssm.mapper.ProgrammeMapper;
import com.megagao.production.ssm.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProgrammeServiceImpl implements ProgrammeService {
    @Autowired
    ProgrammeMapper programmeMapper;
    @Override
    public List<Programme> find() {
        return programmeMapper.find();
    }

    @Override
    public EUDataGridResult getList(int page, int rows) throws Exception {
        // 分页处理
        PageHelper.startPage(page, rows);
        List<Programme> list = programmeMapper.find();
        // 创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        // 取记录总条数
        PageInfo<Programme> pageInfo = new PageInfo<Programme>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
