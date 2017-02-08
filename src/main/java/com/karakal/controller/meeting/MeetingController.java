
package com.karakal.controller.meeting;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.karakal.constant.SystemConstant;
import com.karakal.controller.meeting.vo.MeetingVO;
import com.karakal.entity.Distribute;
import com.karakal.exception.BusinessException;
import com.karakal.service.meeting.MeetingService;
import com.karakal.util.DateUtil;

@RequestMapping("/meeting")
@RestController
public class MeetingController {
    @Autowired
    private MeetingService meetingService;
    
    @Resource
    private Map<String, String> statusMap;
    
    @RequestMapping("save")
    public Object save(String data){
        JSONObject object =JSON.parseObject(data);
        String startDate = object.getString("startDate");
        String endDate = object.getString("endDate");
        object.remove("startDate");
        object.remove("endDate");
        MeetingVO meeting = object.toJavaObject(MeetingVO.class);
        meeting.setStartDate(DateUtil.string2Date(startDate, "yyyy-MM-dd HH:mm"));
        meeting.setEndDate(DateUtil.string2Date(endDate, "yyyy-MM-dd HH:mm"));
        return meetingService.save(meeting);
    }
    @RequestMapping("queryMeetingrooms")
    public Object queryMeetingrooms(MeetingVO meeting){
        return meetingService.queryMeetingrooms();
    }
    @RequestMapping("queryServicetypes")
    public Object queryServicetypes(MeetingVO meeting){
        return meetingService.queryServicetypes(meeting);
    }
    @RequestMapping("saveUser")
    public Object saveUser(MeetingVO meeting){
        if(StringUtils.isBlank(meeting.getType())||StringUtils.isBlank(meeting.getMeetingId())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return meetingService.saveUser(meeting);
    }
    @RequestMapping("query")
    public Object query(MeetingVO meeting){
        return meetingService.query(meeting);
    }
    @RequestMapping("queryRecord")
    public Object queryRecord(MeetingVO meeting){
        return meetingService.queryRecord(meeting);
    }
    @RequestMapping("delete")
    public Object delete(MeetingVO meeting){
        return meetingService.delete(meeting);
    }
    /**
     * 修改派单属性
     * @param meeting
     * @return
     */
    @RequestMapping("updateDistribute")
    public Object updateDistribute(Distribute distribute){
        if(StringUtils.isBlank(distribute.getRid())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return meetingService.updateDistribute(distribute);
    }
}

