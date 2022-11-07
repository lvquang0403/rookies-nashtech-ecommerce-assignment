import Link from "next/link";

function ReviewCartItem({ title, color, quantity, price, src }) {
  return (
    <div className="d-flex">
      <div className="flex-shink-0">
        <img
          className="rounded"
          src={src}
          width={80}
          height={80}
          alt="Product image."
          style={{ objectFit: "cover" }}
        />
      </div>
      <div className="flex-grow-1 ms-3 h-100">
        <div className="vstack">
          <Link href="/product/1">
            <a className="text-dark text-decoration-none">{title}</a>
          </Link>
          <small className="text-muted mb-2" style={{ fontSize: 12 }}>
            <span>{color}</span>
          </small>
          <h6 className="mb-0">{quantity} &times; {new Intl.NumberFormat('de-DE').format(price)} VND</h6>
        </div>
      </div>
    </div>
  );
}

export default ReviewCartItem;
