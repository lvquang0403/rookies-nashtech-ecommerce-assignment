import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/order"

class OrderService {

    createOrder(token, orderInfor, totalPrice) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .post(API_URL,
                {
                    customerName: orderInfor.customerName,
                    orderPhone: orderInfor.phone,
                    address: orderInfor.address,
                    totalPrice: totalPrice

                },
                {
                    headers: headers
                }

            )

    }

    createOrderOneItem(token, product, color, orderInfor) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .post(`${API_URL}/create-one/?productId=${product.productId}&color=${color}`,
                {
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


    getOrderCustomers(token) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .get(`${API_URL}/getCustomerOrders`,
                {
                    headers: headers
                }
            )

    }
    
}

export default new OrderService();