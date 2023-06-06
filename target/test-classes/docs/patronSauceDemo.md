# Hiberus - Héroes y Héroinas
Patrones POM (Page Object Model)
## LoginPage
- Elementos:
  - Username_Input
  - Password_Input
  - Login_Button
  - Error_Message
- Acciones:
  - Hacer Login
  - Introducir Username
  - Intrducir Password

## InventoryPage
- Elementos:
    - Menu_Burger
    - Shopping_Card
    - Sort_Option
    - Inventory_List
    - AddToCard_Button
    - RemoveToCard_Button
    - Price_Item
    - Name_Item
- Acciones:
    - Añadir productos al carrito
    - Ir al carrito
    - Ordenar elementos
    - Abrir menu hamburgesa

## CardPage
- Elementos:
  - Card_List
  - Cantidad_Item
  - Remove_Button
  - Price_Item
  - Name_Item
  - ContinueShopping_Button
  - Checkout_Button
  - Menu_Burger
  - Shopping_Card
- Acciones:
  - Volver a seguir comprando
  - Ir al Checkout
  - Elimitar productos del inventario
  - Abrir menu hamburgesa

## CheckoutInformationPage
- Elementos:
  - FirstName_Input
  - LastName_Input
  - Zip/PostalCode_Input
  - Error_Message
  - Cancel_Button
  - Continue_Button
- Acciones:
  - Cancelar pago
  - Continuar con el pago

## CheckoutOverviewPage
- Elementos:
  - Card_List
  - Price_Item
  - Name_Item
  - Cancel_Button
  - Continue_Button
- Acciones:
  - Cancelar pago
  - Continuar con el pago
  - Comprobar precios

## CheckoutCompletePage
- Elementos:
  - BackHome_Button
  - SuccefullShop_Text
- Acciones:
  - Volver al home
  - Comprobar que la compra se ha hecho satisfatoriamente

## MenuBurger
- Elementos:
  - AllItem_Link
  - About_Link
  - Logout_Link
  - ResetAppState_Link
- Acciones:
  - Hacer Logout
  - Resetear App
  - Ir al Inventario
  - Ir a la información "Abaout"
