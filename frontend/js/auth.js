import { login, saveSession } from "./services.js";

const form = document.querySelector("#loginForm");
const error = document.querySelector("#loginError");

// Envia las credenciales y abre el panel correspondiente al rol.
form.addEventListener("submit", async function (event) {
  event.preventDefault();
  const username = form.username.value.trim();
  const password = form.password.value;
  error.classList.add("hidden");

  try {
    const user = await login(username, password);
    saveSession(user);

    if (user.rol === "ADMIN") {
      window.location.href = new URL("../pages/admin.html", import.meta.url).href;
    } else if (user.rol === "CAJERO") {
      window.location.href = new URL("../pages/cajero.html", import.meta.url).href;
    } else if (user.rol === "COCINA") {
      window.location.href = new URL("../pages/cocina.html", import.meta.url).href;
    } else {
      throw new Error("Rol no reconocido");
    }
  } catch {
    error.classList.remove("hidden");
  }
});
