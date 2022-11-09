import { useState } from "react";
import { FaStar } from "react-icons/fa";
import { useUserContext } from "../../context/user-context";
import ratingService from "../../pages/api/ratingService";


function Rating( { productId , ratings, setRatings } ) {
  const [user, setUser] = useUserContext()
  const [currentValue, setCurrentValue] = useState(0);
  const [hoverValue, setHoverValue] = useState(undefined);
  const [comment, setComment] = useState('')
  const stars = Array(5).fill(0)
  const colors = {
    orange: "#FFBA5A",
    grey: "#a9a9a9"
    
};
  const handleClick = value => {
    setCurrentValue(value)
  }

  const handleSubmit = (token, productId, score, comment, customerId) => {
    if(comment !== ""){
      ratingService.createRating(token, productId, score, comment, customerId)
      .then(res => {
        setRatings([...ratings, res.data])
      })
      .catch(res => {
        if (res.response.status === 400) {
          alert("You have rated this product !!")

        }
      })
      setComment("")
      setCurrentValue(0)
    }
  }

//   const handleMouseOver = newHoverValue => {
//     setHoverValue(newHoverValue)
//     console.log(hoverValue)
//   };

//   const handleMouseLeave = () => {
//     setHoverValue(undefined)
//   }


  return (
    <div style={styles.container}>
      <h2> Rating Product </h2>
      <div style={styles.stars}>
        {stars.map((_, index) => {
          return (
            <FaStar
              key={index}
              size={24}
              onClick={() => handleClick(index + 1)}
            //   onMouseOver={() => handleMouseOver(index + 1)}
            //   onMouseLeave={handleMouseLeave}
              color={(hoverValue || currentValue) > index ? colors.orange : colors.grey}
              style={{
                marginRight: 10,
                cursor: "pointer"
              }}
            />
          )
        })}
      </div>
      <textarea
        placeholder="What's your experience?"
        style={styles.textarea}
        value={comment}
        onChange={e=> setComment(e.target.value)}
      />

      <button onClick={() => handleSubmit(user.token, productId, currentValue, comment, user.id)}
        style={styles.button}
      >
        Submit
      </button>
      
    </div>
  );
};


const styles = {
  container: {
    display: "flex",
    flexDirection: "column",
    alignItems: "center"
  },
  stars: {
    display: "flex",
    flexDirection: "row",
  },
  textarea: {
    border: "1px solid #a9a9a9",
    borderRadius: 5,
    padding: 10,
    margin: "20px 0",
    minHeight: 100,
    width: 300
  },
  button: {
    border: "1px solid #a9a9a9",
    borderRadius: 5,
    width: 300,
    padding: 10,
  }

};




export default Rating;