# SHARE MORE
#### "ShareMore: 더 나누고, 더 절약하는 물건 공유 서비스"

![36 (1)](https://github.com/YASICJUNWOO/sharemore/assets/99794552/36983da0-c755-463a-bb34-81c22ffadb57)

<p align="middle"></p>

## 🌳 프로젝트 소개

- "ShareMore"은 사용자들이 불필요한 물건을 공유하고, 필요한 물건을 저렴하게 이용할 수 있는 플랫폼입니다.
<p>

- 이를 통해 사용자들은 물건의 사용 효율을 높이고, 불필요한 소비를 줄일 수 있습니다.



## ⚒ 프로젝트 기술 스택
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- MySQL
- Elastic Search, Kibana
- Redis, Kafka
- AWS EC2, CodeDeploy, S3, RDS (진행중)

![sharemoreerd](https://github.com/YASICJUNWOO/sharemore/assets/99794552/1a34dfd6-68aa-47c3-8af8-5bb895600e7b)
## 🎯 ERD

![sharemoreerd](https://github.com/YASICJUNWOO/sharemore/assets/99794552/da6b47dd-9d62-477f-bc50-2f8705a07c47)

## 🎯 시스템 아키텍처
<img width="1296" alt="AWS cloud diagram (Community)" src="https://github.com/YASICJUNWOO/sharemore/assets/99794552/7cd315df-4601-4be1-a5b3-3241dfdf2675">

## 🎯 Kafka 구조
![Frame 2 (2)](https://github.com/YASICJUNWOO/sharemore/assets/99794552/34635e95-0b09-412d-a932-f57d40f7f676)


## 개발 내용
<p>
  
#### 1. 물건 등록
  사용자는 공유하고자 하는 물건의 사진(진행중), 설명, 가격 등을 등록할 수 있습니다. 또한, 물건의 상태와 이용 가능 기간 등을 명시하게 됩니다.
<p>

#### 2. 물건 검색 및 카테고리화
사용자는 키워드 검색을 통해 원하는 물건을 찾을 수 있습니다. 또한, 물건들은 카테고리별로 분류되어, 사용자가 원하는 카테고리를 선택하여 물건을 찾을 수 있습니다.
<p>
  
#### 3. 예약 및 결제 시스템
사용자는 원하는 물건을 예약하고(완료), 다양한 결제 방식을 통해 결제할 수 있습니다.
<p>
  
#### 4. 후기 및 평점 시스템
  사용자는 공유된 물건에 대한 사용 후기와 평점을 남길 수 있습니다. 이를 통해 물건의 품질을 보장하고, 다른 사용자의 선택을 돕습니다.
<p>
  
#### 5. 보안 및 신뢰도(진행중)
사용자 인증, 거래 완료 확인 등의 시스템을 통해 사용자의 신뢰를 확보하고, 안전한 거래를 보장합니다.

## 페이지
<p>

### ✅ 홈 페이지
<p align="center">
  <img src="https://github.com/YASICJUNWOO/sharemore/assets/99794552/106ccda8-480d-4b31-9d9d-32d5994f9a85" align="center" width="49%" height = "100%">
  <img src="https://github.com/YASICJUNWOO/sharemore/assets/99794552/7e17a335-4a87-44bd-b6c6-a3d6c4ab09a7" align="center" width="49%" height = "100%">
</p>

- **광고 배너**
- **인기 검색어**

  ➡️ Redis Zset 사용하여 인기 검색어 10개 관리
  
- **사용자 최근 탐색 관련 아이템 추천**
  
  ➡️ Redis Zset. Elastic Search 사용하여 관리
  
<br/>

### ✅상품 리스트
<p align="center">
  <img src="https://github.com/YASICJUNWOO/sharemore/assets/99794552/47d0fdac-4fc9-4f5c-9100-b19b25115c1d" align="center" width="80%" height = "100%">
</p>

- **상품 조건에 따라 필터링**
  - 카테고리
  - 가격 조건
  - 최신 순
  - 인기 순

- **최근 탐색 아이템**
  
  ➡️ Redis Zset 사용하여 최근 탐색 아이템 5개 관리
  
- **아이템 검색**
  
  ➡️ Elastic Search 사용하여 관리
  

  ➡️ nori 한글 형태소 분석기 사용하여 analize, tokenize 하였습니다.
  

<br/>

### ✅상품 상세 페이지
<p align="center">
  <img src="https://github.com/YASICJUNWOO/sharemore/assets/99794552/539c8b05-1e91-4fe9-ba80-f170ffbcdd80" align="center" width="49%" height = "100%">
  <img src="https://github.com/YASICJUNWOO/sharemore/assets/99794552/82aee6ee-7547-4f5a-bc77-21ca168f1c49" align="center" width="49%" height = "100%">
</p>


- **조회수 제공**
  
  ➡️ Redis List 사용하여 아이템 별 조회수 관리
  
  ➡️ (Redis Set , Date , 사용자 id) 사용하여 날짜 / 사용자 별 중복 조회 수 count 방지
  
- **날짜별 예약 상태**
  
  ➡️ 해당 날짜의 예약 중, 예약 건수 제공
  
  ➡️ (예정) QueryDSL로 리팩토링 예정

- **사용자 리뷰**
  
  ➡️ 실제 아이템 예약자들에 한해 리뷰 작성

<br/>


### ✅상품 예약 페이지
<p align="center">
  <img src="https://github.com/YASICJUNWOO/sharemore/assets/99794552/042bdd3f-9cef-4332-b015-c8b9fd65a1bd" align="center" width="49%" height = "100%">
  <img src="https://github.com/YASICJUNWOO/sharemore/assets/99794552/92421f9e-4e27-4454-92a7-2447261c6cb4" align="center" width="49%" height = "100%">
</p>

- **예약 보류 시스템 제공**
  
  ➡️ Redis TTL, List, String 사용하여 결제 프로세스 진입 시 일정 시간 예약 확보 기능
  
  ➡️ DB에 보류에 비해 TPS 40% 감소 -> <a href="https://drive.google.com/file/d/1svZgfCBNPALyKjAIGlXejOJnl8MwTcFx/view?usp=sharing" target="_blank">!트러블 슈팅 보러가기!</a>
  
- **카테고리, 월별 쿠폰**
  
  ➡️ 쿠폰 선택 시 즉각 적인 가격 정보( 총 가격, 할인 가격, 결제 가격) 확인 가능

<br/>

### ✅상품 예약 페이지
<p align="center">
  <img src="https://github.com/YASICJUNWOO/sharemore/assets/99794552/32438bcd-141b-4fce-b4db-b1bbfc7e30ec" align="center" width="80%" height = "100%">
</p>

- **예약 내역 확인**
  
  ➡️ 사용한 아이템에 한해 리뷰 작성 가능
  
## 👨‍💻 규칙

### Commit Message
* feat : 새로운 기능 추가
* fix : 버그 수정, 기능 수정
* docs : 문서 수정
* refactor : 코드 리팩토링 (변수명 수정 등)
* test : 테스트 코드, 리팩토링 테스트 코드 추가
* style : 코드 스타일 변경, 코드 자체 변경이 없는 경우
* remove : 파일 또는 코드, 리소스 제거
* resource : 이미지 리소스, prefab 등의 코드와 상관없는 리소스 추가

예시 :
* resource : 이미지 리소스, prefab 등의 코드와 상관없는 리소스 추가
* feat : Add translation to missing strings
* feat : Disable publishing
* feat : Sort list context menu
* feat : Resize minimize/delete handle icons so they take up the entire topbar
* fix : Fix typo in cleanup.sh file
