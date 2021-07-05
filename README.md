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
  
컴포넌트 스캔과 자동 의존관계 설정  
![1](https://user-images.githubusercontent.com/35962655/124477740-53e69d00-dddf-11eb-80cd-97c659800001.PNG)  
-생성자에 @Autowired가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌  
-의존관계를 개발자가 직접 주입하는 것이 아니라 외부에서 넣어주는 것을 DI(Dependency Injection) 의존성주입 이라고 한다.  

스프링 빈을 등록하는 방법  
 - 컴포넌트 스캔과 자동 의존관계 설정  
 - 자바 코드로 직접 스프링 빈 등록  

컴포넌트 스캔 원리  
 - @Component 애노테이션이 있으면 스프링 빈으로 등록됨  
 - @Controller , @Service, @Repository도  @Component를 포함하므로 스프링 빈으로 등록됨  
 - 생성자가 1개만 있으면 @Autowired 생략 가능   
 - 스프링 컨테이너에 빈을 등록할 때, 기본으로 싱글톤으로 등록  
 
자바 코드로 직접 스프링 빈 등록하기  
 - MemberService와 MemoryMemberRepository의 @Service, @Repository, @Autowired 애노테이션 제거  
 - MemberService, MemberRepository 직접 java 코드로 빈 등록  
 ![2](https://user-images.githubusercontent.com/35962655/124478415-1fbfac00-dde0-11eb-9d66-61799c9086f4.PNG)  

 -xml 설정 방식은 과거의 방식으로 최근에는 잘 사용 안함   
 -DI에는 필드 주입, setter주입, 생성자 주입 3가지 방법이 있는데 주로 생성자 주입을 권함   
 -정형화된, 컨트롤러, 서비스, 리포지토리는 주로 컴포넌트 스캔 사용   
 -정형화 되지 않거나 상황에 따라 클래스를 변경해야 하면 설정을 통해 스프링 빈 등록   
 
 JPA  
 - JPA는 기존의 반복코드 제거와 기본적인 SQL 작성이 가능  
 - JPA 사용시 SQL과 데이터 중심의 설계에서 객체 중심의 설계로 전환 가능  
 ![1](https://user-images.githubusercontent.com/35962655/124481953-c8bbd600-dde3-11eb-829d-d527c9f04b71.PNG)
  - show-sql : JPA가 생성하는 SQL을 출력  
 - ddl-auto : JPA는 테이블을 자동으로 생성하는 기능을 제공, none을 사용하면 해당 기능을 끔  
 
 스프링 데이터 JPA  
 - 리포지토리에 구현 클래스 없이 인터페이스 만드로 개발 가능  
 - findByName(), findByEmail() 처럼 메서드 이름 만으로 조회 가능  
 - 페이징 기능 자동 제공  
![22](https://user-images.githubusercontent.com/35962655/124482257-0de00800-dde4-11eb-9ad7-cd2771cc9b5a.PNG)

 AOP  
-모든 메소드에 시간측정 로직을 추가 하고 싶은 상황  
-시간 측정은 핵심 기능이 아니기에 비즈니스 로직과 분리 시켜야 한다.  
-시간 측정 로직은 공통 관심 상황임  
![33](https://user-images.githubusercontent.com/35962655/124482917-bee6a280-dde4-11eb-9d43-f65903ce30e6.PNG)  
해결  
 - 비즈니스 로직(핵심 관심사항) 과 시간 측정(공통 관심 사항)을 분리 한다.  
 - 시간 측정 로직을 별도의 공통 로직으로 만든다.  
 AOP의 동작 방식  
 ![44](https://user-images.githubusercontent.com/35962655/124483075-e89fc980-dde4-11eb-981a-1824adf1433f.PNG)

