import { useState, useContext, useEffect } from 'react'
import Link from 'next/link'
import { useUserContext } from '../../context/user-context'
import orderService from '../api/orderService'
import OrderTable from '../../components/order/OrderTable'
import { useRouter } from "next/router";

const Profile = () => {
    const router = useRouter()
    const [user, setUser] = useUserContext()
    const [orders, setOrders] = useState([])
    const initialSate = {
        avatar: '',
        name: '',
        password: '',
        cf_password: ''
    }
    const [data, setData] = useState(initialSate)
    const { avatar, name, password, cf_password } = data

    useEffect(() => {
        if (user.id)
            orderService.getOrderCustomers(user.token)
                .then(res => setOrders(res.data.orders))

    }, [user])
    // const orders = [{
    //     _id: 1,
    //     createdAt: '1/1/2022',
    //     delivered: true,
    //     total: 2000,
    // }]

    const auth = {

        user: "user"
    }

    const notify = {
        loading: "loading"
    }

    useEffect(() => {
        if (auth.user) setData({ ...data, name: auth.user.name })
    }, [auth.user])

    const handleChange = (e) => {
        const { name, value } = e.target
        setData({ ...data, [name]: value })
        dispatch({ type: 'NOTIFY', payload: {} })
    }

    const handleUpdateProfile = e => {
        e.preventDefault()
        if (password) {
            const errMsg = valid(name, auth.user.email, password, cf_password)
            if (errMsg) return dispatch({ type: 'NOTIFY', payload: { error: errMsg } })
            updatePassword()
        }

        if (name !== auth.user.name || avatar) updateInfor()
    }

    const updatePassword = () => {
        dispatch({ type: 'NOTIFY', payload: { loading: true } })
        patchData('user/resetPassword', { password }, auth.token)
            .then(res => {
                if (res.err) return dispatch({ type: 'NOTIFY', payload: { error: res.err } })
                return dispatch({ type: 'NOTIFY', payload: { success: res.msg } })
            })
    }

    const changeAvatar = (e) => {
        const file = e.target.files[0]
        if (!file)
            return dispatch({ type: 'NOTIFY', payload: { error: 'File does not exist.' } })

        if (file.size > 1024 * 1024) //1mb
            return dispatch({ type: 'NOTIFY', payload: { error: 'The largest image size is 1mb.' } })

        if (file.type !== "image/jpeg" && file.type !== "image/png") //1mb
            return dispatch({ type: 'NOTIFY', payload: { error: 'Image format is incorrect.' } })

        setData({ ...data, avatar: file })
    }

    const updateInfor = async () => {
        let media;
        dispatch({ type: 'NOTIFY', payload: { loading: true } })

        if (avatar) media = await imageUpload([avatar])

        patchData('user', {
            name, avatar: avatar ? media[0].url : auth.user.avatar
        }, auth.token).then(res => {
            if (res.err) return dispatch({ type: 'NOTIFY', payload: { error: res.err } })

            dispatch({
                type: 'AUTH', payload: {
                    token: auth.token,
                    user: res.user
                }
            })
            return dispatch({ type: 'NOTIFY', payload: { success: res.msg } })
        })
    }


    if (!auth.user) return null;
    return (
        <div className="profile_page">
            <title>Profile</title>

            <section className="row my-3">
                <div className="col-md-4">
                    <h3 className="text-center text-uppercase">
                        Profile
                        {/* {auth.user.role === 'user' ? 'User Profile' : 'Admin Profile'} */}
                    </h3>

                    <div className="form-group">
                        <label htmlFor="name">Last Name</label>
                        <input type="text" name="name" value={name} className="form-control"
                            placeholder="Last Name" onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="name">Firt Name</label>
                        <input type="text" name="name" value={name} className="form-control"
                            placeholder="Firt Name" onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">New Password</label>
                        <input type="password" name="password" value={password} className="form-control"
                            placeholder="Your new password" onChange={handleChange} />
                    </div>

                    <div className="form-group">
                        <label htmlFor="cf_password">Confirm New Password</label>
                        <input type="password" name="cf_password" value={cf_password} className="form-control"
                            placeholder="Confirm new password" onChange={handleChange} />
                    </div>

                    <button className="btn btn-info" disabled={notify.loading}
                        onClick={handleUpdateProfile}>
                        Update
                    </button>
                </div>

                <div className="col-md-8">
                    <h3 className="text-uppercase">Orders</h3>

                    <div className="my-3 table-responsive">
                        <OrderTable orders={orders} />
                    </div>
                </div>
            </section>
        </div>
    )
}

export default Profile