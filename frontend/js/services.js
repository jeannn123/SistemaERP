const API_URL = "http://localhost:8080/api";
const SESSION_KEY = "erpUser";

// Realiza una peticion al backend y devuelve su respuesta JSON.
async function request(path, method, data) {
  const options = {
    method: method,
    headers: {
      Accept: "application/json"
    }
  };

  if (data !== undefined) {
    options.headers["Content-Type"] = "application/json";
    options.body = JSON.stringify(data);
  }

  const response = await fetch(API_URL + path, options);
  const result = await response.json();

  if (!response.ok) {
    throw new Error(result.message || "Error HTTP " + response.status);
  }

  return result;
}

// Solicita una lista de datos.
export function get(path) {
  return request(path, "GET");
}

// Envia datos para crear un registro.
export function post(path, data) {
  return request(path, "POST", data);
}

// Envia las credenciales a Spring Boot.
export function login(username, password) {
  return post("/auth/login", {
    username: username,
    password: password
  });
}

// Guarda la sesion en la pestana actual.
export function saveSession(user) {
  sessionStorage.setItem(SESSION_KEY, JSON.stringify(user));
}

// Obtiene el usuario de la sesion.
export function getSession() {
  const saved = sessionStorage.getItem(SESSION_KEY);
  return saved ? JSON.parse(saved) : null;
}

// Elimina la sesion.
export function logout() {
  sessionStorage.removeItem(SESSION_KEY);
  window.location.href = new URL("../login.html", import.meta.url).href;
}
