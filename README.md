## freelec-springboot2-webservice(👯springboot와 aws로 구현하는 웹서비스 with jojoldu)
## 본 내용은 이동욱님이 집필하신 **스프링 부트와 AWS로 구현하는 웹 서비스의 내용을 요약한 것입니다.**
<!--
**dnr2144/dnr2144** is a ✨ _special_ ✨ repository because its `README.md` (this file) appears on your GitHub profile.

Here are some ideas to get you started:

- 🔭 I’m currently working on ...
- 🌱 I’m currently learning ...
- 👯 I’m looking to collaborate on ...
- 🤔 I’m looking for help with ...
- 💬 Ask me about ...
- 📫 How to reach me: ...
- 😄 Pronouns: ...
- ⚡ Fun fact: ...
-->

---

## 템플릿 엔진
  웹 개발에 있어 템플릿 엔진이란, 지정된 템플릿 양식과 데이터가 합쳐져
HTML 문서를 출력하는 소프트웨어를 이야기한다. 예전에는 JSP, Freemarker 등이 있었고
요즘에는 리액트와 뷰 파일들이 있다. 둘 모두 결과적으로 **지정된 템플릿**과 **데이터**를 이용하여 
HTML을 생성하는 템플릿 엔진이다.
다만 조금의 차이가 있다. 전자는 **서버 템플릿 엔진**이라고 불리며, 후자는 **클라이언트 템플릿 엔진**이라고 불린다.

```javascript 
$(document).ready(function)() {
  if(a=="1") {
  <%
    System.out.println("test");
  %>
  }
});
```
  위 코드는 **if문과 관계없이 무조건 test를 콘솔에 출력한다.** 그 이유는 프론트엔드의
자바스크립트(Nodejs 아님)가 작동하는 영역과 JSP가 작동하는 영역이 다르기 때문에 JSP를 비롯한 서버 템플릿 엔진은
**서버에서 구동된다.** (JSP는 명확하게 서버 템플릿 엔진은 아니지만, VIEW의 역할만 하도록 구성할 때는 템플릿 엔진으로써
사용할 수 있다. 이 경우엔 Spring + JSP로 사용한 경우로 보면 된다.

  서버 템플릿 엔진을 이용한 화면 생성은 **서버에서 Java 코드로 문자열을** 만든 뒤
이 문자열을 HTML로 변환하여 브라우저로 전달한다. 앞선 코드는 HTML을 만드는 과정에서 System.out.println("test"); 를 실행할 뿐이며,
이 때의 자바스크립트 코드는 단순한 문자열일 뿐이다.
반면에 자바스크립트는 **브라우저 위에서 작동한다.** 앞에서 작성된 자바스크립트 코드가 실행되는 장소는
서버가 아닌 브라우저이다. 즉, 브라우저에서 작동될 때는 자바스크립트 코드를 서버 템플릿 엔진의 손을 벗어났기 때문에 서버 쪽에서 제어할 수가 없다.
흔히 이야기하는 Vue.js나 React.js를 이용한 SPA(Single Page Application)는 **브라우저에서 화면을 생성한다.** 즉, 서버에서 이미 코드가 벗어난 경우이다. 
그래서 서버에서는 Xml 형식의 데이터만 전달하고 클라이언트에서 조립한다.

## 머스테치(mustache)
루비, 파이썬, 자바스크립트, PHP, 자바, 펄, GO, ASP 등 현존하는 대부분 언어를 지원하고 있다. 그러다 보니 자바에서 사용될 때는 서버 템플릿 엔진, 자바스크립트에서 사용될 때는 클라이언트 
템플릿 엔진으로 사용한다.
자바 진영에서 많이 사용하는 템플릿 엔진
* Jsp, Velocity: 스프링 부트에서 권장하지 않음
* Freemaker: 템플릿 엔진으로는 너무 과하게 많은 기능을 지원. 템플릿은 화면 역할에만 충실해야 됨
* Thymeleaf: 스프링 진영에서 적극적으로 밀고 있지만 문법이 생각보다 어려우므로 나름 러닝커브가 있음.
