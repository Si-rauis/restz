package edu.java.jdbc.vo;


import java.sql.Date;

//VO ; Value Object
//t_member 테이블의 컬럼과 매핑되는 필드 선언, getter/ setter
//회원 아이디 mid varchar(20) PK
//회원 이름   mname varchar(20)
//비밀번호    mpw varchar(20)
//이메일      email varchar(50)
//성별        gender char(1)
//사진        photo varchar(50)
//생년월일    birth_date date
//가입일자    join_date date
public class MemberVO {
    private String mid;
    private String mname;
    private String mpw;
    private String email;
    private String gender;
    private String photo;
    private String birthDate;
    private Date joinDate;

    public MemberVO() {
    }

    public MemberVO(String mid, String mname, String mpw, String email, String gender, String photo, String birthDate) {
        this.mid = mid;
        this.mname = mname;
        this.mpw = mpw;
        this.email = email;
        this.gender = gender;
        this.photo = photo;
        this.birthDate = birthDate;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMpw() {
        return mpw;
    }

    public void setMpw(String mpw) {
        this.mpw = mpw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
