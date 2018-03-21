package com.jd.cardapp.service;

import com.github.pagehelper.PageInfo;
import com.jd.cardapp.model.Card;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CardService {

    PageInfo<Card> getCards(String type , Integer pageNo ,String keys);

    PageInfo<Card> getCardsList(String keys, Integer pageNo , Integer pageSize, String begin , String end,Integer status);

    int CardAdd(Card card, MultipartFile file1 ,MultipartFile file2 );

    int CardCheck(Integer status,Integer id);

    Card getDetail(Integer id);

    int CardDel(Integer id);

    PageInfo<Card> myCards(Integer uid, Integer pageNo , Integer pageSize);
}
