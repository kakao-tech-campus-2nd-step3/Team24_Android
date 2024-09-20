# Team24_Android

# 🤖 Android Project README

## 📑 목차
1. [코딩 컨벤션](#-코딩-컨벤션)
2. [커밋 컨벤션](#-커밋-컨벤션)
3. [PR 규칙](#-pr-규칙)
4. [Ground Rules](#-ground-rules)

## 💻 코딩 컨벤션

1. ✂️ 불필요한 코드는 삭제한다.
2. 📦 매개변수 4개 이상 시, 데이터 클래스 생성 후 전달한다.
3. 🔄 ListView 사용을 지양하고 RecyclerView 사용을 권장한다.
4. 🎯 코드 depth가 최대 3을 넘지 않도록 한다.
5. 📏 함수 길이를 최대 20줄로 제한한다.
6. 📚 의존성 관리:
    - 🌐 앱 전체에서 필요한 의존성은 앱 모듈에 정의한다.
    - 🧩 특정 기능에서만 사용되는 의존성은 해당 기능 모듈에 정의한다.
7. 📝 함수 문서화:
    - 💡 함수 기능에 대한 요약을 함수 앞에 주석을 이용해 명시한다.
    - ⏳ 당장 구현할 수 없는 함수의 경우, TODO 주석으로 구현해야 할 기능을 작성한다.

### 참고 문서 🔗
- [Kotlin 공식 코딩 컨벤션](https://kotlinlang.org/docs/coding-conventions.html)
- [Android 개발자 Kotlin 스타일 가이드](https://developer.android.com/kotlin/style-guide?hl=ko)
- [Android 리소스 네이밍 컨벤션](https://medium.com/@ajayjg/ids-layouts-resource-file-naming-android-naming-convention-3fc16e39721d)

## 📝 커밋 컨벤션

- 🔍 커밋 메시지 작성법은 [AngularJS Git Commit Message Conventions](https://docs.google.com/document/d/1QrDFcIiPjSLDn3EL15IJygNPiHORgU1_OOAqWjiDU5Y/edit)을 원칙으로 한다.

## 🔄 PR 규칙

1. 👥 1명 이상의 팀원에게 리뷰 또는 댓글을 받고 Merge를 진행한다.
2. 🗓️ 개개인은 매주 최소 1회 PR을 생성한다.
3. 🔀 매주 Weekly 브랜치에서 1차 conflict 해결을 진행한다.
4. 🌿 Develop 브랜치에서 2차 conflict 해결을 진행한다.

## 🌟 Ground Rules

1. 📢 개인 작업 상황은 매일 간단하게 공유한다 (작업 진행 상태, 완료 여부 등).
2. 🛠️ gradle 수정 시 팀원들에게 즉시 알린다.
3. 📊 dependency 관련 변경사항은 노션 내 'dependency' 페이지에 업데이트한다.

---

> 💡 **Note**: 이 README는 프로젝트의 기본 가이드라인을 제공합니다. 팀의 필요에 따라 지속적으로 업데이트됩니다.
