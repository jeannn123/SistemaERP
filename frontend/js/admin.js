import { setupUserBox } from "./app.js";

// Muestra el usuario guardado en la sesion dentro del panel administrador.
setupUserBox();

const content = document.querySelector("#content");
const title = document.querySelector("#viewTitle");
const navItems = Array.from(document.querySelectorAll(".nav-item"));
const areaSelect = document.querySelector("#moduleAreaSelect");

const titles = {
  dashboard: "Dashboard",
  pedidos: "Pedidos",
  productos: "Productos",
  recetas: "Recetas",
  combos: "Combos",
  promociones: "Promociones",
  insumos: "Insumos",
  compras: "Compras",
  movimientos: "Movimientos",
  kardex: "Kardex",
  empleados: "Empleados",
  usuarios: "Usuarios",
  proveedores: "Proveedores",
  "reportes:ventas": "Reportes de ventas",
  "reportes:compras": "Reportes de compras",
  "reportes:productos": "Productos mas vendidos",
  "reportes:inventario": "Movimientos de inventario",
  "reportes:anulados": "Pedidos anulados"
};

// Indica si una opcion del menu pertenece al area seleccionada.
function isVisibleForArea(button, area) {
  return button.dataset.area === "general" || button.dataset.area === area;
}

// Marca visualmente una sola opcion del menu como activa.
function setActiveNav(button) {
  navItems.forEach((item) => item.classList.remove("active"));
  button.classList.add("active");
}

// Filtra el menu por area y, si corresponde, carga su primera pantalla.
function applyArea(area, { loadFirst = true } = {}) {
  navItems.forEach((button) => {
    button.hidden = !isVisibleForArea(button, area);
  });

  if (!loadFirst) return;

  const firstAreaItem = navItems.find((button) => button.dataset.area === area);
  if (!firstAreaItem) return;
  setActiveNav(firstAreaItem);
  loadPage(firstAreaItem.dataset.page, firstAreaItem.dataset.section);
}

// Carga el HTML seleccionado. Solo empleados tiene conexion activa al backend.
async function loadPage(page, section) {
  const pageUrl = new URL("../pages/" + page + ".html", import.meta.url);
  const response = await fetch(pageUrl);
  if (!response.ok) {
    content.innerHTML = `<section class="admin-section active"><div class="card panel">No se pudo cargar la pagina ${page}.html</div></section>`;
    title.textContent = "Error de carga";
    return;
  }
  content.innerHTML = await response.text();

  content.querySelectorAll(".admin-section").forEach((node) => {
    node.classList.toggle("active", node.id === section);
  });

  if (page === "personas" && section === "empleados") {
    const employeeModule = await import("./modules/personas.js");
    await employeeModule.init(content, section);
  }

  title.textContent = titles[`${page}:${section}`] || titles[section] || titles[page] || "ERP";
  content.focus();
}

// Cada opcion del menu carga su pagina y seccion correspondientes.
navItems.forEach((button) => {
  button.addEventListener("click", () => {
    setActiveNav(button);
    loadPage(button.dataset.page, button.dataset.section);
  });
});

// Cambiar el area actualiza las opciones visibles del menu.
areaSelect.addEventListener("change", () => {
  applyArea(areaSelect.value);
});

// Inicia el panel mostrando el HTML del dashboard sin datos dinamicos.
applyArea(areaSelect.value, { loadFirst: false });
loadPage("dashboard", "dashboard");
