package com.os.entity;

import com.os.dto.MemoDTO;
import com.os.util.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE memo SET memo_del_yn = 'Y' WHERE memo_id = ? ")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "memo_id")
    private Long id;

    @Column(nullable = false,length = 300)
    @Length(min = 1 , max = 300 , message = "내용을 최소 1글자부터 300글자 사이로 입력해주세요.")
    private String memoContents;

    @Column(nullable = false)
    private String memoExposeYn;

    @Column(nullable = false)
    private String memoDelYn;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",nullable = false)
    private User user;

    public static Memo toEntity(MemoDTO memoDTO, User user) {
        Memo memo = new Memo();
        memo.setUser(user);
        memo.setMemoContents(memoDTO.getMemoContents());
        memo.setMemoExposeYn(memoDTO.getMemoExposeYn());
        memo.setMemoDelYn("N");

        return memo;
    }
}
