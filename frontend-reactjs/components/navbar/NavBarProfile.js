import React from 'react'
import Link from 'next/link'

const NavBarProfile = () => {
    return (
        <div>
            <div>
                <nav className="navbar navbar-expand-lg navbar-light">
                    <div className="container-fluid">
                        <div className="collapse navbar-collapse" id="navbarNav">
                            <ul className="navbar-nav">
                                <li className="nav-item h4">
                                    <Link href="/admin">
                                        <a className="nav-link">
                                            <button type="button" className="btn btn-info">Info</button>
                                        </a>
                                    </Link>
                                </li>
                                <li className="nav-item h4">
                                    <Link href="/admin/profile/myoders">
                                        <button type="button" className="btn btn-info">Info</button>
                                    </Link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            </div>

        </div>
    )
}

export default NavBarProfile
