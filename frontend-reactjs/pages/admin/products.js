import { useState, useEffect } from "react";
import { useUserContext } from "../../context/user-context";
import userService from "../api/userService";
import { USER_TYPE } from "../../constant/user-type";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link"
import { useRouter } from "next/router";
import productService from "../api/productService";
import { set } from "react-hook-form";
import NavbarAdmin from "../../components/admin/NavbarAdmin";
import { PaginationControl } from 'react-bootstrap-pagination-control';

export default function Users() {
    const router = useRouter()
    const [products, setProducts] = useState([])
    const [user, setUser] = useUserContext()
    const [page, setPage] = useState(1)
    const [pageResponse, setPageResponse] = useState({ pageSize: 0, pageNumber: 0, totalPage: 0 })

    const handleRemove = (id) => {
        if (window.confirm(`Do you really want to delete this product with id ${id} ?`)) {
            productService.deleteProduct(user.token, id)
                .then(res => {
                    const newProducts = products.filter(p => p.productId !== id)
                    setProducts(newProducts)
                })
                .catch(res => {
                    alert(res.response.data.message)
                })
        }

    }



    useEffect(() => {
        productService.getProducts(user.token, 10, page - 1)
            .then(res => {
                setProducts(res.data.products)
                setPageResponse(res.data.pageResponse)
            })
    }, [page])

    return (
        <div className="container my-3">
            <NavbarAdmin />
            <div className="table-responsive">
                {/* <Head>
                    title
                </Head> */}
                <button className="btn btn-dark py-3 my-2" onClick={() => router.push("/admin/product")}>Create New Product</button>
                <table className="table w-100">
                    <thead>
                        <tr>
                            <th></th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Images</th>
                            <th>Create day</th>
                            <th>Update day</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    {
                        products.map((product, index) => {

                            return (
                                <tr key={index} style={{ cursor: "pointer" }}>
                                    <th></th>
                                    <th>{product.productId}</th>
                                    <Link href={{
                                        pathname: `/product/${product.productId}?categoryId=${product.categoryId}`,
                                        query: { categoryId: product.categoryId },
                                    }}>
                                        <th>{product.productName}</th>
                                    </Link>
                                    <th>{new Intl.NumberFormat('de-DE').format(product.price)} VND</th>
                                    <th>{
                                        product.images.map((img, index) => {
                                            return (
                                                <img src={img.url} key={index} alt={img.color}
                                                    style={{
                                                        width: '60px', height: "60px",
                                                        overflow: "hidden", objectFit: "cover"
                                                    }}
                                                />
                                            )

                                        })

                                    }</th>
                                    <th>{product.createDay}</th>
                                    <th>{product.updateDay}</th>
                                    <th>
                                        <Link href={{ pathname: `product`, query: { id: product.productId } }}>
                                            <a><FontAwesomeIcon icon={["fas", "edit"]} className="text-info me-3 " >Edit</FontAwesomeIcon></a>
                                        </Link>
                                        <div className="d-inline" onClick={() => handleRemove(product.productId)}>
                                            <FontAwesomeIcon icon={["fas", "trash"]} className="text-danger ms-2 " ></FontAwesomeIcon>
                                        </div>

                                    </th>
                                </tr>
                            )
                        })
                    }

                    <tbody>
                    </tbody>
                </table>
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
    )
}
