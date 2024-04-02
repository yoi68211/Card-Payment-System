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
        List<Memo> MemoList = memoRepository.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
        List<MemoDTO> MemoDTOList = new ArrayList<>();
        for(Memo Memo : MemoList){
            MemoDTOList.add(MemoDTO.toMemoDTO(Memo));
        }
        return MemoDTOList;
    }
//    public Page<MemoDTO> findAll(Pageable pageable) {
//        Page<>
//        List<MemoDTO> MemoDTOList = new ArrayList<>();
//        for(Memo Memo : MemoList){
//            MemoDTOList.add(MemoDTO.toMemoDTO(Memo));
//        }
//        return MemoDTOList;
//    }

    /*public List<MemoDTO> findbyall(String keyword) {
        List<Memo> MemoList = memoRepository.findAll(keyword);
        List<MemoDTO> MemoDTOList = new ArrayList<>();
        for(Memo Memo : MemoList){
            MemoDTOList.add(MemoDTO.toMemoDTO(Memo));
        }
        return MemoDTOList;
    }
    public Page<MemoDTO>findbycontent(String keyword,Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 15;
        Page<Memo> MemoList = memoRepository.findByMemoContentsContains(keyword,PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        return MemoList.map(memo -> new MemoDTO(memo.getId(),
                memo.getUser().getUsername()
                ,memo.getMemoContents()
                ,memo.getCreateTime()
                ,memo.getUpdateTime()
                ,memo.getMemoExposeYn()
                ,memo.getMemoDelYn()));
    }public Page<MemoDTO> findbywriter(String keyword ,Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 15;
        Page<Memo> MemoList = memoRepository.findAllByUser_UsernameContaining(keyword,PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        return MemoList.map(memo -> new MemoDTO(memo.getId(),
                memo.getUser().getUsername()
                ,memo.getMemoContents()
                ,memo.getCreateTime()
                ,memo.getUpdateTime()
                ,memo.getMemoExposeYn()
                ,memo.getMemoDelYn()));
    }
    */

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

//    public MemoDTO update(MemoDTO memoDTO) {
//        // Memo Memo = Memo.toEntity(memoDTO);
//        User user = userService.findId();
//        Optional<Memo> memoOptional  = memoRepository.findById(memoDTO.getId());
//        if (memoOptional.isPresent()){
//            Memo memo = memoOptional.get();
//            System.out.println("로그인 id => "+ memo.getUser().getId());
//            System.out.println("메모 유저 id ==> " +  user.getId());
//            System.out.println("메모id ==> " +  memo.getId());
//            if(memo.getUser().getId().equals(user.getId())){
//                memo.setMemoContents(memo.getMemoContents());
//                memo.setMemoExposeYn(memo.getMemoExposeYn());
//                memoRepository.save(memo);
//            } else{
//                System.out.println("시바 먼ㄷ[");
//            }
//
//
//        }
//        // 작성자 id 비교
//        // 업데이트할 메모 엔티티 불러오기
//        // set 후 save
//
//        return findById(memoDTO.getId());
//    }
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

    public Page<MemoDTO> paging(Pageable pageable, String searchBoxValue, String searchTypeValue) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 15; // 한 페이지에 보여줄 글 갯수

        Page<Memo> memoEntities;

        if ("entire".equals(searchTypeValue)) {
            memoEntities = memoRepository.findByMemoContentsContains(searchBoxValue, PageRequest.of(page, pageLimit));
        } else if ("writer".equals(searchTypeValue)) {
            memoEntities = memoRepository.findAllByUser_UsernameContaining(searchBoxValue, PageRequest.of(page, pageLimit));
        } else {
            // 기타 검색 조건에 따른 처리
            return null;
        }

        return memoEntities.map(memo -> new MemoDTO(memo.getId(),
                memo.getUser().getUsername(),
                memo.getMemoContents(),
                memo.getCreateTime(),
                memo.getUpdateTime(),
                memo.getMemoExposeYn(),
                memo.getMemoDelYn()
                ));

    }
}
