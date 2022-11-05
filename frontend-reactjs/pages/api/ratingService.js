import axios from "axios"

const API_URL = "http://localhost:8080/api/v1/rating"

class RatingService {

    createRating(token, productId, score, comment) {
        const headers = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
        return axios
            .post(API_URL,
                {
                    productId,
                    score,
                    comment

                },
                {
                    headers: headers
                }

            )

    }
}

export default new RatingService();