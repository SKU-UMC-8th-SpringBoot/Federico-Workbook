# Chapter 3. API URL의 설계 & 프로젝트 세팅 (페데리코)

## 📝 학습 목표

---

1. RESTful한 API 설계를 익힌다.
2. Node.js, Spring boot의 프로젝트 세팅을 한다.



## 🎯 핵심 키워드

---

<aside>
💡 주요 내용들에 대해 조사해보고, 자신만의 생각을 통해 정리해보세요!
레퍼런스를 참고하여 정의, 속성, 장단점 등을 적어주셔도 됩니다.
조사는 공식 홈페이지 **Best**, 블로그(최신 날짜) **Not Bad**

</aside>

이번 주차는 키워드가 딱히 없습니다!

원하실 경우 Restful API에 대해 한번 더 살펴보면 좋을 것 같습니다.

## 📢 학습 후기

---

- 이번 주차 워크북을 해결해보면서 어땠는지 회고해봅시다.
- 핵심 키워드에 대해 완벽하게 이해했는지? 혹시 이해가 안 되는 부분은 뭐였는지?

<aside>
💡 **API 란?
: 애플리케이션을 프로그래밍 할 때, 보다 쉽게 할 수 있도록 해주는 도구

REST API 란?
: HTTP 메소드와 자원을 이용해 서로간의 통신을 주고받는 api

API EndPoint 설계 시 고려해야 할 것들

1. path variable
: path variable**은 **URI 경로의 일부를 변수처럼 사용하는 것**

즉, 어떤 **리소스(데이터)를 고유하게 식별**할 때 URI 경로에 값을 직접 넣는 방식.

ex) GET https://umc.com/users/articles/1

2. **Query String 
: 보통 검색 조회 (GET 요청) 에서 사용이 됨. URI의 끝에 붙는 `?key=value` 형식의 파라미터들.** 

ex) GET /users/articles?name=umc&owner=Federico

**3. Request Body
:** Request Body는 HTTP 요청의 본문에 포함되는 데이터.

일반적으로 클라이언트가 서버에 뭔가

**새로 만들거나, 수정할 때 (POST, PUT, PATCH)** 사용됨.

JSON, XML, form-data 등 다양한 형식으로 보낼 수 있음.

ex)

```json
{
	"name" : "류승환",
	"phoneNum" : "010-1111-2222",
	"nickName" : "Federico",
}
```

1. **Request Header**

: Request Header는 HTTP 요청의 **헤더 영역**에 포함되는 메타 정보.

즉, 이 요청이 **어떤 형식인지**, **어떤 권한을 가지고 있는지**, **언제 만든 건지**같은

**부가 정보, 설정 정보, 인증 정보**를 전달하는 데 사용됨.

ex) 

```json
GET /my-profile
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR...
Content-Type: application/json
Accept-Language: ko-KR
```

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

## 🔥 미션

---

1주차 때 제공된 IA, WF을 참고하여 (아래 **미션 참고 자료**를 보셔도 됩니다!)


<aside>
✉️ **홈 화면, 마이 페이지 리뷰 작성, 미션 목록 조회(진행중, 진행 완료), 미션 성공 누르기,
회원 가입 하기(소셜 로그인 고려 X)**

</aside>

위의 기능을 구현하는데 **필요한 API들을 설계**하여

<aside>
🌟 **API Endpoint, Request Body, Request Header, query String, Path variable**

</aside>

이 포함된 간단한 명세서를 만들기!

실제 명세서는 Spring Boot 8주차 및 Node.js 9주차, Swagger 설정에서 더 자세히 다룹니다.

**< 시니어 미션 >**

[시니어 미션](https://www.notion.so/1c3b57f4596b817fab23c315380774ed?pvs=21)

## 💪 미션 기록

---

<aside>
🍀 미션 기록의 경우, 아래 미션 기록 토글 속에 작성하시거나, 페이지를 새로 생성하여 해당 페이지에 기록하여도 좋습니다!

하지만, 결과물만 올리는 것이 아닌, **중간 과정 모두 기록하셔야 한다는 점!** 잊지 말아주세요.

</aside>

- **미션 기록**
  - 홈 화면

    ![홈 화면](./images/image.png)
        
        
1. **지역 선택 기능**
            
**1-1. 지역 선택 기능** (미션 종류는 5개만 나오게)
            
- **API 엔드포인트 :** `GET /api/regions/{regionName}`
- **Request Header**
                
```json
GET /api/regions/안암동
Authorization: eyJhbGciOiJIUzI1NiIsInR...
Content-Type: application/json
Accept-Language: ko-KR
```
                
            
**1-2. 미션 더보기** (**지역을 선택했을 때 미션이 다 나오면 좋지만, 미션의 개수가 많아지면 성능 저하가 일어날 수 있어서 **미션 더보기** 버튼을 만듦.)
            
- **API 엔드포인트:** `GET /api/regions/{regionName}/missions?page=0&size=20&sort=latest`
- **Request Header**
                
```json
GET /api/regions/안암동/missions?page=0&size=10&sort=popular
Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
Content-Type: application/json
Accept-Language: ko-KR
```
                
        
2. **미션 10개 달성 시 리워드 지급**
            
2-1. **진행 상황 조회 API (진행률 표시)**
            
- **API 엔드포인트** : `GET /api/users/me/rewards/mission-completion`
            - me 가 사용 가능한가?
유저 ID를 URL로 노출할 필요 없음. (`/users/123` 대신 `me`)
![카카오 api 에서도 엔드포인트에 ‘me’ 를 넣어 사용한 모습](./images/image 1.png)
카카오 api 에서도 엔드포인트에 ‘me’ 를 넣어 사용한 모습
                
- **Request Header**
                
```json
GET /api/users/me/rewards/mission-completion
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json
Accept-Language: ko-KR
```
                
            
**2-2. 미션 10개 달성 시 리워드 지급 버튼 누르기**
            
- **API 엔드포인트 :** `POST /api/users/me/rewards/mission-completion`
- Request Header
                
```json
POST /api/users/me/rewards/mission-completion
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Content-Type: application/json
Accept-Language: ko-KR
```
                
- Response Body
            
**성공**
            
```json
{
  "message": "1000P 리워드가 지급되었습니다.",
  "currentPoint": 4300,
   "rewardClaimed": true
}
```
            
**실패 - 아직 미션 부족**
            
```json
{
  "error": "미션 10개를 아직 달성하지 않았습니다."
}
```
            
**실패 - 이미 리워드 수령한 상태**
            
```json
{
  "error": "이미 리워드를 수령하셨습니다."
}
```
            
- 마이 페이지 리뷰 작성
- **API 엔드포인트 :**  `POST /api/reviews`
- **Request Header**
        
```json
POST /api/reviews
Content-Type: multipart/form-data // 이미지 첨부 가능
Authorization: Bearer {JWT}
```
        
- **Request Body**
        
```json
{
  "storeId": 101,
  "rating": 4.5,
  "content": "너무 맛있어요!",
  "images": [
            "이미지링크1.jpg",
            "이미지링크2.jpg"
          ]
}
```
        
- **Response Body (성공)**
        
```json
{
  "message": "리뷰가 성공적으로 등록되었습니다.",
  "reviewId": 88,
  "uploadedImageUrls": [
            "이미지링크1.jpg",
            "이미지링크2.jpg"
          ]
}
```
        
- 미션 목록 조회(진행중, 진행완료)
1. 미션 목록 조회
        
![image.png](./images/image 3.png)
        
1-1. 미션 목록 조회 (진행 중)
- **API 엔드포인트** : `GET /{user_id}/mission_inProgress`
- **Request Header**
            
```json
GET /{user_id}/mission_inProgress
Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
Content-Type: application/json
Accept-Language: ko-KR
```

1-2. 미션 목록 조회(진행 완료)
- **API 엔드포인트** : `GET /{user_id}/mission_complete`
- Request Header
            
```json
GET /{user_id}/mission_complete
Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
Content-Type: application/json
Accept-Language: ko-KR
```
            
        
- 미션 성공 누르기

![image.png](./images/image 2.png)
        
- **API 엔드포인트** : `POST /api/missions/{missionId}/success-request`
- **Request Header**
            
```json
POST /api/missions/42/success-request
Authorization: Bearer eyJhbGciOi...
Content-Type: application/json
```
            
- Response Body
        
```json
{
  "message": "미션 성공 요청이 완료되었습니다.",
  "verificationCode": "920394810",        // 응답번호
  "storeName": "가게이름a",
  "reward": {
      "type": "적립",
      "amount": 500
      }
}
```
        
- 회원 가입하기
1. **서비스 이용 동의**

![image.png](./images/image 4.png)
            
1-1. 회원 약관 정보 보여주기
- **API 엔드포인트** : `GET /api/agreements`
- **Request Header** 
```json
GET /api/agreements
Content-Type: application/json
Accept-Language: ko-KR
```
                
- **Response Body**
                
```json
[
  {
    "type": "over14",
    "title": "만 14세 이상입니다.",
    "required": true
  },
    {
      "type": "terms-of-service",
      "title": "(필수) 서비스 이용약관",
      "required": true
    },
    {
      "type": "privacy-policy",
      "title": "(필수) 개인정보 수집 및 이용",
      "required": true
    },
    {
      "type": "marketing",
      "title": "(선택) 마케팅 수신 동의",
      "required": false
    },
    {
      "type": "location",
      "title": "(선택) 위치정보 제공 동의",
      "required": false
    }
]
```
                
1-2. 회원 약관 정보 제출
- **API 엔드포인트** : `POST /api/auth/agreements`
- **Request Header**
                
```json
POST /api/auth/agreements
Content-Type: application/json
Accept-Language: ko-KR
```
                
- **Request Body**
                
```json
{
  "over14": true,
  "termsOfServiceAgreed": true,
  "privacyPolicyAgreed": true,
  "locationConsent": false,
  "marketingConsent": false
}
```
                
- **Response Body(성공 시)**
                
```json
{
  "message": "약관 동의가 저장되었습니다."
}
```
                
- **Response Body (실 시)**
                
```json
{
"error": "필수 약관에 동의해야 가입이 가능합니다."
}
```
                
1-3. 회원 약관 정보 자세히 보여주기
                
**API 엔드포인트 :** `GET /api/agreements/{type}`
                
| `{type}`  | 뜻 |
| --- | --- |
| `over14` | 14세 이상 확인 약관 |
| `terms-of-service` | 이용약관 |
| `privacy-policy` | 개인 정보 처리 동의 |
| `location` | 위치정보 동의 |
| `marketing` | 마케팅 동의 |

- **Request Header**
                
```json
GET /api/agreements/{type}
Content-Type: application/json
Accept-Language: ko-KR
```
                
        
---
        
1. **회원가입 1 (회원 기본 정보)**

![image.png](./images/image 5.png)
            
**2-1. 회원 기본정보 입력**
- **API 엔드포인트** : `POST /api/auth/signup/basic`
- Request Header
                    
```json
POST /api/auth/signup/basic
Content-Type: application/json
Accept-Language: ko-KR
```
                    
- Request Body
                    
```json
{
   "name": "류승환",
   "gender": "남",
   "birthDate": "2000-04-07",
   "region": "서울특별시 성북구 안암동"
}
```

**2-2. 지역 목록 조회 API (주소 선택용)**
- API 엔드포인트 : `GET /api/regions`
- Request Header
                    
```json
GET /api/regions
Content-Type: application/json
Accept-Language: ko-KR
```
                    
- Response Body
                    
```json
[
  { "id": 1, "name": "서울특별시 성북구 안암동" },
  { "id": 2, "name": "부산광역시 해운대구" }
]
```
                    
        
  
1. **회원가입 2 (회원이 선호하는 음식)**

![image.png](./images/image 6.png)
- 
3-1. 음식 카테고리 가져오기
- API 엔드포인트 : `GET /api/food-categories`
- Request Header
                
```json
GET /api/food-categories
Content-Type: application/json
Accept-Language: ko-KR
```
                
- Respone Body
                
```json
[
  "한식",
  "일식",
  "중식",
  "양식",
  "치킨",
  "분식",
  "고기/구이",
  "도시락",
  "야식 (족발, 보쌈)",
  "패스트푸드",
  "디저트",
  "아시안푸드"
]
```
                
3-2. 선호 음식 카테고리 제출
- **API 엔드포인트** : `POST /api/auth/signup/preferences`
- Request Header
                
```json
POST /api/auth/signup/preferences
Content-Type: application/json
Accept-Language: ko-KR
```
                
- Request Body
                
```json
{
  "foodPreferences": [
                    "한식",
                    "치킨",
                    "디저트",
                    "아시안푸드"
                  ]
}
                
```
                
    
[API 명세서](https://www.notion.so/API-1ceb57f4596b80a79d74de2c9b3592c8?pvs=21)
    

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

[3주차](3%E1%84%8C%E1%85%AE%E1%84%8E%E1%85%A1%201c3b57f4596b8134a0f8e22522a0ce93.csv)

---

Copyright © 2023 최용욱(똘이) All rights reserved.

Copyright © 2024, 2025 김준환(제이미) All rights reserved.