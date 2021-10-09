package com.lam.book.controller;

import com.lam.book.entity.Book;
import com.lam.book.service.BookService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class BookController {

    @Resource
    private BookService bookService;

    @GetMapping("/list")
    public Map list() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Book> books = bookService.findAll();
            map.put("code", "0");
            map.put("message", "success");
            map.put("data", books);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", e.getClass().getSimpleName());
            map.put("message", e.getMessage());
        }
        return map;
    }

    @GetMapping("/info")
    public Map info(Long bid) {
        Map<String, Object> map = new HashMap<>();
        try {
            Book book = bookService.findByBid(bid);
            map.put("code", "0");
            map.put("message", "success");
            map.put("data", book);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", e.getClass().getSimpleName());
            map.put("message", e.getMessage());
        }
        return map;
    }

    @PostMapping("/borrow")
    public Map borrow(Long bid, String mobile, String takedate, String returndate) {
        Date tDate = null;
        Date rDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> map = new HashMap<>();

        try {
            tDate = sdf.parse(takedate);
            rDate = sdf.parse(returndate);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", e.getClass().getSimpleName());
            map.put("message", e.getMessage());
        }

        try {
            bookService.borrow(bid, mobile, tDate, rDate);
            map.put("code", "0");
            map.put("message", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", e.getClass().getSimpleName());
            map.put("message", e.getMessage());
        }
        return map;
    }
}
