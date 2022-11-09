import { useRouter } from "next/router";
import { useState, useEffect } from "react";
import productService from "../../services/product.service";
import Link from "next/link";
import { useUserContext } from "../../context/user-context";
import { useCheckoutContext } from "../../context/checkout-context";
import { CheckoutProvider } from "../../context/checkout-context";
import { UserProvider } from "../../context/user-context";
import CheckoutStepper from "../../components/checkout/checkout-stepper";
import ReviewCartItem from "../../components/checkout/review-cart-item";
import Layout from "../../components/layout";
import PricingCard from "../../components/shopping-cart/pricing-card";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { set } from "react-hook-form";
import orderService from '../api/orderService'

function ConfirmCheckout() {
  const [user, setUser] = useUserContext();
  const [cartItems, setCartItems, orderInfor, setOrderInfor] = useCheckoutContext()
  // const [orderInfor, setOrderInfor] = useCheckoutContext()
  const [totalPrice, setToTalPrice] = useState(0);
  const [product, setProduct] = useState({})
  const [image, setImage] = useState('')
  const router = useRouter();
  const {
    productId,
    color
  } = router.query
  const handleConfirm = (productId) => {
    // router.push({
    //   pathname: "/checkout/checkout-success",
    // });
    if (productId) {
      orderService.createOrderOneItem(user.token, product, color, orderInfor, user.id)
      .then(res => {
        setToTalPrice(0)
        router.push({
          pathname: "/checkout/checkout-success",
        });
      })
    }
    else {
      orderService.createOrder(user.token, orderInfor, totalPrice, cartItems, user.id)
        .then(res => {
          setCartItems([])
          setToTalPrice(0)
          setUser({...user,numberCartItems: 0 })
          router.push({
            pathname: "/checkout/checkout-success",
          });
        })
        .catch(res => console.log(res))
    }
  }
  useEffect(() => {
    if (productId) {
      productService.getDetaiProduct(productId)
        .then(res => {
          setToTalPrice(res.data.price)
          setProduct(res.data)
          setImage(res.data.images.find(img => img.color === color).url)
        })
    }
    else {
      setToTalPrice(cartItems.reduce((total, item) => total + item.totalPrice, 0))
    }

  }, [productId, cartItems])

  console.log(222, orderInfor)
  return (
    <div className="container py-4">
      <div className="row">
        <div className="col-md-12">
          <CheckoutStepper step={3} />
        </div>
      </div>
      <div className="row g-3">
        <div className="col-lg-8">
          <div className="card border-0 shadow-sm">
            <div className="card-body">
              <h4 className="fw-semibold mb-3">Items in cart</h4>
              <div className="row row-cols-1 row-cols-md-2 g-3">
                {
                  (productId && product.images) ? (<ReviewCartItem title={product.productName} price={product.price} color={color} quantity={1} src={image} />) :
                    cartItems.map(item => <ReviewCartItem key={item.id} title={item.productName} price={item.price} color={item.color} quantity={item.quantity} src={item.image} />)
                }
              </div>
              <hr className="text-muted" />
              <div className="row g-3">
                <div className="col-md-6">
                  <h4 className="fw-semibold">Shipping Address</h4>
                  <div className="vstack text-dark small mt-3 fs-6 ">
                    <p><span>Customer Name : </span><span>{orderInfor.customerName}</span></p>
                    <p><span>Customer Phone : </span><span>{orderInfor.phone}</span></p>
                    <p><span>Adress : </span><span>{user.address}</span></p>
                    <p><span>Adress Shipping : </span><span>{orderInfor.address}</span></p>
                  </div>
                </div>
                {/* <div className="col-md-6">
                  <h4 className="fw-semibold">Payment Method</h4>
                  <div className="d-flex gap-3 text-success">
                    <span className="fw-bold">
                      <FontAwesomeIcon icon={["fab", "cc-visa"]} size="lg" />
                    </span>
                    <div className="vstack small text-muted">
                      <span>XXXX-XXXX-XXXX-2345</span>
                      <span>Exp: 03/25</span>
                    </div>
                  </div>
                </div> */}
              </div>
            </div>
          </div>
        </div>
        <div className="col-lg-4">
          <PricingCard end={true} totalPrice={totalPrice} handleConfirm={handleConfirm} productId={productId}>
          </PricingCard>
        </div>
      </div>
      <br />
      <br />
      <br />
    </div>
  );
}

ConfirmCheckout.getLayout = (page) => {
  return (
    <UserProvider>
      <CheckoutProvider>
        <Layout simpleHeader>{page}</Layout>;
      </CheckoutProvider>
    </UserProvider>)
};

export default ConfirmCheckout;
