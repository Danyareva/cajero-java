# 🏧 Cajero Automático en Java

Este proyecto simula un **sistema de cajero automático** básico en Java. El programa permite a los usuarios consultar su saldo, retirar dinero y salir del sistema mediante un menú interactivo. Los datos de saldo se almacenan de forma persistente en un archivo binario.

---

## 📋 Características

- Consulta del saldo actual desde archivo.
- Retiros con verificación de fondos.
- Actualización automática del saldo en archivo binario.
- Interfaz basada en consola con menú interactivo.
- Manejo de errores básicos (como saldo insuficiente).

---

## 🛠️ Estructura del Programa

El sistema está compuesto por varias funciones organizadas en métodos, incluyendo:

- `mostrarMenu()`: Muestra las opciones disponibles al usuario.
- `consultarSaldo()`: Lee y muestra el saldo actual del archivo `saldo.dat`.
- `retirarDinero(int cantidad)`: Resta dinero si hay saldo suficiente y actualiza el archivo.
- `guardarSaldo(int nuevoSaldo)`: Guarda el nuevo saldo en el archivo binario.
- `leerSaldo()`: Lee el saldo actual desde el archivo `saldo.dat`.

---

## 📦 Archivos Utilizados

- `saldo.dat`: Archivo binario que almacena el saldo del usuario. Se crea automáticamente con un saldo inicial de **1000** si no existe.

---

## ▶️ Cómo Ejecutarlo

1. Asegúrate de tener **Java instalado**.
2. Compila el programa:
   ```bash
   javac Cajero.java
