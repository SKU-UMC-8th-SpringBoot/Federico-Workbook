# Chapter 9. API & Paging (페데리코)

# 📝 학습 목표

---

1. 목록 조회 API를 어떻게 만들지 고민한다.
2. 홈 화면처럼 정보량이 많은 경우 어떻게 할지 고민한다.

# 📸 잠깐 ! 스터디 인증샷은 찍으셨나요?📸

---

* 스터디리더께서 대표로 매 주차마다 한 장 남겨주시면 좋겠습니다!🙆💗
 (사진을 저장해서 이미지 임베드를 하셔도 좋고, 복사+붙여넣기해서 넣어주셔도 좋습니다!)

[](https://www.notion.so)

# 📑 9주차 주제

---

8주차에서 목록 조회가 아닌 API를 만들어 봤다면

이번에는 목록 조회가 필요한 API는 어떻게 만들지, 그리고 홈 화면처럼 정보량이 많으면 어떻게 할지, 이를 고민해봅시다!

# 🔖 9주차 본문

---

이번 주차는 미션으로 제공된 와이어 프레임 중, 아래 화면을 어떻게 만들지 고민해봅시다!

![리뷰 목록 조회 화면](Untitled.png)

리뷰 목록 조회 화면

우선 사진은 무시하고, (사진을 저장하는 S3는 12주차에 다룹니다!)

위의 리뷰 목록을 어떻게 조회할지 생각해봅시다.

사실 POST 요청같이 새 데이터를 저장하는 것 보다는 더 쉽다고 생각이 들 수 있지만,

사실은 위와 같은 조회 API가 생각보다 많이 까다롭습니다. 😂

***성능에 큰 영향을 미치는 기능은 조회 기능이기 때문입니다.***

왜 그럴까요?

리뷰가 만약 200개가 존재한다 가정했을 때, 한 번에 200개를 다 보여주면 엄청난 렉이 발생합니다.

따라서 이를 끊어서 보여줘야 하고, 이렇게 끊어서 보여주는 것을 페이징이라고 하죠.

따라서 위 화면에 대한 API를 만들려면 2가지를 고려해야 합니다.

1. **정보량이 적지 않은데, 이것을 어떻게 가져오지?**
2. **페이징을 어떻게 적용하지?**

사실 위의 2개 모두 순수 SQL로 할 수 있죠

1. join 연산으로 연관된 정보를 조회한다.
2. 3주차에 다룬 paging sql을 사용한다.

그러나 저희는 **Spring Data JPA**를 사용하기 때문에 위의 2단계를 거치기 보다는 다른 방식으로 API를 만들게 됩니다.

오늘 워크북에서 예시로 보여드릴 API는 **가게의 리뷰 목록 조회 API** 입니다!

![Untitled](Untitled%201.png)

**이슈 파고 진행하여 주세요!**

<aside>
📜 목록 조회 API를 만들기 위해 필요한 정보 알아보기!

1. 닉네임
2. 리뷰의 점수
3. 리뷰가 작성된 날짜
4. 리뷰의 상세 내용(~~위 화면 자세히 보고 리뷰 작성 API 수정 함~~)
5. ~~사진~~
6. ~~사장님 답글~~
</aside>

5번은 나중에 12주차를 통해 사진 업로드 방법을 배워야 할 수 있고,

6번은 일단 설계 대상에서 제외하고 진행하겠습니다.

사실 위의 화면 자체는 정보량이 그렇게 많지는 않습니다.

일단 위의 화면에서 필요한 데이터만 따졌을 때

**Member 와 Review 테이블의 join이 필요함**은 빠르게 캐치가 가능합니다.

그리고 숨어있지만 또 필요한 것은 **페이징**이겠죠?

자 이제 차근차근 API를 만들어 봅시다.

보통 API를 만들 때 아래의 순서를 따르면 우왕좌왕 하지 않고 만들 수 있습니다.

<aside>
🧭 (👉 전제 조건은 API URL은 설계가 되었다는 전제입니다!)

1. API 시그니처를 만든다!
2. API 시그니처를 바탕으로 swagger에 명세를 해준다
3. 데이터베이스와 연결하는 부분을 만든다
4. 비즈니스 로직을 만든다
5. 컨트롤러를 완성한다
6. validation 처리를 한다
</aside>

## 1. API 시그니처 만들기

이 과정은 많이 함축적인 말인데… 간단히 말해서

1. 응답과 요청 DTO를 만든다.
2. 컨트롤러에서 어떤 형태를 리턴하는지, 어떤 파라미터가 필요한지, URI는 무엇인지 HTTP Method는 무엇인지만 정해둔다.
3. 컨버터 정의만 해둔다.

이 과정을 의미합니다.

네, 일단 직접 해보죠

![~~쉐에에엣~~](Untitled%202.png)

~~쉐에에엣~~

일단 컨트롤러에서 함수를 만들려고 해도 ApiResponse에 담을 값이 없음을 알 수 있습니다.

### **1.1. DTO 작성**

그러니 우선 DTO부터 작성하러 갑시다. (리뷰 관련 DTO에 넣어주세요!) (저는 StoreResponseDTO에 넣어 주었습니다!)

```java
public class StoreResponseDTO {

		// ... 다른 코드들

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewListDTO {
        List<ReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewPreViewDTO {
        String ownerNickname;
        Float score;
        String body;
        LocalDate createdAt;
    }
}
```

위의 코드를 보면, static class로 DTO를 만들었습니다. 

![Untitled](Untitled%203.png)

위의 사진처럼 DTO 클래스 파일을 줄여서 파일 아키텍처 가독성을 높이기 위함입니다.

자잘한 DTO를 전부 다 하나의 클래스로 만들면, 저 DTO 파일을 펼쳐서 필요한 클래스를 찾을 때,
찾기가 힘들고 프로젝트 구조를 알아보기가 힘들어집니다.

그래서 큰 단위의 DTO를 하나의 클래스로 두고 하위 자잘한 DTO들은 static class로 둡니다.

우선 **‘목록’**이기 때문에 리뷰의 정보들의 목록이 필요합니다.

그래서 List<ReviewDetailDTO> 이렇게 리뷰의 정보를 담은 DTO를 List로 담은 또 다른 DTO를 만들었습니다.

ReviewPreViewDTO에는 위에서 말한 4개의 정보를 담았으며,

여기서 작성한 사용자의 정보가 닉네임 말고도 더 많았다면, 그 자체로 DTO로 구성을 하는 것이 좋습니다.

![이런 느낌으로?](Untitled%204.png)

이런 느낌으로?

그런데 지금은 저렇게 따로 DTO로 만들기엔 닉네임 하나여서 굳이 따로 감쌀 필요는 없어요.

### **1.2. Converter 겉 모양(정의만) 작성 해두기**

컨트롤러는 아래와 같이 일단 return null로만 만들어두고 이제 컨버터를 정의 부분만 만들러 갑시다.

![Untitled](Untitled%205.png)

컨버터의 코드는 아래와 같습니다.

```java
public class StoreConverter {
	
		// ... 기타 코드들 (8주차 미션에 있었던 리뷰 추가 등등)

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return null;
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(List<Review> reviewList){
        return null;
    }
}
```

보시면 아시겠지만 일단 return null 해두고 서비스의 매서드를 만들면서 완성(필요하면) 하거나 서비스 메서드 완성 후 세부 로직을 구현해도 됩니다.

지금 저 컨버터의 경우는 그저 응답을 위한 Entity to DTO이기에 서비스 로직 완성 후 작성을 해도 되겠지만, 만약 클라이언트의 요청 데이터를 JPA에서 처리하기 위한 Entity로 만드는, DTO to Entity의 경우는 복잡해질 가능성이 높고 **연관관계 처리를 서비스에서** 하는 것이 좋은 경우가 많기 때문에,

그런 경우는 서비스 로직 작성을 하는 과정에서 컨버터를 완성을 해야하는 경우도 있습니다.

위의 case는 12주차에서 리뷰 작성을 하는 API (사진 포함)에서 만나게 되기에 그 때 자세히 다룰 것입니다. 사실 8주차 API 작성 부분에서도 등장하긴 했습니다!

이렇게 API 시그니처를 작성할 수 있습니다.

### **1.3.** **Swagger를 이용한 API 명세 = Controller 매서드 정의만 해두기**

스웨거에 API가 완성되지 않았음에도 명세를 해두는 이유는 **프론트엔드 개발자와의 개발 과정에서 병목을 최대한 줄이기 위함입니다.**

이게 무슨 소리냐 하면,

API 하나를 모두 완성 후 명세를 하게 되면 프론트엔드 개발자는 해당 API가 완성이 될 때 까지 다른 API의 응답을 모르기 때문에 작업을 멈추게 됩니다.

이런 상황을 최대한 막기 위해 우선적으로 응답 Data의 형태를 알려주어 프론트 개발자도 미리 API 연결 부분을 작업 해둬 최대한 개발을 **병렬적으로** 할 수 있도록 합니다.

**따라서 지금은 API 하나만 시그니처를 만들지만, 되도록 많은 API에 대한 시그니처를 빠르게** 

**만들 것을 추천 드리며 이렇게 할 경우 더 빠른 개발 속도를 기대할 수 있겠죠.**

이제 Controller 부분을 정의만 해두면서 동시에 Swagger 명세를 합시다.

아래는 Controller의 코드입니다.

자세히 보면 Controller의 코드 자체는 거의 없고 이상한 어노테이션만 덕지덕지 있는 것을 확인 가능합니다. (ExistStore 어노테이션은 8주차 미션의 조건에 있었던 가게가 존재하는 지 여부를 판단하는 어노테이션입니다!) (저번 미션 때 만들었던 관련 컨트롤러에 넣어서 진행해주시면 될 것 같습니다!)

```java
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreQueryService storeQueryService;

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,@RequestParam(name = "page") Integer page){
        storeQueryService.getReviewList(storeId,page);
        return null;
    }
}
```

- **@Operation**은 이 API에 대한 설명을 넣게 되며 summary, description으로 설명을 적습니다.
- **@ApiResponses**로 이 API의 응답을 담게 되며 내부적으로 @ApiResponse로 각각의 응답들을

담게 됩니다.

에러 상황에 대해서만 content = 를 통해 형태를 알려줬고 (에러는 코드, 메세지만 중요하지 result는 필요 없어서!)

보면 성공에 대해서는 content를 지정하지 않았습니다.

content가 없으면 그냥 ApiResponse<StoreResponseDTO.ReviewPreViewListDTO>

여기서 StoreResponseDTO.ReviewPreViewListDTO가 응답 형태로 보여지게 됩니다.

- **@Parameters** 는 프론트엔드에서 넘겨줘야 할 정보를 담으며,
위의 코드에선 일단 path variable만 기재했고, API 완성 단계에서 query String도 추가할 것입니다.

그러면, 아래와 같이 Swagger에서 보여지게 됩니다!

![Untitled](Untitled%206.png)

## 2. **서비스 메서드 로직 작성 + 리포지토리 메서드 작성 (필요 시 Converter 완성)**

**사실 이번 부분이 가장 복잡한 과정입니다.**

서비스를 먼저 다 완성 후 리포지토리 메서드를 완성하거나 혹은 반대로 하거나 이렇게 깔끔하게 코드를 작성하기가 힘듭니다.

서비스 로직을 작성하다 보면 리포지토리의 메서드가 필요함을 알게 되고,
리포지토리의 메서드를 처음부터 만들기에는 어떤 비즈니스 로직에서 필요한지 모르기에 두 과정을 섞어가며 진행하게 됩니다.

그리고 외부 API를 호출 할 경우, Feign Client등과 같은 외부 API 호출 부분도 해당 과정에서 이뤄집니다.

**(UMC 워크북에서 다루는게 가능할지 모르겠지만… 외부 API 호출 시 저는 Feign Client를 추천합니다. Rest Template보다는 편해요.)**

이제 진행해봅시다.

제가 코드를 짜는 순서가 **절대** **정답이 아니며**,
여러분들이 적절히 서비스 로직과 리포지토리 로직을 완성해 나가는 단계라고 생각하시면 됩니다.

코드 하나하나 자세한 설명은 적지 않겠습니다!

```java
public interface StoreQueryService {
		
		// ... 다른 코드들 
		
    Page<Review> getReviewList(Long StoreId, Integer page);
    
}
```

위의 Page는 Spring Data JPA에서 제공하는 Paging을 위한 추상화를 제공합니다.

Page 자체에 페이징과 관련된 여러 정보가 담기게 되며 위에서 작성한 DTO에서 그 흔적을 

찾아볼 수 있습니다.

**Spring Data JPA에서 제공하는 Paging 관련 추상화는 여러분들이 찾아 볼 것을 추천드립니다!**

ServiceImpl은 아래와 같으며

```java
 		@Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {
        return null;
    }
```

생각을 해보면, Review Repository가 필요함을 알 수 있습니다.

```java
public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);
}
```

위의 코드는 Spring Data JPA에서 메서드 이름만으로 SQL을 만들어주는 기능을 활용한 것입니다.

PageRequest는 페이징과 관련된 옵션이 포함됩니다.

**serviceImpl**

```java
@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    
    private final ReviewRepository reviewRepository;

    // ... 다른 코드들

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page) {

        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }
}
```

**controller**

```java
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreQueryService storeQueryService;

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,@RequestParam(name = "page") Integer page){
        storeQueryService.getReviewList(storeId,page);
        return null;
    }
}
```

**converter**

여기서 converter를 보면,
ListDTO를 위해 리스트에 들어갈 DTO를 다른 Converter에서 제작해서 이를 Java stream을 통해 DTO의 List를 만드는 것을 알 수 있습니다.

```java
public class StoreConverter {

    public static StoreResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review){
        return StoreResponseDTO.ReviewPreViewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }
    public static StoreResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList){

        List<StoreResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreViewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();
    }
}
```

그리고 .ownerNickname(review.getMember().getName())

이 코드를 통해 review에 @MantyToOne으로 지정해둔 Member를 통해 아주 편하게 데이터를 가져오는 것을 확인 할 수 있습니다.

이는 **객체 그래프 탐색 이라는 Spring Data JPA에서 사용 가능한 아주 강력한 기능입니다.**

이제 컨트롤러를 controller를 converter에 맞게 바꾸고 

```java
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreQueryService storeQueryService;

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,@RequestParam(name = "page") Integer page){
        storeQueryService.getReviewList(storeId,page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }
}
```

테스트 해보면 (DB안에 store와 review 데이터가 들어있어야 합니다!)

![스크린샷 2024-09-27 오후 7.00.51.png](%25E1%2584%2589%25E1%2585%25B3%25E1%2584%258F%25E1%2585%25B3%25E1%2584%2585%25E1%2585%25B5%25E1%2586%25AB%25E1%2584%2589%25E1%2585%25A3%25E1%2586%25BA_2024-09-27_%25E1%2584%258B%25E1%2585%25A9%25E1%2584%2592%25E1%2585%25AE_7.00.51.png)

잘 나오는 모습을 볼수 있습니다!

Controller를 보면, Page를 0번이 1 페이지라고 해 두었는데

이에 대한 검증(page가 음수로 오는 경우)을 커스텀 어노테이션을 이용해 처리할 수 있겠죠?

이는 미션에 포함이 될 것입니다.

# 🎯핵심 키워드

---

<aside>
💡 주요 내용들에 대해 조사해보고, 자신만의 생각을 통해 정리해보세요!
레퍼런스를 참고하여 정의, 속성, 장단점 등을 적어주셔도 됩니다.
조사는 공식 홈페이지 **Best**, 블로그(최신 날짜) **Not Bad**

</aside>

**이번 주차는 핵심 키워드를 무조건 100% 다 조사 해야 하며 자세히 조사 할 것을 권고 드립니다.**

- Spring Data JPA의 Paging
    - Page
        
        Page는 정해진 데이터의 개수를 기준으로 페이지를 계산하여 페이지를 통해 다음 데이터를 받을 수 있음.
        
        ![image.png](image.png)
        
    - Slice
        
        Slice의 경우는 인스타그램이 대표적인 예시임.
        
        다음 포스트를 확인해보려고 할 때, 단순 스크롤을 내려가며 계속적으로 확인이 가능함.
        
        이러한 부분을 **무한 스크롤**이라고 함. 이러한 부분에서는 Page와 달리 페이지 계산이 불필요하다.
        
        보통 페이지를 계산하기 위해서는 전체 데이터의 개수 / 데이터를 로드할 기준 개수를 통해 구하기에 Slice는 Page에 비해 전체 데이터의 개수를 알기 위해 Count Query를 사용할 필요가 없으며 단순 다음 데이터가 존재하는지에 대한 여부를 파악하면 됨.
        
          
        
- 객체 그래프 탐색
    
    엔티티 간의 연관관계를 통해 연결된 객체를 참조하면서 데이터를 조회하거나 사용하는 것
    
    예시)
    
    ```java
    Member member = order.getMember();
    ```
    
    이처럼 order에서 member 를 접근하는 것이 객체 그래프 탐색.
    
    JPA는 테이블이 아닌 객체 간의 관계를 탐색해서 데이터를 사용한다는 점에서 SQL과 다르다.
    

# 📢 학습 후기

---

- 이번 주차 워크북을 해결해보면서 어땠는지 회고해봅시다.
- 핵심 키워드에 대해 완벽하게 이해했는지? 혹시 이해가 안 되는 부분은 뭐였는지?

<aside>
💡

</aside>

# ⚠️ 스터디 진행 방법

---

1. 스터디를 진행하기 전, 워크북 내용들을 모두 채우고 스터디에서는 서로 모르는 내용들을 공유해주세요.
2. 미션은 워크북 내용들을 모두 완료하고 나서 스터디 전/후로 진행해보세요.
3. 다음주 스터디를 진행하기 전, 지난주 미션을 서로 공유해서 상호 피드백을 진행하시면 됩니다.

# 🔥 미션

---

### [UMC 서버 워크북 참고 자료](https://github.com/CYY1007/UMC_SERVER_WORKBOOK.git)

[GitHub - chock-cho/UMC-7th-spring-workbook at feature-week9-workbook](https://github.com/chock-cho/UMC-7th-spring-workbook/tree/feature-week9-workbook)

---

아래의 API를 구현해야 하며, 추가 조건을 무조건 포함해서 구현을 해야 함.

4개 중 3개 이상의 API를 구현해야 하며 그 이하(0개~2개 구현)는 **원 아웃** 부여.

**2개 이상을 구현 했다고 해도, 추가 조건을 모두 만족하지 않을 경우 구현하지 않은 것으로 판단함.**

**핵심 키워드를 하나라도 조사를 하지 않을 시 역시 원 아웃 부여.**

**구현이 필요한 API 목록**

1. 내가 작성한 리뷰 목록
    - 참고 화면
        
        ![Untitled](Untitled%207.png)
        
    - 미션 사진
        
        1페이지 
        
        ![image.png](image%201.png)
        
        2페이지
        
        ![image.png](image%202.png)
        
        페이지가 0일 때 (예외 발생)
        
        ![image.png](image%203.png)
        
2. 특정 가게의 미션 목록
    - 미션 사진
        
        가게의 리뷰가 없을 때 
        
        ![image.png](image%204.png)
        
        가게의 리뷰가 있을 때
        
        ![image.png](image%205.png)
        
        페이지가 0일때 (예외 발생)
        
        ![image.png](image%206.png)
        
3. 내가 진행중인 미션 목록
    - 미션 사진
        
        ![image.png](image%207.png)
        
4. 진행중인 미션 진행 완료로 바꾸기
    - 참고 화면
        
        ![Untitled](Untitled%208.png)
        
- ValidPage 어노테이션 활용
    
    ![image.png](image%208.png)
    
    ![image.png](image%209.png)
    
    ![image.png](image%2010.png)
    
    ![image.png](image%2011.png)
    

프론트는 page = 1부터 시작함. 하지만 JPA는 0부터 시작함. 따라서 서비스 레이어에서 page - 1 을 처리했음. 동시에 @validPage 커스텀 어노테이션으로 유효성검증까지 적용

**API 구현 조건**

1. 반드시 Paging처리를 할 것, 한 페이지에 10개씩 조회 **프론트엔드는 1 이상의 page 번호를 전달**
2. 필요한 데이터는 데이터베이스에서 직접 삽입을 해서 진행 (미션 외 API는 구현해도 됨) 
    1. 다만 미션 외 API는 작성을 해도 구현한 API 갯수로 카운트가 되지 않음
3. 프론트엔드가 주는 page는 쿼리 스트링으로 받아오며 이에 대한 처리를 하는 커스텀 어노테이션 구현을 반드시 할 것 
    1. 1번의 page 범위에 따라 커스텀 어노테이션은 page 1을 0으로 만들어 return 해야 한다.
    2. 그와 동시에 page의 범위가 너무 작은지 (0 이하) 판단을 하여 작은 경우 에러를 발생
    3. 에러 발생 시 반드시 RestControllerAdvice와 연계를 해야 함
4. 반드시 모든 API에 대해 Swagger 명세를 해야 한다. 
5. Converter에서 절대로 for문을 사용해서는 안되며, 무조건 Java의 Stream을 사용해야 한다.
6. 무조건 빌더 패턴을 사용해야 한다.
7. API 구현

# 💪 미션 기록

---

<aside>
🍀 미션 기록의 경우, 아래 미션 기록 토글 속에 작성하시거나, 페이지를 새로 생성하여 해당 페이지에 기록하여도 좋습니다!

하지만, 결과물만 올리는 것이 아닌, **중간 과정 모두 기록하셔야 한다는 점!** 잊지 말아주세요.

</aside>

- **미션 기록**

> **github 링크**
> 
> 
> 

[시니어 미션](https://www.notion.so/200b57f4596b81cdb9fad0be21928c38?pvs=21)

# ⚡ 트러블 슈팅

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

---

Copyright © 2023 최용욱(똘이) All rights reserved.

Copyright © 2024 김준환(제이미) All rights reserved.