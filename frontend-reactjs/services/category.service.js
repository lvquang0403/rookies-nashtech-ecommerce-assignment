import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/category"

class CategoryService {

    getCategoris(categoryId) {
        return axios
        .get(API_URL)
    }
}

export default new CategoryService();