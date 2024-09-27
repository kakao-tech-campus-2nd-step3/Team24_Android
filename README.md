# Team24_Android

## 4주차 코드 리뷰

### ✨ 세부 사항

#### 1. [마이페이지 진입 화면 구성]
- activity_my_page.xml
- waiting_challenge_item.xml

#### 2. [마이페이지 기록 화면 구성]
- activity_my_page_history.xml
- history_item.xml

#### 3. [패키지 구조 변경]
- 기존의 3-tier architecture 식 패키지 구조에서 MVVM 패턴을 더 잘 드러내는 구조로 변경
- **api**: 
  - 각 repository에서 공통적으로 호출할 수 있는 api 호출 인터페이스들의 모음인 `ApiService.kt` 포함

- **di**: 
  - `NetworkModule`: 통신과 관련된 retrofit, ApiService 등의 의존성을 주입
  - `AppModule`: 추후 앱 전역에서 사용할 클래스들의 의존성을 주입

- **repository**: 
  - 각 섹션의 repository 파일들 포함
  - 해당 섹션에서 호출할 API 함수들을 포함
  - ApiService의 인터페이스를 사용

- **view**: 
  - 각 섹션의 view 파일들 포함

#### 4. MyPage 섹션의 View, Repository 제작
- ViewModel 부분:
  - 네비게이션 전략과 함께 LiveData, ViewModel의 작동 원리에 대한 스터디 진행 후 작성 예정

- Repository:
  - 백엔드와 소통하여 API 명세를 바탕으로 제작
  - [API 문서 링크](#) (실제 링크로 교체 필요)

### ❓ 질문 사항

#### 1. 레이아웃 요소 크기에 대하여
피그마에 디자인 된 요소를 레이아웃을 구성해보면서 요소의 크기들을 다 피그마에 맞추는 것이 맞는지, 아니면 실행하면서 크기를 보고 결정하는 게 맞는지 고민이 굉장히 많이 됐습니다.
멘토님께서는 레이아웃 요소들의 크기를 어떻게 결정하는지 알고 싶습니다.

#### 2. 네비게이션에 대하여
원래는 JetPack Navigation을 사용하여 네비게이션을 관리하려고 했는데, 소규모 프로젝트에서 네비게이션을 쓸 필요가 없다는 피드백을 받은 적이 있습니다. 그럴 경우, 어떻게 네비게이션을 처리하는 지 궁금합니다.

—

### 🔗 피그마 링크  
[피그마 디자인 보기](https://www.figma.com/design/t14LOydaYTHOitC2Q7bwMf/%EC%8B%A4%EC%8B%9C%EA%B0%84%EC%B1%8C%EB%A6%B0%EC%A7%80?node-id=0-1&t=sfSd5mXgkwwuwp4c-1)
