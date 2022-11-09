import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/categories"

class CategoryService {

  getCategorys() {
    return axios
      .get(API_URL)
  }

  createCategory(token, categoryName) {
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return axios
      .post(`${API_URL}`,
      {
        categoryName
      },
        {
          headers: headers
        })
  }


  updateCategory(token, categoryId, categoryName) {
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return axios
      .put(`${API_URL}/${categoryId}`,
        {
          categoryName,
        },

        {
          headers: headers
        })
  }


  deleteCategory(token, categoryId) {
    const headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    }
    return axios
      .delete(`${API_URL}/${categoryId}`,
        {
          headers: headers
        })
  }
}

export default new CategoryService();