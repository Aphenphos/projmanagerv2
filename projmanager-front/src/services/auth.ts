const BASE_URL = "http://localhost:8080";

export async function signInUser(username: string, password: string) {
  const userObj = {
    username,
    password,
  };
  const resp = await fetch(`${BASE_URL}/signin`, {
    method: "POST",
    headers: {
      Accept: "application/json",
      "Content-type": "application/json",
    },
    body: JSON.stringify(userObj),
    credentials: "include",
  });

  if (resp.ok) {
    console.log(resp);
    console.log("logged in check cookies");
  } else {
    console.log("something went wrong");
    console.log(resp);
    return;
  }
}
