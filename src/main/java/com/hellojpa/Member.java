package com.hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "USER") //이렇게 하면 table 저장될때.. USER table 에 해당 데이터를 저장합니다
public class Member {

    @Id
    private Long id;

   // @Column(name = "username") //colum name 변경
    private String name;

    //Getter, Setter ...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/**
 * create table Member (
 *  id bigint not null,
 *  name varchar(255),
 * );
 */