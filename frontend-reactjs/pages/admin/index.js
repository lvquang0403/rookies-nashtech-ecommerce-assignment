import ProductTable from "../../components/admin/ProductTable";
import Table from "../../components/admin/ProductTable"
import NavbarAdmin from "../../components/admin/NavbarAdmin";
export default function AdminHome() {
    return (
        <div >
            <div className="container py-3">
                <NavbarAdmin />
                <div className="row h-100">
                    <div className="col-md-12 h-100">
                        <div className="card border-0 shadow-sm h-100">
                            <div className="card-body mt-5">
                                <h3 className="text-center">Hello Admin</h3>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}