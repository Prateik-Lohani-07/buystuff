# E-Commerce App - Requirements Document

## Roles

### 1. Non-registered User

Non-registered user can:

- search for products
- filter products by category, price range
- add/remove products to a cart
- view product details, customer reviews
- create an account (using email)

### 2. Registered User

Registered user can:

- update profile details: name, add payment information (credit card, paypal)
- manage multiple addresses in an address book
- place order on items in a cart (here taxes, discount etc. will be shown)
- track order status
- view their order history
- add a product review

### 3. Admin

Admin can:

- add/remove products to the inventory
- generate reports -> sales, top-selling products
- manage order status for each order
- add discounts for products


## System

System should:

- manage login/registration via JWT
- manage separate role access for different users -> non-registered, registered and admin
- Support multiple payment gateways


## Seller Functionality (optional)

Admin can:
- approve products 
- manage sellers
- 

Seller can:
- manage products
- offer discounts on their products