import axios from "axios"

const API_URL = "http://localhost:8080/api/auth"

class AuthService {

    signin(username, password) {
        return axios
        .post(`${API_URL}/signin`,
        {
            username,
            password
        })

    }
}

export default new AuthService();