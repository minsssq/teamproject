package com.kh.campingez.dao;

import com.kh.campingez.entity.Members;
import com.kh.campingez.entity.Orders;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
class MembersDAOImplTest {

  @Autowired
  private MembersDAO membersDAO;


  @Test
  @DisplayName("회원등록")
  void memSave() {
    Members member = new Members();
    member.setMname("홍길동");
    member.setMid("test127");
    member.setPw("test!1234");
    member.setPhone("010-1234-5678");
    member.setEmail("test5@naver.com");
    member.setNickname("별명5");
    member.setMaddress(null);
    member.setMtype("n");
    member.setCompanyname(null);
    member.setBusinessnumber(null);

    membersDAO.memSave(member);

  }

  @Test
  @DisplayName("회원수정")
  void memUpdate() {
    String mid = "test124";
    Members member = new Members();
    member.setNickname("별명2");
    member.setPw("test!1234");
    member.setPhone("010-1111-1112");
    member.setEmail("test2@naver.com");
    member.setMid(mid);
    membersDAO.memUpdate(mid,member);
  }

  @Test
  @DisplayName("회원탈퇴")
  void memDelete(){
    String mid = "test127";
    membersDAO.memDelete(mid);

  }

  @Test
  @DisplayName("회원조회")
  void memFindById(){
    String mid = "test123";
    Members member = membersDAO.memFindB(mid);

    Assertions.assertThat(member.getNickname()).isEqualTo("별명1");
    Assertions.assertThat(member.getPhone()).isEqualTo("010-1234-5678");
    Assertions.assertThat(member.getMaddress()).isEqualTo(null);
  }

  @Test
  @DisplayName("예약조회(사업자)")
  void orderFindB(){
    int cnumber = 1;
    Orders orders = membersDAO.orderFindB(cnumber);

    Assertions.assertThat(orders.getArea()).isEqualTo(2);
    Assertions.assertThat(orders.getMid()).isEqualTo("test123");
    Assertions.assertThat(orders.getHeadcount()).isEqualTo(3);
  }

  @Test
  @DisplayName("예약조회(일반고객)")
  void orderFindN(){
    String mid = "test123";
    Orders orders = membersDAO.orderFindN(mid);

    Assertions.assertThat(orders.getArea()).isEqualTo(2);
    Assertions.assertThat(orders.getCnumber()).isEqualTo(1);
    Assertions.assertThat(orders.getHeadcount()).isEqualTo(3);

  }

  @Test
  @DisplayName("아이디 찾기")
  void idFind(){
    String mname = "홍길동";
    String phone = "010-1111-1112";
    String email = "test2@naver.com";

    Optional<String> members = membersDAO.idFind(mname,phone,email);

    Assertions.assertThat(members.isPresent()).isTrue();
    Assertions.assertThat(members.get()).isEqualTo("test124");

  }

  @Test
  @DisplayName("pw 찾기")
  void pwFind(){
    String mid = "test123";
    String phone = "010-1234-5678";
    String email = "test1@naver.com";

    Optional<String> members = membersDAO.pwFind(mid,phone,email);

    Assertions.assertThat(members.get()).isEqualTo("test[123899");
  }

  @Test
  @DisplayName("회원유무")
  void isExist() {
    String mid = "test123";
    boolean exist = membersDAO.isExist(mid);
    Assertions.assertThat(exist).isTrue();

    exist = membersDAO.isExist("test@kh.com111");
    Assertions.assertThat(exist).isFalse();
  }

  @Test
  @DisplayName("로그인")
  void login(){
    String mid = "test123";
    String pw = "test[123899";

    Optional<Members> member = membersDAO.login(mid,pw);
    log.info("member={}",member);
    Assertions.assertThat(member.isPresent()).isTrue();

    //회원이 존재하지 않는경우
    member = membersDAO.login("test1@kh.com", "12345");
    Assertions.assertThat(member.isPresent()).isFalse();
  }

}