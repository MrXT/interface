
package com.karakal.service.weal;

import com.karakal.controller.weal.vo.WealVO;


public interface WealService {

    Object saveUser(WealVO weal);

    Object query(WealVO weal);

    Object queryWeals(WealVO weal);

    Object delete(WealVO weal);

}

