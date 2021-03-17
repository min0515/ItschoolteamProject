package com.naver.minsee1234.dao;

import org.springframework.stereotype.Component;

import com.naver.minsee1234.entities.Member;
import com.naver.minsee1234.entities.Tb_student;

@Component
public interface MemberDao {
	public int insertRow(Member member);

	public Tb_student selectOne(String student_no);
}