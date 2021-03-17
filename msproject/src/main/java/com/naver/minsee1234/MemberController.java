package com.naver.minsee1234;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.naver.minsee1234.dao.MemberDao;
import com.naver.minsee1234.entities.Member;
import com.naver.minsee1234.entities.Tb_student;

@Controller
public class MemberController {

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	Member member;

	@Autowired
	Tb_student tb_student;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "member/member_insert";
	}

	@RequestMapping(value = "/memberinsertsave", method = RequestMethod.POST)
	public String memberinsert(Model model, @ModelAttribute Member member) {
		// getMapper는 인터페이스를 받아옴
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		// 사진
		if (member.getPhoto() == null) {
			member.setPhoto("/images/noimage.png");
		}
		// 비밀번호 암호화
		String encodepassword = hashPassword(member.getPassword());
		member.setPassword(encodepassword);

		dao.insertRow(member);
		return "member/member_insert";
	}

	@RequestMapping(value = "/loginUp", method = RequestMethod.POST)
	public String loginUp(Model model, @RequestParam String student_no, HttpSession session) {
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		Tb_student data = dao.selectOne(student_no);
		if (data == null) {
			return "login/login";
		} else {

			session.setAttribute("sessionStudent_no", data.getStudent_no());
			session.setAttribute("sessionStudent_name", data.getStudent_name());
			session.setAttribute("sessionDepartment_no", data.getDepartment_no());
			session.setAttribute("sessionAbsence_yn", data.getAbsence_yn());
			return "index";

		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, Locale locale) {
		session.invalidate();
		return "index";
	}

	private String hashPassword(String plainTextPassword) {
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login/login";
	}

}
