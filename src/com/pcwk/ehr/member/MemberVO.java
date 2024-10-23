package com.pcwk.ehr.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.movie.MovieVO;

public class MemberVO extends DTO{
	private String id;
	private String pass;
	private String name;
	private boolean manager;
	private int wallet;
	private int age;
	private List<MovieVO> reservations;
	
	public MemberVO() {
		super();
	}
	public MemberVO(String loginId) {
        this.id = loginId;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	public int getWallet() {
		return wallet;
	}

	public void setWallet(int wallet) {
		this.wallet = wallet;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<MovieVO> getReservations() {
		return reservations;
	}

	public void setReservations(List<MovieVO> reservations) {
		this.reservations = reservations;
	}

	public MemberVO(String id, String pass, String name, boolean manager, int wallet, int age) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.manager = manager;
		this.wallet = wallet;
		this.age = age;
		this.reservations = new ArrayList<>(); //초기화
	}
	public void addReservation(MovieVO movie) {
	    if (this.reservations == null) {
	        this.reservations = new ArrayList<>();
	    }
	    this.reservations.add(movie);
	}

	public void cancelReservation(MovieVO movie) {
		reservations.remove(movie); // 예매 정보 취소
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		return Objects.equals(id, other.id);
	}
	
	   public void clearReservations() {
	        if (reservations != null) {
	            reservations.clear();
	        }
	    }
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pass=" + pass + ", name=" + name + ", manager=" + manager + ", wallet="
				+ wallet + ", age=" + age + ", reservations=" + reservations + "]";
	}

	public String toFileFormat() {
	    return id + "," + pass + "," + name + "," + manager + "," + wallet + "," + age + "," + reservations;
	}
	
}