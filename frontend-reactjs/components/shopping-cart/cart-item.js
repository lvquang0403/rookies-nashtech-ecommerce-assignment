import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Image from "next/image";
import Link from "next/link";
import { useEffect, useState } from "react";
import { useUserContext } from "../../context/user-context";
import { useCheckoutContext } from "../../context/checkout-context"
import cartService from "../../pages/api/cartService";
function CartItem({ item }) {
  const [user, setUser] = useUserContext()
  const [cartItems, setCartItems] = useCheckoutContext()

  const handleRemoveItem = (item) => {
    const newCarts = cartItems.filter(e => e.id != item.id);
    cartService.removeCartItem(user.token, item.id)
    .then(res => {
      setCartItems(newCarts)
      setUser({...user, numberCartItems: user.numberCartItems - item.quantity})
    })
  }

  const updateNumberCartItemsAndSetCartItems = (newCart) => {
    cartService.getNumberCartItems(user.token, user.id)
    .then(res => {
      setCartItems(newCart)
      setUser({...user, numberCartItems: res.data})
    })
    .catch(res => console.log(res))
  }

  const handleUpdateCartItem = (quantity, item) => {
    // if (!isNaN(quantity)) {
    //   return
    // }
    // cartService.updateCartItem(user.token, cartItemId, quantity)
    // .then(res => console.log(res))
    // .catch(res => console.log(res))
    const newCart = cartItems.map(c => {
      if (c.id === item.id) {
        c.quantity = parseInt(quantity)
        c.totalPrice = c.quantity * c.price
      }
      return c;
    })
    cartService.updateCartItem(user.token, item.id, quantity)
    .then(res => updateNumberCartItemsAndSetCartItems(newCart))
    // console.log(setCartItems)
  }
  const getQtyInput = () => {
    return (
      <div className="input-group input-group-sm">
        <button className="btn btn-outline-primary" type="button">
          <FontAwesomeIcon icon={["fas", "minus"]} />
        </button >
        <input
          type="number"
          step={1}
          min ={1}
          className="form-control text-center border-primary"
          placeholder=""
          value={item.quantity}
          onChange={(e) => {
            handleUpdateCartItem(e.target.value, item)
          }}
          // value={cartItem.quantity}
          size="2"
        />
        <button className="btn btn-outline-primary" type="button" >
          <FontAwesomeIcon icon={["fas", "plus"]} />
        </button>
      </div>
    );
  };

  return (
    <div className="d-flex py-2">
      <div className="flex-shink-0">
        <img
          className="rounded"
          src={item.image}
          width={100}
          height={100}
          alt="Product image."
          style={{ objectFit: "cover" }}
        />
      </div>
      <div className="ms-3 me-md-2 flex-grow-1">
        <Link href={`/product/${item.productId}`}>
          <a className="text-dark text-decoration-none">{item.productName}</a>
        </Link>
        <small className="d-flex flex-wrap text-muted">
          <span className="">{item.color}</span>
          <div className="vr mx-2"></div>
        </small>
        <h5 className="fw-semibold mt-2">{new Intl.NumberFormat('de-DE').format(item.price)} VND</h5>
        <div className="d-inline-block d-sm-none mt-2">{getQtyInput()}</div>
      </div>
      <div className="flex-shrink-0 d-none d-sm-inline ms-3">
        {getQtyInput()}
      </div>
      <div className="flex-shrink-0 ms-4">
        <button className="btn btn-sm btn-outline-danger" type="button" onClick={() => {handleRemoveItem(item)}}>
          <FontAwesomeIcon icon={["fas", "trash-alt"]} />
        </button>
      </div>
    </div>
  );
}

export default CartItem;
