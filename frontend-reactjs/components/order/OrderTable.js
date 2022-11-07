import React from 'react'
import Link from "next/link";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

const OrderTable = ({ orders }) => {
    return (
        <table className="table-bordered table-hover w-100 text-uppercase"
            style={{ minWidth: '600px', cursor: 'pointer' }}>
            <thead className="bg-light font-weight-bold">
                <tr>
                    <td className="p-2">id</td>
                    <td className="p-2">Total price</td>
                    <td className="p-2">Shipping Address</td>
                    <td className="p-2">Delivered</td>
                </tr>
            </thead>

            <tbody>
                {
                    orders.map(order => (
                        <tr key={order.orderId}>
                            <td className="p-2">
                                <Link href={`/order/${order._id}`}>
                                    <a>{order.orderId}</a>
                                </Link>

                            </td>
                            <td className="p-2">
                                {new Intl.NumberFormat('de-DE').format(order.totalPrice)} VND
                            </td>
                            <td className="p-2">{order.shipAddress}</td>
                            <td className="p-2">
                                {
                                    order.status == 1
                                        ? <FontAwesomeIcon
                                            icon={["fas", "check"]}
                                            className="mt-1 text-success"
                                        />
                                        : <FontAwesomeIcon
                                            icon={["fas", "times"]}
                                            className="mt-1 text-danger"
                                        />
                                }
                            </td>
                        </tr>
                    ))
                }
            </tbody>

        </table>
    )
}

export default OrderTable
