
package com.karakal.service.distribute;

import java.util.List;

import com.karakal.controller.distribute.vo.DistributeVo;
import com.karakal.entity.Distribute;


public interface DistributeService {

    Object query(DistributeVo distribute);

    List<Distribute> queryNoEvaluate(DistributeVo distribute);

}

