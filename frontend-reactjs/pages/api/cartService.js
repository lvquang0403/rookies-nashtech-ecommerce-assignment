import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/carts"

class CartService {

    getNumberCartItems(token, customerId) {
        return axios
            .get(`${API_URL}/number-cart-items/${customerId}`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
    }

    addToCart(token, productId, color, quantity, customerId) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .post(API_URL,
                {
                    productId,
                    customerId,
                    color,
                    quantity

                },
                {
                    headers: headers
                }

            )

    }

    updateCartItem(token, cartItemId, quantity){
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
        .put(`${API_URL}/cart-item`,
        {
            cartItemId,
            quantity
        },
        {
            headers: headers
        }
        )


    }

    removeCartItem(token, cartItemId){
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
        .delete(`${API_URL}/${cartItemId}`,
        {
            headers: headers
        }
        )

    }

    getCartItems(token, customerId) {
        // const config = {
        //     headers: {
        //         'Authorization': `Bearer ${token}`,
        //         'Content-Type': 'application/json'
        //     }
        // };
        // // const data = {
        // //     pageSize,
        // //     pageNumber
        // // }
        return axios
            .get(`${API_URL}/cart-items/${customerId}`, {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })

    }
}

export default new CartService();