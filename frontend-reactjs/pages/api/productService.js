import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/products"

class ProductService {

    addProduct(token, product) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .post(API_URL,product,
                {
                    headers: headers
                }

            )

    }


    getProducts(token, pageSize, pageNumber) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }

        const params = {
            pageSize,
            pageNumber
        };

        return axios
            .get(API_URL,{
                params: params,
                headers: headers
            }

            )

    }

    getDetaiProduct(productId) {
        return axios
          .get(API_URL+`/${productId}`)
    }

    updateProduct(token, product, productId) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .put(`${API_URL}/${productId}`, product, 
                {
                    headers: headers
                }

            )

    }

    deleteProduct(token,productId) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .delete(`${API_URL}/${productId}`,
                {
                    headers: headers
                }
            )

    }

    
}

export default new ProductService();