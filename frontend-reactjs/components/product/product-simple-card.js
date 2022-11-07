import Link from "next/link";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ProductRating from "../product-rating";
import axios from "axios";
import { useUserContext } from "../../context/user-context";
import { useEffect } from "react";
import { useRouter } from "next/router";
import cartService from "../../pages/api/cartService";
function ProductSimpleCard({ product, categoryId }) {
  const router = useRouter();
  const [user, setUser] = useUserContext();
  const handleAddToCart = (e,productId, color, quantity) => {
    if(user.type.length ===0 ){
      router.push('/auth/login')
    }
    cartService.addToCart(user.token, productId, color, quantity)
    .then(res => {
      setUser({
        id: user.id,
        type: user.type,
        name: user.name,
        token: user.token,
        numberCartItems: user.numberCartItems + quantity
      })
    })
    .catch(res => console.log(res))
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  useEffect(() => {
  }, [])
  return (
    <div className="card h-100 border-0 shadow-sm">
      <div className="ratio ratio-1x1">
        <img
          className="card-img-top"
          src={product.images[0].url}
          alt="Product image."
          style={{ objectFit: "cover" }}
        />
      </div>
      <div className="card-body">
        <Link href={{
          pathname: `/product/${product.productId}?categoryId=${categoryId}`,
          query: { categoryId: categoryId },
          }}>
          <a className="mb-1 text-dark text-decoration-none">
            {product.productName}
          </a>
        </Link>

        <ProductRating avgRating={product.numberRating} />

        <h6 className="mb-0 fw-semibold mt-2">{new Intl.NumberFormat('de-DE').format(product.price)} VND</h6>
      </div>
      <div className="hstack gap-2">
        <button type="button" className="btn btn-info flex-grow-1 d-none d-lg-block" onClick={(e) => handleAddToCart(e, product.productId, product.images[0].color, 1)} >
          <FontAwesomeIcon icon={["fas", "cart-plus"]} />
          Add to card</button>
      </div>
    </div>
  );
}

export default ProductSimpleCard;
