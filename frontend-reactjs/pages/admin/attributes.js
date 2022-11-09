import { useState, useEffect } from "react";
import { useUserContext } from "../../context/user-context";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Link from "next/link"
import attributeService from "../api/attributeService";
import AttributeForm from "../../components/admin/AttributeForm";
import NavbarAdmin from "../../components/admin/NavbarAdmin";
import { set } from "react-hook-form";

const Attributes = () => {
    const [attributes, setAttributes] = useState([])
    const [user, setUser] = useUserContext()
    const [status, setStatus] = useState({ updata: false, show: false })
    const [attribute, setAttribute] = useState({})

    const handleUpdate = (id) => {
        const at = attributes.find(e => e.attributeId === id);
        setAttribute(at)
        setStatus({ update: true, show: true })
    }
    const handleRemove = (attributeId) => {
        if (window.confirm("Do you really want to remove this Attribute?")) {
            attributeService.deleteAttribute(user.token, attributeId)
                .then(res => {
                    const newAttributes = attributes.filter(c => c.attributeId !== attributeId)
                    setAttributes(newAttributes)
                })
                .catch(res => alert(res.response.data.message))

        }
    }
    useEffect(() => {
        if (user.id) {
            attributeService.getAttributes(user.token)
                .then(res => setAttributes(res.data))
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
                            attributes.map((at, index) => {

                                return (
                                    <tr key={index} style={{ cursor: "pointer" }}>
                                        <th></th>
                                        <th>{at.attributeId}</th>
                                        <th>{at.attributeName}</th>
                                        <th>
                                            <div>

                                                <div onClick={() => handleUpdate(at.attributeId)}><FontAwesomeIcon icon={["fas", "edit"]} className="text-info" ></FontAwesomeIcon></div>
                                                <div className="" onClick={() => handleRemove(at.attributeId)}>
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
                    status.show && <div><AttributeForm setStatus={setStatus}  setAttribute={setAttribute} attributes={attributes} setAttributes={setAttributes} attribute={status.update ? attribute : {}} update={status.update}/></div>
                }</div>
            </div>
        </div>
    )
}

export default Attributes
