import React from 'react'
import Link from "next/link"

const NavbarAdmin = () => {
    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light">
                <div className="container-fluid">
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav">
                            <li className="nav-item h4">
                                <Link href="/admin/products">
                                <a className="nav-link">Products</a>
                                </Link>
                            </li>
                            <li className="nav-item h4">
                            <Link href="/admin/users">
                                <a className="nav-link">Users</a>
                                </Link>
                            </li>
                            <li className="nav-item h4">
                            <Link href="/admin/categories">
                                <a className="nav-link">Categories</a>
                                </Link>
                            </li>
                            <li className="nav-item h4">
                            <Link href="/admin/orders">
                                <a className="nav-link">Orders</a>
                                </Link>
                            </li>
                            <li className="nav-item h4">
                            <Link href="/admin/attributes">
                                <a className="nav-link">Attributes</a>
                                </Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    )
}

export default NavbarAdmin
