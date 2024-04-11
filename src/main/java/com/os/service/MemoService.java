package com.os.service;

import com.os.dto.AllPaymentListDto;
import com.os.dto.MemoDTO;
import com.os.entity.Memo;
import com.os.entity.Payment;
import com.os.entity.User;
import com.os.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private final UserService userService;

    public List<MemoDTO> findAll() {
        LocalDateTime week = LocalDateTime.now().minusWeeks(1);
//        List<Memo> MemoList = memoRepository.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
        List<Memo> MemoList = memoRepository.findAllByCreateTimeAfterOrderByCreateTimeDesc(week);
        List<MemoDTO> MemoDTOList = new ArrayList<>();
        for(Memo Memo : MemoList){
            MemoDTOList.add(MemoDTO.toMemoDTO(Memo));
        }
        return MemoDTOList;
    }

    public Page<MemoDTO> findAllMemos(Pageable pageable) {
        Page<Memo> memoPage = memoRepository.findAll(pageable);
        return memoPage.map(MemoDTO::toMemoDTO);
    }

    public Page<MemoDTO> findByMemoContentsContaining(String keyword, Pageable pageable) {
        Page<Memo> allPaymentsPage = memoRepository.findByMemoContentsContaining(keyword, pageable);
        return allPaymentsPage.map(MemoDTO::toMemoDTO);
    }

    // 작성자로 메모 검색
    public Page<MemoDTO> findByUserUsernameContaining(String keyword, Pageable pageable) {
        Page<Memo> allPaymentsPage = memoRepository.findByUserUsernameContaining(keyword, pageable);
        return allPaymentsPage.map(MemoDTO::toMemoDTO);
    }

    public Page<MemoDTO> findByMemoExposeYn(String keyword, Pageable pageable) {
        Page<Memo> allPaymentsPage = memoRepository.findByMemoExposeYn(keyword, pageable);
        return allPaymentsPage.map(MemoDTO::toMemoDTO);
    }


    public void save(MemoDTO memoDTO) {
        User user = userService.findId();
        Memo memo = Memo.toEntity(memoDTO, user);
        memoRepository.save(memo);
    }

    public void deleteSelectedItems(List<Long> selectedIds) {
        memoRepository.deleteAllById(selectedIds);
    }

    public MemoDTO findById(Long id) {
        Optional<Memo> optionalMemo = memoRepository.findById(id);
        if(optionalMemo.isPresent()) {
            Memo memo = optionalMemo.get();
            return MemoDTO.toMemoDTO(memo);
        } else {
            return null;
        }
    }

    public void update(MemoDTO memoDTO){
        User user = userService.findId();
        Optional<Memo> memoOptional  = memoRepository.findById(memoDTO.getId());
        if (memoOptional.isPresent()) {
            Memo memo = memoOptional.get();

            if (memo.getUser().getId().equals(user.getId())) {
                memo.setMemoContents(memoDTO.getMemoContents());
                memo.setMemoExposeYn(memoDTO.getMemoExposeYn());
                memoRepository.save(memo);
            } else {
                System.out.println("오류");
            }
        }
    }

}
