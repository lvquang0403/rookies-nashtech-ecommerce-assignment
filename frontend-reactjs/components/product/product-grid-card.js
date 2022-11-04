import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link";

function ProductGridCard({ id, title, price, src, categoryId }) {
  return (
    <div className="card h-100 border-0 shadow-sm">
      <Link href={`/product/${id}?categoryId=${categoryId}`}>
        <a>
          <div className="ratio ratio-1x1">
            <img
              className="card-img-top "
              src={src}
              alt="Product image."
              style={{ objectFit: "cover" }}
            />
          </div>
        </a>
      </Link>
      <div className="card-body">
        <div className="vstack gap-2">
          <Link href={`/product/${id}/?categoryId${categoryId}`}>
            <a className="text-dark text-decoration-none">{title}</a>
          </Link>

          <h6 className="fw-semibold">{new Intl.NumberFormat('de-DE').format(price)} VND</h6>

          <div className="hstack gap-2">
            <button className="btn btn-sm btn-secondary text-primary flex-grow-1 d-none d-lg-block" onClick={``}>
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
