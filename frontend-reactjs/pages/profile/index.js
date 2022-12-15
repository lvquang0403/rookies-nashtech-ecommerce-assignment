import React, { useState, useEffect } from 'react'
import { Modal, Button } from "react-bootstrap";
import Link from "next/link"
import * as Yup from 'yup';
import { useForm } from "react-hook-form";
import { yupResolver } from '@hookform/resolvers/yup'
import { useUserContext } from '../../context/user-context'
import { useRouter } from 'next/router'
import userService from '../api/userService'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

const Profile = () => {
    const [show, setShow] = useState(false);
    const [showSucsess, setShowSuccess] = useState(false);
    const [user, setUser] = useUserContext()
    const router = useRouter()
    const userSchema = Yup.object().shape({
        firstName: Yup.string().required('First Name is required'),
        lastName: Yup.string().required('Last Name is required'),
        address: Yup.string().required('Address is required'),
        phone: Yup.string().required('Phone is required'),
        confirmPass: Yup.string()
            .oneOf([Yup.ref('password'), null],  'Confirm Passwords not match')
    })
    const { register, setValue, handleSubmit, watch, getValues, formState: { errors } } = useForm({
        resolver: yupResolver(userSchema)
    });

    useEffect(() => {
        if (user.id) {
            userService.getByCustomerId(user.token, user.id)
                .then(res => {
                    setValue("firstName", res.data.firstName)
                    setValue("lastName", res.data.lastName)
                    setValue("email", res.data.email)
                    setValue("address", res.data.address)
                    setValue("phone", res.data.phone)
                })
        }
    }, [user.id])

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    const handleUpdate = (data) => {
            setShow(true)
    }

    const update = () => {
        const data = getValues();
        userService.updateCustomer(user.id,data ,data.password , user.token)
            .then(res => {
                const user2 = {
                    id: user.id,
                    type: user.type,
                    name: data.lastName,
                    token: user.token,
                    phone: data.phone,
                    address: data.address,
                    numberCartItems: user.numberCartItems
                  }
                  
                setShow(false)
                alert("Update Successfully")
                console.log(user2)
                sessionStorage.setItem("user", JSON.stringify(user2));
                setUser(user2);
            })

    }
    return (
        <div className="container py-3">
            <>
                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Success</Modal.Title>
                    </Modal.Header>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={() => setShowSuccess(false)}>
                            Close
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>
            <>
                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Confirm</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>Do you want update prifile ?</Modal.Body>
                    <Modal.Footer>
                        <Button variant="secondary" onClick={() => setShow(false)}>
                            Close
                        </Button>
                        <Button variant="primary" onClick={() => update()}>
                            Confirm
                        </Button>
                    </Modal.Footer>
                </Modal>
            </>

            <button type="button" className="btn btn-dark ms-4 px-4" onClick={() => router.push("/profile/my-orders")}>My Orders</button>
            <div className="row my-4">
                <div className="col-md-6 offset-md-3 col-lg-4 offset-lg-4">
                    <div className="card border-0 shadow-sm">
                        <div className="card-body px-4">
                            <h4 className="card-title fw-bold mt-2 mb-4">Update Profile</h4>
                            <form className="row g-2" onSubmit={handleSubmit(handleUpdate)}>
                                <div className="col-md-12">
                                    <label className="form-label">First Name</label>
                                    <input {...register("firstName")}
                                        className="form-control"
                                        //  {...register("userName")}
                                        placeholder="First Name"
                                    />
                                    <span className="error text-danger">{errors.firstName?.message}</span>
                                    {/* <span className="error text-danger">{errors.userName?.message}</span> */}
                                </div>
                                <div className="col-md-12">
                                    <label className="form-label">Last Name</label>
                                    <input
                                        {...register("lastName")}
                                        // value={username} onChange={(e) => setUsername(e.target.value)}
                                        className="form-control"
                                        //  {...register("userName")}
                                        placeholder="lastName"
                                    />
                                    <span className="error text-danger">{errors.lastName?.message}</span>
                                    {/* <span className="error text-danger">{errors.userName?.message}</span> */}
                                </div>
                                <div className="col-md-12">
                                    <label className="form-label">Address</label>
                                    <input
                                        {...register("address")}
                                        // value={username} onChange={(e) => setUsername(e.target.value)}
                                        className="form-control"
                                        //  {...register("userName")}
                                        placeholder="Address"
                                    />
                                    <span className="error text-danger">{errors.address?.message}</span>
                                    {/* <span className="error text-danger">{errors.userName?.message}</span> */}
                                </div>
                                <div className="col-md-12">
                                    <label className="form-label">Phone</label>
                                    <input
                                        {...register("phone")}
                                        // value={username} onChange={(e) => setUsername(e.target.value)}
                                        className="form-control"
                                        //  {...register("userName")}
                                        placeholder="Phone"
                                    />
                                    <span className="error text-danger">{errors.phone?.message}</span>
                                    {/* <span className="error text-danger">{errors.userName?.message}</span> */}
                                </div>
                                <div className="col-md-12">
                                    <label className="form-label"> New Password</label>
                                    <input
                                        {...register("password")}
                                        type="password"
                                        className="form-control"
                                        placeholder="Leave blank if not changed" />
                                </div>
                                <div className="col-md-12">
                                    <label className="form-label">Confirm Password</label>
                                    <input
                                        {...register("confirmPass")}
                                        type="password"
                                        className="form-control"
                                        placeholder="Leave blank if not changed" />
                                    <span className="error text-danger">{errors.confirmPass?.message}</span>
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
                                            
                                            // setShow(true)
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

export default Profile
