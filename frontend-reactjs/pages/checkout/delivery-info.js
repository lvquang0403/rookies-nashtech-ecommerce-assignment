import Link from "next/link";
import { useRouter } from 'next/router'
import CheckoutStepper from "../../components/checkout/checkout-stepper";
import { CheckoutProvider } from "../../context/checkout-context";
import PricingCard from "../../components/shopping-cart/pricing-card";
import Layout from "../../components/layout";
import { UserProvider } from "../../context/user-context";
import { useUserContext } from "../../context/user-context";
import { yupResolver } from '@hookform/resolvers/yup'
import * as yup from 'yup'
import { useForm } from "react-hook-form";
import { useState } from "react";
import { useEffect } from "react";
import productService from "../../services/product.service";
import { useCheckoutContext } from "../../context/checkout-context";

function DeliveryInfo() {
  const router = useRouter()
  const [user, setUser] = useUserContext();
  const [cartItems, setCartItems, orderInfor, setOrderInfor] = useCheckoutContext();
  const [totalPrice, setToTalPrice] = useState(0);
  const [product, setProduct] = useState({})
  const {
    productId,
    color
  } = router.query

  useEffect(() => {
    if (productId) {
      productService.getDetaiProduct(productId)
        .then(res => {
          setToTalPrice(res.data.price)
          setProduct(res.data)
        })
    }
    else {
      setToTalPrice(cartItems.reduce((total, item) => total + item.totalPrice, 0))
    }

  }, [productId, cartItems])

  const onSubmit = data => {
    console.log(productId)
    setOrderInfor(data)
    setCartItems(cartItems)
    if (productId) {
      router.push({
        pathname: '/checkout/confirm-checkout',
        query: {
          productId: productId,
          color: color
        }
      })
    } else {
      router.push({
        pathname: '/checkout/confirm-checkout'
      })

    }

  };
  const userSchema = yup.object().shape({
    customerName: yup.string().required("Customer Name is required"),
    phone: yup.string().required("Phone is required").min(10).max(11),
    address: yup.string().required("Address is required")
  })
  const { register, setValue, handleSubmit, watch, formState: { errors } } = useForm({
    resolver: yupResolver(userSchema)
  });
  setValue("customerName", user.name)
  setValue("phone", user.phone)
  setValue("address", user.address)
  console.log(cartItems)
  return (
    <div className="container py-4">
      <div className="row">
        <div className="col-md-12">
          <CheckoutStepper />
        </div>
      </div>
      <div className="row g-3">
        <div className="col-lg-8">
          <div className="card border-0 shadow-sm">
            <div className="card-body">
              <form className="row g-3" onSubmit={handleSubmit(onSubmit)}>
                <h4 className="fw-semibold mb-0">Contact Info</h4>
                <div className="col-md-12">
                  <label className="form-label">Name</label>
                  <input type="text" className="form-control" {...register("customerName")} />
                </div>
                <div className="col-md-12">
                  <label className="form-label">Phone</label>
                  <div className="input-group">
                    <input type="tel" className="form-control" {...register("phone")} />
                  </div>
                </div>
                <div className="col-md-12">
                  <hr className="text-muted mb-0" />
                </div>

                <h4 className="fw-semibold mb-0">Shipping Info</h4>
                <div className="col-md-12">
                  <label className="form-label">Address</label>
                  <input type="text" className="form-control" {...register("address")} />
                </div>
                <div className="col-md-12 mt-4">
                  <div className="d-grid gap-2 d-flex justify-content-end">
                    <Link href="/">
                      <a className="btn btn-outline-primary">Cancel</a>
                    </Link>
                    <input type="submit" value="Continue" />
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
        <div className="col-lg-4">
          <PricingCard totalPrice={totalPrice} />
        </div>
      </div>
      <br />
      <br />
      <br />
    </div>
  );
}

DeliveryInfo.getLayout = (page) => {
  return (
    <UserProvider>
      <CheckoutProvider>
        <Layout simpleHeader>{page}</Layout>;
      </CheckoutProvider>
    </UserProvider>
  )
};

export default DeliveryInfo;
