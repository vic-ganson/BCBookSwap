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
function validEmail(){
  let usernameEntry = document.getElementById('loginUsername').value;
  return usernameEntry.endsWith("@bc.edu");
}


//ON LOGIN FORM SUBMIT
try{
  document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault();
    if(!validEmail()){
      alert("You must login with a Boston College email address (@bc.edu)");
    } else {
      //check usernames etc
      window.location.href = "/index";
      let usernameEntry = document.getElementById('loginUsername').value;
      let passwordEntry = document.getElementById('loginPassword').value;
    }
  });
} catch(e){}


//ON CREATE ACCOUNT FORM SUBMIT
try{
  document.getElementById("createAccountForm").addEventListener("submit", function(event) {
    event.preventDefault();
    let usernameEntry = document.getElementById('loginUsername').value;
    let passwordEntry = document.getElementById('loginPassword').value;
    let passwordEntry2 = document.getElementById('loginPassword2').value;
    if(!validEmail()){
      alert("You must login with a Boston College email address (@bc.edu)");
    } else {
        if(passwordEntry != passwordEntry2){
        alert("Your password entries must match.")
      } else {
        //save account
        window.location.href = "index.html";
      }
    }
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



