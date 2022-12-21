create table authority
(
    id   bigint unsigned auto_increment comment '권한 id'
        primary key,
    type varchar(16) not null comment '권한 타입'
)
    comment '권한';

create table organization
(
    id         bigint unsigned auto_increment comment '조직도 id'
        primary key,
    parents_id bigint unsigned     not null comment '상위 조직 id',
    depth      int unsigned        not null comment '조직 레벨',
    name       varchar(32)         not null comment '조직이름',
    use_yn     char(2) default 'N' not null comment '사용여부',
    constraint organization_organization_id_fk
        foreign key (parents_id) references organization (id)
)
    comment '조직도';

create table user
(
    id              bigint unsigned auto_increment comment '유저 id'
        primary key,
    organization_id bigint unsigned                          not null comment '조직 id',
    authority_id    bigint unsigned                          not null comment '권한 번호',
    email           varchar(256)                             not null comment '이메일',
    birth           date                                     not null comment '생년월일',
    phone           char(32)                                 not null comment '휴대폰 번호',
    status          varchar(8)                               not null comment '유저 상태',
    position        varchar(8)                               not null comment '직책',
    start_work      datetime(6)                              not null comment '입사일',
    end_work        datetime(6)                              null comment '퇴사일',
    address         varchar(256)                             not null comment '주소',
    created_at      datetime(6) default CURRENT_TIMESTAMP(6) not null comment '생성일',
    updated_at      datetime(6) default CURRENT_TIMESTAMP(6) not null on update CURRENT_TIMESTAMP(6) comment '수정일',
    constraint user_authority_id_fk
        foreign key (authority_id) references authority (id),
    constraint user_organization_id_fk
        foreign key (organization_id) references organization (id)
)
    comment '유저';

create table attendance
(
    id         bigint unsigned auto_increment comment '출퇴근 id'
        primary key,
    user_id    bigint unsigned                          not null comment '유저 id',
    clock_time datetime                                 not null comment '출퇴근 날짜',
    ip         varchar(20)                              null comment 'ip',
    location   varchar(200)                             null comment '위치',
    memo       varchar(200)                             null comment '메모',
    device     varchar(10)                              null comment '출근 디바이스',
    created_at datetime(6) default CURRENT_TIMESTAMP(6) not null comment '생성일',
    updated_at datetime(6) default CURRENT_TIMESTAMP(6) not null on update CURRENT_TIMESTAMP(6) comment '수정일',
    constraint attendance_user_id_fk
        foreign key (user_id) references user (id)
)
    comment '출퇴근';

create table user_vacation
(
    id          bigint unsigned auto_increment comment '휴가 id'
        primary key,
    user_id     bigint unsigned                          not null comment '유저 id',
    count       float                                    not null comment '부여 휴가 일수',
    expiry_date date                                     not null comment '만료일',
    type        varchar(16)                              not null comment '연차 종류',
    paid        char(2)     default 'N'                  not null comment '유급 휴가 여부',
    remain      float                                    not null comment '남은 휴가 일수',
    used_yn     char(2)     default 'N'                  null comment '사용완료 여부',
    created_at  datetime(6) default CURRENT_TIMESTAMP(6) not null comment '생성일',
    updated_at  datetime(6) default CURRENT_TIMESTAMP(6) not null on update CURRENT_TIMESTAMP(6) comment '수정일',
    constraint user_vacation_user_id_fk
        foreign key (user_id) references user (id)
)
    comment '연차';

create table user_vacation_log
(
    id               bigint unsigned auto_increment comment '휴가 로그 id'
        primary key,
    user_vacation_id bigint unsigned                          not null comment '휴가 id',
    status           varchar(8)  default 'W'                  not null comment '휴가 상태 (W: 대기, P: 승인, F:반려, C:취소)',
    date             date        default (curdate())          not null comment '날짜',
    created_at       datetime(6) default CURRENT_TIMESTAMP(6) not null comment '생성일',
    updated_at       datetime(6) default CURRENT_TIMESTAMP(6) not null on update CURRENT_TIMESTAMP(6) comment '수정일',
    constraint user_vacation_log_user_vacation_id_fk
        foreign key (user_vacation_id) references user_vacation (id)
)
    comment '연차 로그';

create table work_type
(
    id       int unsigned auto_increment comment '출퇴근 유형 id'
        primary key,
    name     varchar(20) not null comment '출퇴근 유형 이름',
    on_time  varchar(32) null comment '출근 시간',
    off_time varchar(32) null comment '퇴근시간'
)
    comment '출퇴근 유형';

create table organization_work_type
(
    id              int unsigned auto_increment comment '조직 출퇴근 유형 id'
        primary key,
    work_type_id    int unsigned    not null comment '출퇴근 유형 id',
    organization_id bigint unsigned not null comment '조직도 id',
    constraint organization_work_type_organization_id_fk
        foreign key (organization_id) references organization (id),
    constraint organization_work_type_work_type_id_fk
        foreign key (work_type_id) references work_type (id)
)
    comment '조직 출퇴근 유형';

