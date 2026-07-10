<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=waving&color=auto&height=200&section=header&text=LetsGo&fontSize=80&fontAlignY=35&desc=여행%20일정%20플래너%20및%20공유%20플랫폼&descAlignY=55&descSize=20" alt="header">
</div>

<div align="center">
  <strong>가고 싶은 장소를 검색하고, 나만의 맞춤형 여행 일정을 설계하여 동반자와 공유해보세요!</strong><br>
  완성된 코스를 커뮤니티에 자랑할 수 있는 Java/JSP 기반의 웹 애플리케이션입니다.
</div>

---

## 🛠️ Tech Stacks (기술 스택)

### Environment & Database
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black)
![Oracle](https://img.shields.io/badge/Oracle-F00000?style=for-the-badge&logo=oracle&logoColor=white)
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white)

### Backend & Libraries
- **Architecture Pattern**: MVC (Model-View-Controller)
- **ORM / Persistence**: MyBatis 3.2.3
- **Libraries**: Google Gson 2.8.9, JSTL 1.2, Apache DBCP

### Frontend
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

### Modeling Tools
- **ERD**: ExERD

---

## 🏗️ Architecture Design (아키텍처 설계)
![Uploading image.png…]()


본 프로젝트는 유지보수성과 확장성을 위해 **MVC 패턴** 및 **Front Controller 패턴**을 엄격히 준수하여 설계되었습니다.

<div align="center">
  <img src="architecture.png" alt="Architecture Diagram" width="800">
  <p><em>(Client -> FrontController -> ActionFactory -> Action -> Service -> DAO -> Oracle DB & 외부 API)</em></p>
</div>

### 1. Model (모델)
- **DAO (Data Access Object)**: 데이터베이스 Access를 전담하며 MyBatis Mapper 파일(`userMapper.xml`, `Place.xml` 등)을 로드하여 SQL 쿼리를 실행합니다.
- **VO (Value Object) & DTO (Data Transfer Object)**: 데이터베이스 테이블 구조와 대응되는 데이터 모델 클래스들입니다.
- **MyBatis SQL Map**: XML 파일로 분리된 SQL을 통해 Java 코드 내 쿼리 하드코딩을 방지하였습니다.

### 2. View (뷰)
- `WebContent/view/`: 동적 데이터를 JSTL과 EL(Expression Language)을 사용하여 동적으로 렌더링하는 JSP 페이지들로 구성되어 있습니다.
- `WebContent/LetsGoviews/`: UI 디자인 및 설계 단계에서 작성된 HTML 정적 목업 파일들입니다.

### 3. Controller (컨트롤러)
- **`FrontControllerSevlet`**: 클라이언트의 모든 요청(`/controller`)을 중앙 집중식으로 수신합니다.
- **`ActionFactory` & `Action` 인터페이스**: 요청 파라미터 `cmd`에 따라 매핑되는 개별 액션 비즈니스 컨트롤러를 찾아 실행합니다. 이를 통해 새로운 기능 추가 시 서블릿 수정 없이 새로운 `Action` 클래스만 정의하여 동적으로 확장할 수 있습니다.

---

## 🗃️ Database Schema & ERD (데이터베이스 구조)

전체 DB 스키마는 **Oracle** 환경을 기준으로 작성되었으며, **MariaDB**와의 호환을 위해 별도의 DDL 스크립트도 함께 제공됩니다.

### 테이블 명세
1. **`USERS` (사용자 정보)**
   - 가입된 회원들의 아이디, 이메일, 이름 및 비밀번호를 저장합니다.
2. **`PLACE` (장소 공통 데이터)**
   - API를 통해 제공되는 관광지(레저), 식당, 숙소의 공통 기본 정보(제목, 위/경도, 이미지, 카테고리 정보)를 관리합니다.
3. **`MY_SCHEDULE` (개인 여행 일정)**
   - 사용자가 작성한 일정 제목, 여행 시작일, 예산 메모, 할 일 메모 및 커뮤니티 공유 여부를 저장합니다.
4. **`SCHEDULE_POST` (커뮤니티 게시물)**
   - 사용자가 전체 공개로 공유한 일정들이 등록되는 게시판 테이블입니다. 조회 수, 좋아요 수 등을 카운팅합니다.
5. **`VISIT_ITEM` (방문 장소 리스트)**
   - 특정 일정에 포함된 개별 장소 정보와 방문 순서(Order), 다음 목적지까지의 거리를 연결하여 관리합니다.
6. **`SCHEDULE_SHARE_USER` (일정 공유 및 권한 관리)**
   - 다른 사용자와 일정을 공유할 때 읽기(`READ`), 쓰기(`EDIT`) 권한을 세분화하여 협업을 지원합니다.

---

## ✨ Key Features (핵심 기능)

### 🔑 1. 회원 관리 (User Management)
- **로그인 및 세션 상태 검증**: 세션에 기반한 인증을 통해 미인증 사용자의 페이지 접근을 차단합니다 (`sessionCheck.jsp`).
- **아이디 중복 검사 (Ajax)**: 회원가입 시 비동기 요청을 보내 실시간으로 아이디 중복 여부를 사용자에게 알려줍니다.
- **비밀번호 변경**: 사용자 보안 유지를 위해 마이페이지 내 비밀번호 수정 기능을 지원합니다.

### 🗺️ 2. 장소 검색 및 탐색 (Place Exploration)
- **카테고리별 탐색**: 레저, 식당, 숙소의 개별 탭으로 구성되어 있으며 카테고리에 맞는 여행 장소를 제공합니다.
- **실시간 좋아요(Like) 반영**: 마음에 드는 장소에 대해 Ajax 비동기 방식으로 좋아요 수를 증가시키고 정렬 조건을 실시간 갱신합니다.

### 📅 3. 나만의 여행 일정 플래너 (Travel Planner)
- **일정 생성 및 커스터마이징**: 여행 이름과 시작 일자를 지정하고, 나만의 코스를 구축합니다.
- **일정 동반자 관리**: 공유할 동반자 아이디를 추가하고 읽기(R) / 쓰기(W) 권한을 직접 설정하여 함께 계획을 조율할 수 있습니다.
- **예산(Budget) & 할 일(TodoList)**: 해당 일정의 전체 총 예산 메모 및 여행을 떠나기 전 체크해야 할 체크리스트를 편리하게 저장하고 관리할 수 있습니다.
- **방문지 순서 정렬 및 거리**: 일정 내에 추가한 장소들의 방문 순서(Order)를 수정하고 실시간으로 반영합니다.

### 💬 4. 일정 공유 커뮤니티 (Community)
- **일정 공유(포스팅)**: 내가 직접 만든 여행 일정을 기명 혹은 익명으로 게시판에 자랑할 수 있습니다.
- **다른 사람의 코스 담기**: 커뮤니티 게시판에 업로드된 다른 사람의 훌륭한 여행 일정을 클릭 한 번으로 내 일정 리스트에 그대로 복사해올 수 있습니다 (`PostScheduleAddToMyScheduleAction`).
- **인기 일정 랭킹**: 조회 수와 추천 수(Like) 기반으로 게시물을 정렬하여 최적의 추천 코스를 한눈에 파악할 수 있습니다.

---

## 📂 Project Directory Structure (디렉토리 구조)

```text
letsgo/
│
├── src/
│   ├── com/letsgo/place/
│   │   ├── model/
│   │   │   ├── dao/          # Database Access Objects (MyScheduleDAO, PlaceDAO, UserDAO 등)
│   │   │   ├── dto/          # Data Transfer Objects (CopyToMyScheduleDTO 등)
│   │   │   └── vo/           # Value Objects (UserVO, PlaceVO, MyScheduleVO 등)
│   │   ├── service/          # 비즈니스 로직 인터페이스 및 구현 서비스 클래스
│   │   └── servlet/          # MVC Controller 서블릿 및 개별 Action 클래스 (FrontControllerServlet 등)
│   │
│   ├── config/               # MyBatis 설정 및 XML Mapper 파일 (Place.xml, userMapper.xml 등)
│   └── test/                 # DAO 및 비즈니스 로직 테스트 JUnit 코드
│
├── WebContent/
│   ├── view/                 # JSP 기반 동적 사용자 화면 템플릿
│   │   ├── css/              # 디자인 스타일시트
│   │   ├── img/              # 프로젝트 이미지 리소스
│   │   └── (JSP 파일들)      # index.jsp, stay.jsp, leisure.jsp, login.jsp 등
│   │
│   ├── LetsGoviews/          # 초기에 작성된 HTML 목업 화면 설계본
│   └── WEB-INF/
│       └── lib/              # 외부 참조 라이브러리 (MyBatis, Gson, OJDBC 등)
│
├── DDL                       # Oracle 기준 테이블 생성 DDL 스크립트 및 더미 데이터 삽입문
├── MariaDB_DDL.sql           # MariaDB 기준 호환 DDL 스크립트
├── Dummy.sql                 # 테스트용 데이터 삽입 쿼리문
└── 1팀_ERD.exerd             # ExERD 데이터베이스 설계 다이어그램 파일
```
