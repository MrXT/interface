package com.karakal.service.material.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.karakal.commons.bean.QueryResult;
import com.karakal.controller.material.vo.MaterialVo;
import com.karakal.dao.mapper.DepartmentMapper;
import com.karakal.dao.mapper.MaterialMapper;
import com.karakal.dao.mapper.MaterialOrderMapper;
import com.karakal.dao.mapper.MaterialRecordMapper;
import com.karakal.dao.mapper.MaterialnameMapper;
import com.karakal.dao.mapper.MaterialtypeMapper;
import com.karakal.entity.Department;
import com.karakal.entity.Material;
import com.karakal.entity.MaterialOrder;
import com.karakal.entity.MaterialRecord;
import com.karakal.entity.Materialname;
import com.karakal.entity.Materialtype;
import com.karakal.exception.BusinessException;
import com.karakal.service.material.MaterialService;
import com.karakal.util.StringUtil;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;
    
    @Autowired
    private DepartmentMapper departmentMapper;
    
    @Autowired
    private MaterialOrderMapper materialOrderMapper;
    
    @Autowired
    private MaterialRecordMapper materialRecordMapper;

    @Autowired
    private MaterialtypeMapper materialtypeMapper;

    @Autowired
    private MaterialnameMapper materialnameMapper;

    @Override
    public Object queryMaterialType() {
        return materialtypeMapper.selectAll();
    }

    @Override
    public Object query(MaterialVo material) {
        if (material.getPageNo() != null && material.getPageSize() != null) {
            PageHelper.startPage(material.getPageNo(), material.getPageSize(), "m.ctime desc");
        }
        List<Map<String, Object>> list = materialMapper.queryByCondition(material);
        return new QueryResult<Map<String, Object>>(new PageInfo<Map<String, Object>>(list));
    }
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Object saveRecord(MaterialVo material) {
        List<MaterialRecord> materialRecords = new ArrayList<MaterialRecord>();
        MaterialVo materialQuery = new MaterialVo();
        materialQuery.setUserId(material.getFromUserId());
        List<Map<String, Object>> list = materialMapper.queryByCondition(materialQuery);
        Department fromDepartment = departmentMapper.selectDepartmentByuserId(material.getFromUserId());
        Department toDepartment = departmentMapper.selectDepartmentByuserId(material.getToUserId());
        for (Material materialVo : material.getMaterials()) {
            if(material.getType().intValue() == 1){//交接
                Boolean have = false;
                for (Map<String, Object> map : list) {
                    if(map.get("materialId").toString().equals(materialVo.getMaterialId())){
                       have = true; 
                    }
                }
                if(!have){
                    throw new BusinessException("用户没有该物资！");
                }
            }
            if(material.getType().intValue() == 2){//发放
                Material old = materialMapper.selectByPrimaryKey(material.getMaterialId());
                if(old.getStatus().intValue() == 4){
                    throw new BusinessException("该物资已被发放！");
                }
            }
            MaterialRecord materialRecord = new MaterialRecord();
            materialRecord.setCtime(new Date());
            materialRecord.setFromUserId(material.getFromUserId());
            materialRecord.setToUserId(material.getToUserId());
            materialRecord.setMaterialId(materialVo.getMaterialId());
            materialRecord.setFromDepartmentId(fromDepartment.getDepartmentId());
            materialRecord.setToDepartmentId(toDepartment.getDepartmentId());
            materialRecord.setType(material.getType());
            materialRecords.add(materialRecord);
        }
        if(material.getType() == 3){//如果回收
            Map<String,Object> map = new HashMap<String, Object>();
            List<String> materialIds = new ArrayList<String>();
            for (Material materialVo : material.getMaterials()) {
                materialIds.add(materialVo.getMaterialId());
            }
            map.put("ids", StringUtil.join(materialIds, ","));
            map.put("userId", null);
            map.put("status", 1);//旧设备
            return materialMapper.updateBatchUser(map)+ materialRecordMapper.insertList(materialRecords);
        }else{
            for (Material materialVo : material.getMaterials()) {
                Material materialUpdate = new Material();
                materialUpdate.setStatus(4);
                materialUpdate.setUserId(material.getToUserId());
                materialUpdate.setSerialNumber(materialVo.getSerialNumber());
                materialUpdate.setMaterialId(materialVo.getMaterialId());
                materialMapper.updateByPrimaryKeySelective(materialUpdate);
            }
            return material.getMaterials().size()+ materialRecordMapper.insertList(materialRecords);
        }
    }
    @Transactional(rollbackFor=Exception.class)
    @Override
    public Object saveOrder(MaterialVo material) {
        Map<String,Object> map = new HashMap<String, Object>();
        List<Materialtype> materialtypes =materialtypeMapper.selectAll();
        for (Materialtype materialtype : materialtypes) {
            map.put(materialtype.getMaterialtypeId(), materialtype.getMaterialTypeName());
        }
        List<Materialname> materialnames =materialnameMapper.selectAll();
        for (Materialname materialname : materialnames) {
            map.put(materialname.getMaterialnameId(), materialname.getMaterialName());
        }
        List<MaterialOrder> materialOrders = new ArrayList<MaterialOrder>();
        if(material.getType() == 2){//1:申请单 2：挂失单
            List<String> materialNameStrings = new ArrayList<String>();
            List<String> materialTypeStrings = new ArrayList<String>();
            for (String materialId : material.getMaterialIds()) {
                Material mater = materialMapper.selectByPrimaryKey(materialId);
                materialNameStrings.add((String) map.get(mater.getMaterialnameId()));
                materialTypeStrings.add((String) map.get(mater.getMaterialtypeId()));
            }
            Date date = new Date();
            MaterialOrder materialOrder = new MaterialOrder();
            materialOrder.setApplyUserId(material.getUserId());
            materialOrder.setStatus(1);
            materialOrder.setType(material.getType());
            materialOrder.setPicurl(material.getPicurl());
            materialOrder.setMaterialId(com.karakal.commons.util.StringUtil.join(material.getMaterialIds(), ","));
            materialOrder.setMaterialtypeId(com.karakal.commons.util.StringUtil.join(materialTypeStrings, ","));
            materialOrder.setMaterialnameId(com.karakal.commons.util.StringUtil.join(materialNameStrings, ","));
            materialOrder.setRemark(material.getRemark());
            materialOrder.setCtime(date);
            materialOrder.setUtime(date);
            materialOrders.add(materialOrder);
        }else if(material.getType() == 1){
            MaterialOrder materialOrder = new MaterialOrder();
            materialOrder.setApplyUserId(material.getUserId());
            materialOrder.setStatus(1);
            materialOrder.setType(material.getType());
            materialOrder.setPicurl(material.getPicurl());
            materialOrder.setMaterialtypeId(material.getMaterialtypeId());
            materialOrder.setMaterialnameId(material.getMaterialnameId());
            materialOrder.setCtime(new Date());
            materialOrder.setRemark(material.getRemark());
            materialOrder.setUtime(materialOrder.getCtime());
            materialOrders.add(materialOrder);
        }
        return  materialOrderMapper.insertList(materialOrders);
    }
    @Override
    public Object queryOrder(MaterialVo material) {
        if (material.getPageNo() != null && material.getPageSize() != null) {
            PageHelper.startPage(material.getPageNo(), material.getPageSize(), "mo.utime desc");
        }
        List<Map<String, Object>> list = materialOrderMapper.queryByCondition(material);
        return new QueryResult<Map<String, Object>>(new PageInfo<Map<String, Object>>(list));
    }

    @Override
    public Object queryMaterialName(String materialtypeId) {
        Materialname materialname = new Materialname();
        materialname.setMaterialTypeId(materialtypeId);
        return materialnameMapper.select(materialname);
    }


}
