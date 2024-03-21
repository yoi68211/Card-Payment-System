package com.os.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "memo_id")
    private Long id;

    @Column(nullable = false)
    private String memoContents;


    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime memoCreateTime;

    @UpdateTimestamp
    private LocalDateTime memoModifiedAt;

    @Column(nullable = false)
    private String memoExposeYn;

    @Column(nullable = false)
    private String memoDelYn;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",nullable = false)
    private User user;

}
