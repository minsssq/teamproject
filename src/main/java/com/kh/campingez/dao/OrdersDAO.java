package com.kh.campingez.dao;

import com.kh.campingez.entity.Orders;

public interface OrdersDAO {

  //예약
  Orders order(Orders orders);

  //예약 취소
  int orDelete(int onumber);
}
