import { useState, useEffect } from "react";
import { useUserContext } from "../../context/user-context";
import userService from "../api/userService";
import { USER_TYPE } from "../../constant/user-type";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link"
import productService from "../api/productService";

export default function Users() {
    const [products, setProducts] = useState([])
    const [user, setUser] = useUserContext()
    const [page, setPage] = useState(1)
    const [pageResponse, setPageResponse] = useState({ pageSize: 0, pageNumber: 0, totalPage: 0 })

    const handleRemove = (id) => {
        console.log(id)

    }

    useEffect(() => {
        productService.getProducts(user.token)
            .then(res => {
                setProducts(res.data.products)
                setPageResponse(res.data.pageResponse)
            })
    }, [page])

    return (
        <div className="container my-3">
            <div className="table-responsive">
                {/* <Head>
                    title
                </Head> */}
                <table className="table w-100">
                    <thead>
                        <tr>
                            <th></th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Description</th>
                            <th>Images</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    {
                        products.map((product, index) => {

                            return (
                                <tr key={index} style={{ cursor: "pointer" }}>
                                    <th></th>
                                    <th>{product.productId}</th>
                                    <th>{product.productName}</th>
                                    <th>{new Intl.NumberFormat('de-DE').format(product.price)} VND</th>
                                    <th>{product.description}</th>
                                    <th>{
                                        product.images.map((img,index)=>{
                                            return(
                                                <img src={img.url} key={index} alt={img.color}
                                                    style={{
                                                        width :'60px', height: "60px",
                                                        overflow: "hidden", objectFit: "cover"
                                                    }}
                                                />
                                            )

                                        })
                                        
                                        }</th>
                                    <th>
                                        <Link href={{pathname: `product`,query:{id: product.productId}}}>
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
        </div>
    )
}
