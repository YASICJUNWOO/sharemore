# SHARE MORE
"ShareMore: 더 나누고, 더 절약하는 물건 공유 서비스"

![쉐어모어](https://github.com/DevEx-senier-project/DevEx-BE/assets/99794552/7348858a-9d53-431b-adfb-7a58ef8bd0f9)

<p align="middle"></p>

## 🌳 프로젝트 소개

- "ShareMore"은 사용자들이 불필요한 물건을 공유하고, 필요한 물건을 저렴하게 이용할 수 있는 플랫폼입니다.
<p>

- 이를 통해 사용자들은 물건의 사용 효율을 높이고, 불필요한 소비를 줄일 수 있습니다.



## ⚒ 프로젝트 기술 스택
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- AWS EC2, CodeDeploy, S3, RDS
- MySQL


## 🎯 ERD
![erd](https://github.com/DevEx-senier-project/DevEx-BE/assets/99794552/48c81706-b96a-475f-a92c-d6d5eea0b764)

<!-- ## 🎯 시스템 아키텍처 --> 


## 개발 내용
<p>
1. 물건 등록: 사용자는 공유하고자 하는 물건의 사진(진행중), 설명, 가격 등을 등록할 수 있습니다. 또한, 물건의 상태와 이용 가능 기간 등을 명시하게 됩니다.
<p>
2. 물건 검색 및 카테고리화: 사용자는 키워드 검색을 통해 원하는 물건을 찾을 수 있습니다. 또한, 물건들은 카테고리별로 분류되어, 사용자가 원하는 카테고리를 선택하여 물건을 찾을 수 있습니다.
<p>
3. 예약 및 결제 시스템(진행중): 사용자는 원하는 물건을 예약하고, 다양한 결제 방식을 통해 결제할 수 있습니다.
<p>
4. 후기 및 평점 시스템(진행중): 사용자는 공유된 물건에 대한 사용 후기와 평점을 남길 수 있습니다. 이를 통해 물건의 품질을 보장하고, 다른 사용자의 선택을 돕습니다.
<p>
5. 보안 및 신뢰도(진행중): 사용자 인증, 거래 완료 확인 등의 시스템을 통해 사용자의 신뢰를 확보하고, 안전한 거래를 보장합니다.

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
