@inventarySuite
Feature: Inventory test suit

  Background: Navigate to the inventory page
    Given the user is on the inventory page "https://www.saucedemo.com/inventory.html"
    And the user provide the username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    And the user is logged successfully and is into the inventory page

  @InventarySize
  Scenario Outline: Verify inventory size
    Given the user is on the inventory page
    And the user sees <expected_size> elements in the inventory
    When the user compares the inventory size with <expected_size>
    Then the inventory size is correct
    Examples:
      | expected_size |
      | 6             |

  @IfExistProduct
  Scenario Outline: Verify is exist the product
    Given the user is on the inventory page
    And the user sees <product> in inventory
    When the user compares <product> with <expected_product>
    Then the product is correct
    Examples:
      | product                 | expected_product        |
      | Sauce Labs Bolt T-Shirt | Sauce Labs Bolt T-Shirt |
      | Sauce Labs Onesie       | Sauce Labs Onesie       |
      | Sauce Labs Backpack     | Sauce Labs Backpack     |

  @AddProductToCart
  Scenario Outline: Add product to cart
    Given the user is on the inventory page
    And the user sees <product> in inventory
    And the user click <product> button "Add to cart"
    When the product add to cart
    Then the user valid sees 3 products in de cart
    Examples:
      | product                 |
      | Sauce Labs Bolt T-Shirt |
      | Sauce Labs Onesie       |
      | Sauce Labs Backpack     |

  @DeleteProductOnInventory
  Scenario Outline: Delete product on inventory
    Given the user is on the inventory page
    And the user sees <product> in inventory
    And the user click <product> button "Remove"
    When the product remove from de cart
    Then the user valid sees 0 products in de cart
    Examples:
      | product                 |
      | Sauce Labs Bolt T-Shirt |
      | Sauce Labs Onesie       |
      | Sauce Labs Backpack     |

  @AddVariosProductToCart
  Scenario Outline: Add multiple products to cart
    Given the user is on the inventory page
    And the user sees <product_1> in inventory
    And the user click <product_1> button "Add to cart"
    And the user sees <product_2> in inventory
    And the user click <product_2> button "Add to cart"
    And the user sees <product_3> in inventory
    And the user click <product_3> button "Add to cart"
    When the product add to cart
    Then the user valid sees 3 products in de cart
    Examples:
      | product_1               | product_2                | product_3                         |
      | Sauce Labs Bolt T-Shirt | Sauce Labs Onesie        | Test.allTheThings() T-Shirt (Red) |
      | Sauce Labs Onesie       | Sauce Labs Fleece Jacket | Sauce Labs Bike Light             |
      | Sauce Labs Backpack     | Sauce Labs Bike Light    | Sauce Labs Onesie                 |

  @SortInventoryZ>A
  Scenario Outline: Sort product on inventory Z-A
    And the inventory list is loaded
    And the user click "Name (Z to A)" to sort the inventory by name in descending order
    And the inventory is sorted by name in descending order
    And the user gets the inventory array
    And the user should see the following array elements:
      | index | product name                      | price |
      | 0     | Test.allTheThings() T-Shirt (Red) | 15.99 |
      | 1     | Sauce Labs Onesie                 | 7.99  |
      | 2     | Sauce Labs Fleece Jacket          | 49.99 |
      | 3     | Sauce Labs Bolt T-Shirt           | 15.99 |
      | 4     | Sauce Labs Bike Light             | 9.99  |
      | 5     | Sauce Labs Backpack               | 29.99 |
    When the user compare the inventory array whit <expected_inventory_sort>
    Then de user valid inventory sort
    Examples:
      | expected_inventory_sort           |
      | Test.allTheThings() T-Shirt (Red) |
      | Sauce Labs Onesie                 |
      | Sauce Labs Fleece Jacket          |
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Bike Light             |
      | Sauce Labs Backpack               |

  @SortInventoryLow>Hight
  Scenario Outline: Sort product on inventory Low to High
    And the inventory list is loaded
    And the user click "Price (low to high)" to sort the inventory by price in ascending order
    And the inventory is sorted by price in ascending order
    And the user gets the inventory array
    And the user should see the following array elements:
      | index | product name                      | price |
      | 0     | Sauce Labs Onesie                 | 7.99  |
      | 1     | Sauce Labs Bike Light             | 9.99  |
      | 2     | Test.allTheThings() T-Shirt (Red) | 15.99 |
      | 3     | Sauce Labs Bolt T-Shirt           | 15.99 |
      | 4     | Sauce Labs Backpack               | 29.99 |
      | 5     | Sauce Labs Fleece Jacket          | 49.99 |

    When the user compare the inventory array whit <expected_inventory_sort>
    Then de user valid inventory sort
    Examples:
      | expected_inventory_sort           |
      | Sauce Labs Onesie                 |
      | Sauce Labs Bike Light             |
      | Test.allTheThings() T-Shirt (Red) |
      | Sauce Labs Bolt T-Shirt           |
      | Sauce Labs Backpack               |
      | Sauce Labs Fleece Jacket          |

  @SortInventoryHigh>HigLowht
  Scenario Outline: Sort product on inventory High to Hlowigh
    And the inventory list is loaded
    And the user click "Price (high to low)" to sort the inventory by price in descending order
    And the inventory is sorted by price in descending order
    And the user gets the inventory array
    And the user should see the following array elements:
      | index | product name                      | price |
      | 2     | Sauce Labs Fleece Jacket          | 49.99 |
      | 5     | Sauce Labs Backpack               | 29.99 |
      | 3     | Sauce Labs Bolt T-Shirt           | 15.99 |
      | 0     | Test.allTheThings() T-Shirt (Red) | 15.99 |
      | 4     | Sauce Labs Bike Light             | 9.99  |
      | 1     | Sauce Labs Onesie                 | 7.99  |

    When the user compare the inventory array whit <expected_inventory_sort>
    Then de user valid inventory sort
    Examples:
      | expected_inventory_sort           |
      | Sauce Labs Fleece Jacket          |
      | Sauce Labs Backpack               |
      | Sauce Labs Bolt T-Shirt           |
      | Test.allTheThings() T-Shirt (Red) |
      | Sauce Labs Bike Light             |
      | Sauce Labs Onesie                 |
