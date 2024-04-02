package com.os.controller;

import com.os.dto.AllPaymentListDto;
import com.os.dto.CustomerDTO;
import com.os.dto.MemoDTO;
import com.os.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/memo")
public class MemoController {
    private final MemoService memoService;

//    @GetMapping("/index_memo")
//    public String index() {
//        return "/memo/index_memo";
//    }

    @GetMapping("/save")
    public String saveForm() {

        return "/memo/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemoDTO memoDTO) throws IOException {
        System.out.println("memoDTO = " + memoDTO);
        memoService.save(memoDTO);
        return "redirect:/dashboard";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model, @PageableDefault(page=1) Pageable pageable) {
        /*
            게시글 데이터를 가져와서 detail.html에 출력
        */
        MemoDTO memoDTO = memoService.findById(id);

        model.addAttribute("Memo", memoDTO);
        model.addAttribute("page", pageable.getPageNumber());
        return "/memo/detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        MemoDTO memoDTO = memoService.findById(id);
        model.addAttribute("MemoUpdate",memoDTO);
        return "/memo/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemoDTO memoDTO, Model model) {
//        MemoDTO Memo =  memoService.update(memoDTO);
//        model.addAttribute("Memo", Memo);
        memoService.update(memoDTO);


        return "redirect:/dashboard";

    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteSelectedItems(@RequestBody List<Long> selectedIds) {
        try {
            System.out.println("Controller => " + selectedIds);
            memoService.deleteSelectedItems(selectedIds);
            return ResponseEntity.ok("Selected items deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting selected items.");
        }
    }
    // /Memo/paging?page=1
    @GetMapping("/paging")
    public String paging(Model model, @PageableDefault(page = 0, size = 10, sort = "updateTime", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(value = "keyword", required = false) String keyword) {

        Page<MemoDTO> allPaymentsPage;
        if (keyword != null && !keyword.isEmpty()) {
            allPaymentsPage = memoService.findByTitleContaining(keyword, pageable);
        } else {
            allPaymentsPage = memoService.findAllMemos(pageable);
        }

        long memoCount = allPaymentsPage.getTotalElements();

        model.addAttribute("MemoList", allPaymentsPage);
        model.addAttribute("memoCount", memoCount);
        model.addAttribute("keyword", keyword);

        return "/memo/paging";
    }

}
