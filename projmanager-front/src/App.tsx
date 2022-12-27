import type { Component } from "solid-js";

import logo from "./logo.svg";
import styles from "./App.module.css";
import { Route, Routes } from "@solidjs/router";
import SignIn from "./pages/SignIn/SignIn";

const App: Component = () => {
  return (
    <div class={styles.App}>
      Testing
      <Routes>
        <Route path="/signin" component={SignIn} />
      </Routes>
    </div>
  );
};

export default App;
