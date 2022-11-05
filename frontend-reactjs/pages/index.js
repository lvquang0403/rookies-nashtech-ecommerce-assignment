import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Carousel } from "react-responsive-carousel";
import ProductSimpleCard from "../components/product/product-simple-card";
import productService from "../services/product.service";
import categoryService from "../services/category.service";
import { useState, useEffect } from "react";
import Link from "next/link";
import axios from "axios";

export default function Home() {
  const [categorys, setCategorys] = useState([])
  const [products, setProducts] = useState(new Map())
  // const getProductByCategoryId = async () => productService.getProductByCategoryId(categoryId);
  const getProductsByCategory = (id) => {
    productService.getProductByCategoryId(id, 0, 8)
      .then(res => {
        products.set(id, res.data.products)
        const newProducts = new Map(products)
        setProducts(newProducts)
      })
  }
  useEffect(() => {
    categoryService.getCategoris().then(res => {
      setCategorys(res.data)
      res.data.forEach(category => {
        getProductsByCategory(category.categoryId)
      });

    });
  }, []);
  return (
    <div>
      <div className="container py-3">
        <div className="row mb-4">
          <div className="col-12">
            <Carousel
              autoPlay={true}
              infiniteLoop={true}
              showArrows={false}
              showStatus={false}
              showThumbs={false}
              transitionTime={500}
              renderIndicator={(onClickHandler, isSelected, index, label) => {
                if (isSelected) {
                  return (
                    <li className="d-inline-block m-2 text-light">
                      <FontAwesomeIcon icon={["fas", "circle"]} size="xs" />
                    </li>
                  );
                }
                return (
                  <li
                    className="d-inline-block m-2 text-light text-opacity-50"
                    onClick={onClickHandler}
                    key={index}
                    role="button"
                    tabIndex={0}
                  >
                    <FontAwesomeIcon icon={["fas", "circle"]} size="xs" />
                  </li>
                );
              }}
            >
              <div className="ratio ratio-21x9">
                <img
                  src="/images/slider_iphone.jpg"
                  alt="Cover image"
                  className="rounded"
                />
              </div>
              <div className="ratio ratio-21x9">
                <img
                  src="/images/slider_macbook.jpg"
                  alt="Cover image"
                  className="rounded"
                />
              </div>
              <div className="ratio ratio-21x9">
                <img
                  src="/images/slider-iphone2.jpg"
                  alt="Cover image"
                  className="rounded"
                />
              </div>
            </Carousel>
          </div>
        </div>
        <div className="row row-cols-1 row-cols-md-3 g-3 mb-4">
          <div className="col">
            <div className="card h-100 border-0 shadow-sm">
              <figure className="figure card-body mb-0">
                <div
                  className="bg-secondary rounded-circle d-flex mb-2"
                  style={{ width: 50, height: 50 }}
                >
                  <FontAwesomeIcon
                    icon={["fas", "money-bill-alt"]}
                    size="lg"
                    className="text-primary m-auto"
                  />
                </div>
                <h5 className="mb-1 fw-bold">Reasonable Price</h5>
                <figcaption className="figure-caption text-dark">
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                  do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                </figcaption>
              </figure>
            </div>
          </div>
          <div className="col">
            <div className="card h-100 border-0 shadow-sm">
              <figure className="figure card-body mb-0">
                <div
                  className="bg-secondary rounded-circle d-flex mb-2"
                  style={{ width: 50, height: 50 }}
                >
                  <FontAwesomeIcon
                    icon={["fas", "headset"]}
                    size="lg"
                    className="text-primary m-auto"
                  />
                </div>
                <h5 className="mb-1 fw-bold">Customer Support 24/7</h5>
                <figcaption className="figure-caption text-dark">
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                  do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                </figcaption>
              </figure>
            </div>
          </div>
          <div className="col">
            <div className="card h-100 border-0 shadow-sm">
              <figure className="figure card-body mb-0">
                <div
                  className="bg-secondary rounded-circle d-flex mb-2"
                  style={{ width: 50, height: 50 }}
                >
                  <FontAwesomeIcon
                    icon={["fas", "truck"]}
                    size="lg"
                    className="text-primary m-auto"
                  />
                </div>
                <h5 className="mb-1 fw-bold">Fast Delivery</h5>
                <figcaption className="figure-caption text-dark">
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
                  do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                </figcaption>
              </figure>
            </div>
          </div>
        </div>
        {categorys.map(category => {
          return (
            <div key={category.categoryId} className="container">
              <h1 className="my-5 fw-semibold text-center">{category.categoryName}</h1>
              <div className="row row-cols-1 row-cols-sm-2 row-cols-lg-4 g-3 mb-5">
                {products.get(category.categoryId) && products.get(category.categoryId).map(product => {
                  return (
                    <div className="col" key={product.productId}>
                      <ProductSimpleCard product={product} categoryId={category.categoryId} />
                    </div>
                  );
                })}
              </div>
              <div className="d-flex justify-content-center">
                <Link href={{
                  pathname: `/explore/${category.categoryId}`
                }}>
                  <button type="button" className="btn btn-dark px-4 py-2">Xem tất cả</button>
                </Link>
              </div>
            </div>);
        }
        )
        }
      </div>
      {/* <div className="d-flex flex-column align-items-center bg-primary py-5">
        <span className="mb-4 text-light text-opacity-75">
          Subscribe for promotions and wonderful events
        </span>
        <form className="d-flex">
          <div className="me-2">
            <input
              type="email"
              className="form-control"
              placeholder="Your email"
              size="24"
            />
          </div>
          <button className="btn btn-warning">
            <FontAwesomeIcon icon={["fas", "envelope"]} className="me-2" />
            Subscribe
          </button>
        </form>
      </div> */}
    </div >
  );
}

