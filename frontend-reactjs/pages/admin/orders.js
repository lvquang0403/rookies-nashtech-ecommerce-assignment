import React, { useState } from 'react'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from 'next/link'
import { useUserContext } from '../../context/user-context';
import { useEffect } from 'react';
import { useRouter } from "next/router";
import orderService from '../api/orderService';
import NavbarAdmin from '../../components/admin/NavbarAdmin';
import { Update } from '@material-ui/icons';
import { set } from 'react-hook-form';
import { PaginationControl } from 'react-bootstrap-pagination-control';
const OrdersAdmin = () => {
    const router = useRouter()
    const [user, setUser] = useUserContext()
    const [page, setPage] = useState(1)
    const [pageResponse, setPageResponse] = useState({ pageSize: 0, pageNumber: 0, totalPage: 0 })
    const [orders, setOrders] = useState([])
    const [orderItems, setOrderItems] = useState([])

    useEffect(()=>{
        if(orderItems.length > 0){
            window.scrollTo({ top: window.innerHeight, behavior: 'smooth' });
        }

    },[orderItems])
    const updateStatusOrder = (e, order) => {
        var status = null;
        if (e.target.value === '0') {
            status = 0;
        }
        else {
            status = 1;
        }
        orderService.updateStatusOrder(user.token, order.orderId, status)
            .then(res => {
                orderService.getOrders(user.token, page - 1, 30)
                    .then(res => setOrders(res.data.orders))
                // const order = orders.filter(o => o.orderId === order.orderId).
                // setOrders([...newOrders, {...order, status: status}])

            })
            .catch(res => console.log(res))
    }


    const showDetail = (id) => {
        orderService.getOrderItemsById(user.token, id)
            .then(res => setOrderItems(res.data))
            .catch(res => console.log(res))

    }

    useEffect(() => {
        if (user.id) {
            orderService.getOrders(user.token, page - 1, 30)
                .then(res => {
                    setPageResponse(res.data.pageResponse)
                    setOrders(res.data.orders)
                }
                )

        }
    }, [user, page])

    return (
        <div className="profile_page">
            <div className="container py-3 ">
                <NavbarAdmin />
                <div className="row my-4">
                    <div className="col-md-12">
                        <div className="card border-0 shadow-sm">
                            <div className="card-body px-4">
                                <h4 className="card-title fw-bold mt-2 mb-4">All Orders</h4>
                                <table className="table w-100">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>ID</th>
                                            <th>Total Price</th>
                                            <th>Ship Address</th>
                                            <th>Order phone</th>
                                            <th>Create Day</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    {
                                        orders.map((order, index) => {

                                            return (
                                                <tr key={index} style={{ cursor: "pointer" }}>
                                                    <th></th>
                                                    <th>{order.orderId}</th>
                                                    <th>{new Intl.NumberFormat('de-DE').format(order.totalPrice)} VND</th>
                                                    <th>{order.shipAddress}</th>
                                                    <th>{order.orderPhone}</th>
                                                    <th>{order.createDay}</th>
                                                    <th className="">
                                                        <select name="status" defaultValue={order.status} className={order.status === 0 ? "form-select text-danger" : "form-select text-success"} onChange={(e) => updateStatusOrder(e, order)}>
                                                            <option value={0}>
                                                                Pending
                                                            </option>
                                                            <option value={1}>
                                                                Deliveried
                                                            </option>
                                                        </select></th>

                                                    <th><div onClick={() => showDetail(order.orderId)}><button type="button" className="btn btn-dark">Detail</button></div></th>
                                                </tr>
                                            )
                                        })
                                    }

                                    <tbody>
                                    </tbody>
                                </table>

                            </div>
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
                {
                    orderItems.length > 0 && (
                        <>
                            <button type="button" className="btn btn-dark ms-4 my-3" onClick={() => setOrderItems([])}>Close</button>
                            <br />
                            <div className="col-md-12">
                                <div className="card border-0 shadow-sm">
                                    <div className="card-body px-4">
                                        <h4 className="card-title fw-bold mt-2 mb-4">Detail Order</h4>
                                        <table className="table w-100">
                                            <thead>
                                                <tr>
                                                    <th></th>
                                                    <th>Item ID</th>
                                                    <th>Product Name</th>
                                                    <th>Price</th>
                                                    <th>Color</th>
                                                    <th>Quantity</th>
                                                    <th>Total Price</th>
                                                    <th>Image</th>
                                                </tr>
                                            </thead>
                                            {
                                                orderItems.map((item, index) => {

                                                    return (
                                                        <tr key={index} style={{ cursor: "pointer" }}>
                                                            <th></th>
                                                            <th>{item.id}</th>
                                                            <th>{item.productName}</th>
                                                            <th>{new Intl.NumberFormat('de-DE').format(item.price)} VND</th>
                                                            <th>{item.color}</th>
                                                            <th className="text-center">{item.quantity}</th>
                                                            <th>{new Intl.NumberFormat('de-DE').format(item.totalPrice)} VND</th>
                                                            <th>
                                                                <Link href={{
                                                                    pathname: `/product/${item.productId}?categoryId=${item.categoryId}`,
                                                                    query: { categoryId: item.categoryId },
                                                                }}>

                                                                    {
                                                                        <img src={item.image} alt={`image`}
                                                                            style={{
                                                                                width: '60px', height: "60px",
                                                                                overflow: "hidden", objectFit: "cover"
                                                                            }}
                                                                        />}
                                                                </Link>
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
                            </div>
                        </>
                    )

                }
            </div>

        </div>
    )
}

export default OrdersAdmin
