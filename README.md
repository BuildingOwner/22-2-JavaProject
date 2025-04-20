# 몬스터 토벌 RPG 게임

## 📋 목차
- [소개](#소개)
- [기능](#기능)
- [기술 스택](#기술-스택)
- [프로젝트 구조](#프로젝트-구조)
- [시작하기](#시작하기)
- [게임 플레이 방법](#게임-플레이-방법)

## 🚀 소개
몬스터 토벌 RPG 게임은 Java Swing을 활용한 2D 턴제 RPG 게임입니다. 플레이어는 다양한 몬스터와 전투를 벌이며 아이템을 획득하고 스테이지를 진행해나갑니다. 캐릭터를 조작하여 몬스터의 공격을 피하고 스킬을 사용하여 몬스터를 물리치는 게임입니다.

## ✨ 기능
- 캐릭터 이동 및 공격 시스템
- 다양한 몬스터 타입(Vat, Flame Snake, Goblin, Undead)
- 아이템 획득 및 스킬 사용
- 스테이지 진행 시스템
- 게임 저장 및 불러오기 기능
- 배경 음악 및 효과음

## 🛠️ 기술 스택
- Java
- Java Swing (GUI)
- Java AWT
- Java Sound API

## 📂 프로젝트 구조
프로젝트의 주요 클래스 및 구성 요소는 다음과 같습니다:

```
GameProject/
│
├── src/                 # 소스 코드
│   └── main/
│       ├── GameFrame.java    # 게임의 메인 프레임 및 게임 로직
│       ├── Player.java       # 플레이어 클래스
│       ├── Monster.java      # 몬스터 클래스
│       ├── Item.java         # 아이템 클래스
│       ├── GameObject.java   # 게임 객체 추상 클래스
│       ├── GamePanel.java    # 게임 화면 패널
│       ├── StartPanel.java   # 시작 화면 패널
│       ├── EndPanel.java     # 게임 종료 화면 패널
│       ├── ItemPanel.java    # 아이템 관리 패널
│       ├── SavePanel.java    # 게임 저장 패널
│       ├── HelpPanel.java    # 도움말 패널
│       └── Audio.java        # 오디오 처리 클래스
│
├── SaveFiles/           # 게임 저장 파일
├── audio/               # 오디오 파일
└── images/              # 게임 이미지 파일
```

## 🏁 시작하기
프로젝트를 시작하기 위한 필수 조건과 설치 방법을 안내합니다.

### 필수 조건
- Java Development Kit (JDK) 8 이상
- 자바 IDE (이클립스, IntelliJ IDEA 등)

### 설치
```bash
# 저장소 클론
git clone https://github.com/BuildingOwner/22-2-JavaProject.git
cd 22-2-JavaProject

# 이클립스나 IntelliJ IDEA에서 프로젝트 열기
# GameProject 폴더를 Java 프로젝트로 열기
```

## 📖 게임 플레이 방법

### 게임 시작
1. GameFrame 클래스의 main 메소드를 실행하여 게임을 시작합니다.
2. 시작 화면에서 "게임 시작" 버튼을 클릭합니다.
3. 캐릭터 이름을 입력하고 게임을 시작합니다.

### 조작 방법
- **왼쪽/오른쪽 화살표 키**: 캐릭터 이동
- **A 키**: 기본 공격
- **Q, W, E, R 키**: 보유한 아이템 스킬 사용
- **ESC 키**: 게임 일시정지

### 게임 규칙
1. 몬스터는 경고 표시가 나타나는 영역에 공격을 가합니다.
2. 플레이어는 몬스터의 공격을 피하고 기본 공격이나 스킬로 몬스터를 공격합니다.
3. 몬스터를 물리치면 아이템을 획득할 수 있습니다.
4. 스테이지를 클리어할 때마다 난이도가 상승합니다.
5. 플레이어의 체력이 0이 되면 게임이 종료됩니다.

## 개발자
- BuildingOwner
