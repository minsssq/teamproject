package com.kh.campingez.dao;

import com.kh.campingez.entity.Orders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OrdersDAOImpl implements OrdersDAO{

  private final NamedParameterJdbcTemplate template;

  @Override
  public Orders order(Orders orders) {
    StringBuffer sb = new StringBuffer();
    sb.append("insert into ORDERS onumber,cnumber,area,mid,phone,headcount,checkin,checkout) ");
    sb.append("values(onumber_seq.nextval, :cnumber, :area, :mid, :phone, :headcount, :checkin, :checkout) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(orders);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sb.toString(),param,keyHolder,new String[]{"onumber"});


    long onumber = keyHolder.getKey().longValue();
    return orders;
  }

  @Override
  public int orDelete(int onumber) {
    String sb = "delete from orders where onumber = :onumber ";
    return template.update(sb, Map.of("onumber",onumber));
  }
}
