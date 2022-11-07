import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import React, { useEffect, useState } from "react";
import PricingCard from "../../components/shopping-cart/pricing-card";
import { useUserContext } from "../../context/user-context";
import CartItem from "../../components/shopping-cart/cart-item";
import { useCheckoutContext } from "../../context/checkout-context"
import Link from "next/link";

function ShoppingCart() {
    const [user, setUser] = useUserContext();
    const [cartItems, setCartItems] = useCheckoutContext()

    console.log('aaa', cartItems)
    if (cartItems) {
        console.log('bbbb', cartItems.reduce((total, item) => {
            return total + item.totalPrice
        }, 0));
    }
    return (
        <div className="container py-4">
            <div className="row g-3">
                <div className="col-lg-8">
                    <div className="card border-0 shadow-sm">
                        <div className="card-header bg-white">
                            <h5 className="my-2">Shopping Cart</h5>
                        </div>
                        <div className="card-body p-2">
                            {
                                cartItems && cartItems.map(cartItem => {
                                    return (
                                        <React.Fragment key={cartItem.id}>
                                            <CartItem item={cartItem} />
                                            <hr className="text-muted my-1" />
                                        </React.Fragment>)
                                })
                            }
                            <div className="table-responsive">
                                <table className="table table-borderless align-middle mb-0">
                                    {/* <thead>
                    <tr>
                      <th scope="col">Product</th>
                      <th scope="col">Price</th>
                      <th scope="col">Qty</th>
                      <th scope="col"></th>
                    </tr>
                  </thead> */}
                                    {/* <tbody>
                    {cartItems && cartItems.map(item => {
                      <CartItemRow key={item.id} item={item} />
                    })}
                  </tbody> */}
                                </table>
                            </div>
                        </div>
                        <div className="card-footer py-3">
                            <small>
                                <FontAwesomeIcon
                                    icon={["fas", "truck"]}
                                    className="text-success me-2"
                                />
                                Delivery within 1-2 weeks
                            </small>
                        </div>
                    </div>
                </div>
                <div className="col-lg-4">
                    <PricingCard totalPrice={cartItems ? cartItems.reduce((total, item) => {
                        return total + item.totalPrice
                    }, 0) : 0} />
                    <div className="d-grid gap-2 mt-2">
                        <Link href="/checkout/delivery-info">
                            <a className="btn btn-primary">Checkout</a>
                        </Link>
                    </div>
                </div>
            </div>
            <br />
            <br />
            <br />
        </div>
    );
}

export default ShoppingCart;
