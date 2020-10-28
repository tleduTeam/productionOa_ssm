package com.megagao.production.ssm.service;

import com.megagao.production.ssm.domain.Programme;
import com.megagao.production.ssm.domain.customize.EUDataGridResult;

import java.util.List;

public interface ProgrammeService {
    List<Programme> find();
    EUDataGridResult getList(int page, int rows) throws Exception;
}
