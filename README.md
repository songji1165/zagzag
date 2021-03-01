# 클론 티몬 rest api(user 전용)

## # Rest Api 명세서

> - ** : 필수 파라미터
> - Pageable : 요청에 따라, Params에  ( page / size /sort ) 추가

<hr/>

### 1. User
1. POST /users
    - 회원가입
    - 요청 파라미터
    ```text
     Body
     1. (string) **email : 사용자 이메일(아이디) 
     2. (string) **pass : 비밀번호 
     3. (string) **name : 사용자 이름 
     4. (string) gender : ( MALE / FEMALE )
     5. (string) addr :  주소
     6. (string) role :  ( ADMIN / USER )
    ```
    - 응답 데이터
   ```text
     1. email : 사용자 이메일(아이디) 
     3. name : 사용자 이름 
     4. gender : ( MALE / FEMALE )
     5. addr :  주소
    ```
   <br/>  
   
2. PUT /users
   - 사용자 정보 업데이트
   - 요청 파라미터
    ```text
     Header
     1. **Authorization : Bearer {token}
   
     Body
     1. (string) name : 사용자 이름 
     2. (string) addr :  주소
     3. (string) pass : 비밀번호 
    ```
   - 응답 데이터
   ```text
     1. email : 사용자 이메일(아이디) 
     3. name : 사용자 이름 
     4. gender : ( MALE / FEMALE )
     5. addr :  주소
    ```
   <br/>  
   
3. GET /users/me
   - Token에 해당하는 아이디 조회
   - 요청 파라미터
    ```text
     Header
     1. **Authorization : Bearer {token}
    ```
      - 응답 데이터
    ```text
     1. email : 사용자 이메일(아이디) 
    ```
<br/>  

4. GET /user
   - user 정보 조회
     - 로그인 성공시 user 정보 조
   - 요청 파라미터
    ```text
     Header
     1. **Authorization : Bearer {token}
    ```
      - 응답 데이터
    ```text
     1. email : 사용자 이메일(아이디) 
     3. name : 사용자 이름 
     4. gender : ( MALE / FEMALE )
     5. addr :  주소  
    ```
<br/>  

4. GET /users/duplicate-email
    - 이메일 중복검사
    - 요청 파라미터
   ```text
     Params
     1. **email : 사용자 이메일(아이디) 
    ```
    - 응답 데이터
   ```text
        1. ( true / false )
    ```
   <br/>  

### 2. Product
1. POST /products
   - 상품 등록
   - 요청 파라미터
    ```text
     Body
     1. (string) **name : 상품명
     2. (string) **price : 상품 판매 가격 
     3. (string) **image : 상품 이미지 url
     4. (Long) **categoryId : categoryId
    ```
   - 응답 데이터
   ```text
     1. name : 상품명 
     3. price : 상품 판매 가격     
     4. image : 상품 이미지 url
     4. productScore : 상품 평점
     4. deliveryScore :  배송 평점  
     5. category : 카테고리 
            1. id: categoryId
            2. name: 카테고리명
    ```
   <br/>  

2. GET /products
   - 카테고리별 상품 조회
   - 요청 파라미터
    ```text
     Params (pageable)
     1. **category : categoryId
    ```
   - 응답 데이터
   ```text
     1. name : 상품명 
     2. price : 상품 판매 가격     
     3. image : 상품 이미지 url
     4. productScore : 상품 평점
     5. deliveryScore :  배송 평점  
     6. category : 카테고리 
            1. id: categoryId
            2. name: 카테고리명
    ```
   <br/>  

3. GET /products/{productId}/questios
   - 상품별 질문 조회
   - 요청 파라미터
    ```text
     Params (pageable)
   
     Header
     1. Authorization : Bearer {token}
    ```
   - 응답 데이터
   ```text
     1. id : reviewId
     2. content : 리뷰 내용  
     3. productScore : 상품 점수  
     4. deliveryScore : 배달 점수  
     5. likers : 좋아요 갯수  
     6. liked : 좋아요 여부 ( ture / false )
     7. created : 리뷰 작성 일자  
     8. updated : 리뷰 수정 일자  
     9. email : 작성자 이메일
     10. myReveiw : 리뷰 작성자 여부 ( ture / false )
    ```
   <br/>  

### 3. Order
1. POST /orders
   - 상품 주문
   - 요청 파라미터
    ```text
     Header
     1. **Authorization : Bearer {token}
   
     Body
     1. (Long) **productId : 사용자 이메일(아이디) 
     2. (Integer) **price : 주문 가격 
     3. (string) status : 주문상태 ( ORDER ) 
    ```
   - 응답 데이터
   ```text
     1. id : orderId 
     2. product : 주문 상품
        1. id :  productId
        2. name : 상품명
        3. price : 상품가격
        4. image : 상품 이미지 url
        5. category
            1. id: categoryId
            2. name: 카테고리명
     3. totalPrice : 총 주문 금액
     4. status : ( ORDER / DELIVERY / RETURN )
     5. created :  주문 일자
     6. updated :  주문 수정 일자
    ```
   <br/>  

2. GET /orders
   - 주문 조회
   - 요청 파라미터
    ```text
     Params (pageable)
     1. startdt : 조회 시작 기간 yyyy-MM-dd
   
     Header
     1. **Authorization : Bearer {token}
    ```
   - 응답 데이터
   ```text
     1. id : orderId 
     2. product : 주문 상품
        1. id :  productId
        2. name : 상품명
        3. price : 상품가격
        4. image : 상품 이미지 url
        5. category
            1. id: categoryId
            2. name: 카테고리명
     3. totalPrice : 총 주문 금액
     4. status : ( ORDER / DELIVERY / RETURN )
     5. created :  주문 일자
     6. updated :  주문 수정 일자
    ```
   <br/>  

3. PUT /orders/{orderId}
   - 주문 상태 변경 (사용자는 **주문취소** 변경만 가능)
   - 요청 파라미터
    ```text
     Header
     1. **Authorization : Bearer {token}
   
     Body
     1. (string) **status : ( RETURN ) 
    ```
   - 응답 데이터
    ```text
     1. id : orderId 
     2. product : 주문 상품
        1. id :  productId
        2. name : 상품명
        3. price : 상품가격
        4. image : 상품 이미지 url
        5. category
            1. id: categoryId
            2. name: 카테고리명
     3. totalPrice : 총 주문 금액
     4. status : ( ORDER / DELIVERY / RETURN )
     5. created :  주문 일자
     6. updated :  주문 수정 일자
    ```
<br/>  

### 4. Review
1. POST /reviews
   - 리뷰 작성
   - 요청 파라미터
    ```text
     Header
     1. **Authorization : Bearer {token}
   
     Body
     3. (string) **content : 리뷰 내용 
     3. (byte) **productScore : 상품 점수 
     3. (byte) **deliveryScore : 배달 점수 
     3. (Long) **productId : productId 
     3. (Long) **orderId : orderId 
    ```
   - 응답 데이터
   ```text
     1. id : reviewId
     2. content : 리뷰 내용  
     3. productScore : 상품 점수  
     4. deliveryScore : 배달 점수  
     5. likers : 좋아요 갯수  
     6. liked : 좋아요 여부 ( ture / false )
     7. created : 리뷰 작성 일자  
     8. updated : 리뷰 수정 일자  
     9. email : 작성자 이메일
     10. myReveiw : 리뷰 작성자 여부 ( ture / false )
    ```
   <br/>  

2. GET /reviews/product/{productId}
   - 상품별 리뷰 조회
   - 요청 파라미터
    ```text
     Params (pageable)
   
     Header
     1. Authorization : Bearer {token}
    ```
   - 응답 데이터
   ```text
     1. id : reviewId
     2. content : 리뷰 내용  
     3. productScore : 상품 점수  
     4. deliveryScore : 배달 점수  
     5. likers : 좋아요 갯수  
     6. liked : 좋아요 여부 ( ture / false )
     7. created : 리뷰 작성 일자  
     8. updated : 리뷰 수정 일자  
     9. email : 작성자 이메일
     10. myReveiw : 리뷰 작성자 여부 ( ture / false )
    ```
   <br/>  

3. PUT review/likers/{reviewId}
   - 리뷰 좋아요 업데이트
   - 요청 파라미터
    ```text
     Header
     1. **Authorization : Bearer {token}
    ```
   - 응답 데이터
    ```text
     1. 해당 리뷰의 좋아요 총 갯수
    ```
<br/> 

### 5. Question
1. POST /questios
   - 질문 작성
   - 요청 파라미터
    ```text
     Header
     1. **Authorization : Bearer {token}
   
     Body
     1. (string) **content : 질문 내용 
     2. (string) status : 질문글 상태 ( DELETE / BLOCK / NORMAL )
     3. (boolean) secret : 비밀글 여부  
     4. (Long) **productId : productId 
    ```
   - 응답 데이터
   ```text
     1. id : questioId
     2. productId : productId  
     3. content : 질문 내용
     4. type : 작성자 타입 ( BUYER / NON_BUYER / SELLER )  
     5. status : 질문글 상태 ( DELETE / BLOCK / NORMAL )  
     6. secret : 비밀글 여부 ( ture / false )
     7. created : 리뷰 작성 일자  
     7. updated : 리뷰 수정 일자  
     8. comments : 질문의 답변 갯수  
     9. email : 작성자 이메일
     10. myQuestion : 질문 작성자 여부 ( ture / false )
    ```
   <br/>

2. PUT questios/{questionId}
   - 질문 업데이트
   - 요청 파라미터
    ```text
     Header
     1. **Authorization : Bearer {token}
   
     Body
     1. (string) **content : 질문 내용 
     2. (boolean) secret : 비밀글 여부  
    ```
   - 응답 데이터
    ```text
     1. id : reviewId
     2. content : 리뷰 내용  
     3. productScore : 상품 점수  
     4. deliveryScore : 배달 점수  
     5. likers : 좋아요 갯수  
     6. liked : 좋아요 여부 ( ture / false )
     7. created : 리뷰 작성 일자  
     8. updated : 리뷰 수정 일자  
     9. email : 작성자 이메일
     10. myReveiw : 리뷰 작성자 여부 ( ture / false )
    ```
<br/> 

3. GET /questions/{questionId}/comments
   - 질문별 답글 조회
   - 요청 파라미터
    ```text
     Header
     1. Authorization : Bearer {token}
    ```
   - 응답 데이터
   ```text
     1. id : commentId
     2. content : 질문 내용
     3. type : 작성자 타입 ( BUYER / NON_BUYER / SELLER )
     4. status : 답변글 상태 ( DELETE / BLOCK / NORMAL )  
     5. secret : 비밀글 여부 ( ture / false )
     6. created : 답변 작성 일자  
     7. updated : 답변 수정 일자  
     8. email : 작성자 이메일
     10. myComment : 답변 작성자 여부 ( ture / false )
    ```
   <br/>  

### 6. Comment
1. POST /comments
   - 답변 작성
   - 요청 파라미터
    ```text
     Header
     1. **Authorization : Bearer {token}
   
     Body
     1. (string) **content : 답변 내용 
     2. (string) status : 답변글 상태 ( DELETE / BLOCK / NORMAL )
     3. (boolean) secret : 비밀글 여부  
     4. (Long) **questionId : questionId 
    ```
   - 응답 데이터
   ```text
     1. id : commentId
     2. content : 질문 내용
     3. type : 작성자 타입 ( BUYER / NON_BUYER / SELLER )
     4. status : 답변글 상태 ( DELETE / BLOCK / NORMAL )  
     5. secret : 비밀글 여부 ( ture / false )
     6. created : 답변 작성 일자  
     7. updated : 답변 수정 일자  
     8. email : 작성자 이메일
     10. myComment : 답변 작성자 여부 ( ture / false )
    ```
   <br/>

2. PUT comments/{commentId}
   - 댓 업데이트
   - 요청 파라미터
    ```text
     Header
     1. **Authorization : Bearer {token}
   
     Body
     1. (string) **content : 댓글 내용 
     2. (boolean) secret : 비밀글 여부  
    ```
   - 응답 데이터
    ```text
     1. id : commentId
     2. content : 댓 내용  
     3. type : 작성자 타입 ( BUYER / NON_BUYER / SELLER )
     4. status : ( ORDER / DELIVERY / RETURN )
     5. secret : 비밀글 여부 ( ture / false )  
     7. created : 댓글 작성 일자  
     8. updated : 댓 수정 일자  
     9. email : 작성자 이메일
     10. myReveiw : 댓글 작성자 여부 ( ture / false )
    ```
<br/> 

### 7. Category
1. GET /categories
   - 카테고리 전체 조회
   - 요청 파라미터
    ```text
    ```
   - 응답 데이터
   ```text
     1. id : categoryId 
     2. name : 카테고리명
    ```
   <br/>  

<hr/>

## # oauth Api (Token 발급)
### 1. TOKEN 발급
1. POST /oauth/token
   - Token 발급 
   - 요청 파라미터
    ```text
      Params
      1. **grant_type : **password
      2. password : 사용자의 비밀번호
      3. username : 사용자의 ID ( 이메일 )
    ```
   - 응답 데이터
   ```text
     1. access_token 
     2. token_type : bearer
     3. refresh_token 
     4. expires_in
     5. scope
     6. jti 
    ```
   <br/> 
   



