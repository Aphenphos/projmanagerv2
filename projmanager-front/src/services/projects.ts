const BASE_URL = "http://localhost:8080";

export async function getProjects() {
  const resp = await fetch(`${BASE_URL}/getProjects`, {
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-type": "application/json",
    },
    credentials: "include",
  });
  if (resp.ok) {
    console.log(await resp.json());
  } else {
    console.log("you know");
  }
}
