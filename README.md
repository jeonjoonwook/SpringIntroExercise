# SpringIntroExercise
인프런 예제로 배우는 스프링 입문(개정판)


![이미지](https://user-images.githubusercontent.com/35962655/124462943-21cc3f80-ddcd-11eb-8468-334533ec5696.png)
1. /hello 라는 경로를 요청하면 Springboot는 내장 톰캣 서버를 통해 스프링 컨테이너에서 
해당 controller를 찾는다.

2. model에 데이터를 저장하고 문자열을 리턴값으로 반환하면 viewResolver가 해당되는
화면을 찾아서 처리
  resources:templates/ + viewName(hello) + .html

정적 컨텐츠   - controller를 거치지 않고 url로 호출하면 서버가 resource/static 에서 html 파일을 보여준다.   ex) http://localhost:8080/hello-static.html

MVC와 템플릿 엔진  
-controller : 비즈니스 로직 작업 , view : 화면을 그려줌   
-url에 있는 'hello-mvc'를 컨트롤러에서 찾고 name 파라미터를 모델에 담아 view Name(hello-template)을 리턴한다.    
-템플릿 엔진은 모델에 담긴 파라미터 값을 화면에 반환한다.      
-실행 : http://localhost:8080/hello-mvc?name=joonwook    

![이미지2](https://user-images.githubusercontent.com/35962655/124464779-93a58880-ddcf-11eb-814c-a56ca77b5e08.png)

API  
@ResponseBody 문자 반환  
 - @ResponseBody를 사용하면 뷰 리졸버를 사용하지 않고 HTTP BODY에 문자 내용을 반환 한다.  
 - http://localhost:8080/hello-string?name=joonwook  
![이미지3](https://user-images.githubusercontent.com/35962655/124465860-e16ec080-ddd0-11eb-9dfb-4f4c3e9bee61.png)

@ResponseBody 객체 반환  
![이미지4](https://user-images.githubusercontent.com/35962655/124466026-1e3ab780-ddd1-11eb-9399-ee67a2d6f38b.png)  

@ResponseBody 사용  
-객체를 반환하면 객체가 JSON으로 변환된다.  
-viewResolver 대신에 HttpMessageConveter 가 동작해서 화면을 반환 하는 대신, 문자열 혹은 JSON 타입이 반환  
기본 문자 처리 : StringHttpMessageConverter  
기본 객체처리 : MappingJackkson2HttpMessageConverter  
-HTTP Body 에 데이터를 반환  

일반적인 웹 애플리케이션 구조    
  -컨트롤러 : 웹 MVC 의 컨트롤러 역할  
  -서비스 : 비즈닌스 로직   
  -리포지토리 : DB 접근, 도메인 객체를 DB 저장하고 관리   
  -도메인 : 비즈니스 도메인 객체  

![33](https://user-images.githubusercontent.com/35962655/124473612-7f1abd80-ddda-11eb-81ae-a743c8115232.PNG)
- MemberService 가 인터페이스를 바라보게 한다.   
- 인터페이스로 구현 클래스를 자유롭게 변경할 수 있도록 설계한다.  
![이미지2](https://user-images.githubusercontent.com/35962655/124472501-2ac30e00-ddd9-11eb-8c6a-3748c493b917.PNG)  
테스트 코드 작성  
 - 테스트는 순서에 상관없이 메소드 별로 독립적이어야 한다. 메소드 끼리 의존관계 있는것은 좋지 못하다.  
 -@AfterEach는 하나의 메소드가 끝날때마다 실행되는 메소드로, 윗 예제에서는 저장소 데이터들을 삭제하는 역할  
 -테스트 주도 개발(TDD) : 테스트 클래스를 먼저 작성하고 구현클래스를 작성  
 -테스트 코드는 given - when- then구조로 짜야 한다. 데이터가 주어지고 이것을 실행했을때 결과가 제대로 나와야한다.  
 
 MemberService 클래스를 만들고 직접 memberRepository를 new로 생성하지 않고  
외부에서 memberRepository를 주입 받도록 한다. (의존성 주입)  
![3](https://user-images.githubusercontent.com/35962655/124472652-5645f880-ddd9-11eb-966d-289605c1000d.PNG)   
MemberServiceTest클래스를 만들고 메소드가 @BeforeEach를 사용하여 테스트가  
실행 되기 전마다 memberService에 memberRepository를 주입 시켜 준다.  
 ![4](https://user-images.githubusercontent.com/35962655/124472741-71b10380-ddd9-11eb-8796-926f11baa10c.PNG)
  

