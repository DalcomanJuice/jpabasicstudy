package com.hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity //JPA가 관리하는 객체이다.
//@Table(name = "USER") //이렇게 하면 table 저장될때.. USER table 에 해당 데이터를 저장합니다

public class Member {
    @Id
    private Long id;

    //insertable = true -> 업데이트문이 나갈때.. 이 컬럼을 변경 할거야 말거야.
    //updatable = false <- 업데이트 안한다. true <- 업데이트 한다.
    //nullable = false <- not null 붙음.
    //unique = true <- 유니크 안쓴다.. 이름이 이상하게 설정된다. [이름 반영 어렵다] table 쪽에서 건다.
    //length = 10 <- 길이 준다.
    //columDefinition = "varchar(100) default 'EMPTY'" <- 해당 컬럼 정의를 직접 할 수 있다.

    @Column(name = "name", insertable = true)
    private String username;

    private Integer age;

    //enum 의 기본 값은 ORDINAL <- enum의 순서를 넣는다.

    //enumType의 기본 값은 ORDINAL이다
    //enumType의 STRING 으로도 저장이 가능하다.
    //운영환경에서 실수할 여자기 많기 때문에 그냥 String 으로 쓴다.
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /**
     * DATE, 날짜
     * TIME, 시간
     * TIMESTAMP; 날짜 + 시간
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //testLocalDate date,
    //testLocalDateTime timestamp,
    private LocalDate testLocalDate;

    private LocalDateTime testLocalDateTime;

    //Lob 에 문자열이면 clob으로 테이블 컬럼 데이터가 생성된다.
    @Lob
    private String description;

    @Transient //해당 필드는 DB랑 연관 없음 ... 메모리로만 계산할 때..
    private int temp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public Member(){

    }

    //Getter, Setter ...
}

/**
 * create table Member (
 *  id bigint not null,
 *  name varchar(255),
 * );
 */