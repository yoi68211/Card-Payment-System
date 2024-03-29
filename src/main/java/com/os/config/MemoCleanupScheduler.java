package com.os.config;

import com.os.entity.Memo;
import com.os.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@EnableScheduling
@Configuration
public class MemoCleanupScheduler {

    @Autowired
    private MemoRepository memoRepository;


    // 스케줄링 작업 설정
    @Scheduled(fixedDelay = 300000) // 5분마다 실행
    public void permanentlyDeleteSoftDeletedMemo() {
        // 삭제된 메모 중에서 삭제 후 5분이 지난 메모를 조회하여 영구적으로 삭제
        List<Memo> softDeletedMemoList = memoRepository.findByMemoDelYn("Y"); // 소프트 삭제된 메모 목록 조회
        LocalDateTime thresholdTime = LocalDateTime.now().minusMinutes(5); // 삭제 후 5분 후 시간
        for (Memo memo : softDeletedMemoList) {
            // 소프트 삭제된 후 5분이 경과한 메모를 영구적으로 삭제
            System.out.println("5분경과");
            if (memo.getUpdateTime().isBefore(thresholdTime)) {
                memoRepository.delete(memo);
            }
        }
    }
}
