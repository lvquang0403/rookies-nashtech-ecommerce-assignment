import { useState, useEffect } from "react";
import { useUserContext } from "../../context/user-context";
import userService from "../api/userService";
import { USER_TYPE } from "../../constant/user-type";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link"
import NavbarAdmin from "../../components/admin/NavbarAdmin";
import { PaginationControl } from 'react-bootstrap-pagination-control';
export default function Users() {
    const [users, setUsers] = useState([])
    const [user, setUser] = useUserContext()
    const [page, setPage] = useState(1)
    const [pageResponse, setPageResponse] = useState({ pageSize: 0, pageNumber: 0, totalPage: 0 })

    const handleRemove = (customerId) => {
        if (window.confirm("Do you really want to remove this customer?")) {
            userService.deleteCustomer(user.token, customerId)
                .then(res => {
                    const newUsers = users.filter(u => u.customerId !== customerId)
                    setUsers(newUsers)
                })
                .catch(res => alert(res.response.data.message))

        }
    }

    useEffect(() => {
        if (user.id) {
            userService.getCustomers(user.token, 30, page - 1)
                .then(res => {
                    setPageResponse(res.data.pageResponse)
                    setUsers(res.data.customers)
                })
                .catch(res => console.log(res))
        }
    }, [user, page])

    console.log(users)
    return (
        <div className="container my-3">
            <NavbarAdmin />
            <div className="table-responsive">
                {/* <Head>
                    title
                </Head> */}
                <table className="table w-100">
                    <thead>
                        <tr>
                            <th></th>
                            <th data-toggle="modal">ID</th>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Phone</th>
                            <th>Email</th>
                            <th>Admin</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        {
                            users.map((user, index) => {

                                return (
                                    <tr key={index} style={{ cursor: "pointer" }}>
                                        <th></th>
                                        <th>{user.customerId}</th>
                                        <th>{user.firstName + " " + user.lastName}</th>
                                        <th>{user.address}</th>
                                        <th>{user.phone}</th>
                                        <th>{user.email}</th>
                                        <th>{
                                            user.roles.includes(USER_TYPE.ADMIN) ?
                                                <FontAwesomeIcon icon={["fas", "check"]} className="text-success" />
                                                //    <i className="fas fa-check text-success">Root</i>
                                                : <i className="text-danger">X</i>
                                        }
                                        </th>
                                        <th>
                                            <Link href={
                                                !user.roles.includes(USER_TYPE.ADMIN) ?
                                                    `edit-user/${user.customerId}` : '#'
                                            }>
                                                <a><FontAwesomeIcon icon={["fas", "edit"]} className="text-info me-3 " >Edit</FontAwesomeIcon></a>
                                            </Link>
                                            {

                                                user.roles.includes(USER_TYPE.ADMIN) ?
                                                    <div></div> :
                                                    <div className="d-inline" onClick={() => handleRemove(user.customerId)}>
                                                        <FontAwesomeIcon icon={["fas", "trash"]} className="text-danger ms-2 " ></FontAwesomeIcon>
                                                    </div>
                                            }

                                        </th>
                                    </tr>
                                )
                            })
                        }
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
