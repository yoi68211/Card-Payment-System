package com.os.repository;

import com.os.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MemoRepository extends JpaRepository<Memo,Long> {

    // test code
    // 소프트 삭제 쿼리
    @Modifying
    @Query("UPDATE Memo m SET m.memoDelYn = 'Y' WHERE m.id = :id")
    void softDeleteById(@Param("id") Long id);


    // 소프트 삭제된 메모 목록 조회
    List<Memo> findByMemoDelYn(String memoDelYn);
}
