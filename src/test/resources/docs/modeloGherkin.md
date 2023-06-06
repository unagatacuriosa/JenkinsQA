# Gherkin - Saucedemo
## Escenario 1: Login Page
### Caso de prueba 1: Realizar login
- **GIVEN** el usuario se encuentra en la página de inicio "https://saucedemo.com/"
- **WHEN** el usuario proporciona el usuario "standard_user"
- **AND** el usuario proporciona la contraseña "secret_sauce"
- **AND** el usuario hace click en el botón "Login"
- **THEN** el usuario se encuentra en la página de inventario

### Caso de prueba 2: Login incorrecto
- **GIVEN** el usuario se encuentra en la pagina de inicio "https://saucedemo.com/"
- **WHEN** el usuario proporciona el usuario "standar_use"
- **AND** el usuario proporciona la contraseña "secret_sauce"
- **AND** el usuario hace click en el botón "Login"
- **THEN** el usuario se encuentra con un mensaje de error

## Escenario 2: Inventary page
### Caso de prueba 1: Validar que el numero de productos
- **GIVEN** el usuario se encuentra en la pagina de inventario "https://saucedemo.com/inventary"
- **WHEN** el usuario visualiza que hay 6 productos en pantalla
- **THEN** la cantidad de productos son correctas

### Caso de prueba 2: Validad que un producto en expecifico existe
- **GIVEN**el usuario se encuentra en la página de inventario "https://saucedemo.com/inventary"
- **WHEN** el usuario visualiza que el producto "Sauce Labs Bolt T-Shirt" aparece en el inventario
- **THEN** el producto es correcto

### Caso de prueba 3: Añadir producto al carrito
- **GIVEN** el usuario se encuentra en la página de inventario "https://saucedemo.com"
- **WHEN**
- **AND**
- **THEN**

