import { Component, createSignal } from "solid-js";
import { signInUser } from "../../services/auth";

const SignIn: Component = () => {
  const [username, setUsername] = createSignal("");
  const [password, setPassword] = createSignal("");

  function signIn(e: Event) {
    e.preventDefault();
    signInUser(username(), password());
  }
  return (
    <div>
      <form onsubmit={signIn}>
        <input
          type="text"
          placeholder="username"
          onInput={(e) => {
            setUsername(e.currentTarget.value);
          }}
        ></input>
        <input
          type="password"
          placeholder="password"
          onInput={(e) => {
            setPassword(e.currentTarget.value);
          }}
        ></input>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default SignIn;
