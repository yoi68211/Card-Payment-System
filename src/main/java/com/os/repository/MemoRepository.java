package com.os.repository;

import com.os.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MemoRepository extends JpaRepository<Memo,Long> {
    List<Memo> findByMemoDelYn(String memoDelYn);

    Page<Memo> findByMemoContentsContains(String keyword, Pageable pageable);


}
