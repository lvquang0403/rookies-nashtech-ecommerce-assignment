import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/product"

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


    getProducts(token) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .get(`${API_URL}/search`,
                {
                    headers: headers
                }

            )

    }

    getDetaiProduct(productId) {
        return axios
          .get(API_URL+`/${productId}`)
    }

    
}

export default new ProductService();