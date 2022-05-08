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
            //영속
            Member member = em.find(Member.class, 200L);
            member.setName("Z1");

            //영속성 컨텍스트에서 분리시킴 [member와 관련된 영속성 컨텍스트가 모두 분리된다]
            em.detach(member);

            //entity매니져 안의 모든 영속석 컨테이너를 초기화 시킨다. 1차 캐시 모두 삭제
            //먼가 JPA쿼리문을 보고 싶을 경우 clear 를 사용한다
            em.clear();

            //영속성 컨텍스트를 종료한다. 
            em.close();

            System.out.println("==========================");
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
