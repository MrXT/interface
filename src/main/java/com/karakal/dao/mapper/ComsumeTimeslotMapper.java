package com.karakal.dao.mapper;

import com.karakal.entity.ComsumeTimeslot;
import com.karakal.util.MyMapper;

public interface ComsumeTimeslotMapper extends MyMapper<ComsumeTimeslot> {
    
    ComsumeTimeslot queryByContidion(ComsumeTimeslot comsumeTimeslot);
}