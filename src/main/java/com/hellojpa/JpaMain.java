package com.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.management.MemoryManagerMXBean;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //db당 하나만 생성 <- 여러 쓰레드에서 공유

        EntityManager em = emf.createEntityManager(); //한번만 쓰고 버리기 때문에.. 쓰레드간 공유하면 안된다

        EntityTransaction tx = em.getTransaction(); //JPA 모든 데이터 변경은 트랜잭션 안에서 실행한다
        tx.begin();

        try{
            //150저장 상태
            //Member member1 = new Member(150L, "A");
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZ"); //여기서 값만 변경한다. 알아서 commit 시점에 update 쿼리문을 전달한다

            //수정에서는 해당 코드는 필요없다.
            em.persist(member);
            System.out.println("=============선 이후에 쿼리 문이 나간다=============");
            tx.commit();
        }
        catch(Exception e){
            tx.rollback();
        } finally {
            em.close(); //database Connection을 물고 동작하기 때문에 끝나면 닫아준다
        }
        emf.close();
    }
}
