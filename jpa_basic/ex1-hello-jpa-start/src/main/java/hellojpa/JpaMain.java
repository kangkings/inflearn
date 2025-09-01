package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        //팩토리는 앱 로딩시점에 하나만 존재해야함
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //트랜잭션 마다 매니저 생성하여 사용
        //매니저를 생성한다? -> 데이터베이스 커넥션 하나 얻어서 쓴다
        //엔티티 매니저는 쓰레드간 공유X
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        //JPA는 트랜잭션 개념이 매우 중요해서 트랜잭션 안에서 모든 작업이 이루어져야함
        try {
            //삽입
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//            em.persist(member);

            //조회
//            Member member = em.find(Member.class, 2L);
//            System.out.println("Id : " + member.getId());
//            System.out.println("Name : " + member.getName());

            //삭제
//            em.remove(member);

            //수정
            //JPA를 통해서 객체를 가져오면 엔티티매니저에 의해 관리됨
            //이후 트랜잭션이 끝날 때 변경사항을 체크해서 자동으로 변경사항에 대한 Update 쿼리가 나간다.
//            member.setName("HelloJPA");

            //JPQL
            //JPA가 제공하는 추상화된 SQL, 객체지향적 쿼리 언어 제공
            //SQL문법과 유사, 테이블이 아닌 엔티티 객체를 대상으로 쿼리
            //DB독립적 (객체 대상이기 때문, Dialect 바꾸면 해당 벤더에 해당하는 쿼리로 자동 변경)
            //하나의 추상화된 쿼리로 다른 변경 없이 다양한 벤더의 쿼리 사용 가능
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            for (Member member : result) {
                System.out.println(member.getName());
            }

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
