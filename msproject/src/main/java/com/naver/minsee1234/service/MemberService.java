package com.naver.minsee1234.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.minsee1234.dao.MemberDao;
import com.naver.minsee1234.entities.Member;
import com.naver.minsee1234.entities.Tb_student;

@Service
public class MemberService implements MemberDao {

	@Autowired
	MemberDao memberdao;

	@Override
	public int insertRow(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tb_student selectOne(String student_no) {
		// TODO Auto-generated method stub
		return null;
	}

}