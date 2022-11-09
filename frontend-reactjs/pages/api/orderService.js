import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/orders"

class OrderService {

    createOrder(token, orderInfor, totalPrice, cartItems, customerId) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }

        const items = cartItems.map((item => {
            return {
                productId: item.productId,
                price: item.price,
                color: item.color,
                quantity: item.quantity
            }
        }))

        return axios
            .post(`${API_URL}`,
                {
                    customerId: customerId,
                    customerName: orderInfor.customerName,
                    orderPhone: orderInfor.phone,
                    address: orderInfor.address,
                    totalPrice: totalPrice,
                    items: items
                },
                {
                    headers: headers
                }

            )

    }

    createOrderOneItem(token, product, color, orderInfor, customerId) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }

        return axios
            .post(`${API_URL}/create-one/?productId=${product.productId}&color=${color}`,
                {
                    customerId: customerId,
                    customerName: orderInfor.customerName,
                    orderPhone: orderInfor.phone,
                    address: orderInfor.address,
                    totalPrice: product.price
                },
                {
                    headers: headers
                }

            )

    }

    getOrders(token, pageNumber, pageSize) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }

        const params = {
            pageSize,
            pageNumber
        };
        return axios
            .get(`${API_URL}`, {
                params: params,
                headers: headers
            }

            )

    }
    updateStatusOrder(token, orderId, status) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }

        return axios
            .put(`${API_URL}/${orderId}/?status=${status}`, {

            },
            
            {

                headers: headers

            }
            )

    }



    getOrderCustomers(token, customerId) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .get(`${API_URL}/customer-orders/${customerId}`,
                {
                    headers: headers
                }
            )

    }


    getOrderItemsById(token, orderId) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .get(`${API_URL}/order-items/${orderId}`,
                {
                    headers: headers
                }
            )

    }

}

export default new OrderService();