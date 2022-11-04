import { useRouter } from "next/router";
import Link from "next/link";
import { useEffect, useState } from "react";
import productService from "../../services/product.service";
import ProductGridCard from "../../components/product/product-grid-card";
import { PaginationControl } from 'react-bootstrap-pagination-control';
import React from "react";

export default function Products() {
  const { query } = useRouter();
  const [products, setProducts] = useState()
  const [page, setPage] = useState(1)
  const [pageResponse, setPageResponse] = useState({ pageSize: 12, pageNumber: 0, totalPage: 0 })

  useEffect(() => {

    productService.searchProduct(query.productName, page - 1, pageResponse.pageSize)
      .then(res => {
        setProducts(res.data.products)
        setPageResponse(res.data.pageResponse)
      })
      .catch(res => console.log(res))

  }, [query, page])

  return (
    <div className="container py-4">
        <div className="row g-3">
          <div className="col-lg-12">
            <div className="hstack justify-content-between mb-3">
              <span className="text-dark">33 Items found</span>
              <div className="btn-group" role="group">
              </div>
            </div>
            <div className="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-3">
              { products && products.map(product => {
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
  );
}