package com.springcore.order;

import com.springcore.discount.DiscountPolicy;
import com.springcore.member.Member;
import com.springcore.member.MemberRepository;
import com.springcore.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository(); //DIP 위반
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();   //DIP 위반
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository,
        DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
