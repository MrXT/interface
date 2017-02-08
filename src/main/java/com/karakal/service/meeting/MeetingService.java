
package com.karakal.service.meeting;

import com.karakal.controller.meeting.vo.MeetingVO;
import com.karakal.entity.Distribute;


public interface MeetingService {

    Object save(MeetingVO meeting);

    Object query(MeetingVO meeting);

    Object delete(MeetingVO meeting);

    Object saveUser(MeetingVO meeting);

    Object queryRecord(MeetingVO meeting);

    Object queryMeetingrooms();

    Object queryServicetypes(MeetingVO meeting);
    Object updateDistribute(Distribute distribute);


}

