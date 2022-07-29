

# 팀프로젝트 소개
## 주제

사설 및 공공 체육시설 통합 서비스 플랫폼




## 목적
1. 공공체육시설과 사설체육시설의 사업영역이 구분 없이 상호 발전 할 수 있도록 한다.
2. 체육활동 참여 현황과 체육활동 효과, 장애요인 등을 종합적으로 분석하고 이를 토대로 체육 활성화 방안 인식제고, 프로그램, 지도자, 시설, 홍보 측면에서 제시하였다.
3. 체육 시설의 활용, 데이터베이스 구축, 맞춤형 프로그램 및 맞춤형 체육 시설 검색, 트레이너 연계 등의 시스템을 통하여 체육 활동 접근성을 제고한다. 
## 기대효과

1. 체육시설 접근성에 대한 인식 개선 및 이용 활성화.
2. 투명한 예약 서비스로 체육 시설 이용에 대한 공정한 기회 제공.
3. 체육시설, 체육시설 종사자, 소비자의 연결을 통한 일자리 창출 및 경제 활성화
## 사용 기술
<img src="https://img.shields.io/badge/JAVA-007396?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"><img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"><img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"><img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"><img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white"><img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"><img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=black"><img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">


## 개발과정

 > 시스템 구조도 작성, 
 기능 정의, 
 상세자료 입력, 테이블 스키마, ERD 작성, UI 설계, 네이밍 규칙, 구현, 배포

## 프로젝트 기능

▶회원관리
    - 관리자, 시설주, 시설대리인, 트레이너, 일반회원인 다섯 가지 권한으로 회원을 관리
    - 로그인, 로그아웃 기능
    - 마이페이지 기능

▶사용자의 예약관리
    (예약관리- 주문관리-최종결제관리)
1. 예약내역보기

    - 사용자가 결제 후 결제한 건에 대한 예약 내역 조회 기능
2. 최종 결제 관리
    - 사용자의 모든 결제건에 대한 결제 내역 조회 기능

▶트레이너
    - 경력, 자격증으로 세분화하여 이력 관리기능
1. 이력관리
    - 트레이너 전용 프로필 생성 기능
    - 트레이너가 기록한 모든 경력, 자격증 조회 기능
    - 경력증명서, 자격증 첨부 기능
2. 구직
    - 시설의 구인 게시글 한눈에 확인 기능
    - 조건별 검색을 통해 원하는 시설에 구직 기능
3. 레슨관리
    - 트레이너가 진행하는 레슨 스케쥴 확인 기능
    - 레슨을 구매한 회원 조회 기능
    - 레슨별 출석률 확인 기능
4. 회원관리
    - 회원별 게시판 상담 내역 확인 기능
    - 회원별 출석률 확인 기능

▶ 시설관리
(시설 > 구장, 이용권, 레슨)
1. 시설 정보 관리 
    - 시설명, 주소, 번호, 이용가격 등의 시설 정보를 관리
2. 시설 예약 관리
    - 구장별, 레슨별, 이용권별 예약 현황을 한눈에 볼 수 있도록 조회 및 CRUD
3. 시설에 속한 트레이너 관리
    - 시설에 속한 트레이너의 레슨 일정 및 레슨 예약 현황을 한눈에 볼 수 있도록 조회 및 CRUD
4. 시설에 등록된 회원 관리
    - 시설에 등록된 회원의 목록 조회 및 CRUD
    - 회원의 시설 이용 현황 조회 기능.
5. 구인
    - 시설에 부합하는 트레이너 구인 기능.
6. 게시판
    - 회원에게 공지 
    - 회원의 건의, 상담, 신고 등 문의 조회 기능

## 개발환경

- ▶Language : Java, JavaScript(JQuery : jquery-3.6.0), HTML, CSS
- ▶DB : MySQL
- ▶DB관리툴 : HeidiSQL 10.1.0.5464
- ▶서버 : apache - 9.0.1
- ▶WAS : Tomcat - 9.0.1
- ▶협업툴 : GitHub, google공유드라이브, ERDCloud
: Eclipse Oxygen.1a Release (4.7.1a),  STS4 4.2.1, springframework 5.3.7, spring-webmvc 5.3.7, Maven, mybatis-3.5.6, mybatis-spring 2.0.6, bootstrap
- API : spring-jdbc-5.3.7, mysql-connector-java 8.0.25 , log4j 2.13.3, thymeleaf-3.0.12, jackson.core 2.11.4
