function toggleVisible() {
  const passwordInput = document.getElementById("loginPassword");
  if (passwordInput.type === "password") {
    passwordInput.type = "text";
  } else {
    passwordInput.type = "password";
  }
}
function toggleVisible2() {
  const passwordInput = document.getElementById("loginPassword2");
  if (passwordInput.type === "password") {
    passwordInput.type = "text";
  } else {
    passwordInput.type = "password";
  }
}

//password checking
function checkCredentials(){
  let usernameEntry = document.getElementById('loginUsername').value;
  let passwordEntry = document.getElementById('loginPassword').value;

}


//ON LOGIN FORM SUBMIT
try{
  document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault();
    checkCredentials()
  });
} catch(e){}



// create account button from login
const createBtn = document.querySelector(".createAccountButton");
if (createBtn) {
  createBtn.addEventListener("click", function() {
    window.location.href = "createAccount.html";
  });
}

// already-have-account / login button
const loginBtn = document.querySelector(".loginButton");
if (loginBtn) {
  loginBtn.addEventListener("click", function() {
    window.location.href = "login.html";
  });
}



