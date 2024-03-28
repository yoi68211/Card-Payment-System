package com.os.dto;

import com.os.entity.Memo;
import lombok.*;

import java.time.LocalDateTime;

// DTO => (Data Transfer Object), VO, Bean,        Entity
@Getter
@Setter
@ToString
@NoArgsConstructor // 기본생성자
@AllArgsConstructor
public class MemoDTO {
    private Long id;

    private String userName;

    private String memoContents;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String memoExposeYn;

    private String memoDelYn;

    public static MemoDTO toMemoDTO(Memo Memo) {
        MemoDTO memoDTO = new MemoDTO();
        memoDTO.setId(Memo.getId());
        memoDTO.setMemoContents(Memo.getMemoContents());
        memoDTO.setMemoExposeYn(Memo.getMemoExposeYn());
        memoDTO.setCreateTime(Memo.getCreateTime());
        memoDTO.setUpdateTime(Memo.getUpdateTime());
        memoDTO.setUserName(Memo.getUser().getUsername());
        return memoDTO;
    }
}
