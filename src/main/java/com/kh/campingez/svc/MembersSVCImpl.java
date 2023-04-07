package com.kh.campingez.svc;

import com.kh.campingez.dao.MembersDAO;
import com.kh.campingez.entity.Members;
import com.kh.campingez.entity.Orders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MembersSVCImpl implements MembersSVC {

  private final MembersDAO membersDAO;

  //회원등록
  @Override
  public Members memSave(Members members){return membersDAO.memSave(members);}

  //회원수정
  @Override
  public void memUpdate(String mid, Members members){
    membersDAO.memUpdate(members);
  }

  //회원탈퇴
  @Override
  public void memDelete(String mid){
  }

  //회원조회(사업자)
  @Override
  public Members memFindB(String mid){

  }

  //회원조회(일반회원)
  @Override
  public Members memFindN(String mid){

  }

  //예약조회(사업자)
  @Override
  public Orders orderFindB(int cnumber){

  }

  //예약조회(일반회원)
  @Override
  public Orders orderFindN(String mid){

  }

  //아이디찾기
  @Override
  public Optional<String> idFind(String mname, String phone, String email){

  }

  //비밀번호찾기
  @Override
  public Optional<String> pwFind(String mid, String phone,String email){

  }

  //회원유무
  @Override
  public boolean isExist(String mid){return membersDAO.isExist(mid);}

  //로그인
  @Override
  public Optional<Members> login(String email, String passwd){

  }
}
