import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/attributes"

class AttributeService {

    getAttributes() {
        return axios
            .get(API_URL)
    }

    createAttribute(token, attributeName) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .post(`${API_URL}`,
                {
                    attributeName
                },
                {
                    headers: headers
                })
    }

    deleteAttribute(token, attributeId) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .delete(`${API_URL}/${attributeId}`,
                {
                    headers: headers
                })
    }


    updateAttribute(token, attributeId, attributeName) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .put(`${API_URL}/${attributeId}`,
                {
                    attributeName,
                },

                {
                    headers: headers
                })
    }
}

export default new AttributeService();