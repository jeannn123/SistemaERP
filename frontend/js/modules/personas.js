import { showEmployees } from "../app.js";
import { get, post } from "../services.js";

// Inicia solamente la seccion de empleados.
export async function init(root, section) {
  if (section !== "empleados") {
    return;
  }

  const form = root.querySelector("#employeeForm");
  const table = root.querySelector("#employeesTable");

  await loadEmployees(table);

  form.addEventListener("submit", async function (event) {
    event.preventDefault();
    await saveEmployee(form);
    form.reset();
    await loadEmployees(table);
  });
}

// GET /api/empleados: obtiene los empleados de Spring Boot.
async function loadEmployees(table) {
  const employees = await get("/empleados");
  showEmployees(table, employees);
}

// POST /api/empleados: envia los datos escritos en el formulario.
async function saveEmployee(form) {
  const employee = {
    nombre: form.elements.nombre.value.trim(),
    apellido: form.elements.apellido.value.trim(),
    dni: form.elements.dni.value.trim(),
    telefono: form.elements.telefono.value.trim(),
    cargo: form.elements.cargo.value.trim()
  };

  await post("/empleados", employee);
}
