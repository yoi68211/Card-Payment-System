package com.os.repository;

import com.os.dto.MemoDTO;
import com.os.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface MemoRepository extends JpaRepository<Memo,Long> {
    List<Memo> findByMemoDelYn(String memoDelYn);

    Page<Memo> findByMemoContentsContaining(String keyword, Pageable pageable);

    Page<Memo> findByUserUsernameContaining(String keyword, Pageable pageable);

    Page<Memo> findByMemoExposeYn(String keyword, Pageable pageable);

    List<Memo> findAllByCreateTimeAfterOrderByCreateTimeDesc(LocalDateTime week);
}
