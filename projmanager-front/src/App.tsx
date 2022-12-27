import type { Component } from "solid-js";

import logo from "./logo.svg";
import styles from "./App.module.css";
import { Route, Routes } from "@solidjs/router";
import SignIn from "./pages/SignIn/SignIn";
import Projects from "./pages/Projects/Projects";

const App: Component = () => {
  return (
    <div class={styles.App}>
      Testing
      <Routes>
        <Route path="/signin" component={SignIn} />
        <Route path="/projects" component={Projects} />
      </Routes>
    </div>
  );
};

export default App;
