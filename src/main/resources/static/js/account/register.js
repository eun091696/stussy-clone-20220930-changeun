const registerButton = document.querySelector(".account-button");

registerButton.onclick = () => {
  const accountInputs = document.querySelectorAll(".account-input");

  //user 객체 생성 
  let user = {
    lastName: accountInputs[0].value,
    firstName: accountInputs[1].value,
    email: accountInputs[2].value,
    password: accountInputs[3].value
  }

  // JSON.stringify() -> js객체를 JSON문자열로 변환
  // JSON.parse()     -> JSON문자열을 js객체로 변환

  $.ajax({
    async: false,                         //필수
    type: "post",                         //필수
    url: "/api/account/register",         //필수
    contentType: "application/json",      //전송할 데이터가 json인 경우 
    data: JSON.stringify(user),           //전송할 데이터가 있으면
    // 응답발을 data type (return 타입) json
    dataType: "json",                     //json외 text등을 사용할 수 있지만 js사용함
    success: (response, textStatus, request) => {              //성공시에 실행될 메소드
      console.log(response);
      const successURL = request.getResponseHeader("Location");
      location.replace(successURL + "?email=" + response.data)
    },
    error: (error) => {                   //실패시에 실행될 메소드
      console.log(error.responseJSON.data);
      loadErrorMessage(error.responseJSON.data)
    }
  });
}

function loadErrorMessage(errors) {
  const errorList = document.querySelector(".errors")
  const errorMsgs = document.querySelector(".error-msgs")
  const errorArray = Object.values(errors)
  
  errorMsgs.innerHTML = "";

  errorArray.forEach(error => {
    errorMsgs.innerHTML += `
      <li>${error}</li>
    `;
  });

  errorList.classList.remove("errors-invisible");
}