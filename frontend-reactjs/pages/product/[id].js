import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ProductRating from "../../components/product-rating";
import { useRouter } from 'next/router'
import ProductSimpleHorizontal from "../../components/product/product-simple-horizontal";
import { useState, useEffect } from "react";
import productService from "../../services/product.service";
import Rating from "../../components/rating/Rating";
import ratingService from "../api/ratingService";
import cartService from "../api/cartService";
import UserRating from "../../components/rating/UserRating";
import { useUserContext } from "../../context/user-context";
import React from "react";
import Link from 'next/link'
import { useCheckoutContext } from "../../context/checkout-context";
function ProductDetail() {
  const imagess = [1, 2, 3]
  const [user, setUser] = useUserContext()
  const [images, setImages] = useState([])
  const [color, setColor] = useState('')
  const router = useRouter()
  const [product, setProduct] = useState({})
  const [ratings, setRatings] = useState([])
  const [products, setProducts] = useState([])
  const [attributes, setAttributes] = useState()
  const [avgRating, setAvgRating] = useState(0)
  const {
    query: { id, categoryId },

  } = router


  const handleBuy = () => {
    if (user.type.length === 0) {
      router.push('/auth/login')
    }
    else {
      router.push({
        pathname: '/checkout/delivery-info',
        query: { productId: product.productId, color: color }
      })
    }
  }


  const handleAddToCart = (e, productId, color, quantity) => {
    console.log(color, productId)
    if (user.type.length === 0) {
      router.push('/auth/login')
    }
    cartService.addToCart(user.token, productId, color, quantity, user.id)
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
    if (ratings.length > 0) {
      setAvgRating((ratings.reduce((total, e) => total + e.score, 0) / ratings.length))
    }
  }, [ratings])


  useEffect(() => {
    const fetchData = async () => {

      if (categoryId) {
        await productService.getProductByCategoryId(categoryId, 0, 5)
          .then(res => {
            setProducts(res.data.products)
          })
      }
    }
    fetchData();
  }, [categoryId])

  const change = (e) => {
    console.log(e.target.value);
    setColor(e.target.value)
  }

  useEffect(() => {
    const fetchData = async () => {
      if (id) {
        await productService.getDetaiProduct(id)
          .then(res => {
            setProduct(res.data)
            setRatings(res.data.ratings)
            setImages(res.data.images)
            setColor(res.data.images[0].color)
            setAttributes(res.data.attributes)
          })
      }
    }
    fetchData();
  }, [id]);
  console.log(avgRating)
  console.log(ratings)
  return (
    <div className="vstack">
      <div className="bg-secondary">
        <div className="container">
          <div className="row py-4 px-2">
          </div>
        </div>
      </div>
      <div className="bg-white mb-4">
        <div className="container py-4">
          <div className="row gy-3 gx-4">
            <div className="col-lg-5">
              <div className="row">
                <div className="col-12">
                  <div className="ratio ratio-1x1">
                    <img
                      className="rounded"
                      src={images && (images.find(e => e.color === color))?.url}
                      width={300}
                      height={300}
                      alt="Product image."
                    />
                  </div>
                </div>
              </div>
              <div className="row mt-3 d-none d-lg-block">
                <div className="col-12 d-flex justify-content-center">
                  {images.map((e) => {
                    return (
                      <div
                        key={e.color}
                        style={{ width: 60 }}
                        className="me-2 ratio ratio-1x1"
                      >
                        <img
                          className="rounded"
                          src={e.url}
                          width={60}
                          height={60}
                          alt="Product image."
                          key={e}
                        />
                      </div>
                    );
                  })}
                </div>
              </div>
            </div>

            <div className="col-lg-7">
              <div className="d-flex">
                <div className="d-inline h2 mb-0 fw-semibold me-3">
                  {product.productName}
                </div>
              </div>
              <div className="vstack">
                <div className="d-flex mb-3 gap-2">
                  <ProductRating numberRating={avgRating} /><span>{`(${ratings.length}) reviews`}</span>
                  <span className="text-success small">
                    <FontAwesomeIcon icon={["fas", "check-circle"]} />
                    &nbsp;In Stock
                  </span>
                </div>
                <h4 className="fw-semibold">{new Intl.NumberFormat('de-DE').format(product.price)} VND</h4>
                <p className="fw-light">
                  Lorem ipsum is placeholder text commonly used in the graphic,
                  print, and publishing industries for previewing layouts and
                  visual mockups.
                </p>
                <dl className="row mb-0">
                  {attributes && (
                    <div>
                      {attributes.map(at => {
                        return (
                          <React.Fragment key={at.attributeId}>
                            <dt className="col-sm-3 fw-semibold">{at.attributeName}</dt>
                            <dd className="col-sm-9">{at.value}</dd>
                          </React.Fragment>)

                      })
                      }
                    </div>
                  )

                  }
                </dl>
                <hr className="text-muted" />
                <dl className="row gy-2 mb-4">
                  <dt className="col-12 fw-semibold">Color</dt>
                  <dd className="col-12">
                    <div className="hstack gap-2">
                      <div className="form-check">
                        {images.map((img, index) => {
                          return (
                            <div key={img.color}>
                              <input
                                onChange={change}
                                value={img.color}
                                type="radio"
                                checked={(!color && index === 0) || img.color === color}
                                className="form-check-input"
                                name={img.color}
                                id={img.color}
                              />
                              <label
                                className="form-check-label fw-medium"
                                htmlFor={img.color}
                              >
                                {img.color}
                              </label>

                            </div>
                          )

                        })}
                      </div>
                    </div>
                  </dd>
                </dl>

                <div className="d-flex">

                  <div onClick={() => handleBuy()}
                    href="#"
                    className="btn btn-primary px-md-4 col col-md-auto me-2"
                  >
                    Buy now
                  </div>

                  <button className="btn btn-outline-primary col col-md-auto" onClick={(e) => handleAddToCart(e, product.productId, color, 1)}>
                    <FontAwesomeIcon icon={["fas", "cart-plus"]} />
                    &nbsp;Add to cart
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="container">
        <div className="row g-3">
          <div className="col-lg-8">
            <div className="card border-0 shadow-sm">
              <div
                className="px-3 d-flex border-bottom overflow-auto"
                style={{ height: 70 }}
              >
                <ul className="nav nav-pills my-auto flex-nowrap">
                  <li className="nav-item">
                    <a href="#" className="nav-link active" aria-current="true">
                      Description
                    </a>
                  </li>
                </ul>
              </div>
              <div className="card-body">
                <p>
                        {product.description}
                </p>
              </div>
              <div className="card-footer py-3">
                <small>
                  <FontAwesomeIcon
                    icon={["fas", "truck"]}
                    className="text-success me-2"
                  />
                  Delivery within 1-2 weeks
                </small>
              </div>
            </div>
          </div>
          <div className="col-lg-4">
            <div className="card border-0 shadow-sm">
              <div className="px-3 d-flex border-bottom" style={{ height: 70 }}>
                <h5 className="my-auto fw-semibold">Related products</h5>
              </div>
              <div className="card-body">
                {products && products.map(product => <ProductSimpleHorizontal
                  key={product.productId}
                  product={product} categoryId={categoryId} />)}
                {/* <ProductSimpleHorizontal id={1} /> */}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="container">
        <h1 className="mt-3">Reviews</h1>
        <div className="row">
          {user.id && <Rating productId={product.productId} ratings={ratings} setRatings={setRatings} />}
        </div>
        <div className="row">
          {
            ratings && ratings.map((rating, index) => (<UserRating key={index} rating={rating} />))
          }
        </div>
      </div>
      <br />
      <br />
      <br />
    </div>
  );
}

export default ProductDetail;
