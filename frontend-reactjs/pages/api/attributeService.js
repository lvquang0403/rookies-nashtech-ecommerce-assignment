import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/attribute"

class AttributeService {

    getAttributes() {
        return axios
          .get(API_URL)
      }
}

export default new AttributeService();