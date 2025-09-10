package hellojpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
//유니크 제약조건은 테이블 어노테이션에서
//uniqueConstraint 옵션값으로 이름과 함께 설정
//(밑에 컬럼에서 설정하면 UK-~@#@!#@!이런식이라 이름으로 알기 힘들어짐)
@Table(name = "Member")

//테이블마다 sequence를 별도로 만들어 쓸때 필요
//@SequenceGenerator(
//        name = "MEMBER_SEQ_GENERATOR",
//        sequenceName = "MEMBER_SEQ",//매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 1
//)

//Table 매핑 방식 사용
//@TableGenerator(
//        name = "MEMBER+SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnValue = "MEMBER_SEQ", allocationSize = 1
//)
public class Member {

    /*
        매핑 어노테이션
        @Colulmn = 컬럼 매핑
        @Temporal = 날짜 타입 매핑
        @Enumerated = enum 타입 매핑
        @Lob = BLOB, CLOB 매핑
        @Transient = 특정 필드를 컬럼에 매핑하지 않음(메모리에서만 사용)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    /*
    @Column 옵션들
        name
        insertable,updatable
        nullable(DDL)
        unique(DDL) = 잘 사용 x (이름 지정불가)
        columnDefinition(DDL) = 데이터베이스에 컬럼 정보 직접 줄 수 있음
            ex) columnDefinition = "varchar(100) default 'EMPTY'"
        length(DDL)
        precision, scale(DDL) = BigDecimal, BigInteger등 큰 타입에서 사용
            - precision은 소수점 포함 전체 자리수, scale은 소수의자리수 (더블,플롯은 적용 안됨)

     */
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    //ORDINAL 사용X - ORDINAL이 기본값임 (enum 순서를 저장 0부터)
    //STRING 사용O - 권장 (enum 값을 그대로 저장)

    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    //최신 버전부터는 그냥 LocalDate, LocalDateTime 쓰면되고
    //예전 버전 프로젝트면 이렇게 매핑
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //varchar 넘는 큰 값
    @Lob
    private String description;


    public Member() {
    }
}
