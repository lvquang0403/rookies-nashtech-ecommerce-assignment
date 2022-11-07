import axios from "axios"

const API_URL = "http://localhost:8080/api/storage/uploadFile"

class FileService {

    uploadImage(token, file) {
        const headers = {
            'Authorization': `Bearer ${token}`
        }
        var formData = new FormData();
        formData.append("file", file);
        return axios.post(API_URL, formData, {
            headers: headers
        })
    }
}

export default new FileService();