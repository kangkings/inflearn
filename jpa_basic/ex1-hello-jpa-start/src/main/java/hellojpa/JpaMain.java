package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        //팩토리는 앱 로딩시점에 하나만 존재해야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //트랜잭션 마다 매니저 생성하여 사용
        //매니저를 생성한다? -> 데이터베이스 커넥션 하나 얻어서 쓴다
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        //JPA는 트랜잭션 개념이 매우 중요해서 트랜잭션 안에서 모든 작업이 이루어져야함
        Member member = new Member();
        member.setId(1L);
        member.setName("HelloA");
        em.persist(member);
        tx.commit();
        em.close();
        emf.close();
    }
}
