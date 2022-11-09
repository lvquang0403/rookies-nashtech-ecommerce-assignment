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

    signup(data) {
        return axios
        .post(`${API_URL}/signup`,
        {
            firstName: data.firstName,
            lastName: data.lastName,
            address: data.address,
            phone: data.phone,
            email: data.email,
            userName: data.userName,
            password: data.password
        })

    }
}

export default new AuthService();