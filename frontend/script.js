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
async function checkCredentials() {
  const usernameEntry = document.getElementById('loginUsername').value.trim();
  const passwordEntry = document.getElementById('loginPassword').value;

  if (!usernameEntry || !passwordEntry) {
    alert("Please enter both username and password.");
    return;
  }

  // Disable submit while request is running (optional UX improvement)
  const submitBtn = document.querySelector('#loginForm input[type="submit"]');
  if (submitBtn) submitBtn.disabled = true;

  try {
    const res = await fetch(`${API_URL}/save-user`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username: usernameEntry, password: passwordEntry })
    });

    const text = await res.text();

    if (!res.ok) {
      // Server returned an error status
      console.error("Server error:", res.status, text);
      alert("Server error: " + text);
    } else {
      // Success
      alert(text); // e.g. "User saved successfully!"
      // Optional: redirect after successful submission
      // window.location.href = "somepage.html";
    }
  } catch (err) {
    console.error("Network error:", err);
    alert("Network error — couldn't reach server. Check console for details.");
  } finally {
    if (submitBtn) submitBtn.disabled = false;
  }
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
