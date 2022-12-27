import { Component, createSignal } from "solid-js";
import { getProjects } from "../../services/projects";

const Projects: Component = () => {
  const [projects, setProjects] = createSignal([]);
  async function test() {
    const resp = await getProjects();
  }
  return (
    <div>
      <button onclick={test}>Click</button>
      {projects()}
    </div>
  );
};
export default Projects;
