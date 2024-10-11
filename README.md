# Team24_Android

## 4~5주차 코드 리뷰

### ✨ 세부 사항

#### 1. [마이페이지 진입 화면 구현]
- activity_my_page.xml
- waiting_challenge_item.xml
- MyPageActivity.kt
- WaitingChallengeAdapter.kt

#### 2. [마이페이지 기록 화면 구현]
- activity_my_page_history.xml
- history_item.xml
- MyPageHistoryActivity.kt
- HistoryAdapter.kt

#### 3. [마이페이지 프로필 화면 구현]
- activity_my_page_profile.xml
- MyPageProfileActivity.kt

#### 4. [마이페이지 뷰 모델 구현]
- MyPageViewModel.kt

#### 5. [마이페이지 레포지토리 구현]
- MyPageRepository.kt

#### 6. [패키지 구조 변경]
- 기존의 3-tier architecture 식 패키지 구조에서 MVVM 패턴을 더 잘 드러내는 구조로 변경
- **api**: 
  - 각 Repository에서 호출할 Api 파일들을 별도로 생성

- **di**: 
  - `NetworkModule`: 통신과 관련된 retrofit, ApiService 등의 의존성을 주입
  - `AppModule`: 추후 앱 전역에서 사용할 클래스들의 의존성을 주입

- **repository**: 
  - 각 섹션의 repository 파일들 포함
  - 해당 섹션에서 호출할 API 함수들을 포함
  - ApiService의 인터페이스를 사용

- **view**: 
  - 각 섹션의 view 파일들 포함

- **data**:
  - 추후 로컬 데이터베이스 운용을 가정하여 더미 로컬 데이터를 담은 object 생성
  - Entities.kt에 Domain Model 추가

### ❓ 질문 사항

#### 1. 레이아웃 요소 크기에 대하여
피그마에 디자인 된 요소를 레이아웃을 구성해보면서 요소의 크기들을 다 피그마에 맞추는 것이 맞는지, 아니면 실행하면서 크기를 보고 결정하는 게 맞는지 고민이 굉장히 많이 됐습니다.
멘토님께서는 레이아웃 요소들의 크기를 어떻게 결정하는지 알고 싶습니다.

#### 2. 아이콘이나 사진 관리 방법에 대하여 (drawable)
페이지 수가 많아질수록 아이콘과 사진 파일이 많아지는데 디렉토리로 구분하여 사용하려 하니 코드에 반영이 안되는 것을 발견하였습니다. 멘토님께서 resource 관리를 어떻게 하시는지 궁금합니다.

### 🔗 피그마 링크  
[피그마 디자인 보기](https://www.figma.com/design/t14LOydaYTHOitC2Q7bwMf/%EC%8B%A4%EC%8B%9C%EA%B0%84%EC%B1%8C%EB%A6%B0%EC%A7%80?node-id=0-1&t=sfSd5mXgkwwuwp4c-1)
