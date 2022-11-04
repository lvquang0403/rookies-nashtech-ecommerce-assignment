import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/category"

class CategoryService {

    getProductByCategoryId(categoryId) {
        return axios
        .get(API_URL)
        .then(res => {
            console.log(res)
        })

    }
}

export default new CategoryService();