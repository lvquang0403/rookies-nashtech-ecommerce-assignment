import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link";

function ProductSimpleHorizontal({ id, price, productName, src, categoryId }) {
  return (
    <div className="d-flex py-2">
      <div className="flex-shink-0" style={{ height: 80 }}>
        <img
          className="rounded"
          src={src}
          width={80}
          height={80}
          alt="Product image."
          style={{ objectFit: "cover" }}
        />
      </div>
      <div className="d-flex flex-column flex-grow-1 ms-3">
        <Link href={`product/${id}`}>
          <a className="text-dark text-decoration-none">{productName}</a>
        </Link>
        <h6 className="mb-0 fw-semibold">{new Intl.NumberFormat('de-DE').format(price)} VND</h6>
        <div className="mt-auto">
          <button className="btn btn-sm btn-secondary text-primary rounded-3">
            <FontAwesomeIcon icon={("fas", "cart-plus")} />
            &nbsp;Add to cart
          </button>
        </div>
      </div>
    </div>
  );
}

export default ProductSimpleHorizontal;
