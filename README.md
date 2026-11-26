# CasoAutoWebAndyMeza
# Automatización de Pruebas Web E2E - DemoBlaze (Serenity BDD & Screenplay)

Proyecto de automatización de pruebas funcionales End-to-End (E2E) sobre la plataforma de comercio electrónico **DemoBlaze** (`https://www.demoblaze.com/`), desarrollado bajo el patrón de diseño **Screenplay** con **Serenity BDD**, **Cucumber** y **Selenium WebDriver** en **Java 17**.

## Video de Demostración

- [Ver ejecución completa y reporte Serenity BDD en Google Drive](https://drive.google.com/file/d/13rAZ7diRe2dUcVt9UeOzwU3af-StncTe/view?usp=sharing)

---

## Stack Tecnológico

- **Lenguaje:** Java 17
- **Gestor de dependencias y ciclo de vida:** Apache Maven
- **Framework BDD & Reportes:** Serenity BDD + Cucumber
- **Automatización Web:** Selenium WebDriver
- **Runner y Aserciones:** JUnit 4 + Hamcrest
- **Patrón de Arquitectura:** Screenplay (`Actors`, `Tasks`, `Questions`, `Pages`, `Utils`)

---

## Arquitectura del Proyecto

```text
CasoAutoWebAndyMeza/
├── evidencias/                                # Capturas PNG generadas por escenario y paso
├── src/
│   ├── main/java/org/example/
│   │   ├── Pages/                             # Mapeo de elementos web (Targets y PageObjects)
│   │   │   ├── CartPage.java
│   │   │   ├── HomePage.java
│   │   │   └── ProductsPage.java
│   │   ├── Questions/                         # Validaciones del estado de la interfaz y disponibilidad
│   │   │   ├── ElementText.java
│   │   │   ├── ElementVisible.java
│   │   │   └── ResponseCode.java
│   │   ├── Tasks/                             # Acciones de negocio del actor
│   │   │   ├── AddProductToCart.java
│   │   │   ├── CompletePurchase.java
│   │   │   ├── FilterCategory.java
│   │   │   └── NavigateHome.java
│   │   └── Utils/                             # Hooks, parámetros globales y captura de evidencias
│   │       ├── Parameters.java
│   │       ├── PreAndProsCondition.java
│   │       └── ScreenShot.java
│   └── test/
│       ├── java/org/example/
│       │   ├── StepDefinitions/               # Definición de pasos Gherkin y actores
│       │   │   ├── GestionarCatalogo.java
│       │   │   └── ParameterDefinition.java
│       │   └── RunnerTest.java                # Orquestador de ejecución CucumberWithSerenity
│       └── resources/
│           ├── features/
│           │   └── catalogo_carrito.feature   # Escenarios BDD en Gherkin
│           └── serenity.conf                  # Configuración de WebDriver y Serenity
└── pom.xml
