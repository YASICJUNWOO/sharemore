INSERT INTO USERS (name, email, password, address)
VALUES ('김철수', 'chulsoo1@example.com', 'password123', '서울특별시 강남구'),
       ('이영희', 'younghee2@example.com', 'password456', '서울특별시 서초구'),
       ('박지성', 'jisung3@example.com', 'password789', '서울특별시 송파구'),
       ('최유나', 'yuna4@example.com', 'password321', '서울특별시 강동구'),
       ('김태현', 'taehyun5@example.com', 'password654', '서울특별시 관악구'),
       ('이민준', 'minjun6@example.com', 'password987', '서울특별시 양천구'),
       ('박서아', 'seoah7@example.com', 'password741', '서울특별시 동작구'),
       ('최준영', 'junyoung8@example.com', 'password852', '서울특별시 성동구'),
       ('김하은', 'haeun9@example.com', 'password963', '서울특별시 중랑구'),
       ('이지아', 'jia10@example.com', 'password159', '서울특별시 강북구'),
       ('공준우', 'joonoo3@inha.edu', 'hwilove', '성남시 분당구'),
       ('정휘진', 'hwiollf@naver.edu', 'konglove', '성남시 분당구');

INSERT INTO item (owner_id, name, description, category, price,item_image, created_at, like_count)
VALUES (1, '데스크탑용 책상', '나무로 만든 책상', '가구', 50000, 'https://source.unsplash.com/random/?desk','1995-01-01',0),
       (2, '플라스틱 의자', '편안한 의자', '가구', 30000, 'https://source.unsplash.com/random/?chair',now(),0),
       (3, '그램 2023 15인치', '빠른 속도의 노트북', '전자제품', 1000000, 'https://source.unsplash.com/random/?laptop',now(),0),
       (4, '아이폰 15 pro 상태 좋아요','새로운 모델의 휴대폰', '전자제품', 800000, 'https://source.unsplash.com/random/?mobile',now(),0),
       (5, '티엠포 빌려드려요', '편안한 축구화', '의류', 70000, 'https://source.unsplash.com/random/?sneakers',now(),0),
       (6, '폴로 셔츠', '좋은 셔츠', '의류', 40000, 'https://source.unsplash.com/random/?shirt',now(),0),
       (7, '일리 커피머신', '아침에 좋은 커피머신', '주방용품', 200000, 'https://source.unsplash.com/random/?coffeemachine',now(),0),
       (8, '삼성 비스포크 냉장고', '넓은 냉장고', '가전제품', 1500000, 'https://source.unsplash.com/random/?refrigerator',now(),0),
       (9, '삼성 에어컨', '시원한 에어컨', '가전제품', 1200000, 'https://source.unsplash.com/random/?airconditioner',now(),0),
       (10, 'mtb 자전거', '편안한 자전거', '운동용품', 300000, 'https://source.unsplash.com/random/?bicycle',now(),0),
       (1, '나이키 에어포스', '편안한 운동화', '의류', 80000, 'https://source.unsplash.com/random/?sneakers',now(),0),
       (2, '에이스 침대 장기 렌탈', '푹신한 침대', '가구', 250000, 'https://source.unsplash.com/random/?bed',now(),0),
       (3, 'LG TV 15인치', '큰 화면의 TV', '가전제품', 2000000, 'https://source.unsplash.com/random/?tv',now(),0),
       (4, '에어팟 3세대', '소리가 좋은 이어폰', '전자제품', 150000, 'https://source.unsplash.com/random/?earphones',now(),0),
       (5, '소니 블루투스 헤드폰', '소리가 좋은 헤드폰', '전자제품', 200000, 'https://source.unsplash.com/random/?headphones',now(),0),
       (6, '엔티크 식탁', '나무로 만든 식탁', '가구', 150000, 'https://source.unsplash.com/random/?diningtable',now(),0),
       (7, '책장', '나무로 만든 책장', '가구', 100000, 'https://source.unsplash.com/random/?bookshelf',now(),0),
       (8, '리클라이너 소파', '편안한 소파', '가구', 300000, 'https://source.unsplash.com/random/?sofa',now(),0),
       (9, '세탁기', '능률적인 세탁기', '가전제품', 800000, 'https://source.unsplash.com/random/?washingmachine',now(),0),
       (10, '리바이스 청바지', '편안한 청바지', '의류', 50000, 'https://source.unsplash.com/random/?jeans',now(),0),
       (1, '책상', '나무로 만든 책상', '가구', 55000, 'https://source.unsplash.com/random/?desks',now(),0),
       (11,'나이키 머큐리얼 상태 좋음','축구화','운동용품',100000,'https://source.unsplash.com/random/?soccer',now(),0);
/*VALUES (1, '데스크탑용 책상', '나무로 만든 책상', '가구', 50000, 'https://source.unsplash.com/random','1995-01-01',0),
       (2, '플라스틱 의자', '편안한 의자', '가구', 30000, 'https://source.unsplash.com/random',now(),0),
       (3, '그램 2023 15인치', '빠른 속도의 노트북', '전자제품', 1000000, 'https://source.unsplash.com/random',now(),0),
       (4, '아이폰 15 pro 상태 좋아요','새로운 모델의 휴대폰', '전자제품', 800000, 'https://source.unsplash.com/random',now(),0),
       (5, '티엠포 빌려드려요', '편안한 축구화', '의류', 70000, 'https://source.unsplash.com/random',now(),0),
       (6, '폴로 셔츠', '좋은 셔츠', '의류', 40000, 'https://source.unsplash.com/random',now(),0),
       (7, '일리 커피머신', '아침에 좋은 커피머신', '주방용품', 200000, 'https://source.unsplash.com/random',now(),0),
       (8, '삼성 비스포크 냉장고', '넓은 냉장고', '가전제품', 1500000, 'https://source.unsplash.com/random',now(),0),
       (9, '삼성 에어컨', '시원한 에어컨', '가전제품', 1200000, 'https://source.unsplash.com/random',now(),0),
       (10, 'mtb 자전거', '편안한 자전거', '운동용품', 300000, 'https://source.unsplash.com/random',now(),0),
       (1, '나이키 에어포스', '편안한 운동화', '의류', 80000, 'https://source.unsplash.com/random',now(),0),
       (2, '에이스 침대 장기 렌탈', '푹신한 침대', '가구', 250000, 'https://source.unsplash.com/random',now(),0),
       (3, 'LG TV 15인치', '큰 화면의 TV', '가전제품', 2000000, 'https://source.unsplash.com/random',now(),0),
       (4, '에어팟 3세대', '소리가 좋은 이어폰', '전자제품', 150000, 'https://source.unsplash.com/random',now(),0),
       (5, '소니 블루투스 헤드폰', '소리가 좋은 헤드폰', '전자제품', 200000, 'https://source.unsplash.com/random',now(),0),
       (6, '엔티크 식탁', '나무로 만든 식탁', '가구', 150000, 'https://source.unsplash.com/random',now(),0),
       (7, '책장', '나무로 만든 책장', '가구', 100000, 'https://source.unsplash.com/random',now(),0),
       (8, '리클라이너 소파', '편안한 소파', '가구', 300000, 'https://source.unsplash.com/random',now(),0),
       (9, '세탁기', '능률적인 세탁기', '가전제품', 800000, 'https://source.unsplash.com/random',now(),0),
       (10, '리바이스 청바지', '편안한 청바지', '의류', 50000, 'https://source.unsplash.com/random',now(),0),
       (1, '책상', '나무로 만든 책상', '가구', 55000, 'https://source.unsplash.com/random',now(),0),
       (11,'나이키 머큐리얼 상태 좋음','축구화','운동용품',100000,'https://source.unsplash.com/random',now(),0);*/


INSERT INTO review (reviewer_id, item_id, comment, rating)
VALUES (1, 21, '정말 좋아요', 4),
       (3, 21, '아쉬워요', 2),
       (7, 21, '괜찮아요', 3);

INSERT INTO chat_room ()
VALUES (),
       (),
       ();

INSERT INTO chat_room_user (chat_room_id, user_id)
VALUES (1, 11),
       (1, 12),
       (2, 11),
       (2, 3),
       (3, 11),
       (3, 4);

INSERT INTO Coupon (discount_type,coupon_type, coupon_name, discount_amount, discount_rate, minimum_price, maximum_discount_amount, expire_date, coupon_image_url)
VALUES ('PERCENT','month', '5% 할인 쿠폰', 0, 5, 10000, 1000, '2023-12-19 02:00:00','https://i.ibb.co/WBwFFDv/coupon5.png'),
       ('PERCENT','month', '10% 할인 쿠폰', 0, 10, 20000, 2000, '2023-12-19 02:00:00','https://i.ibb.co/WBwFFDv/coupon5.png'),
       ('PERCENT','month', '15% 할인 쿠폰', 0, 15, 30000, 3000, '2023-12-19 02:00:00','https://i.ibb.co/WBwFFDv/coupon5.png'),
         ('PERCENT','category', '가전 카테고리 쿠폰', 0, 5, 10000, 1000, '2023-12-19 02:00:00','https://i.ibb.co/NxrfMdw/coupon.png'),
         ('PERCENT','category', '의류 카테고리 쿠폰', 0, 10, 20000, 2000, '2023-12-19 02:00:00','https://i.ibb.co/NxrfMdw/coupon.png'),
         ('PERCENT','category', '가구 카테고리 쿠폰', 0, 15, 30000, 3000, '2023-12-19 02:00:00','https://i.ibb.co/NxrfMdw/coupon.png');

INSERT INTO user_coupon (user_id, coupon_id, used)
VALUES (11, 2, false);
