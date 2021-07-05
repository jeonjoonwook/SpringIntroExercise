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

