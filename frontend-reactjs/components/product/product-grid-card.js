import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link";
import { useUserContext } from "../../context/user-context";
import cartService from "../../pages/api/cartService";
import { useRouter } from "next/router";

function ProductGridCard({ product, categoryId }) {
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

  return (
    <div className="card h-100 border-0 shadow-sm">
      <Link href={`/product/${product.productId}?categoryId=${categoryId}`}>
        <a>
          <div className="ratio ratio-1x1">
            <img
              className="card-img-top "
              src={product.images[0].url}
              alt="Product image."
              style={{ objectFit: "cover" }}
            />
          </div>
        </a>
      </Link>
      <div className="card-body">
        <div className="vstack gap-2">
          <Link href={`/product/${product.productId}/?categoryId${categoryId}`}>
            <a className="text-dark text-decoration-none">{product.productName}</a>
          </Link>

          <h6 className="fw-semibold">{new Intl.NumberFormat('de-DE').format(product.price)} VND</h6>

          <div className="hstack gap-2">
            <button className="btn btn-sm btn-secondary text-primary flex-grow-1 d-none d-lg-block" onClick={(e) => handleAddToCart(e, product.productId, product.images[0].color,1)}>
              <FontAwesomeIcon icon={["fas", "cart-plus"]} />
              &nbsp;Add to card
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ProductGridCard;
