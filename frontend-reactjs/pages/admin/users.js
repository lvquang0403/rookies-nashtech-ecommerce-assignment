import { useState, useEffect } from "react";
import { useUserContext } from "../../context/user-context";
import userService from "../api/userService";
import { USER_TYPE } from "../../constant/user-type";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link"

export default function Users() {
    const [users, setUsers] = useState([])
    const [user, setUser] = useUserContext()
    const [page, setPage] = useState(1)
    const [pageResponse, setPageResponse] = useState({ pageSize: 0, pageNumber: 0, totalPage: 0 })


    useEffect(() => {
        if (user.id) {
            userService.getCustomers(user.token, 30, page - 1)
                .then(res => setUsers(res.data.customers))
                .catch(res => console.log(res))
        }
    }, [user, page])

    console.log(users)
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
                                                    <a data-toggle="modal"><FontAwesomeIcon icon={["fas", "trash"]} className="text-danger ms-2 " /></a> :
                                                    <FontAwesomeIcon icon={["fas", "trash"]} className="text-danger ms-2 " ></FontAwesomeIcon>
                                            }

                                        </th>
                                    </tr>
                                )
                            })
                        }
                    </tbody>
                </table>
            </div>
        </div>
    )
}
