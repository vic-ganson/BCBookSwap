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
  document.getElementById("loginForm").addEventListener("submit", async function(event) {
      event.preventDefault();
      if(!validEmail()){
          alert("You must login with a Boston College email address (@bc.edu)");
          return;
      }
  
      let usernameEntry = document.getElementById('loginUsername').value;
      let passwordEntry = document.getElementById('loginPassword').value;
  
      try {
          const resp = await fetch('/api/login', {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify({ username: usernameEntry, passwordHash: passwordEntry })
          });
  
          if(resp.ok){
              window.location.href = "/index.html"; // login success
          } else {
              alert("Invalid login");
          }
      } catch(e){
          alert("Server error");
          console.error(e);
      }
  });
} catch(e){}


//ON CREATE ACCOUNT FORM SUBMIT
try{
  document.getElementById("createAccountForm").addEventListener("submit", async function(event){
    event.preventDefault();

    if(!validEmail()){
        alert("You must login with a Boston College email address (@bc.edu)");
        return;
    }

    let usernameEntry = document.getElementById('loginUsername').value;
    let passwordEntry = document.getElementById('loginPassword').value;
    let passwordEntry2 = document.getElementById('loginPassword2').value;

    if(passwordEntry != passwordEntry2){
        alert("Passwords must match");
        return;
    }

    try {
        const resp = await fetch('/api/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username: usernameEntry, passwordHash: passwordEntry })
        });

        if(resp.ok){
            alert("Account created!");
            window.location.href = "/login.html"; // send to login page
        } else {
            const msg = await resp.text();
            alert(msg);
        }
    } catch(e){
        alert("Server error");
        console.error(e);
    }
  });
} catch(e){}


// create account button from login
const createBtn = document.querySelector(".createAccountButton");
if (createBtn) {
  createBtn.addEventListener("click", function() {
    window.location.href = "/createAccount";
  });
}

// already-have-account / login button
const loginBtn = document.querySelector(".loginButton");
if (loginBtn) {
  loginBtn.addEventListener("click", function() {
    window.location.href = "/login";
  });
}



