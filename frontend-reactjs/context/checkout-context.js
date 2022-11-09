import { createContext, useContext, useEffect, useState } from "react";
import { useUserContext } from "./user-context";
import cartService from "../pages/api/cartService";

const Context = createContext();

export function CheckoutProvider({ children }) {
    const [user, setUser] = useUserContext()
    const [oderInfor, setOrderInfor] = useState({});
    const [cartItems, setCartItems] = useState([]);

    useEffect(() => {
        if (user.id) {
          cartService.getCartItems(user.token, user.id)
            .then(res => setCartItems(res.data.items))
            .catch(res => console.log(res))
        }
      }, [user.numberCartItems])
    return (
        <Context.Provider value={[cartItems, setCartItems, oderInfor, setOrderInfor]}>{children}</Context.Provider>
    );
}

export function useCheckoutContext() {
    return useContext(Context);
}