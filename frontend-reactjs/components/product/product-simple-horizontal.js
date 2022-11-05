import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link";
import { useRouter } from "next/router";
import { useUserContext } from "../../context/user-context";
import cartService from "../../pages/api/cartService";

function ProductSimpleHorizontal({ product, categoryId }) {
  const [user, setUser] = useUserContext();
  // const handleAddToCart = (e,productId, color, quantity) => {
  //   cartService.addToCart(user.token, productId, color, quantity)
  //   .then(res => {
  //     setUser({
  //       id: user.id,
  //       type: user.type,
  //       name: user.name,
  //       token: user.token,
  //       numberCartItems: user.numberCartItems + quantity
  //     })
  //   })
  //   .catch(res => console.log(res))
  // }

  return (
    <div className="d-flex py-2">
      <div className="flex-shink-0" style={{ height: 80 }}>
        <img
          className="rounded"
          src={product.images[0].url}
          width={80}
          height={80}
          alt="Product image."
          style={{ objectFit: "cover" }}
        />
      </div>
      <div className="d-flex flex-column flex-grow-1 ms-3">
        <Link href={`/product/${product.productId}?categoryId=${categoryId}`}>
          <a className="text-dark text-decoration-none">{product.productName}</a>
        </Link>
        <h6 className="mb-0 fw-semibold">{new Intl.NumberFormat('de-DE').format(product.price)} VND</h6>
        <div className="mt-auto">
          {/* <button className="btn btn-sm btn-secondary text-primary rounded-3" onClick={(e) => {handleAddToCart(product.productId, product.images[0].color), 1}}>
            <FontAwesomeIcon icon={("fas", "cart-plus")} />
            &nbsp;Add to cart
          </button> */}
        </div>
      </div>
    </div>
  );
}

export default ProductSimpleHorizontal;
