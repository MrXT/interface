
package com.karakal.bean;

import com.karakal.excel.annotation.Excel;

public class ExcelRequestBean {
    @Excel("歌曲ID")
    private Long songId;
    public Long getSongId() {
        return songId;
    }
    public void setSongId(Long songId) {
        this.songId = songId;
    }
    
}

