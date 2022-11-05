import React from 'react'
import ProductRating from '../product-rating'

const UserRating = ( { rating } ) => {
    return (
        <div className="card mt-4">
            <div className="card-body">
                <h5 className="card-title">{rating.fullName}</h5>
                <h6 className="card-subtitle mb-2 text-muted"><ProductRating numberRating={rating.score} /></h6>
                <p className="card-text">{rating.comment}</p>
            </div>
        </div>
    )
}

export default UserRating
