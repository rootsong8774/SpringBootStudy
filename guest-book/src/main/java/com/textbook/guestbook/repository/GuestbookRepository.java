package com.textbook.guestbook.repository;

import com.textbook.guestbook.entity.Guestbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>,
    QuerydslPredicateExecutor<Guestbook> {

}
