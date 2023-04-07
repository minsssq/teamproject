package com.kh.campingez.svc;

import com.kh.campingez.entity.Orders;

public interface OrdersSVC {

  //예약
  Orders order(Orders orders);

  //예약 취소
  int orDelete(int onumber);

}
