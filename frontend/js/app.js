import { getSession, logout } from "./services.js";

// Muestra el usuario actual y conecta el boton para cerrar sesion.
export function setupUserBox() {
  const user = getSession();
  if (!user) {
    window.location.href = new URL("../login.html", import.meta.url).href;
    return;
  }

  document.querySelector("#activeUser").textContent = user.nombre;
  document.querySelector("#activeRole").textContent = user.rol;
  document.querySelector("#logoutBtn").addEventListener("click", logout);
}

// Crea una celda de tabla con texto.
export function createCell(text) {
  const cell = document.createElement("td");
  cell.textContent = text;
  return cell;
}

// Muestra los empleados dentro de la tabla.
export function showEmployees(tableBody, employees) {
  tableBody.innerHTML = "";

  if (employees.length === 0) {
    const row = document.createElement("tr");
    const cell = createCell("Sin empleados registrados.");
    cell.colSpan = 6;
    row.appendChild(cell);
    tableBody.appendChild(row);
    return;
  }

  employees.forEach(function (employee) {
    const row = document.createElement("tr");
    row.appendChild(createCell(employee.idEmpleado));
    row.appendChild(createCell(employee.nombre));
    row.appendChild(createCell(employee.apellido));
    row.appendChild(createCell(employee.dni));
    row.appendChild(createCell(employee.telefono));
    row.appendChild(createCell(employee.cargo));
   
    tableBody.appendChild(row);
  });
}
