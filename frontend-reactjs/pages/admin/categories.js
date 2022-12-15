import { useState, useEffect } from "react";
import { useUserContext } from "../../context/user-context";
import userService from "../api/userService";
import { USER_TYPE } from "../../constant/user-type";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link"
import categoryService from "../api/categoryService";
import CategoryForm from "../../components/admin/CategoryForm";
import NavbarAdmin from "../../components/admin/NavbarAdmin";
import { set } from "react-hook-form";
import { Update } from "@material-ui/icons";

const Categories = () => {
    const [categories, setCategories] = useState([])
    const [user, setUser] = useUserContext()
    const [status, setStatus] = useState({ updata: false, show: false })
    const [category, setCategory] = useState({})

    const handleUpdate = (id) => {
        const cate = categories.find(e => e.categoryId === id);
        setCategory(cate)
        setStatus({ update: true, show: true })
    }
    console.log(category)
    console.log(status)
    const handleRemove = (categoryId) => {
        if (window.confirm("Do you really want to remove this Category?")) {
            categoryService.deleteCategory(user.token, categoryId)
                .then(res => {
                    const newCategories = categories.filter(c => c.categoryId !== categoryId)
                    setCategories(newCategories)
                })
                .catch(res => alert(res.response.data.message))

        }
    }
    useEffect(() => {
        if (user.id) {
            console.log("das")
            categoryService.getCategorys(user.token)
                .then(res => setCategories(res.data))
                .catch(res => console.log(res))
        }
    }, [user, status.update])

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
                            <th>ID</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        {
                            categories.map((category, index) => {

                                return (
                                    <tr key={index} style={{ cursor: "pointer" }}>
                                        <th></th>
                                        <th>{category.categoryId}</th>
                                        <th>{category.categoryName}</th>
                                        <th>
                                            <div className="d-flex">

                                                <div onClick={() => handleUpdate(category.categoryId)}><FontAwesomeIcon icon={["fas", "edit"]} className="text-info me-4" >
                                                    
                                                    </FontAwesomeIcon></div>
                                                <div className="" onClick={() => handleRemove(category.categoryId)}>
                                                    <FontAwesomeIcon icon={["fas", "trash"]} className="text-danger" ></FontAwesomeIcon>
                                                </div>
                                            </div>


                                        </th>
                                    </tr>
                                )
                            })
                        }
                    </tbody>
                </table>
                <button className="btn btn-dark" onClick={() => setStatus({ update: false, show: !status.show })}>Create New</button>
                <div>{
                    status.show && <div><CategoryForm setStatus={setStatus}  setCategory={setCategory} categories={categories} setCategories={setCategories} category={status.update ? category : {}} update={status.update}/></div>
                }</div>
            </div>
        </div>
    )
}

export default Categories
