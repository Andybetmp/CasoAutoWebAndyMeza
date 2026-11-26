@WebStoreAll
Feature: Gestion de catalogo, carrito y orden de compra en DemoBlaze

  Como cliente de la tienda en linea
  Quiero filtrar productos por categoria, agregarlos al carrito y gestionar mi orden de compra
  Para completar mi pedido en la plataforma

  @CP01_Categoria @Categoria @Positivo
  Scenario Outline: Filtrar productos por categoria en el catalogo
    Given el usuario abre la pagina principal de la tienda
    When el usuario filtra el catalogo por la categoria "<categoria>"
    Then deberia visualizar el producto "<productoEsperado>" en el catalogo

    Examples:
      | categoria | productoEsperado |
      | Laptops   | Sony vaio i5     |
      | Monitors  | Apple monitor 24 |

  @CP02_Carrito @Carrito @Positivo
  Scenario Outline: Agregar un producto al carrito y verificar su detalle
    Given el usuario abre la pagina principal de la tienda
    When el usuario selecciona el producto "<producto>" y lo agrega al carrito
    And el usuario abre el carrito de compras
    Then deberia ver el producto "<producto>" listado en el carrito

    Examples:
      | producto          |
      | Samsung galaxy s6 |

  @CP03_Compra @Compra @Positivo
  Scenario Outline: Completar una orden de compra exitosamente
    Given el usuario abre la pagina principal de la tienda
    When el usuario selecciona el producto "<producto>" y lo agrega al carrito
    And el usuario abre el carrito y confirma la compra con nombre "<nombre>" y tarjeta "<tarjeta>"
    Then deberia visualizar el mensaje de confirmacion "Thank you for your purchase!"

    Examples:
      | producto          | nombre    | tarjeta          |
      | Samsung galaxy s6 | Andy Meza | 4532015112830366 |

  @CP04_CompraNegativa @Compra @Negativo
  Scenario: Intentar confirmar una compra sin completar los datos obligatorios
    Given el usuario abre la pagina principal de la tienda
    When el usuario selecciona el producto "Samsung galaxy s6" y lo agrega al carrito
    And el usuario abre el carrito e intenta confirmar la compra con datos vacios
    Then deberia visualizar la alerta de validacion "Please fill out Name and Creditcard."