# SpringIntroExercise
인프런 예제로 배우는 스프링 입문(개정판)


![이미지](https://user-images.githubusercontent.com/35962655/124462943-21cc3f80-ddcd-11eb-8468-334533ec5696.png)
1. /hello 라는 경로를 요청하면 Springboot는 내장 톰캣 서버를 통해 스프링 컨테이너에서 
해당 controller를 찾는다.

2. model에 데이터를 저장하고 문자열을 리턴값으로 반환하면 viewResolver가 해당되는
화면을 찾아서 처리
  resources:templates/ + viewName(hello) + .html

