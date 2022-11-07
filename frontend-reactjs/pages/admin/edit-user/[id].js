import React, { useState, useEffect } from 'react'
import Link from "next/link"
import { useUserContext } from '../../../context/user-context'
import { useRouter } from 'next/router'
import userService from '../../api/userService'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const EditUser = () => {
    const [user, setUser] = useUserContext()
    const router = useRouter()
    const [newPass, setNewpass] = useState('')
    const {
        query: { id },

    } = router
    const [editUser, setEditUser] = useState({})
    const handleUpdate = (e) => {
        e.preventDefault()
        userService.updateCustomer(id, editUser, newPass, user.token)
        .then(res => {
            router.push("/admin/users")
        })

    }
    useEffect(() => {
        if (id) {
            userService.getByCustomerId(user.token, id)
                .then(res => setEditUser(res.data))
        }
    }, [id])
    return (
        <div className="container py-3">
        <div className="row my-4">
          <div className="col-md-6 offset-md-3 col-lg-4 offset-lg-4">
            <div className="card border-0 shadow-sm">
              <div className="card-body px-4">
              <button className="btn btn-dark fw-bold mt-2 mb-4" onClick={() => router.back()}>Back</button>
                <h4 className="card-title fw-bold mt-2 mb-4">Edit User</h4>
                <form className="row g-2">
                  <div className="col-md-12">
                    <label className="form-label">First Name</label>
                    <input
                      value={editUser.firstName} onChange={(e) => setEditUser({...editUser,firstName: e.target.value})}
                      className="form-control"
                    //  {...register("userName")}
                      placeholder="Username"
                    />
                    {/* <span className="error text-danger">{errors.userName?.message}</span> */}
                  </div>
                  <div className="col-md-12">
                    <label className="form-label">Last Name</label>
                    <input
                      // value={username} onChange={(e) => setUsername(e.target.value)}
                      value={editUser.lastName} onChange={(e) => setEditUser({...editUser,lastName: e.target.value})}
                      className="form-control"
                    //  {...register("userName")}
                      placeholder="lastName"
                    />
                    {/* <span className="error text-danger">{errors.userName?.message}</span> */}
                  </div>
                  <div className="col-md-12">
                    <label className="form-label">Email</label>
                    <input
                      // value={username} onChange={(e) => setUsername(e.target.value)}
                      value={editUser.email} onChange={(e) => setEditUser({...editUser,email: e.target.value})}
                      className="form-control"
                    //  {...register("userName")}
                      placeholder="lastName"
                    />
                    {/* <span className="error text-danger">{errors.userName?.message}</span> */}
                  </div>
                  <div className="col-md-12">
                    <label className="form-label">Address</label>
                    <input
                        value={editUser.address} onChange={(e) => setEditUser({...editUser,address: e.target.value})}
                      // value={username} onChange={(e) => setUsername(e.target.value)}
                      className="form-control"
                    //  {...register("userName")}
                      placeholder="Address"
                    />
                    {/* <span className="error text-danger">{errors.userName?.message}</span> */}
                  </div>
                  <div className="col-md-12">
                    <label className="form-label">Phone</label>
                    <input
                    value={editUser.phone} onChange={(e) => setEditUser({...editUser,phone: e.target.value})}
                      // value={username} onChange={(e) => setUsername(e.target.value)}
                      className="form-control"
                    //  {...register("userName")}
                      placeholder="Phone"
                    />
                    {/* <span className="error text-danger">{errors.userName?.message}</span> */}
                  </div>
                  <div className="col-md-12">
                    <label className="form-label"> New Password</label>
                    <input
                    value={newPass} onChange={(e) => setNewpass(e.target.value)}
                    type="password" 
                    className="form-control" 
                    placeholder="Leave blank if not changed" />
                  </div>
                  <div className="col-md-12">
                    {/* <Link href="/auth/forgot-password">
                      <a className="text-decoration-none">Forgot password?</a>
                    </Link> */}
                  </div>
                  <div className="col-md-12 mt-4">
                    <input
                      type="submit"
                      className="btn btn-primary w-100"
                      onClick={(e) => {
                        handleUpdate(e)
                      }}
                      value={"Update"}
                    />
                    {/* Login */}
  
                  </div>
                  <div className="col-md-12">
                  </div>
  
                  <div className="col-md-12">
                  </div>
                </form>
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

export default EditUser
