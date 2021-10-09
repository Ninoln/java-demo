package com.lam.book.service;

import com.lam.book.client.MemberClient;
import com.lam.book.entity.Book;
import com.lam.book.entity.Borrow;
import com.lam.book.entity.MemberDTO;
import com.lam.book.entity.MemberRestResult;
import com.lam.book.repository.BookRepository;
import com.lam.book.repository.BorrowRepository;
import com.lam.book.service.exception.BookNotFoundException;
import com.lam.book.service.exception.MemberNotFoundException;
import com.lam.book.service.exception.NotEnoughStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Resource
    private BookRepository bookRepository;

    @Resource
    private BorrowRepository borrowRepository;

    @Autowired
    private MemberClient memberClient;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findByBid(Long bid) {
        Book book = null;
        Optional<Book> optional = bookRepository.findById(bid);
        if (optional.isPresent()) {
            book = optional.get();
        } else {
            throw new BookNotFoundException("BOOKID: " + bid + " not found");
        }
        return book;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void borrow(Long bid, String mobile, Date takedate, Date returndate) {
        Book book = this.findByBid(bid);
        if (book != null && book.getStock() == 0) {
            throw new NotEnoughStockException("Stock Not Enough");
        }
        MemberRestResult result = memberClient.checkMobile(mobile);
        MemberDTO memberDTO = null;
        if (result.getCode().equals("0")) {
            memberDTO = result.getData();
        } else {
            throw new MemberNotFoundException("Member Not Found");
        }

        Borrow borrow = new Borrow();
        borrow.setBid(bid);
        borrow.setMid(memberDTO.getMid());
        borrow.setTakedate(takedate);
        borrow.setReturndate(returndate);
        borrow.setCreatetime(new Date());
        borrowRepository.saveAndFlush(borrow);

        book.setStock(book.getStock() - 1);
        bookRepository.saveAndFlush(book);
    }

}
