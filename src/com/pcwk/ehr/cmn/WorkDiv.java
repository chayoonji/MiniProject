package com.pcwk.ehr.cmn;

import java.util.List;

import com.pcwk.ehr.member.MemberVO;
import com.pcwk.ehr.movie.MovieVO;

public interface WorkDiv<T> {

    /**
     * 등록
     * @param param
     * @return 1(성공)/0(실패)
     */
    int doSave(T param);

    /**
     * 목록 조회
     * @param param
     * @return List<T>
     */
    List<T> doRetrieve(DTO param);

	/**
	 * 수정
	 * 
	 * @param vo
	 * @return 1(성공)/0(실패)
	 */
	int doUpdate(MemberVO vo);

	/**
	 * 삭제
	 * 
	 * @param vo
	 * @return 1(성공)/0(실패)
	 */
	int doDelete(MemberVO vo);

	/**
	 * 회원단건 조회
	 * 
	 * @param vo
	 * @return MemberVO
	 */
	MemberVO doSelectOne(MemberVO vo);

	int writeFile(String path);

	int readFile(String path);

	MovieVO doSelectOne(MovieVO vo);

	int doDelete(MovieVO vo);

	int doUpdate(MovieVO movie);
}