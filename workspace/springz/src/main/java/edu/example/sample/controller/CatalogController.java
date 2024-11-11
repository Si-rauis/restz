package edu.example.sample.controller;

import edu.example.sample.input.PageInput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @GetMapping("/display-list4")
    public String displatList4(PageInput pageInput){
        //요청 파라미터가 많은 경우 DTO <Date Transfer Object> 객체로 받음
        System.out.println("/catalog/display-list4");
        System.out.println("pageNum=" + pageInput.getPageNum());
        System.out.println("maxCount=" + pageInput.getMaxCount());
        System.out.println(pageInput);
        return "display";
    }

    @GetMapping("/display-list3")
    public String displatList3(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int maxCount){
        //요청 파라미터가 전달되지 않은 경우 기본값
        System.out.println("/catalog/display-list3");
        System.out.println("pageNum=" + pageNum);
        System.out.println("maxCount=" + maxCount);
        return "display";
    }

    @GetMapping("/display-list2")
    public void displatList2(@RequestParam int pageNum,
                               @RequestParam int maxCount){
        System.out.println("/catalog/display-list2");
        System.out.println("pageNum=" + pageNum);
        System.out.println("maxCount=" + maxCount);
    }

    @GetMapping("/display-list1")
    public void displatList1(@RequestParam("pageNum") int pNo,
                               @RequestParam("maxCount") int max){
        System.out.println("/catalog/display-list1");
        System.out.println("pageNum=" + pNo);
        System.out.println("maxCount=" + max);
        // return으로 뷰이름을 명시하지 않은 경우
    }

    @RequestMapping(path="abc",method = RequestMethod.GET)
    public String abc(){
        System.out.println("CatalogController - abc()----------");
        return "abc";
    }

    @GetMapping("/xyz")
    public String xyz(){
        System.out.println("CatalogController - xyz()----------");
        return "xyz";                //뷰 이름
    }


    @PostMapping("/xyz")
    public String xyzPost(){
        System.out.println("CatalogController - xyzPOST()----------");
        return "xyz";                //뷰 이름
    }
}
