package com.hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //db당 하나만 생성 <- 여러 쓰레드에서 공유

        EntityManager em = emf.createEntityManager(); //한번만 쓰고 버리기 때문에.. 쓰레드간 공유하면 안된다

        EntityTransaction tx = em.getTransaction(); //JPA 모든 데이터 변경은 트랜잭션 안에서 실행한다
        tx.begin();

        try{
            //code
            //저장
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member); //JPA가 Maaping 정보를 보고 넣어준다
//            tx.commit();

            //조회
//            Member findMember = em.find(Member.class, 1L);
//            tx.commit();;

            //삭제
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember);
//            tx.commit();

            //수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA"); //수정만 하면 된다
//            tx.commit();

            /**
             * 영속성 컨테이너 이점
             * 1차 캐시
             * 동일성 보장
             * 트랜잭션 지원하는 쓰기 지연
             * 변경 감지
             * 지연 로딩
             */
            //영속 상태
            Member member = new Member();
            member.setId(10L);
            member.setName("HelloJPA");
                //여기 위에 까지는 비영속 상태//

                //아래부터 영속 상태
            em.persist(member);
                //아래부터 비용석 상태
            em.detach(member);
                //commit 시점에 쿼리문을 날립니다.
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
