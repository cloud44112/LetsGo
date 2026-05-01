<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 1. JSTL 선언 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/LetsGo/view/css/myScheduleList.css">
</head>
<body>
<jsp:include page="postScheduleListSideBar.jsp" />
<jsp:include page="header.jsp" />

<main>
    <div class="content-container">
        <div class="content-left">
            <div class="search-area">
                <input type="text" placeholder="장소 이름이나 일정 이름을 검색하세요" />
                <button type="button">검색하기</button>
                <div class="sort-area">
                    <select name="sortOrder">
                        <option value="latest">날짜순</option>
                        <option value="name">이름순</option>
                    </select>
                </div>
            </div>
        </div> </div> <div class="container">
        <c:forEach var="item" items="${postScheduleList}">
            <figure class="figure">
                <div>
                    ${item.title}
                </div>

                <a href="controller?cmd=postScheduleRouteManageUI&postId=${item.postId}" class="box-placeholder">
  					  <img src="${item.firstImage}" alt="일정 이미지" class="box-placeholder">
				</a>
                
                <figcaption class="figure-caption">${item.placeTitle}</figcaption>

                <div>
                	<span type = "button" class="like-btn" data-postId="${item.postId}">❤️ ${item.likeCount}</span>
                    <span>조회수: ${item.viewCount}</span>	
                    <span>👤 ${item.isAnonymous == 1 ? '익명' : item.userName} 님</span>
                </div>
                
                <div>
                    📍 ${item.addr1}
                </div>
            </figure>
        </c:forEach>
    </div>
</main>

<!-- <script type="text/javascript">
    let clickedBtn; // 클릭된 버튼을 저장할 변수

    // 1. 모든 좋아요 버튼 선택
    const likeButtons = document.querySelectorAll(".like-btn");

    // 2. 클릭 시 실행될 함수
    const idEvent = function() {
        clickedBtn = this; // 클릭된 span 요소를 변수에 박제
        const postId = this.getAttribute("data-postId"	);

        const xhr = new XMLHttpRequest();
        
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                const parsed = JSON.parse(xhr.responseText);

                // 3. 서버 응답이 성공(true)일 때만 화면 숫자 변경
                if (parsed.result === true) {
                    // 현재 텍스트(예: "❤️ 10")에서 숫자만 추출해서 +1
                    const currentText = clickedBtn.innerText;
                    const currentCount = parseInt(currentText.replace(/[^0-100]/g, ""));
                    
                    // 화면에 즉시 반영
                    clickedBtn.innerText = "❤️ " + (currentCount + 1);
                    
                    console.log("테스트 성공! 게시물 ID:", postId, "새로운 숫자:", currentCount + 1);
                } else {
                    alert("좋아요 처리에 실패했습니다.");
                }
            }
        };

        // 4. 컨트롤러로 요청 전송 (cmd 명칭 확인 필수!)
        xhr.open("GET", "controller?cmd=PostScheduleLike&postId=" + postId, true);
        xhr.send(null);
    };

    // 5. 모든 버튼에 이벤트 매핑
    likeButtons.forEach(btn => {
        btn.onclick = idEvent;
    });
</script> -->

   <script type = "text/javascript">

         
      
   
      let xhr = new XMLHttpRequest();
      let callbackMethod = function(){
         if(xhr.readyState == 4){
            if(xhr.status == 200 || xhr.status == 300){
               let parsed = JSON.parse(xhr.responseText);
               console.log(JSON.parse(xhr.responseText))
               if (parsed.result == ""){ 
                  this.myScheduleList=null;
               }
               else this.myScheduleList=parsed.result;
            }
         }
      }

      xhr.onreadystatechange = callbackMethod;
		
      let clickedBtn;
      let inputs=document.querySelectorAll(".like-btn");
      let idEvent = function(){
          clickedBtn = this; // 클릭된 span 요소를 변수에 박제
          const postId = this.getAttribute("data-postId"	);
         xhr.open("get", "controller?cmd=PostScheduleLike&postId"+ postId, true);
         xhr.send(null);
      }
      likeButtons.forEach(btn => {
          btn.onclick = idEvent;

   </script>



</body>
</html>