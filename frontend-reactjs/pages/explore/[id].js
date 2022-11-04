import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import ProductGridCard from "../../components/product/product-grid-card";
import { useRouter } from 'next/router'
import productService from "../../services/product.service";
import { useState, useEffect } from "react";
import { PaginationControl } from 'react-bootstrap-pagination-control';

function ExploreProducts() {
  const router = useRouter()
  const [page, setPage] = useState(1)
  const [pageResponse, setPageResponse] = useState({ pageSize: 0, pageNumber: 0, totalPage: 0 })
  const [products, setProducts] = useState([])
  const {
    id
  } = router.query
  console.log(router.query)
  console.log(pageResponse)
  console.log(page)
  const getProductByCategoryId = (id) => {
    productService.getProductByCategoryId(id, page - 1, 9)
      .then(res => {
        setProducts(res.data.products);
        setPageResponse(res.data.pageResponse)
      })
  }
  useEffect(() => {
    if (id) {
      getProductByCategoryId(id)
    }
  }, [id, page]);
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
                  Phones & Tablets
                </li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
      <div className="container py-4">
        <div className="row g-3">
          <div className="col-lg-12">
            <div className="hstack justify-content-between mb-3">
              <span className="text-dark">33 Items found</span>
              <div className="btn-group" role="group">
              </div>
            </div>
            <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-3">
              {products.map(product => {
                return (
                  <div key={product.productId} className="col">
                    <ProductGridCard id={product.productId} title={product.productName} price={product.price} src={product.images[0].url} />
                  </div>)
              })}
            </div>

            <nav className="float-end mt-3">
              <PaginationControl
                page={page}
                total={pageResponse.pageSize * pageResponse.totalPage}
                limit={pageResponse.pageSize}
                changePage={(page) => {
                  setPage(page);
                }}
                ellipsis={1}
              />
            </nav>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ExploreProducts;
