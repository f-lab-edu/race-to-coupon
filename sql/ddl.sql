CREATE TABLE coupon_user (
    coupon_user_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 고유 번호',
    email VARCHAR(255) NOT NULL COMMENT '사용자 로그인 ID(EMAIL)',
    password VARCHAR(60) NOT NULL COMMENT '사용자 암호(BCrypt 적용)',
    name VARCHAR(30) NOT NULL COMMENT '닉네임',
    type VARCHAR(10) NOT NULL COMMENT '사용자 타입(유저, 운영자, 파트너)',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '사용자 정보 생성 시간',
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '사용자 정보 수정 시간'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE INDEX idx_coupon_user_email ON coupon_user (email);
CREATE INDEX idx_coupon_user_name ON coupon_user (name);

CREATE TABLE coupon_issue (
    coupon_issue_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '쿠폰 발행 번호',
    coupon_info_id BIGINT NOT NULL COMMENT '쿠폰 고유 번호',
    coupon_user_id BIGINT NOT NULL COMMENT '사용자 고유 번호',
    status VARCHAR(10) NOT NULL COMMENT '쿠폰 상태 (발행, 사용, 만료 등)',
    usable_start DATETIME NOT NULL COMMENT '쿠폰이 사용될 수 있는 시작 시간',
    usable_end DATETIME NOT NULL COMMENT '쿠폰이 사용될 수 있는 종료 시간',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '발행 쿠폰 생성 시간',
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '발행 쿠폰 수정 시간'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE INDEX idx_coupon_issue_userid_issueid_desc ON coupon_issue (coupon_user_id, coupon_issue_id DESC);
CREATE INDEX idx_coupon_issue_userid_usableend_desc ON coupon_issue (coupon_user_id, usable_end DESC);

CREATE TABLE coupon_info (
    coupon_info_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '쿠폰 고유 번호',
    coupon_user_id BIGINT NOT NULL COMMENT '사용자 고유 번호',
    code VARCHAR(10) COMMENT '쿠폰 입력 코드',
    title VARCHAR(90) NOT NULL COMMENT '쿠폰 이름 (한글 30자 제한)',
    description VARCHAR(100) COMMENT '쿠폰 설명',
    status VARCHAR(20) NOT NULL COMMENT '쿠폰 상태 (발급, 삭제 등)',
    type VARCHAR(20) NOT NULL COMMENT '상위 타입 (이벤트)',
    sub_type VARCHAR(20) NOT NULL COMMENT '하위 타입 (선착순, 여름맞이 행사)',
    discount_type VARCHAR(10) NOT NULL COMMENT '할인 타입 (정액, 정률)',
    discount_value INT NOT NULL COMMENT '할인 금액 또는 할인율',
    total_quantity INT NOT NULL COMMENT '총 발급 가능 수량',
    issued_quantity INT NOT null DEFAULT 0 COMMENT '사용자들이 다운로드 받은 수량',
    min_apply_amount INT DEFAULT 0 COMMENT '쿠폰 사용 하한 금액 0인 경우 제한 없음',
    max_apply_amount INT DEFAULT 0 COMMENT '쿠폰 사용 상한 금액 0인 경우 제한 없음',
    validate_days_after_download INT NOT NULL COMMENT '다운로드 후 사용 가능 일수 (3,5 등)',
    download_start DATETIME NOT NULL COMMENT '다운로드 시작 날짜',
    download_end DATETIME NOT NULL COMMENT '다운로드 종료 날짜',
    modify_user_id BIGINT COMMENT '수정한 사용자 키',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '쿠폰 생성 시간',
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '쿠폰 수정 시간'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;