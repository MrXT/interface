package com.karakal.controller.material;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karakal.constant.SystemConstant;
import com.karakal.controller.material.vo.MaterialVo;
import com.karakal.exception.BusinessException;
import com.karakal.service.material.MaterialService;

@RequestMapping("/material")
@RestController
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @Resource
    private Map<String, String> statusMap;

    @RequestMapping("queryMaterialType")
    public Object queryMeetingrooms() {
        return materialService.queryMaterialType();
    }

    @RequestMapping("queryMaterialName")
    public Object queryMaterialName(String materialtypeId) {
        return materialService.queryMaterialName(materialtypeId);
    }
    @RequestMapping("query")
    public Object query(MaterialVo material) {
        return materialService.query(material);
    }
    /**
     * 保存记录（交接与发放）
     * @param material
     * @return
     */
    @RequestMapping("saveRecord")
    public Object saveRecord(MaterialVo material) {
        if (material.getMaterials() == null || material.getMaterials().size() == 0 || StringUtils.isBlank(material.getFromUserId())
            || StringUtils.isBlank(material.getToUserId()) || material.getType() == null) {
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return materialService.saveRecord(material);
    }
    /**
     * 保存工单（申请与挂失）
     * @param material
     * @return
     */
    @RequestMapping("saveOrder")
    public Object saveOrder(MaterialVo material) {
        if (StringUtils.isBlank(material.getUserId()) || material.getType() == null){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return materialService.saveOrder(material);
    }
    /**
     * 查询工单（申请与挂失）
     * @param material
     * @return
     */
    @RequestMapping("queryOrder")
    public Object queryOrder(MaterialVo material) {
        return materialService.queryOrder(material);
    }
}
