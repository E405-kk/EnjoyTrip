USE `ssafytrip`;

DROP TABLE IF EXISTS `ssafytrip`.`notice` ;
DROP TABLE IF EXISTS `ssafytrip`.`tripplan`;
DROP TABLE IF EXISTS `ssafytrip`.`user` ;
DROP TABLE IF EXISTS `ssafytrip`.`file_info` ;
DROP TABLE IF EXISTS `ssafytrip`.`comment` ;
DROP TABLE IF EXISTS `ssafytrip`.`hotplace` ;
DROP TABLE IF EXISTS `ssafytrip`.`goods` ;
DROP TABLE IF EXISTS `ssafytrip`.`board` ;
DROP TABLE IF EXISTS `ssafytrip`.`plan` ;
DROP TABLE IF EXISTS `ssafytrip`.`plan_detail` ;
DROP TABLE IF EXISTS `ssafytrip`.`monthly` ;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`user` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(16) NOT NULL,
  `user_email` VARCHAR(50) NULL DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `is_admin` int DEFAULT '0',
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO user VALUES ("ssafy", "김싸피", "1234", "ssafy@ssafy.com", now(), 1);

CREATE TABLE IF NOT EXISTS `ssafytrip`.`board` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  KEY `board_to_user_user_id_fk` (`user_id`),
  CONSTRAINT `board_to_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`notice` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  KEY `notice_to_user_user_id_fk` (`user_id`),
  CONSTRAINT `notice_to_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `ssafytrip`.`tripplan`(
	`num` int NOT NULL auto_increment PRIMARY KEY,
    `user_id` varchar(16) DEFAULT NULL,
    `plan_list` varchar(5000) NOT NULL,
	CONSTRAINT `tripplan_to_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

USE `ssafytrip`;
DROP TABLE IF EXISTS `ssafytrip`.`slang`;

CREATE TABLE IF NOT EXISTS `ssafytrip`.`slang`(
	`num` int NOT NULL auto_increment PRIMARY KEY,
    `word` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `hotplace` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int DEFAULT '0',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `comment_cnt` int DEFAULT '0',
  `good` int DEFAULT '0',
  PRIMARY KEY (`article_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `file_info` (
	`idx` int NOT NULL AUTO_INCREMENT,
	`article_no` int NOT NULL,
	`save_folder` varchar(45) DEFAULT NULL,
	`original_file` varchar(50) DEFAULT NULL,
	`save_file` varchar(50) DEFAULT NULL,
	PRIMARY KEY (`idx`),
	KEY `file_to_hotplace_article_no_fk` (`article_no`),
	CONSTRAINT `file_to_hotplace_article_no_fk` FOREIGN KEY (`article_no`) REFERENCES `hotplace` (`article_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE IF NOT EXISTS `comment` (
	`idx` int NOT NULL AUTO_INCREMENT,
	`article_no` int NOT NULL,
	`user_id` varchar(16) NOT NULL,
	`content` varchar(2000) DEFAULT NULL,
	`register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`idx`),
	KEY `comment_to_board_article_no_fk` (`article_no`),
	CONSTRAINT `comment_to_board_article_no_fk` FOREIGN KEY (`article_no`) REFERENCES `board` (`article_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


CREATE TABLE monthly (
    idx INT AUTO_INCREMENT PRIMARY KEY,
    mon INT,
    region VARCHAR(100),
    title VARCHAR(100),
    latitude DECIMAL(20,17),
    longitude DECIMAL(20,17)
);

CREATE TABLE `goods` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) NOT NULL,
  `article_no` int NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `goods_to_user_user_id_fk` (`user_id`),
  KEY `goods_to_hotplace_article_no_fk` (`article_no`),
  CONSTRAINT `goods_to_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES user (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `goods_to_hotplace_article_no_fk` FOREIGN KEY (`article_no`) REFERENCES hotplace (`article_no`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `plan` (
  `plan_id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) NOT NULL,
  `start_date` timestamp,
  `end_date` timestamp,
  `comment` varchar(300),
  PRIMARY KEY (`plan_id`),
   KEY `plan_to_user_user_id_fk` (`user_id`),
  CONSTRAINT `plan_to_user_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `plan_detail` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `plan_id` int NOT NULL,
  `content_id` int NOT NULL,
  `plan_order` int NOT NULL,
  PRIMARY KEY (`idx`),
   KEY `plan_detail_to_plan_plan_id_fk` (`plan_id`),
   KEY `plan_detail_to_attraction_info_content_id_fk` (`content_id`),
  CONSTRAINT `plan_detail_to_plan_plan_id_fk` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`plan_id`) ON DELETE CASCADE,
  CONSTRAINT `plan_detail_to_attraction_info_content_id_fk` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO monthly (mon, region, title, latitude, longitude) VALUES
(1, '강원도 평창', '대관령 양떼목장', 37.67140000000000000, 128.71430000000000000),
(1, '강원도 강릉', '정동진', 37.68970000000000000, 129.03350000000000000),
(1, '제주도', '한라산', 33.36170000000000000, 126.52920000000000000),
(1, '서울', '남산서울타워', 37.55120000000000000, 126.98820000000000000),
(1, '경기도 포천', '아트밸리', 37.95610000000000000, 127.28780000000000000),
(1, '충청남도 태안', '꽃지해변', 36.51660000000000000, 126.31810000000000000),
(1, '경상북도 경주', '불국사', 35.78930000000000000, 129.33110000000000000),
(1, '강원도 홍천', '비발디파크', 37.64810000000000000, 127.68870000000000000),
(1, '전라북도 무주', '덕유산', 35.89680000000000000, 127.74750000000000000),
(1, '경기도 가평', '아침고요수목원', 37.74330000000000000, 127.35330000000000000),

(2, '경기도 가평', '남이섬', 37.79020000000000000, 127.52500000000000000),
(2, '부산', '태종대', 35.05140000000000000, 129.08530000000000000),
(2, '전라남도 담양', '죽녹원', 35.32170000000000000, 126.98410000000000000),
(2, '강원도 속초', '설악산', 38.11960000000000000, 128.46560000000000000),
(2, '서울', '북촌 한옥마을', 37.58260000000000000, 126.98560000000000000),
(2, '경상남도 통영', '동피랑 마을', 34.83820000000000000, 128.42940000000000000),
(2, '강원도 인제', '빙어축제', 38.06940000000000000, 128.17070000000000000),
(2, '충청북도 제천', '청풍호반', 37.00790000000000000, 128.15120000000000000),
(2, '전라북도 전주', '한옥마을', 35.81510000000000000, 127.14800000000000000),
(2, '제주도', '섭지코지', 33.42370000000000000, 126.92200000000000000),

(3, '전라남도 광양', '매화마을', 34.94040000000000000, 127.58740000000000000),
(3, '경기도 양평', '두물머리', 37.54680000000000000, 127.32170000000000000),
(3, '서울', '경복궁', 37.57960000000000000, 126.97700000000000000),
(3, '경상북도 경주', '첨성대', 35.83450000000000000, 129.21930000000000000),
(3, '부산', '광안리 해수욕장', 35.15350000000000000, 129.11860000000000000),
(3, '강원도 강릉', '오죽헌', 37.78920000000000000, 128.88280000000000000),
(3, '제주도', '한담해변', 33.45030000000000000, 126.29440000000000000),
(3, '전라북도 군산', '경암동 철길마을', 35.97320000000000000, 126.71210000000000000),
(3, '충청남도 부여', '부소산성', 36.27650000000000000, 126.91450000000000000),
(3, '경상남도 하동', '쌍계사', 35.24240000000000000, 127.59540000000000000),

(4, '경상남도 진해', '군항제', 35.15340000000000000, 128.68110000000000000),
(4, '서울', '여의도 윤중로', 37.52720000000000000, 126.93360000000000000),
(4, '전라남도 구례', '산수유꽃축제', 35.20210000000000000, 127.46270000000000000),
(4, '제주도', '한라산 영실코스', 33.38420000000000000, 126.51900000000000000),
(4, '강원도 강릉', '경포대', 37.79500000000000000, 128.90380000000000000),
(4, '경기도 안성', '팜랜드', 37.01130000000000000, 127.22650000000000000),
(4, '경상북도 청도', '소싸움축제', 35.64300000000000000, 128.70300000000000000),
(4, '충청북도 청주', '청남대', 36.51820000000000000, 127.49240000000000000),
(4, '부산', '달맞이길', 35.16000000000000000, 129.17160000000000000),
(4, '전라북도 익산', '미륵사지', 35.86640000000000000, 127.01150000000000000),

(5, '전라북도 전주', '한옥마을', 35.81510000000000000, 127.14800000000000000),
(5, '제주도', '우도', 33.49960000000000000, 126.94920000000000000),
(5, '강원도 춘천', '남이섬', 37.79020000000000000, 127.52500000000000000),
(5, '서울', '남산서울타워', 37.55120000000000000, 126.98820000000000000),
(5, '경상남도 남해', '독일마을', 34.73760000000000000, 127.89280000000000000),
(5, '전라남도 보성', '녹차밭', 34.77160000000000000, 127.08070000000000000),
(5, '충청북도 단양', '도담삼봉', 36.97840000000000000, 128.36510000000000000),
(5, '경기도 가평', '아침고요수목원', 37.74330000000000000, 127.35330000000000000),
(5, '부산', '해운대', 35.15870000000000000, 129.16040000000000000),
(5, '강원도 양양', '설악산 오색약수터', 38.11960000000000000, 128.46560000000000000),

(6, '제주도', '성산 일출봉', 33.45830000000000000, 126.94160000000000000),
(6, '경상북도 포항', '호미곶', 36.07470000000000000, 129.56620000000000000),
(6, '부산', '광안대교', 35.15350000000000000, 129.11860000000000000),
(6, '충청북도 제천', '청풍호반', 37.00790000000000000, 128.15120000000000000),
(6, '강원도 평창', '대관령 양떼목장', 37.67140000000000000, 128.71430000000000000),
(6, '서울', '롯데월드', 37.51100000000000000, 127.09800000000000000),
(6, '경기도 가평', '가평펜션', 37.83130000000000000, 127.50960000000000000),
(6, '강원도 속초', '속초해수욕장', 38.20420000000000000, 128.59100000000000000),
(6, '전라북도 부안', '변산반도', 35.69150000000000000, 126.57140000000000000),
(6, '경상남도 거제', '외도 보타니아', 34.62980000000000000, 128.66170000000000000),

(7, '제주도', '중문 해수욕장', 33.24950000000000000, 126.40960000000000000),
(7, '강원도 양양', '낙산사', 38.12240000000000000, 128.62500000000000000),
(7, '경상남도 남해', '상주해수욕장', 34.68110000000000000, 127.90760000000000000),
(7, '전라남도 여수', '오동도', 34.73670000000000000, 127.78020000000000000),
(7, '충청남도 태안', '만리포해수욕장', 36.80350000000000000, 126.12530000000000000),
(7, '서울', '롯데월드', 37.51100000000000000, 127.09800000000000000),
(7, '경기도 가평', '가평펜션', 37.83130000000000000, 127.50960000000000000),
(7, '강원도 속초', '속초해수욕장', 38.20420000000000000, 128.59100000000000000),
(7, '전라북도 부안', '변산반도', 35.69150000000000000, 126.57140000000000000),
(7, '경상남도 하동', '섬진강', 35.12780000000000000, 127.72500000000000000),

(8, '부산', '해운대 해수욕장', 35.15870000000000000, 129.16040000000000000),
(8, '제주도', '협재해수욕장', 33.39610000000000000, 126.23990000000000000),
(8, '강원도 속초', '설악산 비룡폭포', 38.11960000000000000, 128.46560000000000000),
(8, '전라남도 여수', '엑스포해양공원', 34.76040000000000000, 127.66240000000000000),
(8, '경상북도 울릉도', '울릉도', 37.50000000000000000, 130.86000000000000000),
(8, '경기도 화성', '전곡항', 37.17670000000000000, 126.63030000000000000),
(8, '충청남도 보령', '대천해수욕장', 36.32020000000000000, 126.48960000000000000),
(8, '서울', '강동아트센터', 37.55050000000000000, 127.15500000000000000),
(8, '경상남도 거제', '외도 보타니아', 34.62980000000000000, 128.66170000000000000),
(8, '강원도 홍천', '비발디파크 오션월드', 37.64810000000000000, 127.68870000000000000),

(9, '경상북도 안동', '하회마을', 36.54010000000000000, 128.51650000000000000),
(9, '제주도', '산방산', 33.23750000000000000, 126.30720000000000000),
(9, '강원도 평창', '오대산', 37.78900000000000000, 128.59560000000000000),
(9, '충청남도 서산', '해미읍성', 36.71310000000000000, 126.54860000000000000),
(9, '서울', '북촌 한옥마을', 37.58260000000000000, 126.98560000000000000),
(9, '전라남도 담양', '메타세쿼이아 길', 35.32170000000000000, 126.98410000000000000),
(9, '경상남도 통영', '동피랑 벽화마을', 34.83820000000000000, 128.42940000000000000),
(9, '경기도 남양주', '남양주 한강변', 37.63400000000000000, 127.21600000000000000),
(9, '부산', '감천문화마을', 35.09750000000000000, 129.03580000000000000),
(9, '강원도 춘천', '소양강댐', 37.95180000000000000, 127.82930000000000000),

(10, '강원도 설악산', '설악산', 38.11960000000000000, 128.46560000000000000),
(10, '서울', '남산', 37.55120000000000000, 126.98820000000000000),
(10, '경상북도 경주', '불국사', 35.78930000000000000, 129.33110000000000000),
(10, '전라북도 정읍', '내장산', 35.51040000000000000, 126.86160000000000000),
(10, '충청북도 단양', '도담삼봉', 36.97840000000000000, 128.36510000000000000),
(10, '경기도 가평', '아침고요수목원', 37.74330000000000000, 127.35330000000000000),
(10, '부산', '태종대', 35.05140000000000000, 129.08530000000000000),
(10, '제주도', '한라산', 33.36170000000000000, 126.52920000000000000),
(10, '전라남도 순천', '순천만', 34.89730000000000000, 127.50920000000000000),
(10, '강원도 강릉', '경포대', 37.79500000000000000, 128.90380000000000000),

(11, '경기도 양평', '두물머리', 37.54680000000000000, 127.32170000000000000),
(11, '충청남도 공주', '공산성', 36.46910000000000000, 127.12470000000000000),
(11, '경상남도 합천', '해인사', 35.80170000000000000, 128.10330000000000000),
(11, '서울', '창덕궁 후원', 37.58230000000000000, 126.99110000000000000),
(11, '제주도', '송악산', 33.23110000000000000, 126.30500000000000000),
(11, '강원도 원주', '소금산 출렁다리', 37.35740000000000000, 127.95120000000000000),
(11, '전라남도 보성', '율포해수욕장', 34.72550000000000000, 127.07740000000000000),
(11, '경상북도 청송', '주왕산', 36.36980000000000000, 129.03260000000000000),
(11, '경기도 수원', '화성행궁', 37.28080000000000000, 126.97510000000000000),
(11, '강원도 홍천', '청평사', 37.69760000000000000, 127.80860000000000000),

(12, '경기도 가평', '소양강 스카이워크', 37.76990000000000000, 127.57230000000000000),
(12, '전라남도 진도', '진도 바람의 언덕', 34.48170000000000000, 126.30590000000000000),
(12, '강원도 양양', '삼청교', 38.09640000000000000, 128.63590000000000000),
(12, '전라북도 순창', '백양사', 35.37410000000000000, 127.02780000000000000),
(12, '경상북도 울진', '동해바다 김치년 무인도', 37.07380000000000000, 130.97390000000000000),
(12, '전라남도 구례', '천왕사', 35.24470000000000000, 127.45330000000000000),
(12, '경기도 안성', '안성맞춤 추장파크', 37.01460000000000000, 127.28430000000000000),
(12, '강원도 인제', '두물머리 펜션마을', 38.08500000000000000, 128.18240000000000000),
(12, '충청남도 공주', '백제문화단지', 36.48630000000000000, 127.24680000000000000),
(12, '경기도 화성', '융건릉', 37.10920000000000000, 127.12930000000000000);
INSERT INTO slang (word)
VALUES 
("ㅅㅂ"),
("시발"),
("병신"),
("ㅂㅅ"),
("미친새끼"),
("존나"),
("ㅈㄴ"),
("ㅈㄹ"),
("지랄"),
("씹"),
("니미"),
("니애미"),
("병신"),
("ㅂㅅ"),
("개새끼"),
("씨빨"),
("닥쳐"),
("또라이"),
("걸레"),
("좆밥");
