import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/category"

class CategoryService {

    getCategorys() {
        return axios
          .get(API_URL)
      }
}

export default new CategoryService();