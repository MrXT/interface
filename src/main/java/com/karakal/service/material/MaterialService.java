
package com.karakal.service.material;

import com.karakal.controller.material.vo.MaterialVo;


public interface MaterialService {

    Object queryMaterialType();

    Object query(MaterialVo material);

    Object saveRecord(MaterialVo material);

    Object saveOrder(MaterialVo material);

    Object queryMaterialName(String materialtypeId);

    Object queryOrder(MaterialVo material);

}

