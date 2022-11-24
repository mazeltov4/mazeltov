CREATE TABLE organization
(
    id         BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '조직 id',
    parents_id BIGINT UNSINGED COMMENT '상위 조직 id',
    depth      INT UNSINGED NOT NULL COMMENT '조직 레벨',
    name       VARCHAR(32)         NOT NULL COMMENT '조직 이름',
    use_yn     CHAR(2) DEFAULT 'N' NOT NULL COMMENT '조직 사용 여부'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='조직도';


CREATE TABLE organization_work_type
(
    id              INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '조직 출퇴근 유형 PK',
    work_type_id    INT UNSIGNED NOT NULL COMMENT '출퇴근 유형 id',
    organization_id BIGINT UNSINGED NOT NULL COMMENT '조직 id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='조직 출퇴근 유형';


CREATE TABLE work_type
(
    id       INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '출퇴근 유형 id',
    name     VARCHAR(32) NOT NULL COMMENT '출퇴근 유형 이름',
    on_time  VARCHAR(32) COMMENT '출근 시간',
    off_time VARCHAR(32) COMMENT '퇴근 시간'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='출퇴근 유형';


CREATE TABLE authority
(
    id   BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '권한 id',
    type VARCHAR(16) NOT NULL COMMENT '권한 타입',
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='권한';


CREATE TABLE user
(
    id              BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '유저 id',
    organization_id BIGINT UNSIGNED NOT NULL COMMENT '조직 id',
    authority_id    INT UNSIGNED NOT NULL COMMENT '권한 id',
    email           VARCHAR(256) NOT NULL COMMENT '이메일',
    birth           DATE         NOT NULL COMMENT '생년월일',
    phone           VARCHAR(32)  NOT NULL COMMENT '휴대폰 번호',
    status          VARCHAR(8)   NOT NULL COMMENT '유저 상태',
    position        VARCHAR(8)   NOT NULL COMMENT '유저 직급',
    start_work      DATETIME(6) NOT NULL COMMENT '유저 입사일',
    end_work        DATETIME(6) COMMENT '유저 퇴사일',
    address         VARCHAR(256) NOT NULL COMMENT '유저 주소',
    created_at      DATETIME(6) NOT NULL DEFALT CURRENT_TIMESTAMP (6) COMMENT '생성일자',
    updated_at      DATETIME(6) DEFAULT CURRENT_TIMESTAMP (6) ON UPDATE CURRENT_TIMESTAMP (6) NOT NULL COMMENT '수정 일자'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='유저';



CREATE TABLE user_vacation
(
    id          BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '휴가 id',
    user_id     BIGINT UNSIGNED NOT NULL COMMENT '유저 id',
    expiry_date DATE                NOT NULL COMMENT '만료일',
    type        VARCHAR(16)         NOT NULL COMMENT '휴가 종류',
    paid        CHAR(2) DEFAULT 'N' NOT NULL COMMENT '유급 휴가 여부',
    remain      FLOAT               NOT NULL COMMENT '남은 휴가 일수',
    count       FLOAT               NOT NULL COMMENT '부여 휴가 일수',
    used_yn     CHAR(2) DEFALT 'N' NOT NULL '사용 완료 여부',
    created_at  DATETIME(6) NOT NULL DEFALT CURRENT_TIMESTAMP (6) COMMENT '생성일자',
    updated_at  DATETIME(6) DEFAULT CURRENT_TIMESTAMP (6) ON UPDATE CURRENT_TIMESTAMP (6) NOT NULL COMMENT '수정 일자'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='휴가';


CREATE TABLE user_vacation_log
(
    id               BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '휴가 로그 id',
    user_vacation_id BIGINT UNSIGNED NOT NULL COMMENT '휴가 id',
    status           VARCHAR(8)    DEFAULT 'W' NOT NULL COMMENT '휴가 상태(W:대기, P:승인, F:반려, C:취소)',
    use_date         DATE NOT NULL DEFAULT CURRENT_DATE COMMENT '휴가 일자',
    created_at       DATETIME(6) NOT NULL DEFALT CURRENT_TIMESTAMP (6) COMMENT '생성일자',
    updated_at       DATETIME(6) DEFAULT CURRENT_TIMESTAMP (6) ON UPDATE CURRENT_TIMESTAMP (6) NOT NULL COMMENT '수정 일자'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='휴가 로그';
