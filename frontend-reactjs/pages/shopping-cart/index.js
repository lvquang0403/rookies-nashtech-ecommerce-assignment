import { CheckoutProvider } from "../../context/checkout-context";
import ShoppingCart from "../../components/shopping-cart/shopping-cart"

export default function ShoppingCartPage() {
  return (
    <CheckoutProvider>
      <ShoppingCart></ShoppingCart>
    </CheckoutProvider>
  );
}
