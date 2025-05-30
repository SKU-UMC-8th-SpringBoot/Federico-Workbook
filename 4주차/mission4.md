# Chapter 4. Spring Boot의 코어 개념 (페데리코)

- DI, IOC, 서블릿, DB연결 원리 및 실습

# 📝 학습 목표

---

1. Spring boot의 코어 개념인 IoC 컨테이너, DI(의존성 주입), 서블릿에 대한 개념을 숙지한다.

# 실습 — Database와 Spring 프로젝트 연결하기

오늘 실습은 정말 간단합니다 ‼️

이제 저희의 Spring 프로젝트와 Database를 연결해보겠습니다.

지난 번 실습 시간에서 사용했던 데이터베이스를 이용할게요.

`application.yml` 파일에 다음과 같이 작성해주세요.

MySQL은 포트 번호가 3306입니다.

```
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/**umc8th**
    username: [ username ] 
    password: [ password ]
```

## 🎯핵심 키워드

---

<aside>
💡 주요 내용들에 대해 조사해보고, 자신만의 생각을 통해 정리해보세요!
레퍼런스를 참고하여 정의, 속성, 장단점 등을 적어주셔도 됩니다.
조사는 공식 홈페이지 **Best**, 블로그(최신 날짜) **Not Bad**

</aside>

- **DI**
    
    의존관계주입(Dependency Injection)이란, 객체가 스스로 필요한 걸 직접 만들지 않고 주입받는 것을 말한다. 스프링 컨테이너가 등록된 빈을 관리하고, 필요한 객체에 자동으로 주입해준다. `@Autowired`(
    스프링 컨테이너에 등록된 빈 중에서 타입(type) 이 맞는 걸 찾아서 자동으로 주입) , `@Qualifier` (같은 타입의 빈이 여러 개 있을 때 빈 이름을 기준으로 주입) 등등의 어노테이션을 사용해서 의존성 주입을 함.
    
    Spring DI의 세가지 방식
    
    1. Constructor Injection (생성자 주입)
        - 가장 권장되는 의존관계 주입 방식
        - 생성자 주입만이 final 키워드를 사용할 수 있고, 생성자를 통해 주입되는 방식이다.
            - final 키워드를 사용하기에 생성자로 인해 인스턴스가 생성될 때 1번만 할당됨.
            - final 키워드를 사용해서 값이 할당되기에 객체의 불변성이 보장됨.
            - 초기에 무조건 값이 할당되기에 NullPoinException이 발생하지 않음.
    2. Setter Injection (Setter 주입)
        - Spring에서 @Autowired 어노테이션을 사용해서 Setter 메서드를 통해 주입되는 방식
        - 생성자 이후에 호출되므로, 필드에 final 키워드는 사용할 수 없다.(객체가 다 만들어진 이후에 값이 들어가는 것이기 때문에 변수에 한 번만 값을 넣을 수 있는 final키워드를 사용할 수 없음.)
    3. Field Injection (필드 주입)
        - Bean으로 등록된 객체를 사용하고자 하는 클래스에 Field로 선언한 뒤 @Autowired 어노테이션을 붙여주면 자동으로 의존관계가 주입.
        - 객체 내 필드에 선언해서 주입하는 방법
        - 간편하게 의존 관계 주입이 가능하지만 참조관계를 눈으로 확인하기 어렵다.
- **IoC**
    
    객체의 생성과 관리를 개발자가 아닌 Spring 프레임워크가 담당하는 개념이다. 또한, 객체를 직접 생성하고 연결하는 대신 Spring에게 맡긴다. 개발자는 필요한 객체들만 선언하고, Spring이 적절한 객체를 주입해줌.
    
    **스프링 컨테이너의 작동 방식**
    
    1. 객체를 class로 정의
    2. 객체들 간의 연관성 지정 Config 또는 어노테이션을 통해 의존성 주입
    3. IoC 컨테이너가 이 정보를 바탕으로 객체들을 생성, 주입
    - 빈(Bean)이란?
    : Spring 컨테이너가 관리하는 자바의 객체
    - 묵시적 빈 정의
    1. 클래스에 @Component 어노테이션을 추가하고 @Autowired로 다른 클래스에서 해당 Bean을 주입받음.
    2. 대부분의 경우 @Component를 이용한 자동 등록 방식을 사용하는 것이 좋음
    3. @Component 하위 어노테이션으로 @Configuration, @Controller, @Service, @Repository가 있음.
    - 명시적 빈 정의
    1. @Configuration .. 외부 라이브러리 클래스를 가져다 써야할 때 @Configuration + @Bean으로 수동 등록
    2. 개발자가 직접 제어가 불가능한 라이브러리(ex. 외부 라이브러리)를 빈으로 등록할 때 사용(SpringSecurity, AppConfig등의 설정클래스에서 주로 사용됨)
- **프레임워크와 API의 차이**
    
    프레임워크는 프로그램의 기본 뼈대를 제공해서, 개발자가 그 위에 코드를 작성할 수 있는 틀
    
    API는 프로그램이 다른 프로그램에 요청하거나 응답할 수 있게 만든 인터페이스
    
- **AOP**
    
    AOP (Aspect Oriented Programming)
    
    공통적인 기능(로깅,보안, 트랜잭션 관리 등)을 핵심 로직에서 분리해서 따로 관리하는 방법.
    
    메소드나 객체의 기능을 핵심 관심사와 공통관심사로 나누어 프로그래밍 하는 것을 말함. 
    
    “핵심관심사”는 각 객체가 가져야할 본래의 기능, “공통 관심사” 는 여러 객체에서 공통적으로 사용되는 코드를 말함.
    
    AOP 주요 용어
    
    - Aspect :  공통 기능을 모아놓은 모듈 (ex: 로깅, 트랜잭션 관리)
    - Join Point : 실행될 수 있는 지점 (ex: 메서드 호출)
    - Advice :	언제 공통 기능을 실행할지 정의 (before, after, around)
    - Pointcut : 어떤 메서드에 공통 기능을 적용할지 지정하는 것
    - Weaving : 공통 기능을 실제 코드에 연결하는 과정
    
    https://programforlife.tistory.com/107
    
- **서블릿**
    
    서블릿이란 동적 웹 페이지를 만들 때 사용되는 자바 기반의 웹 애플리케이션 프로그래밍 기술이다. HTTP 요청을 처리하는 역할을 함.
    
    - **서블릿의 동작과정**
    
    <aside>
    1. 클라이언트 요청
    2. HttpServletRequest, HttpServletResponse 객체 생성
    3. Web.xml이 어느 서블릿에 대해 요청한 것인지 탐색
    4. 해당하는 서블릿에서 service() 메소드 호출
    5. doGet() 또는 doPost() 호출
    6. 동적 페이지 생성 후 ServletResponse 객체에 응답 전송
    7. HttpServletRequest, HttpServletResponse 객체 소멸
    
    </aside>
    
     
    

## 📢 학습 후기

---

- 이번 주차 워크북을 해결해보면서 어땠는지 회고해봅시다.
- 핵심 키워드에 대해 완벽하게 이해했는지? 혹시 이해가 안 되는 부분은 뭐였는지?

<aside>
💡

</aside>

## ⚠️ 스터디 진행 방법

---

1. 스터디를 진행하기 전, 워크북 내용들을 모두 채우고 스터디에서는 서로 모르는 내용들을 공유해주세요.
2. 미션은 워크북 내용들을 모두 완료하고 나서 스터디 전/후로 진행해보세요.
3. 다음주 스터디를 진행하기 전, 지난주 미션을 서로 공유해서 상호 피드백을 진행하시면 됩니다.

## ✅ 실습 체크리스트

---

## ☑️ 실습 인증

---

![DB연결](image%204.png)

DB연결

## 🔥 미션

---

주니어 기준으로, 이번 주차 미션은 스프링부트의 핵심원리와 흐름을 이해하는 것을 주안점으로 두고 봐주시면 될 것 같습니다.

[시니어 미션](https://www.notion.so/1cab57f4596b81a296ddc34a44c54da8?pvs=21)

# 💪 미션 기록

---

<aside>
🍀 미션 기록의 경우, 아래 미션 기록 토글 속에 작성하시거나, 페이지를 새로 생성하여 해당 페이지에 기록하여도 좋습니다!

하지만, 결과물만 올리는 것이 아닌, **중간 과정 모두 기록하셔야 한다는 점!** 잊지 말아주세요.

</aside>

- **미션 기록**

## ⚡ 트러블 슈팅

---

<aside>
💡 실습하면서 생긴 문제들에 대해서, **이슈 - 문제 - 해결** 순서로 작성해주세요.

</aside>

<aside>
💡 스스로 해결하기 어렵다면? 스터디원들에게 도움을 요청하거나 **너디너리의 지식IN 채널에 질문**해보세요!

</aside>

- ⚡이슈 작성 예시 (이슈가 생기면 아래를 복사해서 No.1, No.2, No3 … 으로 작성해서 트러블 슈팅을 꼭 해보세요!)
    
    **`이슈`**
    
    👉 앱 실행 중에 노래 다음 버튼을 누르니까 앱이 종료되었다.
    
    **`문제`**
    
    👉 노래클래스의 데이터리스트의 Size를 넘어서 NullPointException이 발생하여 앱이 종료된 것이었다. 
    
    **`해결`**
    
    👉  노래 다음 버튼을 눌렀을 때 데이터리스트의 Size를 검사해 Size보다 넘어가려고 하면 다음으로 넘어가는 메서드를 실행시키지 않고, 첫 노래로 돌아가게끔 해결
    
    **`참고레퍼런스`**
    
    - 링크
- ⚡이슈 No.1
    
    **`이슈`**
    
    👉 [트러블이 생긴 상태 작성]
    
    **`문제`**
    
    👉 [어떤 이유로 해당 이슈가 일어났는지 작성]
    
    **`해결`**
    
    👉  [해결 방법 작성]
    
    **`참고레퍼런스`**
    
    - [문제 해결 시 참고한 링크]

## 🤔 참고 자료

---

Copyright © 2024 신수정(베뉴) All rights reserved.