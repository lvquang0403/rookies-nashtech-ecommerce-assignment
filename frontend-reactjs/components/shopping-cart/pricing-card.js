import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link";
import { useRouter } from 'next/router'


function PricingCard({ totalPrice, end, handleConfirm, productId }) {
  const router = useRouter()
  return (
    <div className="card border-0 shadow-sm">
      <div className="card-body">
        <div className="vstack gap-2">
          <div className="d-flex justify-content-between">
            <span>Subtotal:</span>
            <span>{new Intl.NumberFormat('de-DE').format(totalPrice)} VND</span>
          </div>
          <div className="d-flex justify-content-between">
            <span>Discount:</span>
            <span className="text-danger">-</span>
          </div>

          <hr className="text-muted" />

          <div className="d-flex justify-content-between">
            <span className="h5">Total:</span>
            <span className="fw-bold h5 mb-0">{new Intl.NumberFormat('de-DE').format(totalPrice)} VND</span>
          </div>

          {(
            <div className="d-grid gap-2 mt-2">
              {end &&
                <div className="mt-3 d-grid gap-2">
                  <button
                    className="btn btn-primary"
                    onClick={() => handleConfirm(productId)}
                  >
                    Confirm
                  </button>
                </div>
              }
              <Link href="/">
                <a className="btn btn-outline-primary">Continue Shopping</a>
              </Link>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default PricingCard;
