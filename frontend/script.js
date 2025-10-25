function toggleVisible() {
  const passwordInput = document.getElementById("loginPassword");
  if (passwordInput.type === "password") {
    passwordInput.type = "text";
  } else {
  passwordInput.type = "password";
  }
}

document.getElementById("loginForm").addEventListener("submit", function(event) {
  event.preventDefault(); // prevent real form submission
  alert("Login attempted for user: " + document.getElementById("loginUsername").value);
});
