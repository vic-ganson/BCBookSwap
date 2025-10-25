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

const API_URL = "https://hacktheheightsproject.onrender.com";

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
document.querySelector(".createAccountButton").addEventListener("click", function() {
  // send to createaccount page
  window.location.href = "createAccount.html";
});

document.querySelector(".loginButton").addEventListener("click", function() {
  // send to login page
  alert("This is a popup message!");
  window.location.href = "index.html";
});


document.getElementById("loginForm").addEventListener("submit", function(event) {
  event.preventDefault(); // prevent real form submission
  alert("Login attempted for user: " + document.getElementById("loginUsername").value);
});
const API_URL = "https://hacktheheightsproject.onrender.com";
