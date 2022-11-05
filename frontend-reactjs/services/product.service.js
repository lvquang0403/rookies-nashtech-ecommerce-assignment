import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/product"

class ProductService {

    getProductByCategoryId(categoryId, pageNumber, pageSize) {
        return axios
        .get(API_URL+`/?categoryId=${categoryId}`,{
            params: {
              pageNumber: pageNumber,
              pageSize: pageSize
            }
          })
    }

    getDetaiProduct(productId) {
      return axios
        .get(API_URL+`/${productId}`)
    }

    searchProduct(productName, pageNumber, pageSize) {
      return axios
        .get(API_URL+`/search`,{
          params: {
            productName : productName,
            pageNumber: pageNumber,
            pageSize: pageSize
          }
        })
    }
}

export default new ProductService();