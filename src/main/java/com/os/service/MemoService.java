package com.os.service;

import com.os.dto.MemoDTO;
import com.os.entity.Memo;
import com.os.entity.User;
import com.os.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    private final UserService userService;
    public void save(MemoDTO memoDTO) {
        User user = userService.findId();
        Memo memo = Memo.toEntity(memoDTO, user);
        memoRepository.save(memo);
    }


    public List<MemoDTO> findAll() {
        List<Memo> MemoList = memoRepository.findAll();
        List<MemoDTO> MemoDTOList = new ArrayList<>();
        for(Memo Memo : MemoList){
            MemoDTOList.add(MemoDTO.toMemoDTO(Memo));
        }
        return MemoDTOList;
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

    public MemoDTO update(MemoDTO memoDTO) {
//        Memo Memo = Memo.toEntity(memoDTO);
        User user = userService.findId();
        Memo memo  = Memo.toEntity(memoDTO, user);
        memoRepository.save(memo);
        return findById(memoDTO.getId());
    }

    public Page<MemoDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 15; // 한 페이지에 보여줄 글 갯수
        // 한 페이지당 15개씩 글을 보여주고, 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<Memo> memoEntities = memoRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("MemoEntities.getContent() = " + memoEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("MemoEntities.getTotalElements() = " + memoEntities.getTotalElements()); // 전체 글갯수
        System.out.println("MemoEntities.getNumber() = " + memoEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("MemoEntities.getTotalPages() = " + memoEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("MemoEntities.getSize() = " + memoEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("MemoEntities.hasPrevious() = " + memoEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("MemoEntities.isFirst() = " + memoEntities.isFirst()); // 첫 페이지 여부
        System.out.println("MemoEntities.isLast() = " + memoEntities.isLast()); // 마지막 페이지 여부


        // 목록: id, writer, title, hits, createdTime

        return memoEntities.map(memo -> new MemoDTO(memo.getId(), memo.getUser().getUsername(), memo.getMemoContents(), memo.getCreateTime(),memo.getUpdateTime(), memo.getMemoDelYn(), memo.getMemoExposeYn()));
    }
}
