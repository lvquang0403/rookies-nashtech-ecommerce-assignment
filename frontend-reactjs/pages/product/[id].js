import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ProductRating from "../../components/product-rating";
import { useRouter } from 'next/router'
import ProductSimpleHorizontal from "../../components/product/product-simple-horizontal";
import { useState, useEffect } from "react";
import productService from "../../services/product.service";
import React from "react";

function ProductDetail() {
  const imagess = [1, 2, 3]
  const [images, setImages] = useState([])
  const [color, setColor] = useState(0)
  const router = useRouter()
  const [product, setProduct] = useState({})
  const [products, setProducts] = useState([])
  const [attributes, setAttributes] = useState()
  const {
    query: { id, categoryId },

  } = router


  useEffect(() => {
    const fetchData = async () => {
 
    if (categoryId) {
      await productService.getProductByCategoryId(categoryId, 0, 5)
        .then(res => setProducts(res.data.products))
    }
  }
  fetchData();
  }, [categoryId])

  useEffect(() => { 
    const fetchData = async () => {
      if (id) {
        await productService.getDetaiProduct(id)
          .then(res => {
            setProduct(res.data)
            setImages(res.data.images)
            setAttributes(res.data.attributes)
          })          
      }
    }
    fetchData();
  }, [id]);
  console.log(products)
  return (
    <div className="vstack">
      <div className="bg-secondary">
        <div className="container">
          <div className="row py-4 px-2">
            <nav aria-label="breadcrumb col-12">
              <ol className="breadcrumb mb-1">
                <li className="breadcrumb-item">
                  <a href="#">All Categories</a>
                </li>
                <li className="breadcrumb-item">
                  <a href="#">Electronics</a>
                </li>
                <li className="breadcrumb-item active" aria-current="page">
                  {product.productName}
                </li>
              </ol>
            </nav>
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
                      src={images[color] && images[color].url}
                      width={300}
                      height={300}
                      alt="Product image."
                    />
                  </div>
                </div>
              </div>
              <div className="row mt-3 d-none d-lg-block">
                <div className="col-12 d-flex justify-content-center">
                  { }
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
                  <ProductRating />
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
                      <div className="form-check" onChange={e => setColor(e.target.value)}>
                        {images.map((img, index) => {
                          return (
                            <div key={index}>
                              <input
                                value={index}
                                type="radio"
                                className="form-check-input"
                                name="color1"
                                id="c1"
                              />
                              <label
                                className="form-check-label fw-medium"
                                htmlFor="c1"
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
                  <a
                    href="#"
                    className="btn btn-primary px-md-4 col col-md-auto me-2"
                  >
                    Buy now
                  </a>
                  <button className="btn btn-outline-primary col col-md-auto">
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
                  <li className="nav-item">
                    <a href="#" className="nav-link">
                      Specifications
                    </a>
                  </li>
                </ul>
              </div>
              <div className="card-body">
                <p>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                  do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                  Duis ultricies lacus sed turpis tincidunt. Urna cursus eget
                  nunc scelerisque. Sit amet massa vitae tortor condimentum.
                  Amet est placerat in egestas erat. Vel quam elementum pulvinar
                  etiam non quam lacus suspendisse faucibus. Duis at consectetur
                  lorem donec massa sapien faucibus. Leo integer malesuada nunc
                  vel risus commodo viverra maecenas. Pellentesque eu tincidunt
                  tortor aliquam nulla facilisi. Gravida in fermentum et
                  sollicitudin ac. Amet purus gravida quis blandit turpis cursus
                  in hac habitasse. Augue mauris augue neque gravida in
                  fermentum et sollicitudin. Faucibus in ornare quam viverra.
                  Nisl rhoncus mattis rhoncus urna neque viverra justo. Cras
                  semper auctor neque vitae. Nulla facilisi morbi tempus
                  iaculis. Quam vulputate dignissim suspendisse in. Vestibulum
                  rhoncus est pellentesque elit ullamcorper. Suspendisse
                  ultrices gravida dictum fusce ut. Lacus vel facilisis volutpat
                  est velit egestas.
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
                { products && products.map(product => <ProductSimpleHorizontal 
                                          key={product.productId}
                                          price = {product.price}
                                          src = {product.images[0].url}
                                          productName = {product.productName}
                                          id={product.productId}/>)}
                {/* <ProductSimpleHorizontal id={1} /> */}
              </div>
            </div>
          </div>
        </div>
      </div>
      <br />
      <br />
      <br />
    </div>
  );
}

export default ProductDetail;
