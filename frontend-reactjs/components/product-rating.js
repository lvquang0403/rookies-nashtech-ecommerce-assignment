import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Box from '@mui/material/Box';
import Rating from '@mui/material/Rating';
import Typography from '@mui/material/Typography';

function ProductRating({ numberRating }) {
  return (
    <div className="hstack">
      <Rating name="read-only" value={numberRating} readOnly />
    </div>
  );
}

export default ProductRating;
