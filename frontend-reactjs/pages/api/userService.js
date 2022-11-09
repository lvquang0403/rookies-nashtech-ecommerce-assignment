import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/customers"

class CustomerService {



    getCustomers(token, pageSize, pageNumber) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .get(`${API_URL}?pageSize=${pageSize}&pageNumber=${pageNumber}`,
                {
                    headers: headers
                }

            )

    }

    updateCustomer(customerId, customer, newPass, token) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .put(`${API_URL}/${customerId}`,
            {
                firstName: customer.firstName,
                lastName: customer.lastName,
                address: customer.address,
                phone: customer.phone,
                password: newPass
            },
                {
                    headers: headers
                }

            )

    }
    getByCustomerId(token ,customerId) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .get(`${API_URL}/${customerId}`,
                {
                    headers: headers
                }

            )

    }


    deleteCustomer(token,customerId) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .delete(`${API_URL}/${customerId}`,
                {
                    headers: headers
                }

            )

    }
}

export default new CustomerService();