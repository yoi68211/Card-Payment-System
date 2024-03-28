package com.os.repository;

import com.os.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemoRepository extends JpaRepository<Memo,Long> {
}
